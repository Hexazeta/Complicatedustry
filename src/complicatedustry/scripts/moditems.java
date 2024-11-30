package complicatedustry.scripts;

import arc.graphics.Color;
import mindustry.type.Item;


public class moditems {

    public static Item diamond,forceAlloy, sporeReceptacle, powerCompound,compoundAlloy,ultralloy,
            platinum,carborundum,omegalloy,quantum,tin,bronze,hyperalloy,mythratite,pneumatite,genesisMetal,
            gold,superConductiveMetal,superSurgeAlloy,superForceAlloy;

        public static void load() {
            //normal
            {{
                diamond = new Item("diamond"){{
                    color = Color.valueOf("caecfa");healthScaling = 6;cost = 2.5f;
                }};

                sporeReceptacle = new Item("spore-receptacle"){{
                    color = Color.valueOf("7557cf");flammability = 3.5f;
                }};

                platinum = new Item("platinum"){{
                    color = Color.valueOf("b5bfc7");hardness = 2;cost = 1.5f;
                }};

                tin = new Item("tin"){{
                    color = Color.valueOf("adb2ba");hardness = 1;cost = 0.6f;
                }};

                bronze = new Item("bronze"){{
                    color = Color.valueOf("fffffb");cost = 1.2f;
                }};

                gold = new Item("gold"){{
                    color = Color.valueOf("c79d00");cost = 0.2f;
                }};

                genesisMetal = new Item("genesis-metal"){{
                    color = Color.valueOf("81838f");healthScaling = 1.2f;cost = 2f;
                }};

                mythratite = new Item("mythratite"){{
                    color = Color.valueOf("ade080");
                    flammability = 1.05f;explosiveness = 0.5f;
                }};
            }}
            //normal but cooler
            {{
                carborundum = new Item("carborundum"){{
                    color = Color.valueOf("3d3938");healthScaling = 8f;cost = 6f;
                }};

                forceAlloy = new Item("force-alloy"){{
                    color = Color.valueOf("5cb55f");healthScaling = 3; flammability = 0.1f;cost = 1.5f;
                }};

                powerCompound = new Item("power-compound"){{
                    color = Color.valueOf("73abff");
                    frames = 8;frameTime = 45;radioactivity = 2f;explosiveness = 2f;flammability = 3f;
                }};

                compoundAlloy = new Item("compound-alloy"){{
                    color = Color.valueOf("c4417a");explosiveness = 0.1f;cost = 2;
                    flammability = 0.05f;radioactivity = 0.3f;healthScaling = 1.5f;
                }};

                superConductiveMetal = new Item("super-conductive-metal"){{
                    color = Color.valueOf("fffffi");charge = 1.5f;
                }};
            }}
            //cool stuff
            {{
                superSurgeAlloy = new Item("super-surge-alloy", Color.valueOf("f3e979")){{
                    cost = 4f;
                    charge = 4f;
                    healthScaling = 2f;
                }};

                superForceAlloy = new Item("super-force-alloy"){{
                    color = Color.valueOf("5cb55f");healthScaling = 14f;cost = 6f;
                }};

                quantum = new Item("quantum"){{
                    color = Color.valueOf("22ab39");radioactivity = 0.4f;
                }};

                pneumatite = new Item("pneumatite"){{
                    color = Color.valueOf("70e0d0");explosiveness = 1;flammability = 2.5f;healthScaling = 0.8f;
                }};
            }}
            //really cool stuff
            {{
                ultralloy = new Item("ultralloy"){{
                    color = Color.valueOf("58cedb");radioactivity = 4.5f;explosiveness = 1.1f;
                    flammability = 2.1f;healthScaling = 2.5f;charge = 0.5f;cost = 5;
                }};

                omegalloy = new Item("omegalloy"){{
                    color = Color.valueOf("c23673");flammability = 0.8f;explosiveness = 15f;radioactivity = 0.8f;
                    healthScaling = 0.7f;cost = 3;
                }};

                hyperalloy = new Item("hyperalloy"){{
                    color = Color.valueOf("fffffc");explosiveness = 3;
                    flammability = 2f;healthScaling = 10f;charge = 2.5f;cost = 10;
                }};

            }}
    }}
