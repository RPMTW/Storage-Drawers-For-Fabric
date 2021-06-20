package ga.rpmtw.www.storagedrawersforfabric.api.drawer.holder;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

import java.util.function.Supplier;

public class HolderSlot extends Slot
{
    public Supplier<ItemHolder> holder;

    public HolderSlot(Supplier<ItemHolder> holder, int xPosition, int yPosition)
    {
        super(null, 0, xPosition, yPosition);
        this.holder = holder;
    }

    public ItemStack takeStack(int amount)
    {
        ItemHolder holder = this.holder.get();
        amount = holder.getItemType().getMaxCount();
        ItemStack stack = holder.generateStack(amount);
        holder.amount -= amount;
        return stack;
    }

    @Override
    public int getMaxItemCount()
    {
        return 0;
    }

    @Override
    public int getMaxItemCount(ItemStack stack)
    {
        return holder.get().getMaxStacks() * stack.getMaxCount();
    }

    @Override
    public ItemStack getStack()
    {
        ItemHolder holder = this.holder.get();

        ItemStack stack = holder.generateStack(1);

//		stack.getItem().appendTooltip(stack, holder.blockEntity.getWorld(), Arrays.asList(new LiteralText(Integer.toString(holder.amount))), TooltipContext.Default.NORMAL);

        return stack;
    }

    @Override
    public boolean canInsert(ItemStack stack)
    {
        return false;
    }

    @Override
    public boolean canTakeItems(PlayerEntity playerEntity)
    {
        return false;
    }

    @Override
    public void setStack(ItemStack itemStack)
    {
    }

    public void markDirty()
    {
        this.holder.get().blockEntity.markDirty();
    }

}
