package complicatedustry.scripts;

import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.blocks.production.*;

import static mindustry.type.ItemStack.*;

public class modblocks {

    public static Block

    //stuff here
    pyratiteMultiMixer, siliconFoundry, forge, waterConcentrator, advancedOxidationChamber,
    densifier, carbideFoundry, oilExtractor, forceCrucible, plastaniumMultiCompressor,
    cyanogenCatalysis, phaseCatalysis, largeMelter, reinforcedGlassSmelter,
    surgeFoundry, cryofluidMultiMixer, blastMultiMixer,fractionator,
    compoundCrucible, ultralloyCrucible;

    public static void load() {

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
            size = 4;
            itemCapacity = 90;
            liquidCapacity = 35f;
            craftTime = 225f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.water, 10f / 60f);
            consumeItems(with(Items.sand, 18, Items.graphite, 8, Items.pyratite, 5));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.silicon, 32);
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
            craftTime = 96f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.hydrogen, 15f / 60f);
            consumeItems(with(Items.beryllium, 3, Items.tungsten, 3, Items.silicon, 4));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.coal, 2);
            heatRequirement = 10f;
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
            outputItem = new ItemStack(Items.coal, 2);
        }};

        surgeFoundry = new HeatCrafter("surge-foundry") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 5;
            itemCapacity = 50;
            craftTime = 320f;
            hasPower = true;
            hasLiquids = true;
            consumeLiquid(Liquids.slag, 1f);
            consumeItems(with(Items.copper, 5, Items.lead, 7, Items.titanium, 4, Items.silicon, 8));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.surgeAlloy, 4);
            heatRequirement = 20f;
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

        compoundCrucible = new HeatCrafter("compound-crucible") {{
            requirements(Category.crafting, with( Items.graphite, 1));
            squareSprite = true;
            size = 4;
            itemCapacity = 30;
            craftTime = 128f;
            hasPower = true;
            hasLiquids = false;
            consumeItems(with(Items.phaseFabric, 2, Items.plastanium, 3, Items.oxide, 4, Items.carbide, 3));
            consumePower(2f / 3f);
            outputItem = new ItemStack(Items.coal, 2);
            heatRequirement = 16f;
        }};

    }
}