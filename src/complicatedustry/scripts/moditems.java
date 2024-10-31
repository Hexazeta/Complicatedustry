package complicatedustry.scripts;

import arc.graphics.Color;
import mindustry.type.Item;


public class moditems {

    public static Item diamond,forceAlloy,reinforcedGlass,powerAlloy,compoundAlloy,ultralloy,
            feldspar,carborundum,omegalloy,quantum;

        public static void load() {

            diamond = new Item("diamond"){{
            color = Color.valueOf("caecfa");
            }};

            carborundum = new Item("carborundum"){{
            color = Color.valueOf("3d3938")  ;
            }};

            forceAlloy = new Item("force-alloy"){{
                color = Color.valueOf("64b346");
            }};

            reinforcedGlass = new Item("reinforced-glass"){{
                color = Color.valueOf("a3b7c7");
            }};

            powerAlloy = new Item("power-compound"){{
                color = Color.valueOf("73abff");
                frames = 8;frameTime = 90;}};

            compoundAlloy = new Item("compound-alloy"){{
                color = Color.valueOf("c4417a");
            }};

            ultralloy = new Item("ultralloy"){{
                color = Color.valueOf("58cedb");
            }};

            omegalloy = new Item("omegalloy"){{
                color = Color.valueOf("c23673");
            }};

            quantum = new Item("quantum"){{
                color = Color.valueOf("22ab39");
            }};

    }}
