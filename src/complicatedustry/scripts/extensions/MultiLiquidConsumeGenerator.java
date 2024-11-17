package complicatedustry.scripts.extensions;

import arc.Events;
import arc.util.Nullable;
import mindustry.game.EventType;
import mindustry.type.Liquid;
import mindustry.type.LiquidStack;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValues;

import java.util.Arrays;

public class MultiLiquidConsumeGenerator extends ConsumeGenerator {
    // Multiple liquid outputs (array of LiquidStack)
    public @Nullable LiquidStack[] outputLiquids;

    // Directions for each liquid output, -1 means all directions
    public int[] liquidOutputDirections = {-1};  // Default to all directions

    // Flag to dump excess liquid
    public boolean dumpExtraLiquid = true;

    public MultiLiquidConsumeGenerator(String name) {
        super(name);
    }

    @Override
    public void init() {
        // Initialize outputLiquids if not set
        if (outputLiquids == null && outputLiquid != null) {
            outputLiquids = new LiquidStack[]{outputLiquid};  // Wrap single output in an array
        }

        // Initialize directions for the liquids
        if (liquidOutputDirections.length != outputLiquids.length) {
            liquidOutputDirections = new int[outputLiquids.length];
            Arrays.fill(liquidOutputDirections, -1);  // Default to all directions
        }

        super.init();
    }

    @Override
    public void setBars() {
        super.setBars();

        // Add liquid bars for each liquid output
        if (outputLiquids != null && outputLiquids.length > 0) {
            for (LiquidStack stack : outputLiquids) {
                addLiquidBar(stack.liquid);
            }
        }
    }

    @Override
    public void setStats() {
        super.setStats();

        // Add output stats for each liquid output
        if (outputLiquids != null) {
            for (LiquidStack liquidStack : outputLiquids) {
                stats.add(Stat.output, StatValues.liquid(liquidStack.liquid, liquidStack.amount * 60f, true));
            }
        }
    }

    public class MultiLiquidConsumeGeneratorBuild extends ConsumeGeneratorBuild {
        @Override
        public void updateTile() {
            super.updateTile();

            // Handle multiple liquid outputs
            if (outputLiquids != null) {
                float added = 0f;
                for (int i = 0; i < outputLiquids.length; i++) {
                    LiquidStack output = outputLiquids[i];
                    int dir = liquidOutputDirections.length > i ? liquidOutputDirections[i] : -1;
                    added = Math.min(productionEfficiency * delta() * output.amount, liquidCapacity - liquids.get(output.liquid));
                    liquids.add(output.liquid, added);
                    dumpLiquid(output.liquid, dir);  // Dump liquid in the specified direction

                    // Handle explosion on full liquid
                    if (explodeOnFull && liquids.get(output.liquid) >= liquidCapacity - 0.01f) {
                        kill();
                        Events.fire(new EventType.GeneratorPressureExplodeEvent(this));
                    }
                }
            }

            // Handle item consumption and generation (same as original ConsumeGenerator)
            generateTime -= delta() / itemDuration;
        }

        public void dumpLiquid(Liquid liquid, int direction) {
            if (direction == -1) {
                // Dump liquid in all directions
                for (int i = 0; i < 4; i++) {
                    dumpLiquidInDirection(liquid, i);
                }
            } else {
                // Dump liquid in the specified direction
                dumpLiquidInDirection(liquid, direction);
            }
        }

        private void dumpLiquidInDirection(Liquid liquid, int direction) {
            // Implement your liquid dumping logic here
            // This would usually be code that interacts with adjacent blocks or conduits
        }
    }
}