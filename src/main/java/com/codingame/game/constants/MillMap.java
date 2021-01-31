package com.codingame.game.constants;

import com.codingame.game.enumerations.FIELD;

import java.util.HashMap;
import java.util.Map;

import static com.codingame.game.enumerations.FIELD.*;

public class MillMap {
    public static final Map<FIELD, FIELD[][]> MILL_MAPPER;
    static {
        MILL_MAPPER = new HashMap<>();
        MILL_MAPPER.put(A1, new FIELD[][]{{A4, A7}, {D1, G1}});
        MILL_MAPPER.put(D1, new FIELD[][]{{D2, D3}, {A1, G1}});
        MILL_MAPPER.put(G1, new FIELD[][]{{G4, G7}, {A1, D1}});
        MILL_MAPPER.put(B2, new FIELD[][]{{B4, B6}, {D2, F2}});
        MILL_MAPPER.put(D2, new FIELD[][]{{D1, D3}, {B2, F2}});
        MILL_MAPPER.put(F2, new FIELD[][]{{F4, F6}, {B2, D2}});
        MILL_MAPPER.put(C3, new FIELD[][]{{C4, C5}, {D3, E3}});
        MILL_MAPPER.put(D3, new FIELD[][]{{D2, D1}, {C3, E3}});
        MILL_MAPPER.put(E3, new FIELD[][]{{E4, E5}, {C3, D3}});
        MILL_MAPPER.put(A4, new FIELD[][]{{A1, A7}, {B4, C4}});
        MILL_MAPPER.put(B4, new FIELD[][]{{B2, B6}, {A4, C4}});
        MILL_MAPPER.put(C4, new FIELD[][]{{C3, C5}, {A4, B4}});
        MILL_MAPPER.put(E4, new FIELD[][]{{E3, E5}, {F4, G4}});
        MILL_MAPPER.put(F4, new FIELD[][]{{F2, F6}, {E4, G4}});
        MILL_MAPPER.put(G4, new FIELD[][]{{G1, G7}, {E4, F4}});
        MILL_MAPPER.put(C5, new FIELD[][]{{C4, C3}, {D5, E5}});
        MILL_MAPPER.put(D5, new FIELD[][]{{D6, D7}, {C5, E5}});
        MILL_MAPPER.put(E5, new FIELD[][]{{E4, E3}, {C5, D5}});
        MILL_MAPPER.put(B6, new FIELD[][]{{B4, B2}, {D6, F6}});
        MILL_MAPPER.put(D6, new FIELD[][]{{D5, D7}, {B6, F6}});
        MILL_MAPPER.put(F6, new FIELD[][]{{D6, B6}, {F4, F2}});
        MILL_MAPPER.put(A7, new FIELD[][]{{A1, A4}, {D7, G7}});
        MILL_MAPPER.put(D7, new FIELD[][]{{D5, D6}, {A7, G7}});
        MILL_MAPPER.put(G7, new FIELD[][]{{G1, G4}, {A7, D7}});
    }
}
