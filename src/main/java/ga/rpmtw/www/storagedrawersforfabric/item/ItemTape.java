package ga.rpmtw.www.storagedrawersforfabric.item;

import ga.rpmtw.www.storagedrawersforfabric.api.drawer.BlockAbstractDrawer;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.blockentity.BlockEntityAbstractDrawer;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.holder.ItemHolder;
import ga.rpmtw.www.storagedrawersforfabric.init.SDItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.registry.Registry;

public class ItemTape extends Item implements DrawerInteractable
{



    public ItemTape(Settings settings)
    {
        super(settings);
    }

    @Override
    public void interact(BlockEntityAbstractDrawer drawer, PlayerEntity player, ItemHolder holder)
    {
        BlockState state = drawer.getWorld().getBlockState(drawer.getPos());



        ItemStack stack = new ItemStack(SDItems.PACKAGED_DRAWER);
        ((BlockAbstractDrawer) state.getBlock()).toTapeTag(stack.getOrCreateSubTag("DrawerInfo"), drawer);
        stack.getTag().putString("Id", Registry.BLOCK.getId(state.getBlock()).toString());

        drawer.getWorld().spawnEntity(new ItemEntity(drawer.getWorld(), drawer.getPos().getX(), drawer.getPos().getY(), drawer.getPos().getZ(), stack));
        drawer.getWorld().setBlockState(drawer.getPos(), Blocks.AIR.getDefaultState());
        player.getMainHandStack().damage(1, player, playerCallback -> playerCallback.sendToolBreakStatus(Hand.MAIN_HAND));
    }
}
