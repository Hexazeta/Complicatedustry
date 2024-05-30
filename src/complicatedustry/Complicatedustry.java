package complicatedustry;

import mindustry.mod.*;
import complicatedustry.scripts.*;

public class Complicatedustry extends Mod{
    @Override
    public void loadContent() {
        EntityRegistry.register();
        moditems.load();
        modblocks.load();
    }
    public static class EntityRegistry { public static void register(){}}
}
