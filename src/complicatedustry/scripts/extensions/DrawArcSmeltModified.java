package complicatedustry.scripts.extensions;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.gen.Building;
import mindustry.world.blocks.heat.HeatBlock;
import mindustry.world.blocks.production.HeatCrafter;
import mindustry.world.draw.DrawArcSmelt;

import static mindustry.world.meta.BlockGroup.heat;

public class DrawArcSmeltModified extends DrawArcSmelt {

    public Color[] flameColor = {
            Color.valueOf("ffff00"),
            Color.valueOf("ffdd00"),
            Color.valueOf("ff9900"),
            Color.valueOf("ff5500"),
            Color.valueOf("cc0000")  
    };

    public Float[] flameRad = {
            0.7f, 0.9f, 1.1f, 1.3f, 1.5f
    };

    public Float[] circleSpace = {
            1.3f, 1.6f, 1.9f, 2.1f, 2.4f
    };

    public float currentFlameColor = 0, currentFlameRad, currentCircleSpace;

    public void updateFlameProperties(HeatCrafter.HeatCrafterBuild build) {
        // get the efficiency scale from the HeatCrafterBuild
        float efficiency = build.efficiencyScale();  // get the current efficiency (0 to maxEfficiency)

        // access the maxEfficiency directly from the block (which is the HeatCrafter)
        HeatCrafter heatCrafter = (HeatCrafter) build.block;
        float maxEfficiency = heatCrafter.maxEfficiency;

        float scaledEfficiency = efficiency / maxEfficiency;  // normalized efficiency (0 to 1)

        int colorIndex = Math.round(scaledEfficiency * (flameColor.length - 1));
        currentFlameColor = scaledEfficiency;  // can be used for more complex color transitions

        currentFlameRad = Interp.linear.apply(flameRad[colorIndex], flameRad[Math.min(colorIndex + 1, flameRad.length - 1)], scaledEfficiency);
        currentCircleSpace = Interp.linear.apply(circleSpace[colorIndex], circleSpace[Math.min(colorIndex + 1, circleSpace.length - 1)], scaledEfficiency);
    }

    @Override
    public void draw(Building build) {
        if (build instanceof HeatCrafter.HeatCrafterBuild) {
            HeatCrafter.HeatCrafterBuild heatBuild = (HeatCrafter.HeatCrafterBuild) build;
            if (heatBuild.warmup() > 0f && flameColor[0].a > 0.001f) {

                // update flame properties based on efficiency
                updateFlameProperties(heatBuild);

                Lines.stroke(circleStroke * heatBuild.warmup());

                float si = Mathf.absin(flameRadiusScl, flameRadiusMag);
                float a = alpha * heatBuild.warmup();
                Draw.blend(blending);

                Draw.color(flameColor[Math.min(Math.round(currentFlameColor * (flameColor.length - 1)), flameColor.length - 1)], a);

                if (drawCenter) Fill.circle(build.x, build.y, currentFlameRad + si);

                if (drawCenter) Lines.circle(build.x, build.y, (currentFlameRad + currentCircleSpace + si) * heatBuild.warmup());

                Lines.stroke(particleStroke * heatBuild.warmup());
                float base = (Time.time / particleLife);
                rand.setSeed(build.id);
                for (int i = 0; i < particles; i++) {
                    float fin = (rand.random(1f) + base) % 1f, fout = 1f - fin;
                    float angle = rand.random(360f);
                    float len = particleRad * Interp.pow2Out.apply(fin);
                    Lines.lineAngle(build.x + Angles.trnsx(angle, len), build.y + Angles.trnsy(angle, len), angle, particleLen * fout * heatBuild.warmup());
                }

                Draw.blend();
                Draw.reset();
            }
        }
    }
}
