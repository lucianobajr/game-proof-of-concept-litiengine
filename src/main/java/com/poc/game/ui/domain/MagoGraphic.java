package com.poc.game.ui.domain;

import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;

@EntityInfo(width = 32, height = 32)
@MovementInfo(velocity = 130)
@CollisionInfo(collisionBoxWidth = 20, collisionBoxHeight = 16, collision = true)
public class MagoGraphic extends Creature {


    private static MagoGraphic instance;
    private MagoGraphic() {
        super("mago");
    }


    public static MagoGraphic instance() {
        if (instance == null) {
            instance = new MagoGraphic();
        }

        return instance;
    }

}
