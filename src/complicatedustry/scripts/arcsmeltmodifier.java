package complicatedustry.scripts;

import arc.graphics.Color;
import mindustry.world.blocks.production.HeatCrafter;
import mindustry.world.draw.DrawArcSmelt;

import static mindustry.world.meta.Stat.maxEfficiency;

public class arcsmeltmodifier extends DrawArcSmelt {

    // i want to kill myself
    public Color[] flameColor = {
            Color.valueOf("fbde36"),
            Color.valueOf("e9f382"),
            Color.valueOf("bcfcbc"),
            Color.valueOf("bcfcbc"),
            Color.valueOf("50deff")
    };
    public Float[] flameRad = {
            0.5f, 0.75f, 1.0f, 1.25f, 1.5f
    };
    public Float[] circleSpace = {
            1.0f, 1.3f, 1.6f, 1.9f, 2.1f
    };
    //thanks alot to router, glenn and smol for helping


}
