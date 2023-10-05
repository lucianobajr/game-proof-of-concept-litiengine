package com.poc.game.gui.controllers;

import com.poc.game.gui.components.HUDComponent;
import com.poc.game.gui.contexts.PointsContext;
import de.gurkenlabs.litiengine.Game;

import java.awt.*;

public class HUDController {
    private final HUDComponent hudComponent;

    private final PointsContext context;

    public HUDController(PointsContext pointsContext) {
        hudComponent = new HUDComponent(0, 0, Game.window().getWidth(), 40);
        hudComponent.setVisible(true);

        context = pointsContext;


        // Posicione o HUD na parte superior da tela.
        hudComponent.setLocation(0, 0);

        String hintText  = "Pontos: " + context.getPoints();
        hudComponent.setHintText(hintText);
    }

    public void render(Graphics2D g) {
        hudComponent.render(g);
    }

    public void update() {
        // Atualize o HUD conforme necessário.

        String hintText  = "Pontos: " + context.getPoints();
        hudComponent.setHintText(hintText);
    }
}