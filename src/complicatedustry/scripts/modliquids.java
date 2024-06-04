package complicatedustry.scripts;

import arc.graphics.Color;
import mindustry.type.Liquid;


public class modliquids {

    public static Liquid acid,denseFluid;

        public static void load() {

            acid = new Liquid("acid"){{
                color = Color.valueOf("8ffe09");
            }};

            denseFluid = new Liquid("densefluid"){{
                color = Color.valueOf("5ead98");
            }};
        }}
