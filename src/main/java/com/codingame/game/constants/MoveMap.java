package com.codingame.game.constants;

import com.codingame.game.enumerations.FIELD;

import java.util.HashMap;
import java.util.Map;

import static com.codingame.game.enumerations.FIELD.*;

public class MoveMap {
    public static final Map<FIELD, FIELD[]> MOVE_MAPPER;
    static {
        MOVE_MAPPER = new HashMap<>();
        MOVE_MAPPER.put(A1, new FIELD[]{A4, D1});
        MOVE_MAPPER.put(D1, new FIELD[]{D2, A1, G1});
        MOVE_MAPPER.put(G1, new FIELD[]{G4,D1});
        MOVE_MAPPER.put(B2, new FIELD[]{B4, D2});
        MOVE_MAPPER.put(D2, new FIELD[]{D1, D3, B2, F2});
        MOVE_MAPPER.put(F2, new FIELD[]{F4, D2});
        MOVE_MAPPER.put(C3, new FIELD[]{C4, D3});
        MOVE_MAPPER.put(D3, new FIELD[]{D2, C3, E3});
        MOVE_MAPPER.put(E3, new FIELD[]{E4, D3});
        MOVE_MAPPER.put(A4, new FIELD[]{A1, A7, B4});
        MOVE_MAPPER.put(B4, new FIELD[]{B2, B6, A4, C4});
        MOVE_MAPPER.put(C4, new FIELD[]{C3, C5, B4});
        MOVE_MAPPER.put(E4, new FIELD[]{E3, E5, F4});
        MOVE_MAPPER.put(F4, new FIELD[]{F2, F6, E4, G4});
        MOVE_MAPPER.put(G4, new FIELD[]{G1, G7, F4});
        MOVE_MAPPER.put(C5, new FIELD[]{C4, D5});
        MOVE_MAPPER.put(D5, new FIELD[]{D6, C5, E5});
        MOVE_MAPPER.put(E5, new FIELD[]{E4, D5});
        MOVE_MAPPER.put(B6, new FIELD[]{B4, D6});
        MOVE_MAPPER.put(D6, new FIELD[]{D5, D7, B6, F6});
        MOVE_MAPPER.put(F6, new FIELD[]{D6, F4});
        MOVE_MAPPER.put(A7, new FIELD[]{A4, D7});
        MOVE_MAPPER.put(D7, new FIELD[]{D6, A7, G7});
        MOVE_MAPPER.put(G7, new FIELD[]{G4, D7});
    }
}
