package complicatedustry.scripts;

import arc.graphics.Blending;
import arc.graphics.Color;
import arc.math.Interp;
import arc.math.Mathf;
import arc.struct.EnumSet;
import complicatedustry.scripts.extensions.*;
import mindustry.content.*;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.LiquidBulletType;
import mindustry.entities.bullet.MissileBulletType;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.RadialEffect;
import mindustry.entities.effect.WrapEffect;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.gen.Sounds;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.LiquidTurret;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.heat.HeatConductor;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.blocks.liquid.ArmoredConduit;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.liquid.LiquidBridge;
import mindustry.world.blocks.liquid.LiquidRouter;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.Unloader;
import mindustry.world.blocks.units.UnitCargoLoader;
import mindustry.world.blocks.units.UnitCargoUnloadPoint;
import mindustry.world.consumers.ConsumeItemExplode;
import mindustry.world.consumers.ConsumeItemFlammable;
import mindustry.world.consumers.ConsumeItemRadioactive;
import mindustry.world.draw.*;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BlockFlag;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.Env;

import static mindustry.Vars.tilesize;
import static mindustry.content.Blocks.*;
import static mindustry.type.ItemStack.*;


public class Modblocks {

    public static Block
    //stuff here
    carborundumCrucible,mergedBlock1,mergedBlock2,mergedBlock3,mergedBlock4,largeArkyicVent,hyperalloyWall,hyperalloyWallLarge,
    //drills and shit
    hugePlasmaBore,innovatoryDrill,craterDrill,mountainCrusher,iceCrusher,quakeDrill,
    //crafters
    smallHeatRedirector, SmallHeatRouter, surgeRouter, platedConveyor, infestationChamber,
    pyratiteMultiMixer, siliconFoundry,  forge, waterConcentrator, bioSynthesizer, graphiteCentrifuge,
    densifier, carbideFoundry, advancedOilExtractor, forceCrucible, plastaniumMultiCompressor,godConveyor,
    surgeFoundry, flood, reinforcedBridge, armoredDirectionalUnloader, gonzales, infestor, sporeMultiPress,armoredBridge,
    platedConduit, platedBridgeConduit, platedLiquidContainer, platedLiquidTank, unitRepairTurret,
    blastMultiMixer,fractionator, powerMixer, supercooler, compoundCrucible, phaseSuperHeater, quasiconstructor,
    omegalloyCrucible, ultralloyCrucible, reinforcedConveyor, thermalOxidizer, unitPayloadLoader,
    unitPayloadUnloadPoint, overdriveStadium, constructMonolith, regenDome, blastwaveMonolith, mendDome,
    horde, sporeIncubator,mythratiteMixer,pneumatiteMixer,bronzeSmelter,superConductorFurnace,hyperalloyCrucible, 
    compactor, fuelSynthesizer,rtgCoreGenerator,surgeRefinery,ultralloyConveyor,platedLiquidRouter,
    reinforcedPowerNode,largeReinforcedPowerNode,hugeReinforcedPowerNode,platedContainer,platedVault,
    genesisEnergyFloor, genesisEnergyFloorEmpty, genesisFloor, genesisProcessor,genesisEnergy,largeShieldedWall,
    largeForceProjector, darkshale, surgeConduit, forceRefinery, armoredUnloader,

    //power
    boilerGenerator, hyperneoplasiaGenerator, chemicalReactionChamber, advancedPyrolysisGenerator, geothermalGenerator,
    heavyDutyTurbineCondenser, radiativeReactor, TitanReactor,neoplasiaHyperReactor, chronoReactor, behemothReactor;
    //todo unit constructors?

