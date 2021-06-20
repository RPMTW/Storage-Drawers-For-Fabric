package ga.rpmtw.www.storagedrawersforfabric.init.client;

import ga.rpmtw.www.storagedrawersforfabric.SimpleDrawersMod;
import ga.rpmtw.www.storagedrawersforfabric.api.container.DrawerContainer;
import ga.rpmtw.www.storagedrawersforfabric.container.client.BasicDrawerContainerScreen;
import ga.rpmtw.www.storagedrawersforfabric.init.SDContainers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SDContainerScreens
{
    public static void initClient()
    {
        ScreenProviderRegistry.INSTANCE.registerFactory(SDContainers.BASIC_DRAWER_CONTAINER, (container) -> new BasicDrawerContainerScreen((DrawerContainer<?>) container, new Identifier(SimpleDrawersMod.MOD_ID, "textures/gui/drawer_1.png")));
        ScreenProviderRegistry.INSTANCE.registerFactory(SDContainers.DOUBLE_DRAWER_CONTAINER, (container) -> new BasicDrawerContainerScreen((DrawerContainer<?>) container, new Identifier(SimpleDrawersMod.MOD_ID, "textures/gui/drawer_2.png")));
        ScreenProviderRegistry.INSTANCE.registerFactory(SDContainers.QUAD_DRAWER_CONTAINER, (container) -> new BasicDrawerContainerScreen((DrawerContainer<?>) container, new Identifier(SimpleDrawersMod.MOD_ID, "textures/gui/drawer_4.png")));

    }
}
