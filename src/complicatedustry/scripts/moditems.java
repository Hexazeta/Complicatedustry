package complicatedustry.scripts;

import arc.graphics.Color;
import mindustry.type.Item;


public class moditems {

    public static Item diamond,forceAlloy,reinforcedGlass,powerAlloy,compoundAlloy,ultralloy,
            feldspar,aluminum,omegalloy,quantum;

        public static void load() {

            diamond = new Item("diamond"){{
            color = Color.valueOf("caecfa");
            }};

            aluminum = new Item("aluminum"){{
            color = Color.valueOf("6f7780")  ;
            }};

            feldspar = new Item("feldspar"){{
                color = Color.valueOf("a39955")  ;
            }};

            forceAlloy = new Item("force-alloy"){{
                color = Color.valueOf("64b346");
            }};

            reinforcedGlass = new Item("reinforced-glass"){{
                color = Color.valueOf("a3b7c7");
            }};

            powerAlloy = new Item("power-alloy"){{
                color = Color.valueOf("73abff");
            }};

            compoundAlloy = new Item("compound-alloy"){{
                color = Color.valueOf("c4417a");
            }};

            ultralloy = new Item("ultralloy"){{
                color = Color.valueOf("ffffff");
            }};

            omegalloy = new Item("omegalloy"){{
                color = Color.valueOf("111111");
            }};

            quantum = new Item("quantum"){{
                color = Color.valueOf("111111");
            }};

    }}
