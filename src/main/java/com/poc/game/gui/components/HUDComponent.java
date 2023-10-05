package com.poc.game.gui.components;

import de.gurkenlabs.litiengine.gui.GuiComponent;

import java.awt.*;

public class HUDComponent extends GuiComponent {
    private String hintText = "";

    public HUDComponent(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void setHintText(String text) {
        this.hintText = text;
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);

        Font customFont = new Font("Arial", Font.BOLD, 24);
        g.setFont(customFont);
        g.setColor(Color.BLACK);

        int textX = (int) (getX() + 10);
        int textY = (int) (getY() + 20);

        g.drawString(hintText, textX, textY);
    }

}
