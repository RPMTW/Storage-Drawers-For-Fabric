package ga.rpmtw.www.storagedrawersforfabric.block.entity;

import ga.rpmtw.www.storagedrawersforfabric.api.drawer.holder.CombinedInventoryHandler;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.holder.ItemHolder;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.BlockAbstractDrawer;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.blockentity.BlockEntityAbstractDrawer;
import ga.rpmtw.www.storagedrawersforfabric.block.BlockDrawerController;
import ga.rpmtw.www.storagedrawersforfabric.init.SDBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlockEntityDrawerController extends BlockEntity
{

    List<BlockPos> drawerPositions = new ArrayList<>();

    CombinedInventoryHandler handler = new CombinedInventoryHandler(this::getAffectedItemHolders);

    public BlockEntityDrawerController(BlockPos pos, BlockState state) {
        super(SDBlockEntities.DRAWER_CONTROLLER, pos, state);
    }

    public void updateDrawerPositions()
    {
        drawerPositions.clear();
        Direction d = getCachedState().get(BlockDrawerController.FACING).getOpposite();
        BlockPos pos = getPos().offset(d);
        for(int offsetY = 1; offsetY >= -1; offsetY--)
        {
            for(int offset = -1; offset <= 1; offset++)
            {
                BlockPos drawerPos;
                if(d == Direction.NORTH || d == Direction.SOUTH)
                    drawerPos = new BlockPos(pos.getX() + offset, pos.getY() + offsetY, pos.getZ());
                else
                    drawerPos = new BlockPos(pos.getX(), pos.getY() + offsetY, pos.getZ() + offset);

                if(getWorld().getBlockState(drawerPos).getBlock() instanceof BlockAbstractDrawer)
                {
                    drawerPositions.add(drawerPos);
                }

            }
        }
        handler.generateSlotList();

    }

    public List<ItemHolder> getAffectedItemHolders()
    {
        return drawerPositions.stream()
                .flatMap((pos) -> ((BlockEntityAbstractDrawer) world.getBlockEntity(pos)).getItemHolders().stream())
                .collect(Collectors.toList());
    }

    public CombinedInventoryHandler getInventoryHandler()
    {
        return handler;
    }

    public void tick() {
        updateDrawerPositions();
    }
}
