package complicatedustry.scripts;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.entities.effect.RadialEffect;
import mindustry.gen.Sounds;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.distribution.BufferedItemBridge;
import mindustry.world.blocks.distribution.StackConveyor;
import mindustry.world.blocks.distribution.StackRouter;
import mindustry.world.blocks.heat.HeatConductor;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.blocks.production.*;
import mindustry.world.draw.*;
import mindustry.world.meta.BlockGroup;

import static mindustry.type.ItemStack.*;

public class modblocks {

    public static Block

    //stuff here
    smallHeatRedirector, heatRedirector, surgeConveyor, diamondMultiMegaPress, surgeRouter,
    pyratiteMultiMixer, siliconFoundry, diamondMegaPress, forge, waterConcentrator, advancedOxidationChamber,
    densifier, carbideFoundry, oilExtractor, forceCrucible, plastaniumMultiCompressor,
    cyanogenCatalysis, phaseCatalysis, largeMelter, reinforcedGlassSmelter,
    surgeFoundry, cryofluidMultiMixer, blastMultiMixer,fractionator, powerCrucible,
    acidSynthesizer, compoundCrucible, liquidMixer, feldsparProcessor, omegalloyCrucible,
    ultralloyCrucible;

    public static void load() {

        surgeConveyor = new StackConveyor("surge-conveyor"){{
            requirements(Category.distribution, with(Items.surgeAlloy, 1, Items.titanium, 1));
            health = 130;
            //TODO different base speed/item capacity?
            speed = 5f / 60f;
            itemCapacity = 30;

            outputRouter = false;
            hasPower = true;
            consumesPower = true;
            conductivePower = true;

            underBullets = true;
            baseEfficiency = 1f;
            consumePower(1f / 60f);
            researchCost = with(Items.surgeAlloy, 30, Items.tungsten, 80);
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

        smallHeatRedirector = new HeatConductor("small-heat-redirector"){{
            requirements(Category.crafting, with(Items.graphite, 1));

            researchCostMultiplier = 10f;

            group = BlockGroup.heat;
            size = 1;
            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput(), new DrawHeatInput("-heat"));
            regionRotated1 = 1;
        }};

        heatRedirector = new HeatConductor("heat-redirector"){{
            requirements(Category.crafting, with(Items.graphite, 1));

            researchCostMultiplier = 10f;

            group = BlockGroup.heat;
            size = 3;
            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput(), new DrawHeatInput("-heat"));
            regionRotated1 = 1;
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
            consumeItems(with(Items.sand, 18, Items.graphite, 8, Items.pyratite, 2));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.silicon, 32);
        }};

        diamondMegaPress = new HeatCrafter("diamond-mega-press"){{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            craftTime = 120f;
            hasPower = true;
            hasLiquids = false;
            consumeItems(with(Items.graphite, 7));
            consumePower(2f / 3f);
            outputItem = new ItemStack(moditems.diamond, 3);
            heatRequirement = 25f;
        }};

