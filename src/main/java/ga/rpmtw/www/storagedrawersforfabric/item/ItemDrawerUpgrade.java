package ga.rpmtw.www.storagedrawersforfabric.item;

import com.mojang.datafixers.util.Either;
import ga.rpmtw.www.storagedrawersforfabric.api.border.Border;
import ga.rpmtw.www.storagedrawersforfabric.api.border.BorderRegistry;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.blockentity.BlockEntityAbstractDrawer;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.holder.ItemHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvents;

public class ItemDrawerUpgrade extends Item implements DrawerInteractable
{

    Either<Border, Border.BorderType> upgradeFrom;
    Border upgradeTo;

    public ItemDrawerUpgrade(Settings settings, Either<Border, Border.BorderType> upgradeFrom, Border upgradeTo)
    {
        super(settings);
        this.upgradeFrom = upgradeFrom;
        this.upgradeTo = upgradeTo;
    }

    @Override
    public void interact(BlockEntityAbstractDrawer blockEntity, PlayerEntity player, ItemHolder holder)
    {
        Border currentBorder = blockEntity.getCachedState().get(BorderRegistry.BORDER_TYPE);
        upgradeFrom.ifLeft((border) ->
        {
            if(currentBorder.equals(border))
                upgrade(blockEntity, player);
        });

        upgradeFrom.ifRight((borderType) ->
        {

            if(currentBorder.getBorderType().equals(borderType))
                upgrade(blockEntity, player);
        });
    }

    public void upgrade(BlockEntityAbstractDrawer holder, PlayerEntity player)
    {
        holder.getWorld().setBlockState(holder.getPos(), holder.getCachedState().with(BorderRegistry.BORDER_TYPE, upgradeTo));
        player.getMainHandStack().decrement(1);
        player.playSound(SoundEvents.BLOCK_ANVIL_USE, 1, 1);
    }

}