    public static void load() {
        //enviroment
        {{
            largeArkyicVent = new LargeSteamVent("large-arkyic-vent") {{
                    parent = blendGroup = arkyicStone;
                    attributes.set(Attribute.steam, 3f);
                }};

            genesisEnergyFloor = new Floor("genesis-energy-floor") {{
                    playerUnmineable = true;
                    attributes.set(Modattribute.genesis, 0.75f);
                }};

            genesisEnergyFloorEmpty = new Floor("genesis-energy-empty-floor") {{
                    playerUnmineable = true;
                }};

            genesisFloor = new Floor("genesis-floor") {{
                    playerUnmineable = true;
                    variants = 19;
                }};

            genesisEnergy = new Floor("genesis-energy") {{
                    status = StatusEffects.none;
                    variants = 0;
                    isLiquid = false;
                    attributes.set(Modattribute.genesis, 2f);
                    emitLight = true;
                    lightRadius = 60f;
                    lightColor = Color.valueOf("96dcff").cpy().a(0.38f);
                }};

            darkshale = new Floor("dark-shale"){{
                variants = 3;
                attributes.set(Attribute.oil, 3.3f);
            }};
            }}
        //trash
        {{

        }}
        //testing
        {{

        }}
        //drillers
        {{
            innovatoryDrill = new Drill("innovatory-drill"){{
                requirements(Category.production, with(Items.graphite, 1));
                tier = 2;
                drillTime = 259;
                size = 3;
                itemCapacity = 30;
                liquidBoostIntensity = 1.7f;
                consumeLiquid(Liquids.water, 0.08f).boost();
            }};

            hugePlasmaBore = new BeamDrill("huge-plasma-bore") {{
                    requirements(Category.production, with(Items.graphite, 1));
                    consumePower(1.8f);
                    drillTime = 60f * (20f / 27f); //1f = 240/s
                    tier = 7;
                    size = 4;
                    range = 10;
                    fogRadius = 6;
                    laserWidth = 0.75f;
                    itemCapacity = 90;
                    liquidCapacity = 50f;
                    consumeLiquids(LiquidStack.with(Liquids.nitrogen, 32f / 60f, Liquids.ozone, 20f / 60f));

                    consumeLiquid(Liquids.cryofluid, 20f / 60f).boost();
                }};

            quakeDrill = new Drill("quake-drill"){{
                requirements(Category.production, with(Items.graphite, 1));
                drillTime = 210;
                size = 5;
                drawRim = true;
                hasPower = true;
                tier = 4;
                updateEffect = Fx.pulverizeRed;
                updateEffectChance = 0.07f;
                drillEffect = Fx.mineHuge;
                rotateSpeed = 10f;
                warmupSpeed = 0.001f;
                itemCapacity = 70;

                //more than the blast drill
                liquidBoostIntensity = 2f;

                consumePower(10f);
                consumeLiquid(Liquids.water, 0.3f).boost();
            }};

            craterDrill = new BurstDrill("crater-drill") {{
                    requirements(Category.production, with(Items.graphite, 1));
                    drillTime = 60f * (12f / 5f); //1f = 2160/s
                    size = 6;
                    hasPower = true;
                    squareSprite = false;
                    tier = 10;
                    drillEffect = new MultiEffect(
                            Fx.mineImpact,
                            Fx.drillSteam,
                            Fx.steam,
                            Fx.dynamicSpikes.wrap(Moditems.forceAlloy.color, 40f),
                            Fx.mineImpactWave.wrap(Moditems.forceAlloy.color, 60f),
                            Fx.shockwave.wrap(Moditems.forceAlloy.color, 80f)
                    );
                    shake = 5f;
                    itemCapacity = 144;
                    liquidCapacity = 80f;
                    arrowSpacing = 7.1f;
                    baseArrowColor = Color.valueOf("595a66");
                    glowColor.a = 0.6f;
                    fogRadius = 6;
                    consumePower(16f);
                    consumeLiquids(LiquidStack.with(Liquids.nitrogen, 64f / 60f, Liquids.hydrogen, 30f / 60f));
                }};

            mountainCrusher = new WallCrafter("mountain-crusher") {{
                    requirements(Category.production, with(Items.graphite, 1));
                    consumePower(40f / 60f);
                    drillTime = 18f;
                    size = 4;
                    itemCapacity = 90;
                    attribute = Attribute.sand;
                    output = Items.sand;
                    fogRadius = 5;
                    ambientSound = Sounds.drill;
                    ambientSoundVolume = 0.08f;
                    consumeLiquid(Liquids.nitrogen, 10f / 60f);
                    //itemConsumer = consumeItem(Items.carbide).boost(); this isnt in this version yet
                    //boostItemUseTime = 60f * 7f;
                }};

            

            }}
        //payload transporter
        {{
        reinforcedConveyor = new StackConveyor("reinforced-conveyor"){{
                requirements(Category.distribution, with(Items.copper, 3, Items.beryllium, 1));
                health = 90;
                speed = 5f / 60f;
                itemCapacity = 10;
            }};

        armoredDirectionalUnloader = new DirectionalUnloader("armored-directional-unloader"){{
                requirements(Category.distribution, with(Items.graphite, 1));
                health = 360;
                speed = 1.6f;
                solid = false;
                underBullets = true;
                regionRotated1 = 1;
            }};

        armoredUnloader = new Unloader("armored-unloader"){{
                requirements(Category.effect, with(Items.graphite, 1));
                speed = 60f / 30f;
                group = BlockGroup.transportation;
            }};

        platedConveyor = new StackConveyor("armored-surge-conveyor"){{
                requirements(Category.distribution, with(Items.surgeAlloy, 5, Moditems.forceAlloy, 2, Items.carbide, 3));
                health = 90;
                speed = 5f / 60f;
                outputRouter = false;
                itemCapacity = 30;
                hasPower = true;
                underBullets = true;
                consumesPower = true;
                conductivePower = true;
                baseEfficiency = 1f;
                consumePower(3f/60f);
            }};

        ultralloyConveyor = new StackConveyor("ultralloy-conveyor"){{
                requirements(Category.distribution, with(Moditems.ultralloy, 1));
                health = 290;
                speed = 6f / 60f;
                outputRouter = false;
                itemCapacity = 75;
                hasPower = true;
                underBullets = false;
                consumesPower = true;
                conductivePower = true;
                baseEfficiency = 2f;
                consumePower(40f/60f);
            }};

        armoredBridge = new BufferedItemBridge("armored-bridge"){{
                requirements(Category.distribution, with(Items.graphite, 1));
                range = 8;
                fadeIn = moveArrows = false;
                arrowSpacing = 9f;
                itemCapacity = 30;
                unloadable = true;
                envEnabled |= Env.space;
                bufferCapacity = 14;
            }};

        reinforcedBridge = new ItemBridge("reinforced-bridge"){{
                requirements(Category.distribution, with(Items.graphite, 1));
                range = 16;
                arrowPeriod = 0.15f;
                arrowTimeScl = 3.75f;
                hasPower = true;
                itemCapacity = 90;
                unloadable = true;
                pulse = true;
                envEnabled |= Env.space;
                consumePower(0.5f);
            }};

        surgeRouter = new StackRouter("surge-router"){{
            requirements(Category.distribution, with(Items.surgeAlloy, 5, Items.titanium, 1));
            health = 130;

            speed = 5f / 60f;

            hasPower = true;
            consumesPower = true;
            conductivePower = true;
            baseEfficiency = 1f;
            underBullets = true;
            solid = false;
            consumePower(3f / 60f);
        }};

        unitPayloadLoader = new UnitCargoLoader("unit-payload-loader"){{
                requirements(Category.distribution, with(Items.graphite, 1));
                size = 4;
                buildTime = 60f * 16f;
                unitCapModifier = 3;
                consumePower(32f / 60f);
                unitType = UnitTypes.manifold;
                consumeLiquids(LiquidStack.with(Liquids.nitrogen, 35f / 60f, Liquids.arkycite, 10f / 60f));
                itemCapacity = 1000;
            }};

        unitPayloadUnloadPoint = new UnitCargoUnloadPoint("unit-payload-unload-point"){{
                requirements(Category.distribution, with(Items.graphite, 1));
                size = 3;
                itemCapacity = 500;
            }};

        godConveyor = new StackConveyor("god-conveyor"){{
                requirements(Category.distribution, with(Moditems.carborundum, 1000000000));
                health = 90;
                speed = 10f / 60f;
                outputRouter = false;
                itemCapacity = 100000;
                hasPower = true;
                underBullets = true;
                consumesPower = true;
                conductivePower = true;
                baseEfficiency = 1f;
                consumePower(1f);
            }};

        platedConduit = new Conduit("plated-conduit"){{
                requirements(Category.liquid, with(Items.graphite, 1));
                botColor = Pal.darkestMetal;
                leaks = true;
                liquidCapacity = 60f;
                liquidPressure = 1.5f;
                health = 750;
                researchCostMultiplier = 3;
                underBullets = true;
            }};

        surgeConduit = new ArmoredConduit("surge-conduit"){{
                requirements(Category.liquid, with(Items.graphite, 1));
                botColor = Pal.darkestMetal;
                leaks = false;
                liquidCapacity = 400f;
                liquidPressure = 5f;
                health = 5000;
                researchCostMultiplier = 3;
                underBullets = true;
            }};

        platedBridgeConduit = new LiquidBridge("plated-bridge-conduit"){{
                requirements(Category.liquid, with(Items.graphite, 1));
                range = 8;
                hasPower = false;
                researchCostMultiplier = 1;
                underBullets = true;
                liquidCapacity = 30;
                fadeIn = moveArrows = false;
            }};

        platedLiquidRouter = new LiquidRouter("plated-liquid-router"){{
                requirements(Category.liquid, with(Items.graphite, 1));
                liquidCapacity = 200f;
                underBullets = true;
                solid = false;
            }};

        platedLiquidContainer = new LiquidRouter("plated-liquid-container"){{
                requirements(Category.liquid, with(Items.tungsten, 10, Items.beryllium, 16));
                liquidCapacity = 3000f;
                size = 2;
                liquidPadding = 6f/4f;
                researchCostMultiplier = 4;
                solid = true;
            }};

        platedLiquidTank = new LiquidRouter("plated-liquid-tank"){{
                requirements(Category.liquid, with(Items.tungsten, 40, Items.beryllium, 50));
                size = 3;
                solid = true;
                liquidCapacity = 40000f;
                liquidPadding = 2f;
            }};

        }}
        //production
        {{
        smallHeatRedirector = new HeatConductor("small-heat-redirector"){{
            requirements(Category.crafting, with(Items.graphite, 1));
            size = 1;
            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput(), new DrawHeatInput("-heat"));
            regionRotated1 = 1;
        }};

        SmallHeatRouter = new HeatConductor("small-heat-router"){{
            requirements(Category.crafting, with(Items.graphite, 1));
            size = 1;
            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput(-1, false), new DrawHeatOutput(), new DrawHeatOutput(1, false), new DrawHeatInput("-heat"));
            regionRotated1 = 1;
            splitHeat = true;
        }};

        carbideFoundry = new HeatCrafter("carbide-foundry") {{
                requirements(Category.crafting, with( Items.graphite, 1));
                squareSprite = true;
                size = 4;
                itemCapacity = 40;
                liquidCapacity = 70f;
                craftTime = 150f;
                updateEffect = Fx.pulverizeMedium;
                drawer = new DrawMulti(  new DrawRegion("-bottom"), new DrawCrucibleFlame(){{
                    particles = 40; particleRad = 11f; particleSize = 3.5f; fadeMargin = 0.6f; rotateScl = 2f;}},
                        new DrawDefault(), new DrawHeatInput());
                ambientSound = Sounds.smelter;
                ambientSoundVolume = 0.09f;
                hasPower = true;
                hasLiquids = true;
                consumeLiquid(Liquids.oil, 40f / 60f);
                consumeItems(with(Items.tungsten, 11, Items.graphite, 13));
                consumePower(32f);
                outputItem = new ItemStack(Items.carbide, 9);
                heatRequirement = 45f;
            }};

        plastaniumMultiCompressor = new GenericCrafter("plastanium-multi-compressor") {{
                requirements(Category.crafting, with( Items.graphite, 1));
                squareSprite = true;
                size = 3;
                itemCapacity = 50;
                liquidCapacity = 200f;
                craftTime = 60f;
                craftEffect = Fx.formsmoke;
                updateEffect = Fx.smeltsmoke;
                drawer = new DrawMulti(new DrawDefault(), new DrawFade());
                hasPower = true;
                hasLiquids = true;
                consumeLiquid(Liquids.oil, 80f / 60f);
                consumeItems(with(Items.titanium, 15, Items.sporePod, 4));
                consumePower(30f);
                outputItem = new ItemStack(Items.plastanium, 13);
            }};

        mergedBlock1 = new HeatProducer("catalytic-chamber"){{
                requirements(Category.crafting, with(Items.graphite, 1));
                squareSprite = false;
                size = 5;
                researchCostMultiplier = 1.2f;
                hasItems = true;
                hasLiquids = true;
                craftTime = 60f;
                drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidRegion(Liquids.ozone),
                        new DrawMultiWeave(){{glowColor = new Color(1f, 0.4f, 0.4f, 0.8f);}},
                        new DrawRegion(), new DrawHeatOutput(),
                        new DrawHeatRegion("-glow"){{color = new Color(1f, 0.4f, 0.3f, 1f);}});
                invertFlip = false;
                itemCapacity = 300;
                liquidCapacity = 400f;
                consumePower(90f);
                consumeLiquid(Liquids.ozone, 100f / 60f);
                consumeItems(ItemStack.with(Items.silicon, 26, Items.beryllium, 19, Items.thorium, 12));
                outputItems = ItemStack.with(Items.oxide, 30, Items.phaseFabric, 20);
                heatOutput = 60;
            }};

