package ga.rpmtw.www.storagedrawersforfabric.container;

import ga.rpmtw.www.storagedrawersforfabric.api.container.DrawerContainer;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.holder.HolderSlot;
import ga.rpmtw.www.storagedrawersforfabric.block.entity.BlockEntityHalfDrawer;
import net.minecraft.entity.player.PlayerEntity;

public class DoubleDrawerContainer extends DrawerContainer<BlockEntityHalfDrawer>
{


    public DoubleDrawerContainer(int syncId, PlayerEntity entity, BlockEntityHalfDrawer drawer)
    {
        super(syncId, entity, drawer);
        holderSlots.add(new HolderSlot(() -> drawer.getItemHolders().get(0), 80, 35));
        holderSlots.add(new HolderSlot(() -> drawer.getItemHolders().get(1), 80, 53));
    }


}
