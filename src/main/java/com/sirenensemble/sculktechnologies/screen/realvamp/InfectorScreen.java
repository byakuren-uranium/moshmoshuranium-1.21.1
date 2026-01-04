package com.sirenensemble.sculktechnologies.screen.realvamp;

import com.mojang.blaze3d.systems.RenderSystem;
import com.sirenensemble.sculktechnologies.SculkTechnologies;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.EnchantmentMenu;

public class InfectorScreen extends AbstractContainerScreen<InfectorMenu> {
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID, "textures/gui/infector/infector_gui.png");
    private static final ResourceLocation CHARGE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID, "textures/gui/infector/infector_charge_bar.png");
    private static final ResourceLocation GUI_BUTTON =
            ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID, "textures/gui/infector/infector_button_gui.png");
    private static final ResourceLocation GUI_BUTTON_LIGHTED =
            ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID, "textures/gui/infector/infector_button_gui_lighted.png");


    public InfectorScreen(InfectorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        int mousebuttonx = mouseX - (x + 34);
        int mousebuttony = mouseY - (y + 53);



        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        renderCharge(guiGraphics, x, y, font);
        if(mousebuttonx >= 0 && mousebuttony >= 0 && mousebuttonx < 107 && mousebuttony < 18){
        renderButton(guiGraphics, x, y, font);
    }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        double d0 = mouseX - (double)(i + 34);
        double d1 = mouseY - (double)(j + 53);

            if (d0 >= (double)0.0F && d1 >= (double)0.0F && d0 < (double)107.0F && d1 < (double)18.0F && ((InfectorMenu)this.menu).clickMenuButton(this.minecraft.player, 1)) {
                this.minecraft.gameMode.handleInventoryButtonClick(((InfectorMenu) this.menu).containerId, 1);
                return true;
            }

        return super.mouseClicked(mouseX, mouseY, button);
    }



    private void renderCharge( GuiGraphics guiGraphics, int x, int y, Font font){
        Component textbutton = Component.literal("INFECT");
        guiGraphics.drawString(font, textbutton, x + 74, y + 59, getSlotColor(8), false);
        Component text = Component.literal(menu.getChargeBus() + "/" + menu.getMaxCharge());
        guiGraphics.drawString(font, text, x + 100, y + 5, getSlotColor(111111), false);
            guiGraphics.blit(CHARGE_TEXTURE, x + 3, y + 47, 0, 0, menu.getChargeScale(), 5, 170, 5);
    }
    private void renderButton(GuiGraphics guiGraphics, int x, int y, Font font){
        Component textbutton = Component.literal("INFECT");
        guiGraphics.drawString(font, textbutton, x + 74, y + 59, getSlotColor(8), false);
        guiGraphics.blit(GUI_BUTTON_LIGHTED, x + 34, y + 53, 0,0, 107, 18);
    }
}
