package complicatedustry.scripts.extensions;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.Rand;
import arc.math.geom.Vec2;
import arc.struct.EnumSet;
import arc.util.Nullable;
import arc.util.Time;
import mindustry.entities.Sized;
import mindustry.entities.Units;
import mindustry.game.Team;
import mindustry.gen.Entityc;
import mindustry.gen.Rotc;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.world.blocks.units.RepairTurret;
import mindustry.world.meta.*;

import static mindustry.Vars.tilesize;

public class RepairTurretTower extends RepairTurret {
    static final Rand rand = new Rand();

    public RepairTurretTower(String name) {
        super(name);
        update = true;
        solid = true;
        flags = EnumSet.of(BlockFlag.repair);
        hasPower = true;
        outlineIcon = true;
        group = BlockGroup.projectors;
        envEnabled |= Env.space;
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(Stat.range, repairRadius / tilesize, StatUnit.blocks);
        stats.add(Stat.repairSpeed, repairSpeed * 60f, StatUnit.perSecond);
    }

    public class RepairTurretTowerBuild extends RepairPointBuild {

        private boolean isHealing = false;

        @Override
        public void updateTile() {
            super.updateTile();

            isHealing = false;

            if (target != null && target.health() < target.maxHealth()) {

                float healingAmount = repairSpeed * strength * edelta();

                target.heal(healingAmount);

                Units.nearby(team, target.x, target.y, repairRadius, u -> {
                    if (u != target && u.health() < u.maxHealth() && u.damaged()) {
                        float surroundingHealing = healingAmount / 2f;
                        u.heal(surroundingHealing);
                    }
                });

                isHealing = true;
            }
        }

        public void drawBeam(float x, float y, float rotation, float length, int id, @Nullable Sized target, Team team,
                             float strength, float pulseStroke, float pulseRadius, float beamWidth,
                             Vec2 lastEnd, Vec2 offset,
                             Color laserColor, Color laserTopColor,
                             TextureRegion laser, TextureRegion laserEnd, TextureRegion laserTop, TextureRegion laserTopEnd) {
            rand.setSeed(id + (target instanceof Entityc e ? e.id() : 0));

            if (target != null) {
                float originX = x + Angles.trnsx(rotation, length);
                float originY = y + Angles.trnsy(rotation, length);

                lastEnd.set(target).sub(originX, originY);
                lastEnd.setLength(Math.max(2f, lastEnd.len()));

                lastEnd.add(offset.trns(
                        rand.random(360f) + Time.time / 2f,
                        Mathf.sin(Time.time + rand.random(200f), 55f, rand.random(target.hitSize() * 0.2f, target.hitSize() * 0.45f))
                ).rotate(target instanceof Rotc rot ? rot.rotation() : 0f));

                lastEnd.add(originX, originY);
            }

            if (strength > 0.01f) {
                float originX = x + Angles.trnsx(rotation, length);
                float originY = y + Angles.trnsy(rotation, length);

                Draw.z(Layer.flyingUnit + 1);

                Draw.color(laserColor);

                float f = (Time.time / 85f + rand.random(1f)) % 1f;

                Draw.alpha(1f - Interp.pow5In.apply(f));
                Lines.stroke(strength * pulseStroke);
                Lines.circle(lastEnd.x, lastEnd.y, 1f + f * pulseRadius);

                Draw.color(laserColor);
                Drawf.laser(laser, laserEnd, originX, originY, lastEnd.x, lastEnd.y, strength * beamWidth);
                Draw.z(Layer.flyingUnit + 1.1f);
                Draw.color(laserTopColor);
                Drawf.laser(laserTop, laserTopEnd, originX, originY, lastEnd.x, lastEnd.y, strength * beamWidth);
                Draw.color();
            }
        }

        @Override
        public void draw() {
            super.draw();

            Draw.rect(baseRegion, x, y);
            Draw.z(Layer.turret);
            Drawf.shadow(region, x - (size / 2f), y - (size / 2f), rotation - 90);
            Draw.rect(region, x, y, rotation - 90);

            if (isHealing && target != null) {
                drawBeam(x, y, rotation, length, id, target, team, strength,
                        pulseStroke, pulseRadius, beamWidth, lastEnd, offset, laserColor, laserTopColor,
                        laser, laserEnd, laserTop, laserTopEnd);
            }

            if (isHealing && target != null) {
                Draw.z(Layer.effect);
                float pulseMod = Mathf.absin(Time.time, 6f, 0.5f);
                Draw.color(Pal.heal);
                Lines.stroke(pulseStroke * pulseMod);
            }


        }

        @Override
        public void drawSelect() {
            Drawf.dashCircle(x, y, repairRadius, Pal.placing);
        }
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);
        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, repairRadius, Pal.accent);
    }
}


