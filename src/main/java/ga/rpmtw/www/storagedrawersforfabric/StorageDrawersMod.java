package ga.rpmtw.www.storagedrawersforfabric;

import ga.rpmtw.www.storagedrawersforfabric.api.border.Border;
import ga.rpmtw.www.storagedrawersforfabric.init.*;
import net.fabricmc.api.ModInitializer;

public class StorageDrawersMod implements ModInitializer
{

    public static final String MOD_ID = "storagedrawersforfabric";

    @Override
    public void onInitialize()
    {
        Border.init();

        SDBlocks.init();
        SDBlockEntities.init();
        SDItems.init();
        SDTags.init();
        SDContainers.init();
    }

}
