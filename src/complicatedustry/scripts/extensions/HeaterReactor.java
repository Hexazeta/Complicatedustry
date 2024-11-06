package complicatedustry.scripts.extensions;

import arc.Events;
import arc.math.Mathf;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.game.EventType;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.heat.HeatBlock;
import mindustry.world.blocks.power.NuclearReactor;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawHeatOutput;
import mindustry.world.draw.DrawMulti;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

public class HeaterReactor extends NuclearReactor {

    //todo fix all of this shit
    public float heatOutput = 10f;
    public float warmupRate = 0.15f;
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

        stats.add(Stat.output, heatOutput, StatUnit.heatUnits);
    }

    @Override
    public boolean rotatedOutput(int x, int y){
        return false;
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("heat", (HeaterReactorBuild entity) -> new Bar("bar.heat", Pal.lightOrange, () -> entity.heat / heatOutput));
    }

    public class HeaterReactorBuild extends NuclearReactorBuild implements HeatBlock {

        @Override
        public void updateTile() {
            super.updateTile();

            heat = Mathf.approachDelta(heat, heatOutput * efficiency,  warmupRate * delta());

            int fuel = items.get(fuelItem);
            float fullness = (float) fuel / itemCapacity;
            productionEfficiency = fullness;

            if (fuel > 0 && enabled) {
                heat += (fullness * heating * Math.min(delta(), 4f)) + 10f;

                if (timer(timerFuel, itemDuration / timeScale)) {
                    consume();
                }
            } else {
                productionEfficiency = 0f;
            }

            // heating moment
            if (heat > 0) {
                float maxUsed = Math.min(liquids.currentAmount(), heat / coolantPower);
                heat -= maxUsed * coolantPower;
                liquids.remove(liquids.current(), maxUsed);
            }

            if(heat >= 9.999f){
                Events.fire(EventType.Trigger.thoriumReactorOverheat);
                kill();
            }
        }

        @Override
        public float heatFrac(){
            return heat ;
        }

        @Override
        public float heat(){
            return heat * heatOutput;
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
