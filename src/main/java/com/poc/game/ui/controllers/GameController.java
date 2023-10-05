package com.poc.game.ui.controllers;

import com.poc.game.ui.screens.IngameScreen;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.event.KeyEvent;

public class GameController {
    public static void bootstrap(String[] args){
        // Carrega os recursos do jogo, como imagens, sons, mapas, etc.
        Resources.load("game.litidata");

        Game.init(args);
        setInfos();
        initializeControllers();

        // Carrega o primeiro mapa do jogo
        Game.world().loadEnvironment("map");
        addScreens();

        Game.graphics().setBaseRenderScale(6f);

        // adiciona a tecla esc para sair do jogo
        Input.keyboard().onKeyPressed(KeyEvent.VK_ESCAPE, e -> System.exit(0));

        Game.start();
    }

    public static void setInfos(){
        // set meta information about the game
        Game.info().setName("Prova de conceito");
        Game.info().setSubTitle("");
        Game.info().setVersion("v0.0.1");
        Game.info().setDescription("Prova de conceito feita com LITIEngine");
    }

    public static void initializeControllers(){
        MagoController.initialize();
    }

    public static void addScreens(){
        // Adiciona as telas que ajudarão a organizar os diferentes estados do jogo
        Screen screen = new IngameScreen(2);

        Game.screens().add(screen);
    }
}
