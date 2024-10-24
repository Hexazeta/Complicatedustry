package complicatedustry.scripts;

import arc.graphics.Color;
import mindustry.type.Liquid;


public class modliquids {

    public static Liquid acid,supercooledfluid;

        public static void load() {

            acid = new Liquid("acid"){{
                color = Color.valueOf("8ffe09");
            }};

            supercooledfluid = new Liquid("supercooledfluid"){{
                color = Color.valueOf("b2c9d6");
            }};
        }}
