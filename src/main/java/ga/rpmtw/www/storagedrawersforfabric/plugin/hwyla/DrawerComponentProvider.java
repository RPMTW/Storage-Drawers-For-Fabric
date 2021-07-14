package ga.rpmtw.www.storagedrawersforfabric.plugin.hwyla;

import ga.rpmtw.www.storagedrawersforfabric.api.border.Border;
import ga.rpmtw.www.storagedrawersforfabric.api.border.BorderRegistry;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.*;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.blockentity.BlockEntityAbstractDrawer;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.RenderableTextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.List;

public class DrawerComponentProvider implements IComponentProvider {

    public static DrawerComponentProvider INSTANCE = new DrawerComponentProvider();

    DrawerComponentProvider() {
    }

    @Override
    public ItemStack getStack(IDataAccessor accessor, IPluginConfig config) {
        if (accessor.getBlock() instanceof BlockAbstractDrawer) {
            return getItemStack((BlockAbstractDrawer) accessor.getBlock(),
                    accessor.getBlockState().get(BlockAbstractDrawer.BORDER_TYPE));
        }
        return IComponentProvider.super.getStack(accessor, config);
    }

    public static ItemStack getItemStack(BlockAbstractDrawer drawer, Border border) {
        ItemStack result = new ItemStack(drawer.asItem());
        NbtCompound data = new NbtCompound();

        data.putString("Border", BorderRegistry.getName(border));

        result.putSubTag("DrawerInfo", data);
        return result;
    }

    @Override
    public void appendBody(List<Text> tooltip, IDataAccessor accessor, IPluginConfig config) {
        BlockEntityAbstractDrawer drawer = (BlockEntityAbstractDrawer) accessor.getBlockEntity();

        assert drawer != null;
        RenderableTextComponent[] renderables = drawer.getItemHolders().stream()
                .map(holder -> getRenderable(holder.generateStack(holder.getAmount()))).toArray(RenderableTextComponent[]::new);
        tooltip.add(new RenderableTextComponent(renderables));

    }

    private static RenderableTextComponent getRenderable(ItemStack stack) {
        if (!stack.isEmpty()) {
            NbtCompound tag = new NbtCompound();
            tag.putString("id", Registry.ITEM.getId(stack.getItem()).toString());
            tag.putInt("count", stack.getCount());
            if (stack.hasTag()) {
                assert stack.getTag() != null;
                tag.putString("nbt", stack.getTag().toString());
            }
            return new RenderableTextComponent(new Identifier("item"), tag);
        } else {
            NbtCompound spacerTag = new NbtCompound();
            spacerTag.putInt("width", 18);
            return new RenderableTextComponent(new Identifier("spacer"), spacerTag);
        }
    }

}