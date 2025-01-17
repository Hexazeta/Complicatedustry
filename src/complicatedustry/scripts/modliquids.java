package complicatedustry.scripts;

import arc.graphics.Color;
import mindustry.type.CellLiquid;
import mindustry.type.Liquid;

import static mindustry.content.Liquids.*;


public class modliquids {
    //todo more liquids
    public static Liquid steam,supercooledfluid,helium,sporeinfestedwater,antifreeze,sporeMass,hyperplasm,aeroflux,
    calatysm;

        public static void load() {

            steam = new Liquid("steam"){{
                color = Color.valueOf("8ffe09");
            }};

            supercooledfluid = new Liquid("supercooledfluid"){{
                color = Color.valueOf("b2c9d6");heatCapacity = 2.5f;
                viscosity = 0.75f;temperature = -0.1f;
            }};

            helium = new Liquid("helium"){{
                color = Color.valueOf("b2c9d6");flammability = 2f;
            }};

            sporeinfestedwater = new Liquid("spore-infested-water"){{
                color = Color.valueOf("7557cf");flammability = 1.6f;alwaysUnlocked = true;
                heatCapacity = 0.3f;boilPoint = 0.4f;
            }};

            antifreeze = new Liquid("antifreeze"){{
                color = Color.valueOf("1eabc7");heatCapacity = 1;temperature = -3f;
            }};

            sporeMass = new CellLiquid("spore-mass"){{
                color = Color.valueOf("121212");
            }};

            hyperplasm = new CellLiquid("hyperplasm"){{
                heatCapacity = 0.9f;
                temperature = 3.5f;
                viscosity = 1f;
                flammability = 0f;
                capPuddles = false;
                spreadTarget = water;
                moveThroughBlocks = true;
                incinerable = false;
                blockReactive = false;
                canStayOn.addAll(water, oil, cryofluid);
                colorFrom = Color.valueOf("ff614d");
                color = Color.valueOf("d43547");
                colorTo = Color.valueOf("821443");
            }};

            aeroflux = new Liquid("aeroflux"){{
                color = Color.valueOf("97a5f7");heatCapacity = 0.6f;temperature = 0f;
            }};
        }}
