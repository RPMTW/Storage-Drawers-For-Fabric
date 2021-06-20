package ga.rpmtw.www.storagedrawersforfabric.container;

import ga.rpmtw.www.storagedrawersforfabric.api.drawer.holder.HolderSlot;
import ga.rpmtw.www.storagedrawersforfabric.api.container.DrawerContainer;
import ga.rpmtw.www.storagedrawersforfabric.block.entity.BlockEntityBasicDrawer;
import net.minecraft.entity.player.PlayerEntity;

public class BasicDrawerContainer extends DrawerContainer<BlockEntityBasicDrawer>
{


    public BasicDrawerContainer(int syncId, PlayerEntity entity, BlockEntityBasicDrawer drawer)
    {
        super(syncId, entity, drawer);
        holderSlots.add(new HolderSlot(() -> drawer.getItemHolders().get(0), 80, 44));
    }


}
