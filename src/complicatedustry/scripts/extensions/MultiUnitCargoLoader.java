package complicatedustry.scripts.extensions;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.math.geom.Point2;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.content.UnitTypes;
import mindustry.entities.Units;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Call;
import mindustry.gen.Groups;
import mindustry.gen.Unit;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.type.Item;
import mindustry.type.UnitType;
import mindustry.ui.Bar;
import mindustry.ui.Fonts;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.UnitTetherBlock;
import mindustry.gen.*;
import arc.struct.*;

import static complicatedustry.scripts.extensions.LargeSteamVent.offsets;
import static mindustry.gen.Groups.unit;

public class MultiUnitCargoLoader extends Block {
    public Seq<UnitType> unitTypes;
    public float buildTime;
    public float polyStroke;
    public float polyRadius;
    public int polySides;
    public float polyRotateSpeed;
    public Color polyColor;

    public MultiUnitCargoLoader(String name) {
        super(name);
        this.unitTypes = new Seq<>();
        this.unitTypes.add(UnitTypes.manifold, UnitTypes.manifold, UnitTypes.manifold);
        this.buildTime = 480.0f;
        this.polyStroke = 1.8f;
        this.polyRadius = 8.0f;
        this.polySides = 8;
        this.polyRotateSpeed = 1.0f;
        this.polyColor = Pal.accent;
        this.solid = true;
        this.update = true;
        this.hasItems = true;
        this.itemCapacity = 1000;
        this.ambientSound = Sounds.respawning;
    }

    public boolean outputsItems() {
        return false;
    }

