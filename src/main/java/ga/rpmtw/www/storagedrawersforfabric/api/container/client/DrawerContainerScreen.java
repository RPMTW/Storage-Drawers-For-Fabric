package ga.rpmtw.www.storagedrawersforfabric.api.container.client;

import com.mojang.blaze3d.systems.RenderSystem;
import ga.rpmtw.www.storagedrawersforfabric.api.container.DrawerContainer;
import ga.rpmtw.www.storagedrawersforfabric.api.drawer.holder.HolderSlot;
import ga.rpmtw.www.storagedrawersforfabric.utils.NumberUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public abstract class DrawerContainerScreen<T extends DrawerContainer<?>> extends HandledScreen<T>
{

    public DrawerContainerScreen(T container)
    {

        super(container, container.playerInv, container.drawer.getCachedState().getBlock().asItem().getName());


        this.backgroundWidth = 176;
        this.backgroundHeight = 187;
    }

    protected void drawForeground(MatrixStack stack, int mouseX, int mouseY)
    {
        handler.holderSlots.forEach((slot) ->
        {
            itemRenderer.renderGuiItemIcon(slot.getStack(), slot.x, slot.y);
            RenderSystem.pushMatrix();
            RenderSystem.translatef(slot.x + 3, slot.y + 3, 0);
            RenderSystem.scalef(0.75f, 0.75f, 1);
            itemRenderer.renderGuiItemOverlay(textRenderer, slot.getStack(), 0, 0, NumberUtils.displayShortNumber(slot.holder.get().getAmount()));
            RenderSystem.popMatrix();

        });
        HolderSlot slot = getHolderSlotAt(mouseX, mouseY);
        if(slot != null)
        {
            int slotX = slot.x;
            int slotY = slot.y;

            RenderSystem.disableDepthTest();
            RenderSystem.colorMask(true, true, true, false);
            this.fillGradient(stack, slotX, slotY, slotX + 16, slotY + 16, -2130706433, -2130706433);
            RenderSystem.colorMask(true, true, true, true);
            RenderSystem.enableDepthTest();
        }
        this.textRenderer.draw(stack, this.title.asString(), 8.0F, 6.0F, 4210752);
        this.textRenderer.draw(stack, this.playerInventory.getDisplayName().asString(), 8.0F,
                (float) (this.backgroundHeight - 96 + 2), 4210752);

    }

    public HolderSlot getHolderSlotAt(int x, int y)
    {
        return handler.holderSlots.stream()
                .filter((slot) -> isPointWithinBounds(slot.x, slot.y, 16, 16, x, y)).findAny()
                .orElse(null);
    }

    protected boolean isPointWithinBounds(int xPosition, int yPosition, int width, int height, double pointX,
                                          double pointY)
    {
        int i = this.x;
        int j = this.y;
        pointX -= i;
        pointY -= j;
        return pointX >= (double) (xPosition - 1) && pointX < (double) (xPosition + width + 1)
                && pointY >= (double) (yPosition - 1) && pointY < (double) (yPosition + height + 1);
    }

    public void render(MatrixStack stacks, int mouseX, int mouseY, float delta)
    {
        this.renderBackground(stacks);
        this.drawBackground(stacks, delta, mouseX, mouseY);
        super.render(stacks, mouseX, mouseY, delta);
        HolderSlot slot = getHolderSlotAt(mouseX, mouseY);

        if(slot != null)
        {
            focusedSlot = slot;
        }

        this.drawMouseoverTooltip(stacks, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(MatrixStack matrixStack, float delta, int mouseX, int mouseY)
    {
//        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.client.getTextureManager().bindTexture(getBackgroundTexture());
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(matrixStack, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }

    public abstract Identifier getBackgroundTexture();

}
