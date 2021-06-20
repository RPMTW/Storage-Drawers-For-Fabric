package ga.rpmtw.www.storagedrawersforfabric.block;

import ga.rpmtw.www.storagedrawersforfabric.api.border.Border;
import ga.rpmtw.www.storagedrawersforfabric.api.border.BorderRegistry;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.BlockAbstractDrawer;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.DrawerType;
import ga.rpmtw.www.storagedrawersforfabric.block.entity.BlockEntityHalfDrawer;
import ga.rpmtw.www.storagedrawersforfabric.init.SDContainers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.BlockView;

public class BlockHalfDrawer extends BlockAbstractDrawer
{

    public BlockHalfDrawer(Settings settings, Border border)
    {
        super(settings);
        this.setDefaultState(getDefaultState().with(DrawerType.DRAWER_TYPE, DrawerType.HALF).with(BorderRegistry.BORDER_TYPE, border));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView arg0)
    {
        return new BlockEntityHalfDrawer();
    }

    @Override
    public Identifier getContainerIdentifier()
    {
        return SDContainers.DOUBLE_DRAWER_CONTAINER;
    }

}
