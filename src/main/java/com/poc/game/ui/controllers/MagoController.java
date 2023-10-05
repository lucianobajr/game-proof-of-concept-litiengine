package com.poc.game.ui.controllers;

import com.poc.game.ui.domain.MagoGraphic;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.LocationLockCamera;
import de.gurkenlabs.litiengine.input.PlatformingMovementController;

import java.awt.event.KeyEvent;

public final class MagoController {

    private MagoController() {
    }

    /*
    Inicializa a lógica do jogo envolvendo o jogador. 
    */

    public static void initialize() {
        setCharacterCamera();
        setCaracterSpawnpoint();
        setCharacterMovementKeys();
    }
    public static void setCharacterCamera() {
        // Define a camera que será usada no jogo que é travada na localização do jogador
        Camera camera = new LocationLockCamera(MagoGraphic.instance());
        camera.setClampToMap(true);
        Game.world().setCamera(camera);
    }

    public static  void setCharacterMovementKeys(){
        MagoGraphic instance = MagoGraphic.instance();
        PlatformingMovementController<MagoGraphic> movementController = new PlatformingMovementController<>(instance);
        instance.addController(movementController);

        // Define as teclas que serão usadas para controlar o jogador.
        movementController.setDownKeys(KeyEvent.VK_DOWN, KeyEvent.VK_S);
        movementController.setUpKeys(KeyEvent.VK_UP, KeyEvent.VK_W);
        movementController.setRightKeys(KeyEvent.VK_RIGHT, KeyEvent.VK_D);
        movementController.setLeftKeys(KeyEvent.VK_LEFT, KeyEvent.VK_A);
    }

    public static void setCaracterSpawnpoint(){
        // Quando o mundo for carregado, o jogador será spawnado no ponto de spawn com o nome "enter"
        Game.world().onLoaded(e -> {

            Spawnpoint enter = e.getSpawnpoint("enter");

            if (enter != null) {
                enter.spawn(MagoGraphic.instance());
               // CharacterGraphic.speechBubble.start();
            }


        });
    }
}
