package com.poc.game.ui.domain;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.graphics.IRenderable;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.util.geom.GeometricUtilities;

import java.awt.*;
import java.awt.geom.Point2D;

@CollisionInfo(collisionBoxWidth = 30, collisionBoxHeight = 12, collision = true)
public class CoinGraphic extends Prop implements IUpdateable, IRenderable {
    private static final int INTERACTION_DISTANCE = 30; // A distância em que o jogador pode interagir com a madeira
    private boolean isRendered = true;
    public CoinGraphic(String name, double x, double y) {
        super(x, y, name);
        setAddShadow(true);
    }


    @Override
    public void update() {
        if (Input.keyboard().isPressed(java.awt.event.KeyEvent.VK_X) && isPlayerNearby() && isRendered) {
            Game.world().environment().remove(this);
            isRendered = false;
            Game.audio().playSound("src/main/resources/audio/get_coin.ogg");
        }
    }

    private boolean isPlayerNearby() {
        // Verifique se o jogador está dentro da distância de interação
        Point2D playerPosition = new Point2D.Double(MagoGraphic.instance().getX(), MagoGraphic.instance().getY());
        Point2D coinPosition = new Point2D.Double(this.getX(), this.getY());
        double distance = GeometricUtilities.distance(playerPosition, coinPosition);

        return distance <= INTERACTION_DISTANCE;
    }

    @Override
    public void render(Graphics2D g) {
        if (isRendered && isPlayerNearby() ) {
            Font customFont = new Font("Arial", Font.BOLD, 16); // Substitua "Arial" e o tamanho conforme necessário

            g.setFont(customFont);
            g.setColor(Color.RED);

            // Exiba o texto "Aperte X para coletar" acima da madeira
            int textX = (int) (this.getX());
            int textY = (int) (this.getY() + 10);
            Game.graphics().renderText(g, "Aperte X para coletar", textX, textY);
        }
    }
}