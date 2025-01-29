package complicatedustry;

import mindustry.content.Blocks;
import mindustry.mod.*;
import complicatedustry.scripts.*;

public class Complicatedustry extends Mod{
    @Override
    public void loadContent() {
        EntityRegistry.register();
        Modliquids.load();
        Blocks.taintedWater.asFloor().liquidDrop = Modliquids.sporeinfestedwater;
        Blocks.deepTaintedWater.asFloor().liquidDrop = Modliquids.sporeinfestedwater;
        Blocks.darksandTaintedWater.asFloor().liquidDrop = Modliquids.sporeinfestedwater;
        Moditems.load();
        Modblocks.load();
        Modunits.load();
    }
    public static class EntityRegistry { public static void register(){}}
}
