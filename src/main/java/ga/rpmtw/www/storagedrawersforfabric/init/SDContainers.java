package ga.rpmtw.www.storagedrawersforfabric.init;

import ga.rpmtw.www.storagedrawersforfabric.StorageDrawersMod;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.blockentity.BlockEntityAbstractDrawer;
import ga.rpmtw.www.storagedrawersforfabric.block.entity.BlockEntityBasicDrawer;
import ga.rpmtw.www.storagedrawersforfabric.block.entity.BlockEntityHalfDrawer;
import ga.rpmtw.www.storagedrawersforfabric.block.entity.BlockEntityQuadDrawer;
import ga.rpmtw.www.storagedrawersforfabric.container.BasicDrawerContainer;
import ga.rpmtw.www.storagedrawersforfabric.container.DoubleDrawerContainer;
import ga.rpmtw.www.storagedrawersforfabric.container.QuadDrawerContainer;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class SDContainers
{

    public static Identifier BASIC_DRAWER_CONTAINER = new Identifier(StorageDrawersMod.MOD_ID, "basic_drawer");
    public static Identifier DOUBLE_DRAWER_CONTAINER = new Identifier(StorageDrawersMod.MOD_ID, "double_drawer");
    public static Identifier QUAD_DRAWER_CONTAINER = new Identifier(StorageDrawersMod.MOD_ID, "quad_drawer");

    public SDContainers()
    {
    }

    public static void init()
    {
        ContainerProviderRegistry.INSTANCE.registerFactory(BASIC_DRAWER_CONTAINER, (syncId, identifier, player, buf) ->
        {
            BlockPos blockPos = buf.readBlockPos();

            BlockEntityAbstractDrawer blockEntity = (BlockEntityAbstractDrawer) player.world.getBlockEntity(blockPos);

            return new BasicDrawerContainer(syncId, player, (BlockEntityBasicDrawer) blockEntity);
        });

        ContainerProviderRegistry.INSTANCE.registerFactory(DOUBLE_DRAWER_CONTAINER, (syncId, identifier, player, buf) ->
        {
            BlockPos blockPos = buf.readBlockPos();

            BlockEntityAbstractDrawer blockEntity = (BlockEntityAbstractDrawer) player.world.getBlockEntity(blockPos);

            return new DoubleDrawerContainer(syncId, player, (BlockEntityHalfDrawer) blockEntity);
        });

        ContainerProviderRegistry.INSTANCE.registerFactory(QUAD_DRAWER_CONTAINER, (syncId, identifier, player, buf) ->
        {
            BlockPos blockPos = buf.readBlockPos();

            BlockEntityAbstractDrawer blockEntity = (BlockEntityAbstractDrawer) player.world.getBlockEntity(blockPos);

            return new QuadDrawerContainer(syncId, player, (BlockEntityQuadDrawer) blockEntity);
        });
    }



}
