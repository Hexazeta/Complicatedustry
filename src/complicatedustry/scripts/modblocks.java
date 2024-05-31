package complicatedustry.scripts;

import arc.graphics.Color;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.distribution.BufferedItemBridge;
import mindustry.world.blocks.heat.HeatConductor;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.blocks.production.*;
import mindustry.world.draw.*;
import mindustry.world.meta.BlockGroup;

import static mindustry.type.ItemStack.*;

public class modblocks {

    public static Block

    //transports
    titaniumBridgeConveyor,

    //stuff here
    heatRedirector,
    pyratiteMultiMixer, siliconFoundry, diamondMegaPress, forge, waterConcentrator, advancedOxidationChamber,
    densifier, carbideFoundry, oilExtractor, forceCrucible, plastaniumMultiCompressor,
    cyanogenCatalysis, phaseCatalysis, largeMelter, reinforcedGlassSmelter,
    surgeFoundry, cryofluidMultiMixer, blastMultiMixer,fractionator, powerCrucible,
    acidSynthesizer, compoundCrucible, ultralloyCrucible;

    public static void load() {

        titaniumBridgeConveyor = new BufferedItemBridge("titanium-bridge-conveyor"){{
            requirements(Category.distribution, with(Items.lead, 6, Items.copper, 6, Items.titanium, 6));
            fadeIn = moveArrows = false;
            itemCapacity = 20;
            range = 8;
            arrowSpacing = 6f;
            bufferCapacity = 14;
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
            hasPower = true;
            hasLiquids = false;
            consumeItems(with(Items.sand, 8, Items.graphite, 2, Items.lead, 5));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.pyratite, 4);
        }};

        siliconFoundry = new GenericCrafter("silicon-foundry") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            drawer = new DrawMulti( new DrawRegion("-bottom"), new DrawArcSmelt(), new DrawDefault(), new DrawRegion("-center"));
            size = 4;
            itemCapacity = 90;
            liquidCapacity = 35f;
            craftTime = 225f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.oil, 10f / 60f);
            consumeItems(with(Items.sand, 18, Items.graphite, 8, Items.pyratite, 5));
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

        forge = new GenericCrafter("forge") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            craftTime = 90f;
            hasPower = true;
            hasLiquids = false;
            consumeItems(with(Items.sand, 3, Items.lead, 2, Items.pyratite, 1));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.metaglass, 3);
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
            size = 3;
            itemCapacity = 30;
            liquidCapacity = 20f;
            craftTime = 180f;
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
            craftTime = 150f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.water, 36f / 60f);
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.sporePod, 3);
        }};

        carbideFoundry = new HeatCrafter("carbide-foundry") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            liquidCapacity = 30f;
            craftTime = 90f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.water, 5f / 60f);
            consumeItems(with(Items.tungsten, 3, Items.graphite, 4));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.carbide, 2);
            heatRequirement = 14f;
        }};

        oilExtractor = new GenericCrafter("oil-extractor"){{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            liquidCapacity = 90;
            craftTime = 80f;
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
            itemCapacity = 30;
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
            craftTime = 150f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.hydrogen, 15f / 60f);
            consumeItems(with(Items.pyratite, 3, Items.sporePod, 2));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.blastCompound, 3);
        }};

        plastaniumMultiCompressor = new GenericCrafter("plastanium-multi-mixer") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            liquidCapacity = 50f;
            craftTime = 90f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.oil, 20f / 60f);
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
            itemCapacity = 30;
            craftTime = 120f;
            hasPower = true;
            hasLiquids = false;
            consumeItems(with(Items.blastCompound, 7, Items.pyratite, 11, Items.thorium, 13));
            consumePower(2f / 3f);
            outputItem = new ItemStack(moditems.powerAlloy, 5);
        }};

        phaseCatalysis = new HeatCrafter("phase-catalysis") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 3;
            itemCapacity = 30;
            liquidCapacity = 10f;
            craftTime = 180f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.ozone, 4f / 60f);
            consumeItems(with(Items.thorium, 3,Items.silicon, 8));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.phaseFabric, 2);
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
            itemCapacity = 50;
            liquidCapacity = 160f;
            craftTime = 210f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.slag, 40f / 60f);
            consumeItems(with(Items.copper, 5, Items.lead, 6, Items.titanium, 4, Items.silicon, 8));
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
            liquidCapacity = 50f;
            craftTime =180f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.ozone, 10f / 60f);
            consumeLiquid(Liquids.hydrogen, 15f / 60f);
            consumeItems(with(Items.titanium, 2));
            consumePower(2f / 3f);
            outputsLiquid = true;
            outputLiquid = new LiquidStack(Liquids.cryofluid, 25f / 60f);
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
            itemCapacity = 30;
            craftTime = 120f;
            hasPower = true;
            hasLiquids = false;
            consumeItems(with(Items.phaseFabric, 6, Items.plastanium, 7, Items.oxide, 8, Items.carbide, 7));
            consumePower(2f / 3f);
            outputItem = new ItemStack(moditems.compoundAlloy, 3);
            heatRequirement = 16f;
            maxEfficiency = 5f;
        }};

        ultralloyCrucible = new HeatCrafter("ultralloy-crucible") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 5;
            itemCapacity = 40;
            liquidCapacity = 50f;
            craftTime = 180f;
            hasPower = true;
            hasLiquids = true;
            consumeItems(with(Items.surgeAlloy, 8, moditems.forceAlloy, 7, moditems.powerAlloy, 6, moditems.compoundAlloy, 7));
            consumeLiquid(Liquids.cryofluid, 20f / 60f);
            consumePower(2f / 3f);
            outputItem = new ItemStack(moditems.ultralloy, 4);
            heatRequirement = 40f;
            maxEfficiency = 6f;
        }};

    }
}