package complicatedustry.scripts;

import arc.graphics.Color;
import arc.math.Interp;
import arc.math.Mathf;
import arc.struct.EnumSet;
import complicatedustry.scripts.extensions.HeaterReactor;
import complicatedustry.scripts.extensions.arcsmeltmodifier;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.entities.bullet.LiquidBulletType;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.RadialEffect;
import mindustry.entities.effect.WrapEffect;
import mindustry.gen.Sounds;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.LiquidTurret;
import mindustry.world.blocks.distribution.Duct;
import mindustry.world.blocks.distribution.StackConveyor;
import mindustry.world.blocks.distribution.StackRouter;
import mindustry.world.blocks.heat.HeatConductor;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.units.UnitCargoLoader;
import mindustry.world.blocks.units.UnitCargoUnloadPoint;
import mindustry.world.draw.*;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BlockFlag;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.Env;

import static mindustry.Vars.tilesize;
import static mindustry.type.ItemStack.*;


public class modblocks {

    public static Block
    //stuff here
    carborundumCrucible,mergedBlock1,mergedBlock2,mergedBlock3,mergedBlock4,
    //drills and shit
    hugePlasmaBore,innovatoryQuakeDrill,craterDrill,mountainCrusher,
    //crafters
    smallHeatRedirector, SmallHeatRouter, atmosphericCondenser, surgeRouter, reinforcedDuct, platedConveyor,
    pyratiteMultiMixer, siliconFoundry, diamondMegaPress, forge, waterConcentrator, advancedOxidationChamber,
    densifier, carbideFoundry, advancedOilExtractor, forceCrucible, plastaniumMultiCompressor,
    cyanogenCatalysis, phaseCatalysis,surgeFoundry, cryofluidMultiMixer, flood,
    blastMultiMixer,fractionator, powerMixer, supercooler, compoundCrucible, phaseSuperHeater, quasiconstructor,
    omegalloyCrucible, ultralloyCrucible, reinforcedConveyor, thermalOxidizer, unitPayloadLoader,
    unitPayloadUnloadPoint, overdriveStadium, constructMonolith, regenDome, blastwaveMonolith, mendDome,
    //power
    boilerGenerator, triGenerationReactor, chemicalReactionChamber, advancedPyrolysisGenerator, geothermalGenerator,
    heavyDutyTurbineCondenser, radiativeReactor, thoriumTitanReactor,neoplasiaHyperReactor;
    //todo unit constructors?

