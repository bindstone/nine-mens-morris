package com.codingame.game.constants;

import com.codingame.game.Point;
import com.codingame.game.enumerations.FIELD;

import java.util.HashMap;
import java.util.Map;

import static com.codingame.game.enumerations.FIELD.*;

public class FieldMap {

    public static final Map<String, FIELD> FIELD_MAPPER;
    public static final Map<FIELD, Point> FIELD_POSITION_MAPPER;

    static {
        FIELD_MAPPER = new HashMap<>();
        FIELD_MAPPER.put("A1", A1);
        FIELD_MAPPER.put("D1", D1);
        FIELD_MAPPER.put("G1", G1);
        FIELD_MAPPER.put("B2", B2);
        FIELD_MAPPER.put("D2", D2);
        FIELD_MAPPER.put("F2", F2);
        FIELD_MAPPER.put("C3", C3);
        FIELD_MAPPER.put("D3", D3);
        FIELD_MAPPER.put("E3", E3);
        FIELD_MAPPER.put("A4", A4);
        FIELD_MAPPER.put("B4", B4);
        FIELD_MAPPER.put("C4", C4);
        FIELD_MAPPER.put("E4", E4);
        FIELD_MAPPER.put("F4", F4);
        FIELD_MAPPER.put("G4", G4);
        FIELD_MAPPER.put("C5", C5);
        FIELD_MAPPER.put("D5", D5);
        FIELD_MAPPER.put("E5", E5);
        FIELD_MAPPER.put("B6", B6);
        FIELD_MAPPER.put("D6", D6);
        FIELD_MAPPER.put("F6", F6);
        FIELD_MAPPER.put("A7", A7);
        FIELD_MAPPER.put("D7", D7);
        FIELD_MAPPER.put("G7", G7);
    }


    static {
        FIELD_POSITION_MAPPER = new HashMap<>();
        FIELD_POSITION_MAPPER.put(A1, new Point(550,950));
        FIELD_POSITION_MAPPER.put(D1, new Point(945,950));
        FIELD_POSITION_MAPPER.put(G1, new Point(1350,950));
        FIELD_POSITION_MAPPER.put(B2, new Point(670,820));
        FIELD_POSITION_MAPPER.put(D2, new Point(945,820));
        FIELD_POSITION_MAPPER.put(F2, new Point(1220,820));
        FIELD_POSITION_MAPPER.put(C3, new Point(810,680));
        FIELD_POSITION_MAPPER.put(D3, new Point(945,680));
        FIELD_POSITION_MAPPER.put(E3, new Point(1080,680));
        FIELD_POSITION_MAPPER.put(A4, new Point(550,545));
        FIELD_POSITION_MAPPER.put(B4, new Point(670,545));
        FIELD_POSITION_MAPPER.put(C4, new Point(810,545));
        FIELD_POSITION_MAPPER.put(E4, new Point(1080,545));
        FIELD_POSITION_MAPPER.put(F4, new Point(1220,545));
        FIELD_POSITION_MAPPER.put(G4, new Point(1350,545));
        FIELD_POSITION_MAPPER.put(C5, new Point(810,410));
        FIELD_POSITION_MAPPER.put(D5, new Point(945,410));
        FIELD_POSITION_MAPPER.put(E5, new Point(1080,410));
        FIELD_POSITION_MAPPER.put(B6, new Point(670,280));
        FIELD_POSITION_MAPPER.put(D6, new Point(945,280));
        FIELD_POSITION_MAPPER.put(F6, new Point(1220,280));
        FIELD_POSITION_MAPPER.put(A7, new Point(550,150));
        FIELD_POSITION_MAPPER.put(D7, new Point(945,150));
        FIELD_POSITION_MAPPER.put(G7, new Point(1359,150));
    }
}