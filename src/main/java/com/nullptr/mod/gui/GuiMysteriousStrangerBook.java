package com.nullptr.mod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiMysteriousStrangerBook extends GuiScreen {
    private final int imageHeight = 125;
    private final int imageWidth = 125;
    private int currentPage = 0;
    private static final int totalPages = 4;
    private static ResourceLocation[] pageTextures = new ResourceLocation[totalPages];
 //   private static String[] pageText = new String[totalPages];
    private GuiButton closeButton;
    private NextPageButton nextPageButton;
    private NextPageButton previousPageButton;

    public GuiMysteriousStrangerBook() {
        pageTextures[0] = new ResourceLocation("mod:textures/items/unknown.png");
        pageTextures[1] = new ResourceLocation("mod:textures/items/unknown-2.png");
        pageTextures[2] = new ResourceLocation("mod:textures/items/unknown-3.jpg");
    }
    @Override
    public void initGui() {
        buttonList.clear();
        closeButton = new GuiButton(0, width / 2 + 2, 4 + imageHeight, 98, 20, I18n.format("gui.done"));
        buttonList.add(closeButton);
        int offsetFromScreenLeft = (width - imageWidth) / 2;
        buttonList.add(nextPageButton = new NextPageButton(1, offsetFromScreenLeft + 120, 156, true));
        buttonList.add(previousPageButton = new NextPageButton(2, offsetFromScreenLeft + 38, 156, false));
    }

    @Override
    public void updateScreen() {
        closeButton.visible = (currentPage == totalPages - 1);
        nextPageButton.visible = (currentPage < totalPages - 1);
        previousPageButton.visible = currentPage > 0;
    }

    @Override
    public void drawScreen(int width, int height, float partialTicks) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (currentPage >= 0 && currentPage < pageTextures.length) {
           if (currentPage == 0) {
           Minecraft.getMinecraft().getTextureManager().bindTexture(pageTextures[0]);
           } else if (currentPage == 1) {
           Minecraft.getMinecraft().getTextureManager().bindTexture(pageTextures[1]);
           } else if (currentPage == 2) {
           Minecraft.getMinecraft().getTextureManager().bindTexture(pageTextures[2]);
           }
        }
        int offsetFromScreenLeft = (width - imageWidth ) / 2;
        Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect(offsetFromScreenLeft, 2, 0, 0, imageWidth, imageHeight);
        int widthOfString;
        super.drawScreen(width, height, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == closeButton) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen) null);
        } else if (button == nextPageButton) {
            if (currentPage < totalPages - 1) {
                ++currentPage;
            }
        } else if (button == previousPageButton) {
            if (currentPage > 0) {
                --currentPage;
            }
        }
    }

    @Override
    public void onGuiClosed() {
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    static class NextPageButton extends GuiButton {
        private final boolean isNextButton;
        public int positionX;
        public int positionY;
        public NextPageButton(int buttonId, int posX, int posY, boolean isNextButton) {
            super(buttonId, posX, posY, 23, 13, "");
            positionX = posX;
            positionY = posY;
            this.isNextButton = isNextButton;
        }

       // @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY) {
            if (visible) {
                boolean isButtonPressed = (mouseX >= positionX && mouseY >= positionY && mouseX < positionX + width && mouseY < positionY + height);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getMinecraft().getTextureManager().bindTexture(pageTextures[1]);
                int textureX = 0;
                int textureY = 192;
                if (isButtonPressed) {
                    textureX += 23;
                }
                if (!isNextButton) {
                    textureY += 13;
                }
                mc.getMinecraft().ingameGUI.drawTexturedModalRect(positionX, positionY, textureX, textureY, 23, 13);
               // super.drawButton(mc, mouseX, mouseY);
            }
        }
    }
}