    public static void load() {
        //testing
        {{
        mergedBlock1 = new HeatProducer("idkwhattonamethisyet"){{
            requirements(Category.crafting, with(Items.graphite, 1));
            size = 5;
            researchCostMultiplier = 1.2f;
            hasItems = true;
            hasLiquids = true;
            craftTime = 210f;
            invertFlip = false;
            itemCapacity = 70;
            consumePower(1f);
            consumeLiquid(Liquids.ozone, 70f / 60f);
            consumeItems(ItemStack.with(Items.silicon, 26, Items.beryllium, 7, Items.thorium, 9));
            outputItems = ItemStack.with(Items.oxide, 12, Items.phaseFabric, 20);
            heatOutput = 60;
        }};

        mergedBlock2 = new HeatCrafter("dual-coolant-synthesizer"){{
            requirements(Category.crafting, with(Items.graphite, 1));
            size = 5;
            researchCostMultiplier = 1.2f;
            craftTime = 165f;
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(Liquids.cryofluid, 3f),
                    new DrawBubbles(Color.valueOf("7693e3")){{sides = 36;
                        recurrence = 10f;spread = 10;radius = 5f;amount = 65;}},
                    new DrawLiquidTile(Liquids.cyanogen),
                    new DrawParticles(){{
                        color = Color.valueOf("89e8b6");alpha = 0.4f;particleSize = 3f;
                        particles = 30;particleRad = 10f;particleLife = 240f;reverse = true;
                        particleSizeInterp = Interp.one;
                    }},
                    new DrawRegion(),
                    new DrawLiquidOutputs(),
                    new DrawHeatInput()
            );
            rotate = true;
            invertFlip = true;
            hasLiquids = true;
            hasItems = true;
            itemCapacity = 40;
            liquidCapacity = 50f;
            consumeItems(ItemStack.with(Items.titanium, 5, Items.graphite, 3));
            consumeLiquids(LiquidStack.with(Liquids.hydrogen, 30f / 60f,
                    Liquids.ozone, 20f / 60f, Liquids.arkycite, 180f / 60f));
            consumePower(1f);
            regionRotated1 = 3;
            outputLiquids = LiquidStack.with(Liquids.cryofluid, 1f, Liquids.cyanogen, 15f / 60f);
            liquidOutputDirections = new int[]{1, 3};
            heatRequirement = 30;
            maxEfficiency = 5;
        }};

        mergedBlock3 = new HeatCrafter("diamond-and-nitro"){{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 50;
            liquidCapacity = 500f;
            craftTime = 240f;
            hasPower = true;
            hasLiquids = true;
            consumeItems(with(Items.graphite, 25));
            consumePower(2f / 3f);
            outputItem = new ItemStack(moditems.diamond, 11);
            outputLiquid = new LiquidStack(Liquids.nitrogen, 25f / 60f);
            heatRequirement = 60f;
            maxEfficiency = 10f;
        }};

        mergedBlock4 = new HeatCrafter("plastand-carbide"){{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 50;
            liquidCapacity = 1500f;
            craftTime = 180f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.oil, 300f / 60f);
            consumeItems(with(Items.titanium, 24, Items.tungsten, 9, Items.graphite, 11));
            consumePower(2f / 3f);
            outputItems = ItemStack.with(Items.plastanium, 15, Items.carbide, 7);
            heatRequirement = 40f;
        }};
        }}
        //drillers
        {{
                hugePlasmaBore = new BeamDrill("huge-plasma-bore") {{
                    requirements(Category.production, with(Items.graphite, 1));
                    consumePower(1.8f);
                    drillTime = 40f;
                    tier = 7;
                    size = 4;
                    range = 10;
                    fogRadius = 6;
                    laserWidth = 1f;
                    itemCapacity = 30;
                    consumeLiquid(Liquids.nitrogen, 4f / 60f);
                    consumeLiquid(Liquids.cryofluid, 10f / 60f).boost();
                }};

                craterDrill = new BurstDrill("crater-drill") {{
                    requirements(Category.production, with(Items.graphite, 1));
                    drillTime = 60f * 6f;
                    size = 6;
                    hasPower = true;
                    tier = 10;
                    //TODO better effect
                    //isnt this already good?
                    drillEffect = new MultiEffect(
                            Fx.mineImpact,
                            Fx.drillSteam,
                            Fx.dynamicSpikes.wrap(Liquids.nitrogen.color, 30f),
                            Fx.mineImpactWave.wrap(Liquids.nitrogen.color, 45f)
                    );
                    shake = 5f;
                    itemCapacity = 70;
                    arrowOffset = 2f;
                    arrowSpacing = 5f;
                    arrows = 2;
                    glowColor.a = 0.6f;
                    fogRadius = 6;
                    //TODO different requirements
                    consumePower(16f);
                    consumeLiquids(LiquidStack.with(Liquids.nitrogen, 9f / 60f));
                }};

                innovatoryQuakeDrill = new Drill("innovatory-quake-drill") {{
                    requirements(Category.production, with(Items.graphite, 1));
                    drillTime = 200;
                    size = 4;
                    drawRim = true;
                    hasPower = true;
                    tier = 5;
                    updateEffect = Fx.pulverizeRed;
                    updateEffectChance = 0.03f;
                    drillEffect = Fx.mineHuge;
                    rotateSpeed = 8f;
                    warmupSpeed = 0.001f;
                    itemCapacity = 50;
                    //more than the laser drill
                    //how much is the laser drill for this to be more
                    liquidBoostIntensity = 2f;
                    consumePower(13f);
                    consumeLiquid(Liquids.cryofluid, 0.5f).boost();
                }};

                mountainCrusher = new WallCrafter("mountain-crusher") {{
                    requirements(Category.production, with(Items.graphite, 1));
                    consumePower(40f / 60f);
                    drillTime = 26f;
                    size = 3;
                    attribute = Attribute.sand;
                    output = Items.sand;
                    fogRadius = 3;
                    ambientSound = Sounds.drill;
                    ambientSoundVolume = 0.04f;
                }};
            }}
        //payload transporter
        {{
            reinforcedConveyor = new StackConveyor("reinforced-conveyor"){{
                requirements(Category.distribution, with(Items.copper, 3, Items.beryllium, 1));
                health = 90;
                speed = 4f / 60f;
                itemCapacity = 5;
            }};

            reinforcedDuct = new Duct("reinforced-duct"){{
                requirements(Category.distribution, with(Items.beryllium, 3, Items.copper, 1));
                health = 140;
                speed = 2f;
                armored = true;
            }};

            platedConveyor = new StackConveyor("armored-surge-conveyor"){{
                requirements(Category.distribution, with(Items.surgeAlloy, 5, moditems.forceAlloy, 2, Items.carbide, 3));
                health = 90;
                speed = 5f / 60f;
                outputRouter = false;
                itemCapacity = 14;
                hasPower = true;
                underBullets = true;
                consumesPower = true;
                conductivePower = true;
                baseEfficiency = 1.5f;
                consumePower(3f/60f);
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
                unitCapModifier = 2;
                consumePower(32f / 60f);
                consumeLiquids(LiquidStack.with(Liquids.nitrogen, 35f / 60f, Liquids.arkycite, 10f / 60f));
                itemCapacity = 1000;
            }};

            unitPayloadUnloadPoint = new UnitCargoUnloadPoint("unit-payload-unload-point"){{
                requirements(Category.distribution, with(Items.graphite, 1));
                size = 3;
                itemCapacity = 300;
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
        pyratiteMultiMixer = new GenericCrafter("pyratite-multi-mixer") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            craftTime = 120f;
            craftEffect = Fx.coalSmeltsmoke;
            updateEffect= Fx.coalSmeltsmoke;
            hasPower = true;
            hasLiquids = false;
            consumeItems(with(Items.sand, 8, Items.graphite, 2, Items.lead, 5));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.pyratite, 4);
        }};

        siliconFoundry = new GenericCrafter("silicon-foundry") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            craftEffect = Fx.smeltsmoke;
            updateEffect = Fx.smeltsmoke;
            drawer = new DrawMulti( new DrawRegion("-bottom"), new DrawArcSmelt(),
                    new DrawDefault(),new DrawFlame(Color.valueOf("ffef99")),
                    new DrawRegion("-center"));
            size = 4;
            itemCapacity = 120;
            liquidCapacity = 35f;
            craftTime = 640f / 9f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.oil, 10f / 60f);
            consumeItems(with(Items.sand, 18, Items.graphite, 8, Items.pyratite, 4));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.silicon, 32);
        }};

        diamondMegaPress = new HeatCrafter("diamond-mega-press"){{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            craftTime = 120f;
            craftEffect = Fx.smeltsmoke;
            craftEffect = new RadialEffect(Fx.coalSmeltsmoke, 4, 90,0){{rotationOffset = 45f;}};
            drawer = new DrawMulti(
                    new DrawRegion("-spinner", 2f){{
                        spinSprite = true;}},
                    new DrawDefault(), new DrawHeatInput());
            hasPower = true;
            hasLiquids = false;
            consumeItems(with(Items.graphite, 7));
            consumePower(2f / 3f);
            outputItem = new ItemStack(moditems.diamond, 3);
            heatRequirement = 25f;
            maxEfficiency = 8f;
        }};

        atmosphericCondenser = new HeatCrafter("atmospheric-condenser"){{
            requirements(Category.production, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            liquidCapacity = 90f;
            craftTime = 60f;
            drawer = new DrawMulti(new DrawRegion("-bottom"),
                    new DrawLiquidTile(Liquids.nitrogen, 4.1f),
                    new DrawDefault(), new DrawHeatInput(),
                    new DrawParticles(){{
                        color = Color.valueOf("d4f0ff");
                        alpha = 0.8f;
                        particleSize = 3f;
                        particles = 30;
                        particleRad = 15f;
                        particleLife = 120f;
                    }});
            hasPower = true;
            hasLiquids = false;
            consumePower(2f / 3f);
            outputLiquid = new LiquidStack(Liquids.nitrogen, 15f / 60f);
            heatRequirement = 20f;
            ambientSound = Sounds.extractLoop;
            ambientSoundVolume = 0.06f;
            maxEfficiency = 5f;
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
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.metaglass, 4);
        }};

        waterConcentrator = new GenericCrafter("water-concentrator") {{
            requirements(Category.production, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            liquidCapacity = 50f;
            craftTime =19.2f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.ozone, 6f / 60f);
            consumeLiquid(Liquids.hydrogen, 9f / 60f);
            consumePower(2f / 3f);
            outputsLiquid = true;
            outputLiquid = new LiquidStack(Liquids.water, 15f / 60f);
        }};

        advancedOxidationChamber = new HeatProducer("advanced-oxidation-chamber") {{
            requirements(Category.production, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 30;
            liquidCapacity = 20f;
            craftTime = 150f;
            updateEffect = Fx.smeltsmoke;
            rotateDraw = false;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidRegion(), new DrawDefault(), new DrawHeatOutput());
            ambientSound = Sounds.extractLoop;
            ambientSoundVolume = 0.08f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.ozone, 12f / 60f);
            consumeItems(with(Items.copper, 2, Items.beryllium, 3));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.oxide, 7);
            heatOutput = 20;
        }};

        densifier = new AttributeCrafter("densifier") {{
            requirements(Category.production, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            liquidCapacity = 90f;
            craftTime = 120f;
            envRequired |= Env.spores;
            attribute = Attribute.spores;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water),
                    new DrawCultivator(){{spread = 6f; radius = 4.5f; bubbles = 18; sides = 12;}},
                    new DrawDefault());
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.water, 36f / 60f);
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.sporePod, 3);
            maxBoost = 3f;
        }};

        advancedOilExtractor = new AttributeCrafter("advanced-oil-extractor"){{
            requirements(Category.production, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 30;
            liquidCapacity = 250;
            craftTime = 12f;
            updateEffect = Fx.pulverizeMedium;
            envRequired |= Env.groundOil;
            attribute = Attribute.oil;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidRegion(Liquids.water),
                    new DrawLiquidRegion(Liquids.oil),
                    new DrawCultivator(){{spread = 8f; radius = 6f; bubbles = 25; sides = 20;}},
                    new DrawRegion("-spinner",-2f){{spinSprite = true;}},
                    new DrawPistons(){{sinMag = 2.75f; sinOffset = 0f; lenOffset = -1f;}},
                    new DrawDefault(), new DrawRegion("-top")
            );
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.water, 36f / 60f);
            consumeItems(with(Items.sporePod, 2, Items.sand, 3));
            consumePower(2f / 3f);
            outputsLiquid = true;
            outputLiquid = new LiquidStack(Liquids.oil, 150f / 60f);
            boostScale = 1f / 12f;
            baseEfficiency = 0f;
            maxBoost = 2f;
        }};

        forceCrucible = new HeatCrafter("force-crucible") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 50;
            liquidCapacity= 40f;
            craftTime = 120f;
            updateEffect = Fx.formsmoke;
            drawer = new DrawMulti( new DrawRegion("-bottom"), new DrawPlasma(),new DrawDefault(), new DrawHeatInput());
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.hydrogen, 20f / 60f);
            consumeItems(with(Items.beryllium, 7, Items.tungsten, 6, Items.silicon, 8, Items.graphite, 7));
            consumePower(2f / 3f);
            outputItem = new ItemStack(moditems.forceAlloy, 2);
            heatRequirement = 20f;
        }};

            carbideFoundry = new HeatCrafter("carbide-foundry") {{
                requirements(Category.crafting, with( Items.graphite, 1));
                squareSprite = true;
                size = 4;
                itemCapacity = 30;
                liquidCapacity = 30f;
                craftTime = 150f;
                updateEffect = Fx.pulverizeMedium;
                drawer = new DrawMulti(  new DrawRegion("-bottom"), new DrawCrucibleFlame(){{
                    particles = 40; particleRad = 11f; particleSize = 3.5f; fadeMargin = 0.6f; rotateScl = 2f;}},
                        new DrawDefault(), new DrawHeatInput());
                ambientSound = Sounds.smelter;
                ambientSoundVolume = 0.09f;
                hasPower = true;
                hasLiquids = true;
                consumeLiquid(Liquids.water, 15f / 60f);
                consumeItems(with(Items.tungsten, 6, Items.graphite, 8));
                consumePower(2f / 3f);
                outputItem = new ItemStack(Items.carbide, 5);
                heatRequirement = 30f;
            }};

            blastMultiMixer = new GenericCrafter("blast-multi-mixer") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            liquidCapacity = 40f;
            craftTime = 120f;
            craftEffect = Fx.coalSmeltsmoke;
            updateEffect = Fx.coalSmeltsmoke;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.hydrogen, 15f / 60f);
            consumeItems(with(Items.pyratite, 3, Items.sporePod, 5));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.blastCompound, 4);
        }};

        plastaniumMultiCompressor = new GenericCrafter("plastanium-multi-compressor") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            liquidCapacity = 70f;
            craftTime = 40f;
            craftEffect = Fx.formsmoke;
            updateEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.oil, 35f / 60f);
            consumeItems(with(Items.titanium, 3));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.plastanium, 2);
        }};

        cyanogenCatalysis = new HeatCrafter("cyanogen-catalysis") {{
            requirements(Category.production, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 30;
            liquidCapacity = 200f;
            craftTime = 150f;
            drawer = new DrawMulti(new DrawRegion("-bottom"),
                    new DrawLiquidTile(Liquids.cyanogen),
                    new DrawParticles(){{
                        color = Color.valueOf("89e8b6");alpha = 0.4f;particleSize = 3f;
                        particles = 30;particleRad = 10f;particleLife = 240f;reverse = true;
                        particleSizeInterp = Interp.one;
                    }}, new DrawDefault(), new DrawHeatInput(),
                    new DrawHeatRegion("-heat-top")
            );
            maxEfficiency = 5f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.arkycite, 130f / 60f);
            consumeItems(with(Items.graphite, 2, Items.lead, 1));
            consumePower(2f / 3f);
            outputsLiquid = true;
            outputLiquid = new LiquidStack(Liquids.cyanogen, 7f / 60f);
            heatRequirement = 12f;
        }};

        powerMixer = new GenericCrafter("power-mixer") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = false;
            size = 4;
            itemCapacity = 40;
            craftTime = 96f;
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
            consumeItems(with(Items.blastCompound, 7, Items.pyratite, 11, Items.thorium, 13));
            consumePower(2f / 3f);
            outputItem = new ItemStack(moditems.powerAlloy, 5);
        }};