        diamondMultiMegaPress = new HeatCrafter("diamond-multi-mega-press"){{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 40;
            craftTime = 60f;
            hasPower = true;
            hasLiquids = false;
            consumeItems(with(Items.graphite, 21));
            consumePower(2f / 3f);
            outputItem = new ItemStack(moditems.diamond, 6);
            heatRequirement = 40f;
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
            requirements(Category.crafting, with( Items.graphite, 1));
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
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 30;
            liquidCapacity = 20f;
            craftTime = 150f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.ozone, 6f / 60f);
            consumeItems(with(Items.copper, 1, Items.beryllium, 2));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.oxide, 3);
            heatOutput = 8;
        }};

        densifier = new GenericCrafter("densifier") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            liquidCapacity = 90f;
            craftTime = 120f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.water, 36f / 60f);
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.sporePod, 3);
        }};

        carbideFoundry = new HeatCrafter("carbide-foundry") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 30;
            liquidCapacity = 30f;
            craftTime = 180f;
            updateEffect = Fx.pulverizeMedium;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawCrucibleFlame(){{
                particles = 40; particleRad = 11f; particleSize = 3.5f; fadeMargin = 0.6f; rotateScl = 2f;}},
                    new DrawDefault(), new DrawHeatInput());
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.water, 10f / 60f);
            consumeItems(with(Items.tungsten, 6, Items.graphite, 8));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.carbide, 4);
            heatRequirement = 14f;
        }};

        oilExtractor = new GenericCrafter("oil-extractor"){{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            liquidCapacity = 90;
            craftTime = 12f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.water, 15f / 60f);
            consumeItems(with(Items.sporePod, 1, Items.sand, 2));
            consumePower(2f / 3f);
            outputsLiquid = true;
            outputLiquid = new LiquidStack(Liquids.oil, 35f / 60f);
        }};

        forceCrucible = new HeatCrafter("force-crucible") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 50;
            liquidCapacity= 40f;
            craftTime = 120f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.hydrogen, 20f / 60f);
            consumeItems(with(Items.beryllium, 7, Items.tungsten, 6, Items.silicon, 8));
            consumePower(2f / 3f);
            outputItem = new ItemStack(moditems.forceAlloy, 2);
            heatRequirement = 10f;
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
            liquidCapacity = 50f;
            craftTime = 40f;
            craftEffect = Fx.formsmoke;
            updateEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.oil, 25f / 60f);
            consumeItems(with(Items.titanium, 3));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.plastanium, 2);
        }};

        cyanogenCatalysis = new GenericCrafter("cyanogen-catalysis") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            liquidCapacity = 200f;
            craftTime = 150f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.arkycite, 70f / 60f);
            consumeItems(with(Items.graphite, 2));
            consumePower(2f / 3f);
            outputsLiquid = true;
            outputLiquid = new LiquidStack(Liquids.cyanogen, 6f / 60f);
        }};

        powerCrucible = new GenericCrafter("power-crucible") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 40;
            craftTime = 96f;
            hasPower = true;
            hasLiquids = false;
            consumeItems(with(Items.blastCompound, 7, Items.pyratite, 11, Items.thorium, 13));
            consumePower(2f / 3f);
            outputItem = new ItemStack(moditems.powerAlloy, 5);
        }};

        phaseCatalysis = new HeatCrafter("phase-catalysis") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 40;
            liquidCapacity = 10f;
            craftTime = 165f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.ozone, 9f / 60f);
            consumeItems(with(Items.thorium, 6,Items.silicon, 16));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.phaseFabric, 11);
            heatRequirement = 12f;
        }};

        largeMelter = new HeatCrafter("large-melter"){{
            requirements(Category.crafting, with(Items.graphite, 1));
            size = 3;
            itemCapacity = 20;
            liquidCapacity = 80f;
            craftTime = 10f;
            hasLiquids = hasPower = true;
            consumePower(2f / 3f);
            consumeItem(Items.scrap, 2);
            outputLiquid = new LiquidStack(Liquids.slag, 30f / 60f);
            heatRequirement = 5f;
        }};

        reinforcedGlassSmelter = new GenericCrafter("reinforced-glass-smelter") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            craftTime = 90f;
            hasPower = true;
            hasLiquids = false;
            consumeItems(with(Items.metaglass, 3, Items.tungsten, 2));
            consumePower(2f / 3f);
            outputItem = new ItemStack(moditems.reinforcedGlass, 2);
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
            consumeLiquid(Liquids.slag, 40f / 60f);
            consumeItems(with(Items.copper, 7, Items.lead, 8, Items.titanium, 6, Items.silicon, 14));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.surgeAlloy, 4);
            heatRequirement = 20f;
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

        acidSynthesizer = new GenericCrafter("acid-synthesizer"){{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            liquidCapacity = 80f;
            craftTime = 6.9f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.hydrogen,10f / 60f);
            consumeLiquid(Liquids.ozone,30f / 60f);
            consumeLiquid(Liquids.nitrogen,10f / 60f);
            consumePower(2f / 3f);
            outputLiquid = new LiquidStack(modliquids.acid, 10f / 60f);
        }};

        compoundCrucible = new HeatCrafter("compound-crucible") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 60;
            craftTime = 120f;
            hasPower = true;
            hasLiquids = false;
            consumeItems(with(Items.phaseFabric, 6, Items.plastanium, 7, Items.oxide, 8, Items.carbide, 7));
            consumePower(2f / 3f);
            outputItem = new ItemStack(moditems.compoundAlloy, 3);
            heatRequirement = 16f;
            maxEfficiency = 5f;
        }};

        liquidMixer = new GenericCrafter("liquid-mixer"){{
            requirements(Category.crafting, with( Items.graphite, 1));
            size = 3;
            liquidCapacity = 50f;
            craftTime = 5f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.oil, 10f / 60f);
            consumeLiquid(Liquids.cryofluid, 20f/ 60f);
            consumeLiquid(Liquids.cyanogen, 25f / 60f);
            consumePower(2f / 3f);
            outputLiquid = new LiquidStack(modliquids.denseFluid, 15f / 60f);
        }};

        feldsparProcessor = new GenericCrafter("feldspar-Processor"){{
            requirements(Category.crafting, with( Items.graphite, 1));
            size = 3;
            itemCapacity = 50;
            liquidCapacity = 50f;
            craftTime = 120;
            hasPower = true;
            hasLiquids = true;
            consumeItems(with(moditems.feldspar, 20)); //Al4Si3O8
            consumeLiquid(Liquids.hydrogen, 32f / 60f);
            consumePower(2f / 3f);
            outputItems = ItemStack.with(moditems.aluminum, 8, Items.silicon, 6);
            outputLiquid = new LiquidStack(Liquids.water, 16f/ 60f);
        }};

        omegalloyCrucible = new GenericCrafter("omegalloy-crucible"){{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 5;
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
            heatRequirement = 40f;
            maxEfficiency = 6f;
        }};

    }
}