package ga.rpmtw.www.storagedrawersforfabric.api.drawer.blockentity;

import ga.rpmtw.www.storagedrawersforfabric.api.drawer.holder.CombinedInventoryHandler;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.holder.ItemHolder;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Tickable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BlockEntityAbstractDrawer extends BlockEntity implements BlockEntityClientSerializable, Tickable {

    public BlockEntityAbstractDrawer(BlockEntityType<?> type) {
        super(type);
    }

    public abstract ItemHolder getItemHolderAt(float x, float y);

    public abstract List<ItemHolder> getItemHolders();

    public abstract void setItemHolders(List<ItemHolder> holders);

    public abstract CombinedInventoryHandler getInventoryHandler();

    public void tick()
    {
        if(!world.isClient)
            getItemHolders().forEach((holder) -> holder.getInventoryHandler().transferItems());
    }

    @Override
    public void sync()
    {
        markDirty();
        BlockEntityClientSerializable.super.sync();
    }

    @Override
    public void fromTag(BlockState state, NbtCompound tag)
    {
        fromClientTag(tag);
        super.fromTag(state, tag);
    }

    @Override
    public NbtCompound toTag(NbtCompound tag)
    {
        toClientTag(tag);
        return super.toTag(tag);
    }

    @Override
    public void fromClientTag(NbtCompound tag)
    {
        List<ItemHolder> holders = new ArrayList<>();
        if(tag.contains("Holder"))
        {
            holders.add(ItemHolder.fromNBT(tag.getCompound("Holder"), this));
        } else if(tag.contains("Holders"))
        {
            NbtList listTag = tag.getList("Holders", 10);
            holders.addAll(listTag.stream()
                    .map((holderTag) -> ItemHolder.fromNBT((NbtCompound) holderTag, this)).collect(Collectors.toList()));
        }
        if(!holders.isEmpty())
            setItemHolders(holders);
    }

    @Override
    public NbtCompound toClientTag(NbtCompound tag)
    {
        List<NbtCompound> holderList = getItemHolders().stream().map((holder) -> holder.toNBT(new NbtCompound()))
                .collect(Collectors.toList());
        NbtList listTag = new NbtList();
        listTag.addAll(holderList);
        tag.put("Holders", listTag);
        return tag;
    }

}
