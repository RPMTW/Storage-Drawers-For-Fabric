package ga.rpmtw.www.storagedrawersforfabric.plugin.hwyla;

import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.TooltipPosition;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.*;

public class HwylaPlugin implements IWailaPlugin
{

    @Override
    public void register(IRegistrar registrar)
    {
        registrar.registerStackProvider(DrawerComponentProvider.INSTANCE, BlockAbstractDrawer.class);
        registrar.registerComponentProvider(DrawerComponentProvider.INSTANCE, TooltipPosition.BODY, BlockAbstractDrawer.class);

    }

}