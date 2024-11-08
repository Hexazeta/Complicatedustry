package complicatedustry.scripts.extensions;

import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.content.Fx;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.logic.LAccess;
import mindustry.ui.Bar;
import mindustry.world.blocks.heat.HeatBlock;
import mindustry.world.blocks.power.NuclearReactor;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawHeatOutput;
import mindustry.world.draw.DrawMulti;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;


import static mindustry.Vars.*;

public class HeaterReactor extends NuclearReactor {
    //todo fix all of this
    public float heatOutput = 10f;
    public float warmupRate = 0.15f;

    public float flashThreshold = 0.01f, flashAlpha = 0.4f, flashSpeed = 7f;
    public Color flashColor1 = Color.red, flashColor2 = Color.valueOf("89e8b6");

    public HeaterReactor(String name) {
        super(name);

        drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
        rotateDraw = false;
        rotate = true;
        canOverdrive = false;
        drawArrow = true;

    }

    @Override
    public void setStats(){
        super.setStats();

        if(hasItems){
            stats.add(Stat.productionTime, itemDuration / 60f, StatUnit.seconds);
        }

        stats.add(Stat.output, heatOutput, StatUnit.heatUnits);
    }

    @Override
    public boolean rotatedOutput(int x, int y){
        return false;
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("instability", (HeaterReactorBuild entity) -> new Bar("bar.instability", Pal.sap, () -> entity.instability));

        addBar("heat", (HeaterReactorBuild entity) -> new Bar("bar.heat", Pal.lightOrange, () -> entity.heat / heatOutput));
    }

    public class HeaterReactorBuild extends GeneratorBuild implements HeatBlock {
        public float heat = 0, instability, warmup, flash;

        @Override
        public void updateTile() {
            super.updateTile();

            int fuel = items.get(fuelItem); //will change later
            float fullness = (float)fuel / itemCapacity;
            productionEfficiency = fullness;

            //for heat output, regardless of efficiency?
            heat = Mathf.approachDelta(heat, heatOutput * efficiency, warmupRate * delta());

            //instability and things
            warmup = Mathf.lerpDelta(warmup, productionEfficiency > 0 ? 1f : 0f, warmupRate);

            if(instability >= 1f){
                kill();
            }

            if(fuel > 0 && enabled){
                instability += fullness * heating * Math.min(delta(), 4f);

                if(timer(timerFuel, itemDuration / timeScale)){
                    consume();
                }
            }else{
                productionEfficiency = 0f;
            }

            if(instability > 0){
                float maxUsed = Math.min(liquids.currentAmount(), instability / coolantPower);
                instability -= maxUsed * coolantPower;
                liquids.remove(liquids.current(), maxUsed);
            }

            if(instability > smokeThreshold){
                float smoke = 1.0f + (instability - smokeThreshold) / (1f - smokeThreshold);
                if(Mathf.chance(smoke / 20.0 * delta())){
                    Fx.reactorsmoke.at(x + Mathf.range(size * tilesize / 2f),
                            y + Mathf.range(size * tilesize / 2f));
                }
            }

        }

        @Override
        public boolean shouldExplode(){
            return super.shouldExplode() && (items.get(fuelItem) >= 5 || instability >= 0.5f);
        }

        public void draw(){
            super.draw();

            if(instability > flashThreshold){
                if(!state.isPaused()) flash += (1f + ((instability - flashThreshold) / (1f - flashThreshold)) * flashSpeed) * Time.delta;
                Draw.z(Layer.blockAdditive);
                Draw.blend(Blending.additive);
                Draw.color(flashColor1, flashColor2, Mathf.absin(flash, 8f, 1f));
                Draw.alpha(flashAlpha * Mathf.clamp((instability - flashThreshold) / (1f - flashThreshold) * 4f));
                Draw.rect(lightsRegion, x, y);
                Draw.blend();
            }
        }

        @Override
        public float heatFrac(){
            return heat / heatOutput;
        }

        @Override
        public float heat(){
            return heat;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(heat);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            heat = read.f();

    }}}
