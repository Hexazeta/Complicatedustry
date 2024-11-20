package complicatedustry.scripts.extensions;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.util.Time;
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
    private float currentLength = 0f; // Track current piston length

    @Override
    public void draw(Building build) {
        // Check if the building is producing or working (active progress)
        boolean isProducing = build.totalProgress() > 0 && build.totalProgress() < 1;

        // Handle extending piston when production is happening
        if (isProducing) {
            if (!isExtending) {
                // Start extending the piston if it was not already extending
                isExtending = true;
                isRetracting = false;
            }

            // Smoothly extend the piston, based on total progress of the building
            currentLength = Mathf.lerp(currentLength, maxDistance, extendSpeed * Time.delta);
        } else {
            // Handle retracting piston when production stops
            if (!isRetracting) {
                // Start retracting the piston if it was not already retracting
                isRetracting = true;
                isExtending = false;
            }

            // Smoothly retract the piston
            currentLength = Mathf.lerp(currentLength, 0f, retractSpeed * Time.delta);
        }

        // Ensure the piston doesn't move beyond its bounds
        if (currentLength > maxDistance) {
            currentLength = maxDistance;
        }
        if (currentLength < 0) {
            currentLength = 0f;
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
            Tmp.v1.trns(angle, currentLength - horiOffset); // Correcting to pass the distance as the second argument
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

