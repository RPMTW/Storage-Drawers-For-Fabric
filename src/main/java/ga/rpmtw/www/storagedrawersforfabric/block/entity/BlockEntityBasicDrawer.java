package ga.rpmtw.www.storagedrawersforfabric.block.entity;

import ga.rpmtw.www.storagedrawersforfabric.api.drawer.holder.CombinedInventoryHandler;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.holder.ItemHolder;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.blockentity.BlockEntityAbstractDrawer;
import ga.rpmtw.www.storagedrawersforfabric.init.SDBlockEntities;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.List;

public class BlockEntityBasicDrawer extends BlockEntityAbstractDrawer implements BlockEntityClientSerializable {

    private ItemHolder holder = new ItemHolder(32, this);

    private CombinedInventoryHandler handler = new CombinedInventoryHandler(() -> Arrays.asList(holder));


    public BlockEntityBasicDrawer(BlockPos pos, BlockState state) {
        super(SDBlockEntities.BASIC_DRAWER, pos, state);
    }

    @Override
    public ItemHolder getItemHolderAt(float x, float y) {
        return holder;
    }

    @Override
    public List<ItemHolder> getItemHolders() {
        return Arrays.asList(holder);
    }

    @Override
    public void setItemHolders(List<ItemHolder> holders) {
        holder = holders.get(0);
        handler.generateSlotList();
    }

    @Override
    public CombinedInventoryHandler getInventoryHandler() {
        return handler;
    }

}
