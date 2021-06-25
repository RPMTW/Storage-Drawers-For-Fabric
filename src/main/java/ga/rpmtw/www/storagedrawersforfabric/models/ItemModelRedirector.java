package ga.rpmtw.www.storagedrawersforfabric.models;

import ga.rpmtw.www.storagedrawersforfabric.api.border.Border;
import ga.rpmtw.www.storagedrawersforfabric.api.border.BorderRegistry;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.BlockAbstractDrawer;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.BlockAbstractDrawer.DeserializedInfo;
import ga.rpmtw.www.storagedrawersforfabric.callback.RedirectModelCallback;
import ga.rpmtw.www.storagedrawersforfabric.item.ItemPackagedDrawer;
import ga.rpmtw.www.storagedrawersforfabric.utils.ModelUtils;
import ga.rpmtw.www.storagedrawersforfabric.utils.PropertyStateConverter;
import ga.rpmtw.www.storagedrawersforfabric.utils.WrappedBakedModel;
import net.minecraft.block.Block;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation.Mode;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemModelRedirector implements RedirectModelCallback {

    @Override
    public BakedModel onRender(ItemStack stack, Mode renderMode, boolean leftHanded, BakedModel model) {
        if (stack.getItem() instanceof BlockItem) {
            Block block = ((BlockItem) stack.getItem()).getBlock();
            if (block instanceof BlockAbstractDrawer) {
                DeserializedInfo info = BlockAbstractDrawer.deserializeInfo(stack);
                Border b = info.getBorder();
                if (b == null)
                    b = block.getDefaultState().get(BorderRegistry.BORDER_TYPE);

                return ModelUtils.getBakedDrawerModel(block.getDefaultState().with(BorderRegistry.BORDER_TYPE, b));
            }
        }
        if (stack.getItem() instanceof ItemPackagedDrawer) {
            NbtCompound tag = stack.getSubTag("DrawerInfo");
            if (tag != null) {
                BlockAbstractDrawer drawer = (BlockAbstractDrawer) Registry.BLOCK.get(new Identifier(tag.getString("Id")));


                Border border = PropertyStateConverter.fromPropertyString(drawer, tag.getString("Variant")).get(BlockAbstractDrawer.BORDER_TYPE);

                return new WrappedBakedModel(ModelUtils.getBakedDrawerModel(drawer.getDefaultState().with(BorderRegistry.BORDER_TYPE, border)), model);

//                return MinecraftClient.getInstance().getBakedModelManager().getModel(new ModelIdentifier(Registry.ITEM.getId(stack.getItem()), tag.getString("DrawerId")));
            }

        }

        return model;
    }

}
