package complicatedustry.scripts;

import arc.graphics.Color;
import mindustry.type.Liquid;


public class modliquids {
    //todo more liquids
    public static Liquid steam,supercooledfluid,helium,sporeinfestedwater,antifreeze,mythril;

        public static void load() {

            steam = new Liquid("steam"){{
                color = Color.valueOf("8ffe09");
            }};

            supercooledfluid = new Liquid("supercooledfluid"){{
                color = Color.valueOf("b2c9d6");
            }};

            helium = new Liquid("helium"){{
                color = Color.valueOf("b2c9d6");
            }};

            sporeinfestedwater = new Liquid("spore-infested-water"){{
                color = Color.valueOf("7557cf");
            }};

            antifreeze = new Liquid("antifreeze"){{
                color = Color.valueOf("121212");
            }};

        }}