//todo what the fuck is going on
        phaseCatalysis = new HeatCrafter("phase-catalysis") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 40;
            liquidCapacity = 10f;
            craftTime = 165f;
            craftEffect = Fx.smeltsmoke;
            updateEffect = Fx.smeltsmoke;
            ambientSound = Sounds.techloop;
            ambientSoundVolume = 0.02f;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawSpikes(){{
                radius = 8.5f; length = 6.7f; color = Color.valueOf("ffd59e"); stroke = 1.5f;
                layers = 4; amount = 10; rotateSpeed = 0.5f; layerSpeed = -0.9f;}},
                    new DrawMultiWeave(){{glowColor = new Color(1f, 0.4f, 0.4f, 0.8f);}},
                    new DrawDefault(), new DrawHeatInput(), new DrawHeatRegion("-vents"){{
                color = new Color(1f, 0.4f, 0.3f, 1f);}}
            );
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.ozone, 9f / 60f);
            consumeItems(with(Items.thorium, 6,Items.silicon, 16));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.phaseFabric, 11);
            heatRequirement = 32f;
        }};

        surgeFoundry = new HeatCrafter("surge-foundry") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 5;
            itemCapacity = 70;
            liquidCapacity = 160f;
            craftTime = 120f;
            craftEffect = Fx.smeltsmoke;
            craftEffect = new RadialEffect(Fx.surgeCruciSmoke, 8, 45f, 9f);
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.9f;
            drawer = new DrawMulti( new DrawRegion("-bottom"), new DrawCircles(){{
                color = Color.valueOf("ffc073").a(0.24f);strokeMax = 2.5f; radius = 10f;amount = 3;}},
                    new DrawLiquidRegion(Liquids.slag), new DrawDefault(), new DrawHeatInput(),
                    new DrawHeatRegion("-vents"){{color.a = 1f;}},
                    new DrawHeatRegion(){{color = Color.valueOf("ff6060ff");}}, new DrawFlame() );
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.slag, 50f / 60f);
            consumeItems(with(Items.copper, 7, Items.lead, 8, Items.titanium, 6, Items.silicon, 14));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.surgeAlloy, 4);
            heatRequirement = 30f;
            maxEfficiency = 5f;
        }};

        cryofluidMultiMixer = new GenericCrafter("cryofluid-multi-mixer") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            liquidCapacity = 75f;
            craftTime =180f;
            craftEffect = Fx.steam;
            updateEffect = Fx.steam;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidRegion(Liquids.hydrogen),
                    new DrawLiquidRegion(Liquids.ozone), new DrawLiquidRegion(Liquids.cryofluid), new DrawDefault());
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.ozone, 12f / 60f);
            consumeLiquid(Liquids.hydrogen, 18f / 60f);
            consumeItems(with(Items.titanium, 2));
            consumePower(2f / 3f);
            outputsLiquid = true;
            outputLiquid = new LiquidStack(Liquids.cryofluid, 30f / 60f);
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
            craftTime = 15f;
            size = 4;
            itemCapacity = 30;
            liquidCapacity = 40f;
            consumePower(2f / 3f);
            consumeItem(Items.scrap, 3);
            consumeLiquid(Liquids.slag, 15f / 60f);
            consumeLiquid(Liquids.water, 6f / 60f);
        }};

        supercooler = new GenericCrafter("supercooler"){{
            requirements(Category.crafting, with( Items.graphite, 1));
            size = 3;
            itemCapacity = 50;
            liquidCapacity = 50f;
            craftTime = 120;
            hasPower = true;
            hasLiquids = true;
            consumeItems(with(Items.plastanium, 15)); //Al4Si3O8
            consumeLiquid(Liquids.cryofluid, 35f / 60f);
            consumeLiquid(Liquids.cyanogen, 25f / 60f);
            consumePower(2f / 3f);
            outputLiquid = new LiquidStack(modliquids.supercooledfluid, 10f/ 60f);
        }};

        compoundCrucible = new HeatCrafter("compound-crucible") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 60;
            craftTime = 120f;
            drawer = new DrawMulti(new DrawRegion("-bottom"),
                    new arcsmeltmodifier(){{}},
                    new DrawDefault(), new DrawHeatInput());
            hasPower = true;
            hasLiquids = false;
            consumeItems(with(Items.phaseFabric, 6, Items.plastanium, 7, Items.oxide, 8, Items.carbide, 7));
            consumePower(2f / 3f);
            outputItem = new ItemStack(moditems.compoundAlloy, 3);
            heatRequirement = 32f;
            maxEfficiency = 5f;
        }};

        phaseSuperHeater = new HeatProducer("phase-super-heater"){{
            requirements(Category.production, with(Items.graphite, 1));
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

        quasiconstructor = new GenericCrafter("quasiconstructor"){{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            liquidCapacity = 80f;
            craftTime = 6.9f;
            hasPower = true;
            hasLiquids = true;
            hasItems = true;
            consumeLiquid(Liquids.hydrogen,50f / 60f);
            consumeLiquid(modliquids.supercooledfluid, 30f / 60f);
            consumeItems(with(Items.phaseFabric, 10));
            consumePower(2f / 3f);
            outputItem = new ItemStack(moditems.quantum, 2);
        }};

        omegalloyCrucible = new GenericCrafter("omegalloy-crucible"){{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 6;
            itemCapacity = 70;
            craftTime = 180f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.water, 40f / 60f);
            consumeItems(with(Items.copper, 17, Items.lead, 16, Items.sand, 15,
                    Items.titanium, 14, Items.graphite, 16, Items.thorium, 13,
                    Items.beryllium, 17, Items.tungsten, 15));
            consumePower(2f / 3f);
            outputItem = new ItemStack(moditems.omegalloy, 8);
        }};

        ultralloyCrucible = new HeatCrafter("ultralloy-crucible") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 5;
            itemCapacity = 80;
            liquidCapacity = 50f;
            craftTime = 360f;
            hasPower = true;
            hasLiquids = true;
            consumeItems(with(Items.surgeAlloy, 30, moditems.forceAlloy, 10, moditems.powerAlloy, 5, moditems.compoundAlloy, 15));
            consumeLiquid(Liquids.cryofluid, 35f / 60f);
            consumePower(2f / 3f);
            outputItem = new ItemStack(moditems.ultralloy, 4);
            heatRequirement = 65f;
            maxEfficiency = 6f;
        }};

            carborundumCrucible = new GenericCrafter("carborundum-crucible"){{
                requirements(Category.crafting, with(Items.graphite, 1));
                craftEffect = Fx.smeltsmoke;
                outputItem = new ItemStack(moditems.carborundum, 5);
                itemCapacity = 50;
                craftTime = 120f;
                size = 4;
                hasPower = true;
                hasLiquids = false;
                ambientSound = Sounds.smelter;
                ambientSoundVolume = 0.07f;
                isMultiblock();
                consumeItems(with(Items.carbide, 9, moditems.diamond, 4, Items.silicon , 17));
                consumePower(1f);
            }};

            thermalOxidizer = new HeatProducer("thermal-oxidizer"){{
                requirements(Category.production, with(Items.graphite, 1));
                drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
                size = 3;
                heatOutput = 50f;
                hasLiquids = true;
                liquidCapacity = 20f;
                craftTime = 60f * 8f;
                ambientSound = Sounds.hum;
                consumeItems(with(Items.pyratite, 5));
                consumeLiquid(Liquids.ozone, 20f/60f);
            }};



        }}
        //power
        {{
                chemicalReactionChamber = new ConsumeGenerator("chemical-reaction-chamber") {{
                    requirements(Category.power, with(Items.graphite, 1));
                    powerProduction = 50f;
                    consumeLiquids(LiquidStack.with(Liquids.ozone, 6f / 60f, Liquids.arkycite, 130f / 60f, Liquids.hydrogen, 6f / 60f));
                    outputLiquid = new LiquidStack(Liquids.water, 6f / 60f);
                    outputsLiquid = true;
                    size = 4;
                    generateEffect = Fx.none;
                    liquidCapacity = 400f;
                    ambientSound = Sounds.smelter;
                    ambientSoundVolume = 0.06f;
                }};

                advancedPyrolysisGenerator = new ConsumeGenerator("advanced-pyrolysis-generator") {{
                    requirements(Category.power, with(Items.graphite, 1));
                    powerProduction = 100f;
                    consumeLiquids(LiquidStack.with(Liquids.slag, 1f, Liquids.arkycite, 130f / 60f));
                    consumeItem(Items.titanium, 4);
                    size = 4;
                    liquidCapacity = 600f;
                    outputLiquid = new LiquidStack(Liquids.cryofluid, 1f);
                    generateEffect = Fx.none;
                    ambientSound = Sounds.smelter;
                    ambientSoundVolume = 0.06f;
                    researchCostMultiplier = 0.4f;
                }};

                geothermalGenerator = new ThermalGenerator("geothermal-generator") {{
                    requirements(Category.power, with(Items.graphite, 1));
                    powerProduction = 20f;
                    consumeLiquid(Liquids.cryofluid, 40f / 60f);
                    generateEffect = Fx.redgeneratespark;
                    effectChance = 0.1f;
                    outputsLiquid = true;
                    outputLiquid = new LiquidStack(Liquids.water, 30f / 60f);
                    size = 3;
                    floating = true;
                    ambientSound = Sounds.hum;
                    ambientSoundVolume = 0.06f;
                }};

                heavyDutyTurbineCondenser = new ThermalGenerator("heavy-duty-turbine-condenser") {{
                    requirements(Category.power, with(Items.graphite, 1));
                    attribute = Attribute.steam;
                    group = BlockGroup.liquids;
                    displayEfficiencyScale = 1f / 3f;
                    minEfficiency = 9f - 0.0001f;
                    powerProduction = 1f / 3f;
                    displayEfficiency = false;
                    generateEffect = Fx.turbinegenerate;
                    effectChance = 0.04f;
                    size = 5;
                    ambientSound = Sounds.hum;
                    ambientSoundVolume = 0.06f;
                    hasLiquids = true;
                    //if its already built why is it called a building
                    //why the fuck is it is 1500 per float
                    outputLiquid = new LiquidStack(Liquids.water, 3f / (60f * 5f));
                    liquidCapacity = 60f;
                    fogRadius = 5;
                }};

            radiativeReactor = new VariableReactor("radiative-reactor"){{
                requirements(Category.power, with(Items.graphite, 1));
                powerProduction = 480f;
                maxHeat = 1000f;
                //why not
                consumeLiquids(LiquidStack.with(Liquids.cyanogen, 16f / 60f,
                        Liquids.cryofluid, 24f / 60f, modliquids.supercooledfluid, 5f / 60f));
                liquidCapacity = 80f;
                explosionMinWarmup = 3f;
                explosionRadius = 40;
                explosionDamage = 9000;
                ambientSound = Sounds.flux;
                ambientSoundVolume = 0.13f;
                size = 7;
            }};

            thoriumTitanReactor = new HeaterReactor("thorium-titan-reactor"){{
                requirements(Category.power, with(Items.graphite, 1));
                hasPower = true;
                hasItems = true;
                hasLiquids = true;
                outputsPower = true;
                powerProduction = 90f;
                size = 4;
                health = 2000;
                consumeItems(with(Items.thorium, 7, Items.phaseFabric, 3));
                consumeLiquid(Liquids.cyanogen, 10f / 60f);
                heatOutput = 175f;
                fuelItem = Items.phaseFabric;
                coolantPower = 1f;
            }};

            neoplasiaHyperReactor = new HeaterGenerator("neoplasia-hyper-reactor"){{
                requirements(Category.power, with(Items.graphite, 1));
                size = 7;
                liquidCapacity = 500f;
                outputLiquid = new LiquidStack(Liquids.neoplasm, 70f / 60f);
                explodeOnFull = true;
                heatOutput = 300f;
                consumeLiquid(Liquids.arkycite, 200f / 60f);
                consumeLiquid(Liquids.cryofluid, 30f / 60f);
                consumeItem(Items.phaseFabric, 4);
                itemDuration = 60f * 3f;
                itemCapacity = 20;
                explosionRadius = 20;
                explosionDamage = 5000;
                explodeEffect = new MultiEffect(Fx.bigShockwave, new WrapEffect(Fx.titanSmoke, Liquids.neoplasm.color), Fx.neoplasmSplat);
                explodeSound = Sounds.largeExplosion;
                powerProduction = 600f;
                ambientSound = Sounds.bioLoop;
                ambientSoundVolume = 0.2f;
                explosionPuddles = 120;
                explosionPuddleRange = tilesize * 9f;
                explosionPuddleLiquid = Liquids.neoplasm;
                explosionPuddleAmount = 300f;
                explosionMinWarmup = 0.25f;
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
            range = 600f;
            speedBoost = 4f;
            useTime = 150f;
            hasBoost = false;
            consumeItems(with(Items.phaseFabric, 7, Items.silicon, 4));
            consumeLiquid(Liquids.nitrogen, 45f / 60f);
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


        }}
        //turrets
        flood = new LiquidTurret("flood"){{
            requirements(Category.turret, with(Items.graphite, 1));
            ammo(
                    Liquids.water, new LiquidBulletType(Liquids.water){{
                        lifetime = 64f;
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
                    Liquids.slag,  new LiquidBulletType(Liquids.slag){{
                        lifetime = 64f;
                        speed = 9f;
                        knockback = 2.8f;
                        puddleSize = 18f;
                        orbSize = 6f;
                        damage = 10f;
                        drag = 0.005f;
                        ammoMultiplier = 0.9f;
                        statusDuration = 60f * 9f;
                    }},
                    Liquids.cryofluid, new LiquidBulletType(Liquids.cryofluid){{
                        lifetime = 64f;
                        speed = 9f;
                        knockback = 2.8f;
                        puddleSize = 18f;
                        orbSize = 6f;
                        drag = 0.005f;
                        ammoMultiplier = 0.9f;
                        statusDuration = 60f * 9f;
                        damage = 0.5f;
                    }},
                    Liquids.oil, new LiquidBulletType(Liquids.oil){{
                        lifetime = 64f;
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
                    modliquids.supercooledfluid, new LiquidBulletType(modliquids.supercooledfluid){{
                        lifetime = 64f;
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

    }
}
