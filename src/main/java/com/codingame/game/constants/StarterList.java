package com.codingame.game.constants;

import com.codingame.game.Point;

import java.util.ArrayList;
import java.util.List;

public class StarterList {
    public static final List<Point> BLACK_STARTER;

    public static final List<Point> WHITE_STARTER;

    static {
        WHITE_STARTER = new ArrayList<>();
        WHITE_STARTER.add(new Point(400, 400));
        WHITE_STARTER.add(new Point(300, 400));
        WHITE_STARTER.add(new Point(200, 400));
        WHITE_STARTER.add(new Point(400, 500));
        WHITE_STARTER.add(new Point(300, 500));
        WHITE_STARTER.add(new Point(200, 500));
        WHITE_STARTER.add(new Point(400, 600));
        WHITE_STARTER.add(new Point(300, 600));
        WHITE_STARTER.add(new Point(200, 600));
    }

    static {
        BLACK_STARTER = new ArrayList<>();
        BLACK_STARTER.add(new Point(1500, 400));
        BLACK_STARTER.add(new Point(1600, 400));
        BLACK_STARTER.add(new Point(1700, 400));
        BLACK_STARTER.add(new Point(1500, 500));
        BLACK_STARTER.add(new Point(1600, 500));
        BLACK_STARTER.add(new Point(1700, 500));
        BLACK_STARTER.add(new Point(1500, 600));
        BLACK_STARTER.add(new Point(1600, 600));
        BLACK_STARTER.add(new Point(1700, 600));
    }
}
