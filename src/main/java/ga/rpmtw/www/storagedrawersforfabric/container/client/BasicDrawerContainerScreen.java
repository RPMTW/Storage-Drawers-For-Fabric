package ga.rpmtw.www.storagedrawersforfabric.container.client;

import ga.rpmtw.www.storagedrawersforfabric.api.container.DrawerContainer;
import ga.rpmtw.www.storagedrawersforfabric.api.container.client.DrawerContainerScreen;
import net.minecraft.util.Identifier;

public class BasicDrawerContainerScreen<T extends DrawerContainer<?>> extends DrawerContainerScreen<T>
{

    private Identifier texture;

    public BasicDrawerContainerScreen(T container, Identifier texture)
    {
        super(container);
        this.texture = texture;
    }

    @Override
    public Identifier getBackgroundTexture()
    {
        return texture;
    }
}