        mergedBlock2 = new HeatCrafter("dual-coolant-synthesizer"){{
                requirements(Category.crafting, with(Items.graphite, 1));
                size = 5;
                researchCostMultiplier = 1.2f;
                craftTime = 165f;
                drawer = new DrawMulti(
                        new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.cryofluid, 3f),
                        new DrawBubbles(Color.valueOf("7693e3")){{sides = 36;
                            recurrence = 10f;spread = 10;radius = 5f;amount = 65;}},
                        new DrawLiquidTile(Liquids.cyanogen), new DrawParticles(){{
                            color = Color.valueOf("89e8b6");alpha = 0.4f;particleSize = 3f;
                            particles = 30;particleRad = 10f;particleLife = 240f;reverse = true;
                            particleSizeInterp = Interp.one;}}, new DrawRegion(),
                        new DrawLiquidOutputs(), new DrawHeatInput()
                );
                rotate = true;
                invertFlip = true;
                hasLiquids = true;
                hasItems = true;
                group = BlockGroup.liquids;
                itemCapacity = 40;
                liquidCapacity = 2000f;
                consumeItems(ItemStack.with(Items.titanium, 5, Items.graphite, 3));
                consumeLiquids(LiquidStack.with(Liquids.hydrogen, 30f / 60f,
                        Liquids.ozone, 20f / 60f, Liquids.arkycite, 180f / 60f));
                consumePower(27f);
                regionRotated1 = 3;
                outputLiquids = LiquidStack.with(Liquids.cryofluid, 1f, Liquids.cyanogen, 15f / 60f);
                liquidOutputDirections = new int[]{1, 3};
                heatRequirement = 30;
                maxEfficiency = 6;
            }};

        mergedBlock3 = new HeatCrafter("atmospheric-crystallizer"){{
                requirements(Category.crafting, with( Items.graphite, 1));
                squareSprite = false;
                size = 5;
                itemCapacity = 250;
                liquidCapacity = 1000f;
                craftTime = 240f;
                craftEffect = Fx.steam;
                updateEffect = Fx.steam;
                drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.nitrogen),
                        new DrawDefault(), new DrawHeatInput());
                hasPower = true;
                hasLiquids = true;
                consumeItems(with(Items.graphite, 25));
                consumePower(9);
                outputItem = new ItemStack(Moditems.diamond, 11);
                outputLiquid = new LiquidStack(Liquids.nitrogen, 25f / 60f);
                heatRequirement = 60f;
                maxEfficiency = 12f;
            }};

        pyratiteMultiMixer = new AttributeCrafter("pyratite-multi-mixer") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            liquidCapacity = 30f;
            craftTime = 120f;
            craftEffect = Fx.coalSmeltsmoke;
            updateEffect= Fx.coalSmeltsmoke;
            hasPower = true;
            hasLiquids = true;
            consumeItems(with(Items.sand, 12, Items.graphite, 4, Items.lead, 7));
            consumeLiquid(Liquids.oil, 10f / 60f);
            consumePower(2f);
            outputItem = new ItemStack(Items.pyratite, 6);
            attribute = Attribute.oil;
            boostScale = 5f / 126f;
            maxBoost = 3f;
        }};

        siliconFoundry = new AttributeCrafter("silicon-arc-smelter") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            craftEffect = updateEffect = Fx.smeltsmoke;
            drawer = new DrawMulti( new DrawRegion("-bottom"), new DrawArcSmelt(){{particles = 60;particleRad = 11;
                circleSpace = 3;circleStroke = 2.25f;particleStroke = 1.5f;blending = Blending.additive;flameRad = 1.5f;
            }}, new DrawDefault());
            size = 4;
            itemCapacity = 120;
            liquidCapacity = 35f;
            craftTime = 640f / 9f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.oil, 30f / 60f);
            consumeItems(with(Items.sand, 18, Items.graphite, 8, Items.pyratite, 4));
            consumePower(48f);
            outputItem = new ItemStack(Items.silicon, 32);
            ambientSound = Sounds.electricHum;
            boostScale = 1f / 6f;
            maxBoost = 2f;
        }};

        forge = new GenericCrafter("forge") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            craftTime = 40f;
            craftEffect = Fx.smeltsmoke;
            updateEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(new DrawDefault() , new DrawFlame(Color.valueOf("ffc099")));
            hasPower = true;
            hasLiquids = false;
            consumeItems(with(Items.sand, 4, Items.lead, 5, Items.pyratite, 1));
            consumePower(2f);
            outputItem = new ItemStack(Items.metaglass, 4);
        }};

        genesisProcessor = new AttributeCrafter("genesis-processor") {{
                requirements(Category.production, with( Items.graphite, 1));
                squareSprite = true;
                size = 3;
                itemCapacity = 30;
                craftTime = 60f;
                attribute = Modattribute.genesis;
                hasPower = true;
                hasLiquids = false;
                consumePower(1f);
                outputItem = new ItemStack(Moditems.genesisMetal, 3);
                boostScale = 0.15f; baseEfficiency = 0; maxBoost = 3f;
            }};

        graphiteCentrifuge = new GenericCrafter("graphite-centrifuge"){{
                requirements(Category.crafting, with(Items.graphite, 1));
                craftEffect = Fx.coalSmeltsmoke;
                outputItem = new ItemStack(Items.graphite, 9);
                craftTime = 67.5f;
                liquidCapacity = itemCapacity = 50;
                size = 3;
                hasPower = hasItems = hasLiquids = true;
                rotateDraw = false;
                consumeItem(Items.sporePod, 2);
                consumeLiquid(Liquids.oil, 0.7f);
                consumePower(30f);
            }};

        waterConcentrator = new AttributeCrafter("water-concentrator") {{
            requirements(Category.production, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            liquidCapacity = 400f;
            craftTime =19.2f;
            attribute = Attribute.water;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidRegion(Liquids.water), new DrawDefault());
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.ozone, 30f / 60f);
            consumeLiquid(Liquids.hydrogen, 45f / 60f);
            consumePower(10f);
            outputsLiquid = true;
            outputLiquid = new LiquidStack(Liquids.water, 100f / 60f);
            boostScale = 10f / 27f;
            maxBoost = 2;
        }};

        densifier = new AttributeCrafter("densifier") {{
            requirements(Category.production, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 40;
            liquidCapacity = 120f;
            craftTime = 120f;
            updateEffect = Fx.smokePuff.wrap(Items.sporePod.color);updateEffectChance = 0.01f;
            envRequired |= Env.spores;
            attribute = Attribute.spores;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water),
                    new DrawCultivator(){{spread = 6f; radius = 4.5f; bubbles = 18; sides = 12;}},
                    new DrawDefault());
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.water, 1f);
            consumePower(60f);
            outputItem = new ItemStack(Items.sporePod, 12);
            boostScale = 50f / 27f;
            maxBoost = 5f;
        }};

        sporeIncubator = new AttributeCrafter("spore-incubator") {{
                requirements(Category.production, with( Items.graphite, 1));
                squareSprite = true;
                size = 3;
                itemCapacity = 80;
                liquidCapacity = 180f;
                craftTime = 60f;
                updateEffect = Fx.smeltsmoke.wrap(Items.sporePod.color, 10f);
                envRequired |= Env.spores;
                attribute = Attribute.spores;
                drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Modliquids.sporeinfestedwater),
                    new DrawCultivator(){{spread = 6f; radius = 4.5f; bubbles = 18; sides = 12;}},
                    new DrawDefault());
                hasPower = true;
                hasLiquids = true;
                consumeLiquid(Modliquids.sporeinfestedwater, 81f / 60f);
                consumeItem(Items.sporePod, 16);
                consumePower(40f);
                outputItem = new ItemStack(Moditems.sporeReceptacle, 6);
                boostScale = 10f / 9f;
                maxBoost = 3f;
            }};

        bioSynthesizer = new AttributeCrafter("bio-synthesizer") {{
            requirements(Category.production, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 100;
            liquidCapacity = 500f;
            craftTime = 120f;
            updateEffect = new MultiEffect(
                    Fx.smeltsmoke.wrap(Items.sporePod.color, 30f),
                    Fx.smokePuff.wrap(Items.sporePod.color, 20f)
            );updateEffectChance = 0.07f;
            drawer = new DrawMulti(new DrawRegion("-bottom"),
                    new DrawLiquidTile(Modliquids.sporeinfestedwater, 3){{padTop = 21f;}},
                    new DrawLiquidTile(Modliquids.sporeinfestedwater, 3){{padLeft = 21f;}},
                    new DrawLiquidTile(Modliquids.sporeinfestedwater, 3){{padRight = 21f;}},
                    new DrawLiquidTile(Modliquids.sporeinfestedwater, 3){{padBottom = 21f;}},
            new DrawLiquidTile(Liquids.arkycite, 3f){{padTop = padLeft = padRight = padBottom = 10f;}},
                    new DrawDefault());
            envRequired |= Env.spores;
            attribute = Attribute.spores;
            hasPower = true;
            hasLiquids = true;
            consumeLiquids(LiquidStack.with(Modliquids.sporeinfestedwater, 200f / 60f, Liquids.arkycite, 90f / 60f));
            consumeItem(Moditems.sporeReceptacle, 15);
            consumePower(200f);
            outputItem = new ItemStack(Moditems.sporeHeart, 1);
            boostScale = 5f / 12f;
            maxBoost = 2f;
        }};

        infestor = new AttributeCrafter("infestor") {{
                requirements(Category.production, with( Items.graphite, 1));
                squareSprite = true;
                size = 3;
                itemCapacity = 30;
                liquidCapacity = 250f;
                craftTime = 90f;
                attribute = Attribute.spores;
                hasPower = true;
                hasLiquids = true;
                drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water),
                        new DrawLiquidTile(Modliquids.sporeinfestedwater), new DrawDefault());
                consumeItem(Items.sporePod, 2);
                consumeLiquid(Liquids.water, 35f / 60f);
                consumePower(4f);
                outputLiquid = new LiquidStack(Modliquids.sporeinfestedwater, 54f / 60f);
                boostScale = 10f / 27f;
                maxBoost = 1f;
            }};

        sporeMultiPress = new GenericCrafter("spore-multi-press"){{
                requirements(Category.crafting, with(Items.graphite, 1));
                liquidCapacity = 350f; itemCapacity = 30;
                craftTime = 40f;
                regionRotated1 = 2;
                outputLiquids = LiquidStack.with(Liquids.oil, 84f / 60, Liquids.hydrogen, 90f / 60);
                liquidOutputDirections = new int[]{1, 3};
                invertFlip = true;
                drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.hydrogen){{padRight = 15f;}},
                        new DrawLiquidTile(Liquids.hydrogen){{padLeft = 15f;}},new DrawLiquidTile(Liquids.oil){{padding = 8f;}},
                        new DrawRegion(), new DrawPistons(){{sinOffset = 10f; lenOffset = -2f; sinMag = 2f;}},
                        new DrawRegion("-top"), new DrawLiquidOutputs());
                group = BlockGroup.liquids;
                rotate = true;
                size = 3;
                health = 1000;
                hasLiquids = true;
                hasPower = true;
                craftEffect = Fx.steam;
                consumeItem(Moditems.sporeReceptacle, 2);
                consumePower(18f);
            }};

        advancedOilExtractor = new AttributeCrafter("advanced-oil-extractor"){{
            requirements(Category.production, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 30;
            liquidCapacity = 400;
            craftTime = 60f;
            updateEffect = Fx.pulverizeMedium;
            envRequired |= Env.groundOil;
            attribute = Attribute.oil;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidRegion(Liquids.water),
                    new DrawCultivator(){{spread = 8f; radius = 6f; bubbles = 25; sides = 20;}},
                    new DrawLiquidRegion(Liquids.oil),
                    new DrawRegion("-spinner",-2f){{spinSprite = true;}},
                    new DrawPistons(){{sinMag = 2.75f; sinOffset = 0f; lenOffset = -1f;}},
                    new DrawDefault(), new DrawRegion("-top")
            );
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.water, 64f / 60f);
            consumeItems(with(Items.sporePod, 3, Items.sand, 5));
            consumePower(45f);
            outputsLiquid = true;
            outputLiquid = new LiquidStack(Liquids.oil, 200f / 60f);
            boostScale = 5f / 56f;
            baseEfficiency = 0f;
            maxBoost = 5f;
        }};

        forceCrucible = new HeatCrafter("force-crucible") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 70;
            liquidCapacity = 40f;
            craftTime = 120f;
            updateEffect = Fx.formsmoke;
            drawer = new DrawMulti( new DrawRegion("-bottom"), new DrawPlasma(){{plasmas = 5;}},new DrawDefault(), new DrawHeatInput());
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.hydrogen, 35f / 60f);
            consumeItems(with(Items.beryllium, 10, Items.tungsten, 9, Items.silicon, 12, Items.graphite, 11));
            consumePower(8f);
            outputItem = new ItemStack(Moditems.forceAlloy, 6);
            heatRequirement = 30f;
            maxEfficiency = 6f;
        }};

        forceRefinery = new HeatCrafter("force-refinery") {{
                requirements(Category.crafting, with( Items.graphite, 1));
                squareSprite = true;
                size = 5;
                itemCapacity = 450;
                liquidCapacity = 130f;
                craftTime = 60f;
                updateEffect = Fx.formsmoke;
                hasPower = true;
                hasLiquids = true;
                consumeLiquid(Liquids.nitrogen, 110f / 60f);
                consumeItems(with(Items.oxide, 32, Items.carbide, 12, Items.phaseFabric, 20));
                consumePower(80f);
            outputItems = ItemStack.with(Moditems.forceAlloy, 8, Moditems.superForceAlloy, 1);
                heatRequirement = 100f;
                maxEfficiency = 7f;
            }};

        thermalOxidizer = new HeatProducer("thermal-oxidizer"){{
                requirements(Category.crafting, with(Items.graphite, 1));
                drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
                size = 2;
                heatOutput = 20f;
                hasLiquids = true;
                liquidCapacity = 20f;
                craftTime = 60f * 8f;
                ambientSound = Sounds.hum;
                consumeItems(with(Items.pyratite, 5));
                consumeLiquid(Liquids.ozone, 5f/60f);
            }};

        blastMultiMixer = new GenericCrafter("blast-multi-mixer") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            liquidCapacity = 70f;
            craftTime = 120f;
            craftEffect = Fx.coalSmeltsmoke;
            updateEffect = Fx.coalSmeltsmoke;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.hydrogen, 30f / 60f);
            consumeItems(with(Items.pyratite, 5, Items.sporePod, 7));
            consumePower(2f);
            outputItem = new ItemStack(Items.blastCompound, 6);
        }};

        powerMixer = new GenericCrafter("power-mixer") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = false;
            size = 4;
            itemCapacity = 70;
            craftTime = 160f;
            updateEffect = Fx.coalSmeltsmoke;
            drawer = new DrawMulti(new DrawRegion("-bottom"),
                    new DrawPistons(){{sinMag = 4f;sinScl = 5f;sides = 1;
                        angleOffset = 90f;horiOffset = 0f;sinOffset = 0f;}},
                    new DrawPistons(){{sinMag = 4f;sinScl = 5f;sides = 1;
                        angleOffset = 90f;horiOffset = -4.5f;sinOffset = 60f * Mathf.degRad;}},
                    new DrawPistons(){{sinMag = 4f;sinScl = 5f;sides = 1;
                        angleOffset = 90f;horiOffset = -9f;sinOffset = 120f * Mathf.degRad;}},
                    new DrawPistons(){{sinMag = 4f;sinScl = 5f;sides = 1;
                        angleOffset = 90f;horiOffset = -13.5f;sinOffset = 180f * Mathf.degRad;}},
                    new DrawPistons(){{sinMag = 4f;sinScl = 5f;sides = 1;
                        angleOffset = 90f;horiOffset = -18f;sinOffset = 240f * Mathf.degRad;}},
                    new DrawPistons(){{sinMag = 4f;sinScl = 5f;sides = 1;
                        angleOffset = 90f;horiOffset = -22.5f;sinOffset = 300f * Mathf.degRad;}},
                    new DrawDefault()
            );
            hasPower = true;
            hasLiquids = false;
            consumeItems(with(Items.blastCompound, 12, Items.pyratite, 18,
                    Items.thorium, 24, Moditems.sporeReceptacle, 36));
            consumePower(6f);
            outputItem = new ItemStack(Moditems.powerCompound, 15);
        }};

        fractionator = new Separator("fractionator"){{
                requirements(Category.crafting, with(Items.graphite, 1));
                results = with(
                        Items.copper, 2,
                        Items.lead, 2,
                        Items.thorium, 2,
                        Items.beryllium, 1,
                        Items.tungsten, 1,
                        Items.sand, 2
                );
                hasPower = true;
                hasLiquids = true;
                drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidRegion(Liquids.slag),
                        new DrawRegion("-middle"),
                        new DrawRegion("-spinner"){{spinSprite = true; rotateSpeed = 2f;}},
                        new DrawDefault(), new DrawRegion("-top"));
                craftTime = 15f;
                size = 4;
                itemCapacity = 30;
                liquidCapacity = 40f;
                consumePower(12f);
                consumeItem(Items.scrap, 3);
                consumeLiquid(Liquids.slag, 15f / 60f);
                consumeLiquid(Liquids.water, 6f / 60f);
            }};
