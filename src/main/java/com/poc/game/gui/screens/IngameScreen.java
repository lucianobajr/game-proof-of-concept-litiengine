package com.poc.game.gui.screens;

import com.poc.game.backend.application.services.ListItemsService;
import com.poc.game.backend.domain.entities.Item;
import com.poc.game.gui.contexts.PointsContext;
import com.poc.game.gui.controllers.HUDController;
import com.poc.game.gui.domain.CoinGraphic;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.environment.tilemap.IMap;
import de.gurkenlabs.litiengine.environment.tilemap.IMapObject;
import de.gurkenlabs.litiengine.environment.tilemap.IMapObjectLayer;
import de.gurkenlabs.litiengine.graphics.IRenderable;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IngameScreen extends GameScreen {
    public static final String NAME = "INGAMESCREEN";
    public static ArrayList<IRenderable> components = new ArrayList<>();
    private final IMapObjectLayer collisionLayer;
    private static final int SAFETY_MARGIN = 32;

    // Defina uma distância mínima entre os itens gerados.
    private static final int MIN_DISTANCE_BETWEEN_ITEMS = 124;

    private final HUDController hudController;

    public IngameScreen(){
        super(NAME);

        PointsContext context = new PointsContext();

        hudController = new HUDController(context);

        Random random = new Random();
        IMap map = Game.world().environment().getMap();

        collisionLayer = map.getMapObjectLayer("collision");

        if (collisionLayer == null) {
            throw new RuntimeException("Camada de colisão não encontrada no mapa.");
        }

        // utilizando os serviços do backend
        ListItemsService service = new ListItemsService();

        List<Item> items = service.execute();

        for (Item item : items) {
            int x, y;


            do {
                x = random.nextInt((map.getWidth() - 1) * map.getTileWidth());
                y = random.nextInt((map.getHeight() - 1) * map.getTileHeight());
            } while (collidesWithMap(x, y) || isTooCloseToCollision(x, y) || isTooCloseToOtherItems(x, y));

            CoinGraphic coinGraphic = new CoinGraphic(item.getName(), x, y, item.getValue(), context, hudController);

            components.add(coinGraphic);
            Game.world().environment().add(coinGraphic);
        }
    }

    @Override
    public void render(final Graphics2D g) {
        super.render(g);
        for (IRenderable c: components) {
            c.render(g);
        }

        hudController.render(g);
    }

    private boolean isTooCloseToCollision(int x, int y) {
        for (IMapObject object : collisionLayer.getMapObjects()) {
            Rectangle2D boundingBox = object.getBoundingBox();
            if (boundingBox != null && boundingBox.intersects(x - SAFETY_MARGIN, y - SAFETY_MARGIN, SAFETY_MARGIN * 2, SAFETY_MARGIN * 2)) {
                return true;
            }
        }
        return false;
    }

    private boolean isTooCloseToOtherItems(int x, int y) {
        for (IRenderable item : components) {
            if (item instanceof CoinGraphic) {
                CoinGraphic coin = (CoinGraphic) item;
                double distance = coin.getLocation().distance(x, y);

                if (distance < MIN_DISTANCE_BETWEEN_ITEMS) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean collidesWithMap(int x, int y) {
        for (IMapObject object : collisionLayer.getMapObjects()) {
            Rectangle2D boundingBox = object.getBoundingBox();
            if (boundingBox != null && boundingBox.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

}
