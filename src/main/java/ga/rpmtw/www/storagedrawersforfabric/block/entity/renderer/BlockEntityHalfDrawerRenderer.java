package ga.rpmtw.www.storagedrawersforfabric.block.entity.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.holder.ItemHolder;
import ga.rpmtw.www.storagedrawersforfabric.block.entity.BlockEntityHalfDrawer;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.BlockAbstractDrawer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import static ga.rpmtw.www.storagedrawersforfabric.utils.RenderConstants.*;

public class BlockEntityHalfDrawerRenderer extends BlockEntityAbstractDrawerRenderer<BlockEntityHalfDrawer>
{

    public BlockEntityHalfDrawerRenderer(BlockEntityRenderDispatcher dispatcher)
    {
        super(dispatcher);
    }

    @Override
    public void render(BlockEntityHalfDrawer blockEntity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay)
    {
        light = calcLight(blockEntity);

        Direction facing = blockEntity.getCachedState().get(BlockAbstractDrawer.FACING);
        float offset = 0;

        for(ItemHolder holder : blockEntity.getItemHolders())
        {
            drawHolder(offset, holder, matrices, vertexConsumers, light, overlay, facing);
            offset += 7.5;
        }

        matrices.pop();
        RenderSystem.setupLevelDiffuseLighting(DIFFUSE_LIGHT_0, DIFFUSE_LIGHT_1, matrices.peek().getModel());
        matrices.push();

    }

    private void drawHolder(float offsetY, ItemHolder holder, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
                            int overlay, Direction facing)
    {
        if(holder.isLocked())
            drawLock(16 - 2.5, 16 - 5 - offsetY, matrices, vertexConsumers, light, overlay, facing);

        if(!holder.isEmpty() || holder.isLocked())
        {
            drawCenteredText(11.5, 3.5 + offsetY, holder.getDisplayAmount(), matrices, vertexConsumers, facing);
            drawItem(6, 4.25 + offsetY, 0.3F, holder.generateStack(1), matrices, vertexConsumers, light, overlay, facing);


        }
    }

}
