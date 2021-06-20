package ga.rpmtw.www.storagedrawersforfabric.item;

import ga.rpmtw.www.storagedrawersforfabric.api.drawer.blockentity.BlockEntityAbstractDrawer;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.holder.ItemHolder;
import net.minecraft.entity.player.PlayerEntity;

public interface DrawerInteractable
{

    void interact(BlockEntityAbstractDrawer blockEntity, PlayerEntity player, ItemHolder holder);

}
