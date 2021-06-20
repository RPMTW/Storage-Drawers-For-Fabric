package ga.rpmtw.www.storagedrawersforfabric.container;

import ga.rpmtw.www.storagedrawersforfabric.api.container.DrawerContainer;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.holder.HolderSlot;
import ga.rpmtw.www.storagedrawersforfabric.block.entity.BlockEntityQuadDrawer;
import net.minecraft.entity.player.PlayerEntity;

public class QuadDrawerContainer extends DrawerContainer<BlockEntityQuadDrawer>
{


    public QuadDrawerContainer(int syncId, PlayerEntity entity, BlockEntityQuadDrawer drawer)
    {
        super(syncId, entity, drawer);
        holderSlots.add(new HolderSlot(() -> drawer.getItemHolders().get(0), 71, 35));
        holderSlots.add(new HolderSlot(() -> drawer.getItemHolders().get(1), 89, 35));
        holderSlots.add(new HolderSlot(() -> drawer.getItemHolders().get(2), 71, 53));
        holderSlots.add(new HolderSlot(() -> drawer.getItemHolders().get(3), 89, 53));
    }


}
