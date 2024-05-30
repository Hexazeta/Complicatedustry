package complicatedustry.scripts;

import arc.graphics.Color;
import mindustry.type.Item;

public class moditems {

    public static Item diamond;

        public static void load() {

            diamond = new Item("diamond"){{
            color = Color.valueOf("caecfa");
            }};

    }}
