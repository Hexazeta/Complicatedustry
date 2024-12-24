package complicatedustry;

import mindustry.content.Blocks;
import mindustry.mod.*;
import complicatedustry.scripts.*;

public class Complicatedustry extends Mod{
    @Override
    public void loadContent() {
        EntityRegistry.register();
        modliquids.load();
        Blocks.taintedWater.asFloor().liquidDrop = modliquids.sporeinfestedwater;
        Blocks.deepTaintedWater.asFloor().liquidDrop = modliquids.sporeinfestedwater;
        Blocks.darksandTaintedWater.asFloor().liquidDrop = modliquids.sporeinfestedwater;
        moditems.load();
        modblocks.load();
        modunits.load();
    }
    public static class EntityRegistry { public static void register(){}}
}
