package complicatedustry.scripts;

import arc.graphics.Color;
import mindustry.type.Liquid;


public class modliquids {
    //todo more liquids
    public static Liquid steam,supercooledfluid;

        public static void load() {

            steam = new Liquid("steam"){{
                color = Color.valueOf("8ffe09");
            }};

            supercooledfluid = new Liquid("supercooledfluid"){{
                color = Color.valueOf("b2c9d6");
            }};
        }}
