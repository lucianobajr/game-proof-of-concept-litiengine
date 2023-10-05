package com.poc.game.ui.screens;

import com.poc.game.ui.domain.CoinGraphic;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.environment.tilemap.IMap;
import de.gurkenlabs.litiengine.graphics.IRenderable;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class IngameScreen extends GameScreen {
    public static final String NAME = "INGAMESCREEN";

    public static ArrayList<IRenderable> components = new ArrayList<>();

    public IngameScreen(int count){
        super(NAME);

        Random random = new Random();
        IMap map = Game.world().environment().getMap();

        for(int i=0; i < count; i++){

            // Deve ser implementada a busca no banco de dados

            int x = random.nextInt((map.getWidth() -1)* map.getTileWidth());
            int y = random.nextInt((map.getHeight() -1 ) * map.getTileHeight());

            System.out.println(x);
            System.out.println(y);

            CoinGraphic coinGraphic = new CoinGraphic("coin",x,y);
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
    }
}
