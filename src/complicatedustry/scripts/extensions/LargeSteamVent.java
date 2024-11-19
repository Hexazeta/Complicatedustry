package complicatedustry.scripts.extensions;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.math.geom.Point2;
import arc.util.Time;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.graphics.Pal;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

import static mindustry.Vars.tilesize;

public class LargeSteamVent extends Floor {
    public static final Point2[] offsets = {
            new Point2(0, 0),
            new Point2(1, 0),
            new Point2(2, 0),
            new Point2(3, 0),
            new Point2(4, 0),
            new Point2(-1, 0),
            new Point2(-2, 0),
            new Point2(-3, 0),
            new Point2(-4, 0),
            new Point2(0, 1),
            new Point2(1, 1),
            new Point2(2, 1),
            new Point2(3, 1),
            new Point2(4, 1),
            new Point2(-1, 1),
            new Point2(-2, 1),
            new Point2(-3, 1),
            new Point2(-4, 1),
            new Point2(0, -1),
            new Point2(1, -1),
            new Point2(2, -1),
            new Point2(3, -1),
            new Point2(4, -1),
            new Point2(-1, -1),
            new Point2(-2, -1),
            new Point2(-3, -1),
            new Point2(-4, -1),
            new Point2(0, 2),
            new Point2(1, 2),
            new Point2(2, 2),
            new Point2(3, 2),
            new Point2(4, 2),
            new Point2(-1, 2),
            new Point2(-2, 2),
            new Point2(-3, 2),
            new Point2(-4, 2),
            new Point2(0, -2),
            new Point2(1, -2),
            new Point2(2, -2),
            new Point2(3, -2),
            new Point2(4, -2),
            new Point2(-1, -2),
            new Point2(-2, -2),
            new Point2(-3, -2),
            new Point2(-4, -2),
    };

    public Block parent = Blocks.air;
    public Effect effect = Fx.ventSteam;
    public Color effectColor = Pal.vent;
    public float effectSpacing = 30f;

    static {
        for (var p : offsets) {
            p.sub(4, 4);
        }
    }

    public LargeSteamVent(String name) {
        super(name);
        variants = 2;
        size = 5;
    }

    @Override
    public void drawBase(Tile tile) {
        parent.drawBase(tile);

        if (checkAdjacent(tile)) {
            Mathf.rand.setSeed(tile.pos());
            Draw.rect(variantRegions[Mathf.randomSeed(tile.pos(), 0, Math.max(0, variantRegions.length - 1))],
                    tile.worldx() - tilesize, tile.worldy() - tilesize);
        }
    }

    @Override
    public boolean updateRender(Tile tile) {
        return checkAdjacent(tile);
    }

    @Override
    public void renderUpdate(Floor.UpdateRenderState state) {
        if (state.tile.block() == Blocks.air && (state.data += Time.delta) >= effectSpacing) {
            effect.at(state.tile.x * tilesize - tilesize, state.tile.y * tilesize - tilesize, effectColor);
            state.data = 0f;
        }
    }

    public boolean checkAdjacent(Tile tile) {
        for (var point : offsets) {
            Tile other = Vars.world.tile(tile.x + point.x, tile.y + point.y);
            if (other == null || other.floor() != this) {
                return false;
            }
        }
        return true;
    }
}

