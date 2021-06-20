package ga.rpmtw.www.storagedrawersforfabric;

import ga.rpmtw.www.storagedrawersforfabric.callback.ModelPostBakeCallback;
import ga.rpmtw.www.storagedrawersforfabric.callback.ModelPreBakeCallback;
import ga.rpmtw.www.storagedrawersforfabric.callback.RedirectModelCallback;
import ga.rpmtw.www.storagedrawersforfabric.models.ItemModelRedirector;
import ga.rpmtw.www.storagedrawersforfabric.models.ModelMerger;
import ga.rpmtw.www.storagedrawersforfabric.models.border.BorderLoader;
import ga.rpmtw.www.storagedrawersforfabric.block.entity.renderer.BlockEntityFullDrawerRenderer;
import ga.rpmtw.www.storagedrawersforfabric.block.entity.renderer.BlockEntityHalfDrawerRenderer;
import ga.rpmtw.www.storagedrawersforfabric.block.entity.renderer.BlockEntityQuadDrawerRenderer;
import ga.rpmtw.www.storagedrawersforfabric.init.SDBlockEntities;
import ga.rpmtw.www.storagedrawersforfabric.init.client.SDContainerScreens;
import ga.rpmtw.www.storagedrawersforfabric.utils.ModelUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;

public class SimpleDrawersModClient implements ClientModInitializer
{

    @Override
    public void onInitializeClient()
    {
        ModelPostBakeCallback.EVENT.register(new ModelMerger());
        ModelPreBakeCallback.EVENT.register(new ModelMerger());

        RedirectModelCallback.EVENT.register(new ItemModelRedirector());

        ModelUtils.loadSpecialModels();

        ModelLoadingRegistry.INSTANCE.registerAppender(new BorderLoader());
        ModelLoadingRegistry.INSTANCE.registerVariantProvider((resourceManager) -> new BorderLoader());

        BlockEntityRendererRegistry.INSTANCE.register(SDBlockEntities.BASIC_DRAWER,
                BlockEntityFullDrawerRenderer::new);

        BlockEntityRendererRegistry.INSTANCE.register(SDBlockEntities.HALF_DRAWER,
                BlockEntityHalfDrawerRenderer::new);

        BlockEntityRendererRegistry.INSTANCE.register(SDBlockEntities.QUAD_DRAWER,
                BlockEntityQuadDrawerRenderer::new);

        SDContainerScreens.initClient();
    }

}
