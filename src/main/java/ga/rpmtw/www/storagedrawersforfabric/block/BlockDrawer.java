package ga.rpmtw.www.storagedrawersforfabric.block;

import ga.rpmtw.www.storagedrawersforfabric.api.border.Border;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.BlockAbstractDrawer;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.DrawerType;
import ga.rpmtw.www.storagedrawersforfabric.block.entity.BlockEntityBasicDrawer;
import ga.rpmtw.www.storagedrawersforfabric.init.SDContainers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class BlockDrawer extends BlockAbstractDrawer {

    public BlockDrawer(Settings settings, Border border)
    {
        super(settings);
        this.setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(BORDER_TYPE, border).with(DRAWER_TYPE,
                DrawerType.FULL));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView arg0)
    {
        return new BlockEntityBasicDrawer();
    }

    @Override
    public Identifier getContainerIdentifier()
    {
        return SDContainers.BASIC_DRAWER_CONTAINER;
    }

}
