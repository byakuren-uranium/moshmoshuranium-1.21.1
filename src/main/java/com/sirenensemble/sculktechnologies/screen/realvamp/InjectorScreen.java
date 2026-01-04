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
import org.joml.AxisAngle4d;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import java.awt.*;

public class InjectorScreen extends AbstractContainerScreen<InjectorMenu> {
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID, "textures/gui/sculk_injector/injector_gui.png");
    private static final ResourceLocation ARROW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID, "textures/gui/sculk_injector/arrow_progress.png");
    private static final ResourceLocation CHARGE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID, "textures/gui/sculk_injector/charge_scale.png");

    public InjectorScreen(InjectorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        renderProgressArrow(guiGraphics, x, y);
        renderCharge(guiGraphics, x, y, font);
    }

private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
    if(menu.isCrafting()) {
            guiGraphics.blit(ARROW_TEXTURE,x + 70, y + 35, 0, 0, menu.getChargeScale(), 16, 24, 16);
    }
}

@Override
public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
    super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
}



    private void renderCharge(GuiGraphics guiGraphics, int x, int y, Font font){
       Component text = Component.literal(menu.getChargeBus() + "/" + menu.getMaxCharge());
        guiGraphics.drawString(font, text, x + 100, y + 5, getSlotColor(111111), false);
        if(menu.validCharge()){
            guiGraphics.blit(CHARGE_TEXTURE, x + 165, y + 4, 0, 0, 5, menu.getChargeScale(), 5, 170);

        }
}
    }
