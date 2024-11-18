package complicatedustry.scripts;

import arc.graphics.Color;
import mindustry.type.Item;


public class moditems {

    public static Item diamond,forceAlloy, sporeReceptacle, powerCompound,compoundAlloy,ultralloy,
            platinum,carborundum,omegalloy,quantum,tin,bronze,hyperalloy,mythratite,pneumatite,genesisMetal,
            gold,superConductiveMetal;

        public static void load() {
            //normal
            {{
                diamond = new Item("diamond"){{
                    color = Color.valueOf("caecfa");
                }};

                sporeReceptacle = new Item("spore-receptacle"){{
                    color = Color.valueOf("7557cf");
                }};

                platinum = new Item("platinum"){{
                    color = Color.valueOf("ffffff");
                }};

                tin = new Item("tin"){{
                    color = Color.valueOf("fffffa");
                }};

                bronze = new Item("bronze"){{
                    color = Color.valueOf("fffffb");
                }};

                gold = new Item("gold"){{
                    color = Color.valueOf("fffffh");
                }};

                genesisMetal = new Item("genesis-metal"){{
                    color = Color.valueOf("fffffg");
                }};

                mythratite = new Item("mythratite"){{
                    color = Color.valueOf("fffffd");
                }};
            }}
            //normal but cooler
            {{
                carborundum = new Item("carborundum"){{
                    color = Color.valueOf("3d3938");
                }};

                forceAlloy = new Item("force-alloy"){{
                    color = Color.valueOf("5cb55f");
                }};

                powerCompound = new Item("power-compound"){{
                    color = Color.valueOf("73abff");
                    frames = 8;frameTime = 45;}};

                compoundAlloy = new Item("compound-alloy"){{
                    color = Color.valueOf("c4417a");
                }};

                superConductiveMetal = new Item("super-conductive-metal"){{
                    color = Color.valueOf("fffffi");
                }};
            }}
            //cool stuff
            {{
                quantum = new Item("quantum"){{
                    color = Color.valueOf("22ab39");
                }};

                pneumatite = new Item("pneumatite"){{
                    color = Color.valueOf("fffffe");
                }};
            }}
            //really cool stuff
            {{
                ultralloy = new Item("ultralloy"){{
                    color = Color.valueOf("58cedb");
                }};

                omegalloy = new Item("omegalloy"){{
                    color = Color.valueOf("c23673");
                }};

                hyperalloy = new Item("hyperalloy"){{
                    color = Color.valueOf("fffffc");
                }};

            }}
    }}