//todo what the fuck is going on
        surgeFoundry = new HeatCrafter("surge-foundry") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 5;
            itemCapacity = 150;
            liquidCapacity = 160f;
            craftTime = 120f;
            updateEffect = Fx.smeltsmoke;
            craftEffect = new RadialEffect(Fx.surgeCruciSmoke, 8, 45f, 9f);
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.9f;
            drawer = new DrawMulti( new DrawRegion("-bottom"), new DrawCircles(){{
                color = Color.valueOf("ffc073").a(0.24f);strokeMax = 2.5f; radius = 10f;amount = 3;}},
                    new DrawLiquidRegion(Liquids.slag), new DrawDefault(), new DrawHeatInput(),
                    new DrawHeatRegion("-vents"){{color.a = 1f;}},
                    new DrawHeatRegion(){{color = Color.valueOf("ff6060ff");}},
                    new DrawFlame(){{flameColor = Color.valueOf("dea471"); flameRadius = 3.5f;
                        lightRadius = 70;}});
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.slag, 1f);
            consumeItems(with(Items.copper, 10, Items.lead, 12, Items.titanium, 9, Items.silicon, 21));
            consumePower(12f);
            outputItem = new ItemStack(Items.surgeAlloy, 6);
            heatRequirement = 45f;
            maxEfficiency = 6f;
        }};

        surgeRefinery = new HeatCrafter("surge-refinery") {{
                requirements(Category.crafting, with( Items.graphite, 1));
                squareSprite = true;
                size = 6;
                itemCapacity = 500;
                liquidCapacity = 1800f;
                craftTime = 90f;
                updateEffect = Fx.smeltsmoke;
                ambientSound = Sounds.smelter;
                ambientSoundVolume = 0.9f;
                hasPower = true;
                hasLiquids = true;
                consumeLiquid(Modliquids.heavySlag, 40f / 60f);
                consumeItems(with(Moditems.bronze, 6, Items.metaglass, 10, Items.plastanium, 28, Items.phaseFabric, 30));
                consumePower(120f);
                outputItems = ItemStack.with(Items.surgeAlloy, 21, Moditems.superSurgeAlloy, 1);
                heatRequirement = 100f;
                maxEfficiency = 7f;
            }};

        supercooler = new GenericCrafter("supercooler"){{
            requirements(Category.crafting, with( Items.graphite, 1));
            size = 3;
            itemCapacity = 50;
            liquidCapacity = 200f;
            craftTime = 120;
            hasPower = true;
            hasLiquids = true;
            squareSprite = false;
            drawer = new DrawMulti(new DrawRegion("-bottom"),
                    new DrawLiquidRegion(Modliquids.supercooledfluid), new DrawDefault());
            consumeItems(with(Items.plastanium, 15)); //Al4Si3O8
            consumeLiquid(Liquids.cryofluid, 100f / 60f);
            consumeLiquid(Liquids.cyanogen, 25f / 60f);
            consumePower(3f);
            outputLiquid = new LiquidStack(Modliquids.supercooledfluid, 20f/ 60f);
        }};

        compoundCrucible = new HeatCrafter("compound-crucible") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 120;
            craftTime = 400f;
            drawer = new DrawMulti(new DrawRegion("-bottom"),
                    new DrawArcSmeltModified(),
                    new DrawDefault(), new DrawHeatInput());
            hasPower = true;
            hasLiquids = false;
            consumeItems(with(Items.phaseFabric, 20, Items.plastanium, 25, Items.oxide, 30, Items.carbide, 25));
            consumePower(10f);
            outputItem = new ItemStack(Moditems.compoundAlloy, 10);
            heatRequirement = 32f;
            maxEfficiency = 6f;
        }};

        phaseSuperHeater = new HeatProducer("phase-super-heater"){{
            requirements(Category.crafting, with(Items.graphite, 1));
            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
            size = 3;
            heatOutput = 100f;
            hasLiquids = true;
            liquidCapacity = 20f;
            craftTime = 60f * 6f;
            ambientSound = Sounds.hum;
            consumeItems(with(Items.phaseFabric, 2, Items.silicon, 3));
            consumeLiquid(Liquids.ozone, 10f/60f);
        }};

        omegalloyCrucible = new GenericCrafter("omegalloy-crucible"){{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 6;
            itemCapacity = 90;
            liquidCapacity = 400f;
            craftTime = 180f;
            drawer = new DrawMulti( new DrawDefault(), new DrawFlame(){{flameRadius = 12;
                lightRadius = 180;flameRadiusIn = 4;flameColor = Color.valueOf("a3e3ff");}});
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.water, 120f / 60f);
            consumeLiquid(Liquids.arkycite, 170f / 60f);
            consumeLiquid(Liquids.oil, 145f / 60f);
            consumeLiquid(Liquids.slag, 95f / 60f);
            consumeItems(with(Items.copper, 28, Items.lead, 27, Items.sand, 25,
                    Items.titanium, 24, Items.graphite, 27, Items.thorium, 22,
                    Items.beryllium, 28, Items.tungsten, 25));
            consumePower(36f);
            outputItem = new ItemStack(Moditems.omegalloy, 18);
        }};

        quasiconstructor = new GenericCrafter("quasi-constructor"){{
                requirements(Category.crafting, with( Items.graphite, 1));
                squareSprite = true;
                size = 4;
                itemCapacity = 50;
                liquidCapacity = 80f;
                craftTime = 60f;
                drawer = new DrawMulti(new DrawRegion("-bottom"),new DrawParticles(){{
                    color = Color.valueOf("22ab39");alpha = 0.2f;particleSize = 4f;particles = 60;particleRad = 10f;
                    fadeMargin = 1f;particleLife = 80f;blending = Blending.additive;}},new DrawDefault());
                hasPower = true;
                hasLiquids = true;
                hasItems = true;
                consumeLiquid(Liquids.hydrogen,50f / 60f);
                consumeLiquid(Modliquids.supercooledfluid, 30f / 60f);
                consumeItems(with(Items.phaseFabric, 10));
                consumePower(16f);
                outputItem = new ItemStack(Moditems.quantum, 3);
            }};

        ultralloyCrucible = new HeatCrafter("ultralloy-crucible") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 7;
            itemCapacity = 1000;
            liquidCapacity = 3000f;
            craftTime = 360f;
            craftEffect = new MultiEffect(
                    Fx.dynamicSpikes.wrap(Moditems.ultralloy.color, 90f),
                    Fx.dynamicWave.wrap(Moditems.ultralloy.color, 120f),
                    Fx.shockwave.wrap(Moditems.ultralloy.color, 60f)
            );
            drawer = new DrawMulti(new DrawRegion("-bottom"),
                    new DrawParticles(){{color = Color.valueOf("58cedb");
                        alpha = 0.3f;particleSize = 6f;particles = 70;particleRad = 9f;
                        fadeMargin = 1f;particleLife = 80f;blending = Blending.additive;}},
                    new DrawRegion("-top"){{spinSprite = true; rotateSpeed = 0.625f;}},
                    new DrawRegion("-middle"){{spinSprite = true; rotateSpeed = 0.46875f;}},
                    new DrawDefault(), new DrawHeatInput());
            hasPower = true;
            hasLiquids = true;
            consumeItems(with(Items.surgeAlloy, 90, Moditems.forceAlloy, 30, Moditems.powerCompound, 15, Moditems.compoundAlloy, 45));
            consumeLiquid(Liquids.cryofluid, 150f / 60f);
            consumePower(144f);
            outputItem = new ItemStack(Moditems.ultralloy, 10);
            heatRequirement = 130f;
            maxEfficiency = 7f;
        }};

        carborundumCrucible = new GenericCrafter("carborundum-crucible"){{
                requirements(Category.crafting, with(Items.graphite, 1));
                squareSprite = false;
                craftEffect = new MultiEffect(
                        Fx.smokePuff.wrap(Color.white,80f),
                        Fx.smokePuff.wrap(Color.white,60f),
                        Fx.smokePuff.wrap(Color.white,40f),
                        Fx.smokeCloud.wrap(Color.gray, 60f),
                        Fx.smokeCloud.wrap(Color.gray, 45f),
                        Fx.smokeCloud.wrap(Color.gray, 30f)
                ).wrap(Color.gray,45f);
                outputItem = new ItemStack(Moditems.carborundum, 90);
                itemCapacity = 120;
                craftTime = 240f;
                size = 5;
                hasPower = true;
                hasLiquids = false;
                ambientSound = Sounds.smelter;
                ambientSoundVolume = 0.07f;
                isMultiblock();
                consumeItems(with(Items.carbide, 152, Moditems.diamond, 72, Items.silicon, 200));
                consumePower(21f);
            }};

        mythratiteMixer = new GenericCrafter("mythratite-mixer") {{
                requirements(Category.crafting, with( Items.graphite, 1));
                squareSprite = true;
                size = 3;
                itemCapacity = 30;
                craftTime = 120f;
                craftEffect = Fx.smeltsmoke;
                updateEffect= Fx.smeltsmoke;
                hasPower = true;
                hasLiquids = false;
                consumeItems(with(Items.titanium, 7, Items.tungsten, 5, Items.lead, 8));
                consumePower(3f);
                outputItem = new ItemStack(Moditems.mythratite, 5);
            }};

        pneumatiteMixer = new GenericCrafter("pneumatite-mixer") {{
                requirements(Category.crafting, with( Items.graphite, 1));
                squareSprite = true;
                size = 4;
                itemCapacity = 60;
                craftTime = 80f;
                craftEffect = new MultiEffect(Fx.coalSmeltsmoke.wrap(Moditems.pneumatite.color), Fx.smeltsmoke.wrap(Moditems.pneumatite.color));
                hasPower = true;
                hasLiquids = false;
                drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-spinner"){{spinSprite = true; rotateSpeed = -1f;}});
                consumeItems(with(Moditems.mythratite, 10, Items.pyratite, 12, Moditems.genesisMetal, 27));
                consumePower(9f);
                outputItem = new ItemStack(Moditems.pneumatite, 7);
            }};

        bronzeSmelter = new GenericCrafter("bronze-smelter") {{
                requirements(Category.crafting, with( Items.graphite, 1));
                squareSprite = true;
                size = 3;
                itemCapacity = 30;
                craftTime = 40f;
                drawer = new DrawMulti(new DrawRegion("-spinner"){{spinSprite = true; rotateSpeed = -2;}},
                        new DrawDefault());
                hasPower = true;
                hasLiquids = false;
                consumeItems(with(Items.copper, 7, Moditems.tin, 3));
                consumePower(1f);
                outputItem = new ItemStack(Moditems.bronze, 2);
            }};

        superConductorFurnace = new GenericCrafter("super-conductor-furnace") {{
                requirements(Category.crafting, with( Items.graphite, 1));
                squareSprite = false;
                size = 4;
                itemCapacity = 60;
                craftTime = 80f;
                drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawMultiWeave(){{
                    glowColor = new Color(0.7f, 0.7f, 0.7f, 0.8f);}}, new DrawDefault());
                hasPower = true;
                hasLiquids = false;
                consumeItems(with(Moditems.bronze, 20, Moditems.platinum, 6, Moditems.gold, 14));
                consumePower(4f);
                outputItem = new ItemStack(Moditems.superConductiveMetal, 9);
            }};

        hyperalloyCrucible = new GenericCrafter("hyperalloy-crucible") {{
                requirements(Category.crafting, with( Items.graphite, 1));
                squareSprite = true;
                size = 6;
                itemCapacity = 150;
                liquidCapacity = 200f;
                craftTime = 120f;
                hasPower = true;
                hasLiquids = true;
                consumeItems(with(Moditems.pneumatite, 30, Moditems.superConductiveMetal, 60, Moditems.carborundum, 54));
                consumeLiquid(Liquids.cyanogen, 45f / 60f);
                consumePower(100f);
                outputItem = new ItemStack(Moditems.hyperalloy, 15);
            }};

        compactor = new GenericCrafter("compactor") {{
                requirements(Category.crafting, with( Items.graphite, 1));
                squareSprite = true;
                size = 4;
                itemCapacity = 30;
                liquidCapacity = 1000f;
                craftTime = 120f;
                hasPower = true;
                hasLiquids = true;
                consumeItem(Moditems.genesisMetal, 1);
                consumeLiquid(Liquids.slag, 300f / 60f);
                consumePower(2f);
                outputLiquid = new LiquidStack(Modliquids.heavySlag, 1f);
            }};

        fuelSynthesizer = new GenericCrafter("fuel-synthesizer") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 30;
            liquidCapacity = 2000f;
            craftTime = 90f;
            hasPower = true;
            rotate = true;
            invertFlip = true;
            hasLiquids = true;
            hasItems = true;
            group = BlockGroup.liquids;
            consumeItem(Moditems.sporeReceptacle, 4);
            consumeLiquid(Liquids.arkycite, 240f / 60f);
            consumeLiquid(Liquids.nitrogen, 30f / 60f);
            consumeLiquid(Liquids.oil, 90f / 60f);
            consumePower(7f);
            outputLiquids = LiquidStack.with(Modliquids.turboFuel, 30f / 60f, Liquids.ozone, 150f / 60f);
            regionRotated1 = 3;
            liquidOutputDirections = new int[]{1, 3};
            }};

        }}
        //power
        {{
            reinforcedPowerNode = new PowerNode("reinforced-node"){{
                requirements(Category.power, with(Items.graphite, 1));
                maxNodes = 5;laserRange = 8;
            }};

            largeReinforcedPowerNode = new PowerNode("large-reinforced-node"){{
                requirements(Category.power, with(Items.graphite, 1));
                maxNodes = 10; size = 2;laserRange = 19;
            }};

            hugeReinforcedPowerNode = new PowerNode("huge-reinforced-node"){{
                requirements(Category.power, with(Items.graphite, 1));
                maxNodes = 20;size = 3;laserRange = 30;
            }};

            heavyDutyTurbineCondenser = new ThermalGenerator("heavy-duty-turbine-condenser") {{
                requirements(Category.power, with(Items.graphite, 1));
                attribute = Attribute.steam;
                group = BlockGroup.liquids;
                displayEfficiencyScale = 2f / 75f;
                minEfficiency = 3f - 0.001f;
                powerProduction = 2f / 9f;
                displayEfficiency = false;
                generateEffect = Fx.turbinegenerate;
                effectChance = 0.12f;
                size = 5;
                drawer = new DrawMulti(new DrawRegion("-bottom"),
                        new DrawDefault(),
                        new DrawBlurSpin("-rotator", 0.9f * 9f){{blurThresh = 0.025f;}},
                        new DrawRegion("-top")
                );
                ambientSound = Sounds.hum;
                ambientSoundVolume = 0.1f;
                hasLiquids = true;
                //if its already built why is it called a building
                //why the fuck is it is 1500 per float
                outputLiquid = new LiquidStack(Liquids.water, 5f / (60f * 2.5f));
                liquidCapacity = 300f;
                fogRadius = 5;
            }};

            boilerGenerator = new ConsumeGenerator("boiler-generator"){{
                requirements(Category.power, with(Items.graphite, 1));
                powerProduction = 7f;
                itemDuration = 45f;
                itemCapacity = 30;
                liquidCapacity = 600;
                consumeLiquid(Liquids.water, 64f / 60f);
                consumeLiquid(Liquids.oil, 40f / 60f);
                outputLiquid = new LiquidStack(Liquids.arkycite, 5f / 60f);
                hasLiquids = true;
                size = 3;
                generateEffect = Fx.generatespark;
                effectChance = 0.25f;
                generateEffectRange = 7.5f;
                squareSprite = false;
                ambientSound = Sounds.smelter;
                ambientSoundVolume = 0.2f;

                consume(new ConsumeItemFlammable());
                consume(new ConsumeItemExplode());

                drawer = new DrawMulti(
                        new DrawRegion("-bottom"),
                        new DrawLiquidTile(Liquids.water){{padding = 3f;}},
                        new DrawRegion("-turbine2"){{rotateSpeed = 3f;}},
                        new DrawDefault(),
                        new DrawWarmupRegion(),
                        new DrawRegion("-turbine1"){{rotateSpeed = -3f;}},
                        new DrawRegion("-cap")
                );
            }};

            chemicalReactionChamber = new ConsumeGenerator("chemical-reaction-chamber") {{
                    requirements(Category.power, with(Items.graphite, 1));
                    powerProduction = 50f;
                    consumeLiquids(LiquidStack.with(Liquids.ozone, 16f / 60f, Liquids.arkycite, 140f / 60f, Liquids.hydrogen, 9f / 60f));
                    outputLiquid = new LiquidStack(Liquids.water, 10f / 60f);
                    outputsLiquid = true;
                    size = 4;
                    generateEffect = Fx.none;
                    liquidCapacity = 400f;
                    ambientSound = Sounds.smelter;
                    ambientSoundVolume = 0.06f;
                }};

            advancedPyrolysisGenerator = new PowerGeneratorTest("advanced-pyrolysis-generator") {{
                    requirements(Category.power, with(Items.graphite, 1));
                    powerProduction = 150f;
                    consumeLiquids(LiquidStack.with(Liquids.slag, 90f / 60f, Liquids.arkycite, 195f / 60f));
                    consumeItem(Items.titanium, 7);
                    size = 4;
                    liquidCapacity = 600f;
                    itemCapacity = 20;
                    outputLiquids = LiquidStack.with(Liquids.ozone, 12f / 60f,
                            Liquids.cryofluid, 1f, Liquids.hydrogen, 18f / 60f);
                    ambientSound = Sounds.smelter;
                    ambientSoundVolume = 0.06f;
                    researchCostMultiplier = 0.4f;
                rotate = true;
                invertFlip = true;
                hasLiquids = true;
                hasItems = true;
                liquidOutputDirections = new int[]{1, 2, 3};
                regionRotated1 = 1;
                }};

            geothermalGenerator = new ThermalGenerator("geothermal-generator") {{
                    requirements(Category.power, with(Items.graphite, 1));
                    powerProduction = 60f;
                    consumeLiquid(Liquids.cryofluid, 40f / 60f);
                    generateEffect = Fx.redgeneratespark;
                    liquidCapacity = 70f;
                    effectChance = 0.1f;
                    outputsLiquid = true;
                    outputLiquid = new LiquidStack(Liquids.water, 30f / (60f * 9));
                    size = 3;
                    floating = true;
                    ambientSound = Sounds.hum;
                    ambientSoundVolume = 0.06f;
                }};//todo fi thi shi

            rtgCoreGenerator = new ConsumeGenerator("rtg-core-generator"){{
                requirements(Category.power, with(Items.graphite, 1));
                size = 5;
                powerProduction = 48f;
                itemDuration = 60 * 2f;
                itemCapacity = 20;
                envEnabled = Env.any;
                generateEffect = new MultiEffect(Fx.sparkExplosion, Fx.generatespark);

                drawer = new DrawMulti(new DrawDefault(), new DrawWarmupRegion());
                consume(new ConsumeItemRadioactive());
            }};

            TitanReactor = new HeaterReactor("titan-reactor"){{
                requirements(Category.power, with(Items.graphite, 1));
                hasPower = true;
                hasItems = true;
                hasLiquids = true;
                outputsPower = true;
                drawer = new DrawMulti(new DrawRegion("-bottom"),new DrawLiquidTile(Liquids.cyanogen, 5f),
                        new DrawDefault(), new DrawHeatOutput());
                powerProduction = 180f;
                size = 4;
                health = 2000;
                consumeItems(with(Items.thorium,7, Items.phaseFabric, 3));
                consumeLiquid(Liquids.cyanogen, 10f / 60f);
                heatOutput = 200f;
                coolantPower = 1f;
                warmupRate = 0.3f;
            }};

            behemothReactor = new ImpactReactor("behemoth-reactor"){{
                requirements(Category.power, with(Items.graphite, 1));
                size = 5;
                health = 900;
                powerProduction = 750f;
                itemDuration = 210f;
                ambientSound = Sounds.pulse;
                ambientSoundVolume = 0.14f;
                itemCapacity = 30;
                liquidCapacity = 130f;

                consumePower(100f);
                consumeItems(with(Items.blastCompound, 2, Items.pyratite, 5, Items.sporePod, 12));
                consumeLiquid(Liquids.cyanogen, 65f / 60f);
            }};

            chronoReactor = new ImpactReactor("chrono-reactor"){{
                requirements(Category.power, with(Items.graphite, 1));
                size = 6;
                health = 2500;
                powerProduction = 1660f;
                itemDuration = 300f;
                ambientSound = Sounds.pulse;
                ambientSoundVolume = 0.25f;
                itemCapacity = 20;
                liquidCapacity = 100f;

                consumePower(360f);
                consumeItem(Moditems.powerCompound, 3);
                consumeLiquid(Modliquids.supercooledfluid, 50f / 60f);
            }};

            radiativeReactor = new VariableReactor("radiative-reactor"){{
                requirements(Category.power, with(Items.graphite, 1));
                powerProduction = 1920f;
                maxHeat = 3000f;
                //why not
                consumeLiquids(LiquidStack.with(Liquids.cyanogen, 1f,
                        Liquids.cryofluid, 4f, Modliquids.supercooledfluid, 40f / 60f));
                liquidCapacity = 350f;
                explosionMinWarmup = 12f;
                explosionRadius = 120;
                explosionDamage = 27000;
                ambientSound = Sounds.flux;
                ambientSoundVolume = 0.13f;
                size = 7;
            }};

            neoplasiaHyperReactor = new HeaterGenerator("neoplasia-hyper-reactor"){{
                requirements(Category.power, with(Items.graphite, 1));
                size = 7;
                liquidCapacity = 500f;
                outputLiquid = new LiquidStack(Liquids.neoplasm, 70f / 60f);
                explodeOnFull = true;
                heatOutput = 600f;
                warmupRate = 0.3f;
                consumeLiquid(Liquids.arkycite, 280f / 60f);
                consumeLiquid(Liquids.cryofluid, 30f / 60f);
                consumeItems(with(Items.phaseFabric, 5));
                itemDuration = 60f * 3f;
                itemCapacity = 20;
                explosionRadius = 30;
                explosionDamage = 7500;
                explodeEffect = new MultiEffect(Fx.bigShockwave, Fx.dynamicWave, new WrapEffect(Fx.titanSmoke, Liquids.neoplasm.color), Fx.smokeCloud, Fx.neoplasmSplat);
                explodeSound = Sounds.largeExplosion;
                powerProduction = 1000f;
                ambientSound = Sounds.bioLoop;
                ambientSoundVolume = 0.2f;
                explosionPuddles = 180;
                explosionPuddleRange = tilesize * 11f;
                explosionPuddleLiquid = Liquids.neoplasm;
                explosionPuddleAmount = 450f;
                explosionMinWarmup = 0.5f;
            }};

            hyperneoplasiaGenerator = new HeaterGenerator("hyperplasia-generator"){{
                requirements(Category.power, with(Items.graphite, 1));
                size = 6;
                liquidCapacity = 400f;
                outputLiquid = new LiquidStack(Modliquids.hyperplasm, 1f);
                explodeOnFull = true;
                heatOutput = 400f;
                warmupRate = 0.9f;
                consumeLiquid(Liquids.neoplasm, 210f / 60f);
                consumeLiquid(Liquids.slag, 150f / 60f);
                consumeItem(Moditems.quantum, 1);
                itemDuration = 60f * 3f;
                itemCapacity = 10;
                explosionRadius = 10;
                explosionDamage = 2500;
                explodeEffect = new MultiEffect(Fx.bigShockwave, new WrapEffect(Fx.titanSmoke, Liquids.neoplasm.color), Fx.neoplasmSplat);
                explodeSound = Sounds.largeExplosion;
                powerProduction = 300f;
                ambientSound = Sounds.bioLoop;
                ambientSoundVolume = 0.2f;
                explosionPuddles = 20;
                explosionPuddleRange = tilesize * 9f;
                explosionPuddleLiquid = Modliquids.hyperplasm;
                explosionPuddleAmount = 150f;
                explosionMinWarmup = 0.5f;
            }};
        }}
        //core and effect shit idk
        {{
        mendDome = new MendProjector("mend-Dome"){{
                requirements(Category.effect, with(Items.graphite, 1));
                consumePower(8f);
                size = 3;
                reload = 500f;
                range = 200f;
                healPercent = 30f;
                phaseBoost = 56f;
                scaledHealth = 80;
            }};

        overdriveStadium = new OverdriveProjector("overdrive-stadium"){{
            requirements(Category.effect, with(Items.graphite, 1));
            consumePower(30f);
            size = 5;
            liquidCapacity = 300f;
            itemCapacity = 70;
            range = 500f;
            speedBoost = 10f;
            useTime = 150f;
            hasBoost = false;
            consumeItems(with(Moditems.quantum, 2, Moditems.carborundum, 23));
            consumeLiquids(LiquidStack.with(Liquids.nitrogen, 200f / 60f, Modliquids.supercooledfluid, 10f / 60f));
        }};

        constructMonolith = new BuildTurret("constructMonolith"){{
            requirements(Category.effect, with(Items.graphite, 1));
            outlineColor = Pal.darkOutline;
            range = 350f;
            size = 4;
            buildSpeed = 2f;
            consumePower(10f);
            consumeLiquid(Liquids.nitrogen, 8f / 60f);
            consumeItem(Items.silicon, 2);
        }};

        regenDome = new RegenProjector("regen-dome"){{
            requirements(Category.effect, with(Items.graphite, 1));
            size = 4;
            range = 50;
            baseColor = Pal.regen;
            consumePower(5f);
            consumeLiquids(LiquidStack.with(Liquids.hydrogen, 5f / 60f, Liquids.nitrogen, 3f / 60f));
            consumeItem(Items.phaseFabric).boost();
            healPercent = 15f / 60f;
            Color col = Color.valueOf("8ca9e8");
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.hydrogen, 9f / 4f), new DrawDefault(), new DrawGlowRegion(){{
                color = Color.sky;
            }}, new DrawPulseShape(false){{
                layer = Layer.effect;
                color = col;
            }}, new DrawShape(){{
                layer = Layer.effect;
                radius = 3.5f;
                useWarmupRadius = true;
                timeScl = 2f;
                color = col;
            }});
        }};

        blastwaveMonolith = new ShockwaveTower("blastwave-monolith"){{
                requirements(Category.effect, with(Items.graphite, 1));
                size = 4;
                consumeLiquids(LiquidStack.with(Liquids.cyanogen, 10f / 60f));
                consumePower(250f / 60f);
                range = 350f;
                reload = 150f;
                bulletDamage = 240;
            }};

        largeForceProjector = new ForceProjector("large-force-projector"){{
                requirements(Category.effect, with(Items.graphite, 1));
                size = 5;
                phaseRadiusBoost = 225f;
                radius = 200f;
                shieldHealth = 6000f;
                phaseShieldBoost = 2000f;
                cooldownNormal = 5f;
                sides = 8;
                cooldownLiquid = 3.25f;
                cooldownBrokenBase = 0.85f;
                consumeLiquid(Liquids.ozone, 10f / 50f);
                itemConsumer = consumeItem(Items.phaseFabric).boost();
                consumePower(30f);
            }};

        }}
        //turrets
        {{
        flood = new LiquidTurret("flood") {{
                    requirements(Category.turret, with(Items.graphite, 1));
                    ammo(
                            Liquids.water, new LiquidBulletType(Liquids.water) {{
                                lifetime = 56f;
                                speed = 9f;
                                knockback = 3.5f;
                                puddleSize = 18f;
                                orbSize = 6f;
                                drag = 0.005f;
                                ammoMultiplier = 0.9f;
                                statusDuration = 60f * 9f;
                                damage = 0.5f;
                                layer = Layer.bullet - 2f;
                            }},
                            Liquids.slag, new LiquidBulletType(Liquids.slag) {{
                                lifetime = 56f;
                                speed = 9f;
                                knockback = 2.8f;
                                puddleSize = 18f;
                                orbSize = 6f;
                                damage = 10f;
                                drag = 0.005f;
                                ammoMultiplier = 0.9f;
                                statusDuration = 60f * 9f;
                            }},
                            Liquids.cryofluid, new LiquidBulletType(Liquids.cryofluid) {{
                                lifetime = 56f;
                                speed = 9f;
                                knockback = 2.8f;
                                puddleSize = 18f;
                                orbSize = 6f;
                                drag = 0.005f;
                                ammoMultiplier = 0.9f;
                                statusDuration = 60f * 9f;
                                damage = 0.5f;
                            }},
                            Liquids.oil, new LiquidBulletType(Liquids.oil) {{
                                lifetime = 56f;
                                speed = 9f;
                                knockback = 2.8f;
                                puddleSize = 18f;
                                orbSize = 6f;
                                drag = 0.005f;
                                ammoMultiplier = 0.9f;
                                statusDuration = 60f * 9f;
                                damage = 0.5f;
                                layer = Layer.bullet - 2f;
                            }},
                            Modliquids.supercooledfluid, new LiquidBulletType(Modliquids.supercooledfluid) {{
                                lifetime = 56f;
                                speed = 9f;
                                knockback = 3.5f;
                                puddleSize = 18f;
                                orbSize = 6f;
                                drag = 0.005f;
                                ammoMultiplier = 0.9f;
                                statusDuration = 60f * 9f;
                                damage = 2f;
                            }}
                    );
                    size = 4;
                    reload = 3f;
                    shoot.shots = 4;
                    velocityRnd = 0.1f;
                    inaccuracy = 9f;
                    recoil = 1.5f;
                    shootCone = 60f;
                    liquidCapacity = 70f;
                    shootEffect = Fx.shootLiquid;
                    range = 390f;
                    scaledHealth = 250;
                    flags = EnumSet.of(BlockFlag.turret, BlockFlag.extinguisher);
                }};

        unitRepairTurret = new RepairTurretTower("repair-turret") {{
                    requirements(Category.units, with(Items.graphite, 1));
                    size = 3;
                    length = 9f;
                    repairSpeed = 5f;
                    repairRadius = 240f;
                    liquidCapacity = 30f;
                    powerUse = 24f;
                    beamWidth = 1.5f;
                    pulseRadius = 7.5f;
                    consumeItem(Items.silicon, 1);
                    consumeLiquid(Liquids.ozone, 15f / 60f);
                    coolantMultiplier = 2.5f;
                    acceptCoolant = true;
                }};

        horde = new ItemTurret("horde") {{
                    requirements(Category.turret, with(Items.graphite, 1));
                    ammo(
                            Items.blastCompound, new MissileBulletType(7.5f, 20) {{
                                width = 16f;
                                height = 16f;
                                shrinkY = 0f;
                                splashDamageRadius = 60f;
                                splashDamage = 60f * 3f;
                                ammoMultiplier = 10f;
                                hitEffect = Fx.blastExplosion;
                                despawnEffect = Fx.blastExplosion;

                                status = StatusEffects.blasted;
                                statusDuration = 120f;
                            }},
                            Items.pyratite, new MissileBulletType(7.5f, 25) {{
                                frontColor = Pal.lightishOrange;
                                backColor = Pal.lightOrange;
                                width = 14f;
                                height = 16f;
                                shrinkY = 0f;
                                homingPower = 0.16f;
                                splashDamageRadius = 40f;
                                splashDamage = 60f * 3f;
                                makeFire = true;
                                ammoMultiplier = 10f;
                                hitEffect = Fx.blastExplosion;
                                status = StatusEffects.burning;
                            }},
                            Items.surgeAlloy, new MissileBulletType(7.5f, 36) {{
                                width = 16f;
                                height = 16f;
                                shrinkY = 0f;
                                splashDamageRadius = 50f;
                                splashDamage = 50f * 3.5f;
                                hitEffect = Fx.blastExplosion;
                                despawnEffect = Fx.blastExplosion;
                                ammoMultiplier = 8f;
                                lightningDamage = 20;
                                lightning = 4;
                                lightningLength = 20;
                            }}
                    );

                    shoot = new ShootBarrel() {{
                        barrels = new float[]{
                                -8, -1.25f, 0,
                                0, 0, 0,
                                8, -1.25f, 0,
                                -8, -2.5f, 0,
                                0, -1.25f, 0,
                                8, -2.5f, 0
                        };
                        shots = 6;
                        shotDelay = 7f;
                    }};

                    shootY = 4.5f;
                    reload = 60f;
                    inaccuracy = 30f;
                    liquidCapacity = 100f;
                    range = 360f;
                    consumeAmmoOnce = false;
                    size = 3;
                    scaledHealth = 600;
                    shootSound = Sounds.missile;
                    envEnabled |= Env.space;

                    limitRange(10f);
                    coolant = consumeCoolant(0.6f);
                }};

        gonzales = new ItemTurret("gonzales"){{
                requirements(Category.turret, with(Items.graphite, 1));
                ammo(
                        Moditems.omegalloy, new BasicBulletType(25f, 1000){{
                            hitSize = 25f;width = 17f;height = 41f;shootEffect = Fx.shootBig;ammoMultiplier = 21;
                            reloadMultiplier = 9f;knockback = 5f;pierceCap = 25;pierceBuilding = true;
                            splashDamage = 125f;splashDamageRadius = 225f;
                        }}
                );
                reload = 35f;
                recoilTime = reload * 5f;
                coolantMultiplier = 2f;
                ammoUseEffect = Fx.casing3Double.wrap(Moditems.omegalloy.color, 30f);
                range = 400f;
                inaccuracy = 0f;
                recoil = 5f;
                shoot = new ShootAlternate(16f);
                shake = 11f;
                size = 8;
                shootCone = 300f;
                shootSound = Sounds.shootBig;

                scaledHealth = 900;
                coolant = consumeCoolant(10f);

                limitRange();
            }};
        }}
        //walls
        {{
            int wallHealthMultiplier = 9;

            hyperalloyWall = new Wall("hyperalloy-wall"){{
                requirements(Category.defense, with(Moditems.hyperalloy, 9));
                health = 360 * wallHealthMultiplier;
                size = 2;lightningColor = Moditems.hyperalloy.color;
                lightningChance = 0.25f;lightningDamage = 70;lightningLength = 25;
            }};

            hyperalloyWallLarge = new Wall("hyperalloy-wall-large"){{
                requirements(Category.defense, ItemStack.mult(hyperalloyWall.requirements, 9));
                health = 360 * 6 * wallHealthMultiplier;
                size = 3;lightningColor = Moditems.hyperalloy.color;
                lightningChance = 0.25f;lightningDamage = 70;lightningLength = 25;
            }};

            largeShieldedWall = new ShieldWall("large-shielded-wall"){{
                requirements(Category.defense, ItemStack.with(Items.graphite, 1));
                consumePower(35f / 60f);

                outputsPower = false;
                hasPower = true;
                consumesPower = true;
                conductivePower = true;

                chanceDeflect = 25f;
                shieldHealth = 4500f;
                health = 300 * wallHealthMultiplier * 9;
                armor = 50f;
                size = 3;
            }};
        }}
    }
}
