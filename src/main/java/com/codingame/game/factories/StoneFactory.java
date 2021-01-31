package com.codingame.game.factories;

import com.codingame.game.Point;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.codingame.gameengine.module.entities.Group;
import com.codingame.gameengine.module.entities.Sprite;

public class StoneFactory {

    public static final Group createWhiteStone(GraphicEntityModule graphicEntityModule, Point point) {
        return createStone(graphicEntityModule, "stone1.png", point);
    }

    public static final Group createBlackStone(GraphicEntityModule graphicEntityModule, Point point) {
        return createStone(graphicEntityModule, "stone2.png", point);
    }

    public static final Group createStone(GraphicEntityModule graphicEntityModule, String image, Point point) {
        Sprite stone = graphicEntityModule
                .createSprite()
                .setImage(image)
                .setAnchor(0.5);
        return graphicEntityModule
                .createGroup(stone)
                .setX(point.getX())
                .setY(point.getY());
    }
}
