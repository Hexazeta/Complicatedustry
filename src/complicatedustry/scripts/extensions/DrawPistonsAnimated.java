package complicatedustry.scripts.extensions;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.util.Tmp;
import mindustry.gen.Building;
import mindustry.world.Block;
import mindustry.world.draw.DrawPistons;


public class DrawPistonsAnimated extends DrawPistons {
    private float maxDistance = 10f; // Maximum piston extension distance
    private float retractSpeed = 2f; // Speed at which the piston retracts
    private float extendSpeed = 2f; // Speed at which the piston extends

    // Additional variables to track piston state
    private boolean isExtending = false;
    private boolean isRetracting = false;

    @Override
    public void draw(Building build) {
        // Check if the building is producing or working (active progress)
        boolean isProducing = build.totalProgress() > 0 && build.totalProgress() < 1;

        float len = 0f;

        // If the building is producing, the piston should extend
        if (isProducing) {
            // Piston extending logic
            if (!isExtending) {
                isExtending = true;
                isRetracting = false;
            }
            // Extend piston smoothly
            len = Mathf.absin(build.totalProgress() + sinOffset, sinScl, sinMag) + lenOffset;
            if (len > maxDistance) {
                len = maxDistance;
            }
        } else {
            // If the building stops producing, the piston should retract
            if (!isRetracting) {
                isRetracting = true;
                isExtending = false;
            }
            // Retract piston smoothly
            len = Mathf.absin(build.totalProgress() + sinOffset, sinScl, sinMag) - retractSpeed + lenOffset;
            if (len < 0) {
                len = 0; // Ensure the piston doesn't go beyond its initial position
            }
        }

        // Handle piston movement in a loop for all sides
        for (int i = 0; i < sides; i++) {
            float angle = angleOffset + i * 360f / sides;
            TextureRegion reg = regiont.found() && (Mathf.equal(angle, 315) || Mathf.equal(angle, 135)) ? regiont :
                    angle >= 135 && angle < 315 ? region2 : region1;

            if (Mathf.equal(angle, 315)) {
                Draw.yscl = -1f;
            }

            // Move the piston based on the angle and length (corrected the `trns()` method usage)
            Tmp.v1.trns(angle, len - horiOffset); // Correcting to pass the distance as the second argument
            Draw.rect(reg, build.x + Tmp.v1.x, build.y + Tmp.v1.y, angle);

            Draw.yscl = 1f;
        }
    }

    @Override
    public void load(Block block) {
        super.load(block);
        region1 = Core.atlas.find(block.name + suffix + "0", block.name + suffix);
        region2 = Core.atlas.find(block.name + suffix + "1", block.name + suffix);
        regiont = Core.atlas.find(block.name + suffix + "-t");
        iconRegion = Core.atlas.find(block.name + suffix + "-icon");
    }

    @Override
    public TextureRegion[] icons(Block block) {
        return new TextureRegion[]{iconRegion};
    }
}
