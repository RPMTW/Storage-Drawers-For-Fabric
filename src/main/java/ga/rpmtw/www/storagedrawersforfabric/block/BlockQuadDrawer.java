package ga.rpmtw.www.storagedrawersforfabric.block;

import ga.rpmtw.www.storagedrawersforfabric.api.border.Border;
import ga.rpmtw.www.storagedrawersforfabric.api.border.BorderRegistry;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.BlockAbstractDrawer;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.DrawerType;
import ga.rpmtw.www.storagedrawersforfabric.block.entity.BlockEntityQuadDrawer;
import ga.rpmtw.www.storagedrawersforfabric.init.SDContainers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.BlockView;

public class BlockQuadDrawer extends BlockAbstractDrawer
{

    public BlockQuadDrawer(Settings settings, Border border)
    {
        super(settings);
        this.setDefaultState(getDefaultState().with(DrawerType.DRAWER_TYPE, DrawerType.QUAD).with(BorderRegistry.BORDER_TYPE, border));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView arg0)
    {
        return new BlockEntityQuadDrawer();
    }

    @Override
    public Identifier getContainerIdentifier()
    {
        return SDContainers.QUAD_DRAWER_CONTAINER;
    }

}
