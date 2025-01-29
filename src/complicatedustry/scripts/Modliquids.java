package complicatedustry.scripts;

import arc.graphics.Color;
import mindustry.content.StatusEffects;
import mindustry.type.CellLiquid;
import mindustry.type.Liquid;

import static mindustry.content.Liquids.*;


public class Modliquids {
    //todo more liquids
    public static Liquid turboFuel,supercooledfluid,helium,sporeinfestedwater,antifreeze,sporeMass,hyperplasm, heavySlag,
    calatysm;

        public static void load() {

            turboFuel = new Liquid("turbo-fuel"){{
                viscosity = 0.5f;
                flammability = 4f;
                explosiveness = 3.75f;
                heatCapacity = 2f;
                barColor = Color.valueOf("ffffff");
                effect = StatusEffects.tarred;
                boilPoint = 0.975f;
                gasColor = Color.grays(0.9f);
                canStayOn.addAll(water, arkycite);
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
                color = Color.valueOf("ffffff");flammability = 3.6f;viscosity = 0.75f;
                heatCapacity = 0.75f;boilPoint = 1f;spreadTarget = neoplasm;
                moveThroughBlocks = true;capPuddles = false;canStayOn.addAll(water, neoplasm, hyperplasm);
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

            heavySlag = new Liquid("heavy-slag", Color.valueOf("ffa166")){{
                temperature = 3f;
                viscosity = 0.825f;
                effect = StatusEffects.melting;
                lightColor = Color.valueOf("f0511d").a(0.4f);
            }};
        }}