    public void setBars() {
        super.setBars();
        this.addBar("units", (e) -> new Bar(() -> Core.bundle.format("bar.unitcap", Fonts.getUnicodeStr(this.unitTypes.first().name),
                e.team.data().countType(this.unitTypes.first()),
                Units.getStringCap(e.team)), () -> Pal.power, () -> (float)e.team.data().countType(this.unitTypes.first()) / (float)Units.getCap(e.team)));
    }

    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        return super.canPlaceOn(tile, team, rotation) && Units.canCreate(team, this.unitTypes.first());
    }

    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);
        if (!Units.canCreate(Vars.player.team(), this.unitTypes.first())) {
            this.drawPlaceText(Core.bundle.get("bar.cargounitcap"), x, y, valid);
        }
    }

    public static void unitTetherBlockSpawned(Tile tile, int id) {
        if (tile != null) {
            Building var3 = tile.build;
            if (var3 instanceof UnitTetherBlock build) {
                build.spawned(id);
            }
        }
    }

    public class MultiUnitTransportSourceBuild extends Building implements UnitTetherBlock {
        public Seq<Unit> units;
        public Seq<Float> buildProgresses;
        public Seq<Float> totalProgresses;
        public Seq<Float> warmups;
        public Seq<Float> readiness;
        public Seq<Boolean> unitCreated;

        public MultiUnitTransportSourceBuild() {
            this.units = new Seq<>();
            this.buildProgresses = new Seq<>();
            this.totalProgresses = new Seq<>();
            this.warmups = new Seq<>();
            this.readiness = new Seq<>();
            this.unitCreated = new Seq<>();

            for (int i = 0; i < unitTypes.size; i++) {
                units.add((Unit) null);
                buildProgresses.add(0.0f);
                totalProgresses.add(0.0f);
                warmups.add(0.0f);
                readiness.add(0.0f);
                unitCreated.add(false);
            }
        }

        @Override
        public void updateTile() {
            if (unitTypes.isEmpty()) {
                return;
            }

            for (int i = 0; i < unitTypes.size; i++) {
                Unit unit = units.get(i);

                // If unit already exists and is alive, skip creation
                if (unit != null && !unit.dead && unit.isAdded()) {
                    continue;
                }

                // If the unit is dead or null, reset its state and try creating it
                if (unit != null && (unit.dead || !unit.isAdded())) {
                    units.set(i, null);
                    unitCreated.set(i, false); // Reset unitCreated when unit is destroyed or lost
                }

                // Prevent creating units if they have already been created
                if (unit == null && Units.canCreate(this.team, unitTypes.get(i)) && !unitCreated.get(i)) {
                    buildProgresses.set(i, buildProgresses.get(i) + (this.edelta() / MultiUnitCargoLoader.this.buildTime));
                    totalProgresses.set(i, totalProgresses.get(i) + this.edelta());
                    warmups.set(i, Mathf.approachDelta(warmups.get(i), this.efficiency, 0.016666668F));

                    if (buildProgresses.get(i) >= 1.0F && !Vars.net.client()) {
                        Unit newUnit = unitTypes.get(i).create(this.team);
                        units.set(i, newUnit);
                        newUnit.set(this.x, this.y);
                        newUnit.rotation = 90.0F;
                        newUnit.add();
                        if (newUnit instanceof BuildingTetherc bt) {
                            bt.building(this);
                        }
                        unitCreated.set(i, true); // Mark unit as created
                        Call.unitTetherBlockSpawned(this.tile, newUnit.id);
                    }
                }
            }
        }

        @Override
        public void spawned(int id) {
            Fx.spawn.at(this.x, this.y);
            for (int i = 0; i < units.size; i++) {
                buildProgresses.set(i, 0.0f);
                totalProgresses.set(i, 0.0f);
                unitCreated.set(i, false); // Reset unitCreated when reloading
            }
        }

        @Override
        public boolean acceptItem(Building source, Item item) {
            return this.items.total() < MultiUnitCargoLoader.this.itemCapacity;
        }

        @Override
        public boolean shouldConsume() {
            for (Unit unit : units) {
                if (unit == null) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean shouldActiveSound() {
            for (float warmup : warmups) {
                if (warmup > 0.01f) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public void draw() {
            Draw.rect(this.block.region, this.x, this.y);

            for (int i = 0; i < units.size; i++) {
                Unit unit = units.get(i);
                if (unit == null) {
                    int finalI = i;
                    Draw.draw(35.0f, () -> Drawf.construct(this, MultiUnitCargoLoader.this.unitTypes.get(finalI).fullIcon, 0.0F, buildProgresses.get(finalI), warmups.get(finalI), totalProgresses.get(finalI)));
                } else {
                    Draw.z(99.99f);
                    Draw.color(MultiUnitCargoLoader.this.polyColor);
                    Lines.stroke(MultiUnitCargoLoader.this.polyStroke * readiness.get(i));
                    Lines.poly(this.x, this.y, MultiUnitCargoLoader.this.polySides, MultiUnitCargoLoader.this.polyRadius, Time.time * MultiUnitCargoLoader.this.polyRotateSpeed);
                    Draw.reset();
                    Draw.z(30.0f);
                }
            }
        }

        @Override
        public float totalProgress() {
            float total = 0;
            for (float progress : totalProgresses) {
                total += progress;
            }
            return total;
        }

        @Override
        public float progress() {
            float progress = 0;
            for (float p : buildProgresses) {
                progress += p;
            }
            return progress;
        }

        @Override
        public void write(Writes write) {
            super.write(write);
            for (int i = 0; i < units.size; i++) {
                write.i(units.get(i) == null ? -1 : units.get(i).id); // Save unit ID
                write.f(buildProgresses.get(i)); // Save build progress
                write.i(unitCreated.get(i) ? 1 : 0); // Save unitCreated as 1 (true) or 0 (false)
            }
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            for (int i = 0; i < units.size; i++) {
                int id = read.i();
                float progress = read.f();
                boolean created = read.i() == 1; // Read as 1 for true, 0 for false

                if (id != -1) {
                    Unit restoredUnit = unit.getByID(id);
                    if (restoredUnit != null) {
                        units.set(i, restoredUnit);
                        buildProgresses.set(i, progress);
                        unitCreated.set(i, created); // Restore unitCreated flag
                    }
                } else {
                    units.set(i, null);
                    unitCreated.set(i, false); // Reset unitCreated flag if unit is null
                }
            }
        }
    }
}
