package complicatedustry;

import mindustry.mod.*;
import complicatedustry.scripts.*;

public class Complicatedustry extends Mod{
    @Override
    public void loadContent() {
        EntityRegistry.register();
        modliquids.load();
        moditems.load();
        modblocks.load();
        modunits.load();
    }
    public static class EntityRegistry { public static void register(){}}
}
