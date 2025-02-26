package ga.rpmtw.www.storagedrawersforfabric.item;

import ga.rpmtw.www.storagedrawersforfabric.api.drawer.BlockAbstractDrawer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

public class ItemPackagedDrawer extends Item
{
    public ItemPackagedDrawer(Settings settings)
    {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        BlockPos pos = context.getBlockPos().add(context.getSide().getVector());
        if(context.getWorld().getBlockState(pos).isAir())
        {
            BlockAbstractDrawer drawer = (BlockAbstractDrawer) Registry.BLOCK.get(new Identifier(context.getStack().getTag().getString("Id")));

            drawer.setBlockFromTape(context.getStack().getSubTag("DrawerInfo"), context.getWorld(), pos, context.getPlayer());
            context.getStack().decrement(1);

            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
