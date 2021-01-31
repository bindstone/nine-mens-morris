package com.codingame.game.enumerations;

import com.codingame.game.InvalidAction;

public enum FIELD {

    A1(0), D1(1), G1(2),
    B2(3), D2(4), F2(5),
    C3(6), D3(7), E3(8),
    A4(9), B4(10), C4(11),
    E4(12), F4(13), G4(14),
    C5(15), D5(16), E5(17),
    B6(18), D6(19), F6(20),
    A7(21), D7(22), G7(23);

    public final int pos;

    FIELD(int pos) {
        this.pos = pos;
    }

    public static FIELD getField(int id) {
        switch (id) {
            case 0: return A1;
            case 1: return D1;
            case 2: return G1;
            case 3: return B2;
            case 4: return D2;
            case 5: return F2;
            case 6: return C3;
            case 7: return D3;
            case 8: return E3;
            case 9: return A4;
            case 10: return B4;
            case 11: return C4;
            case 12: return E4;
            case 13: return F4;
            case 14: return G4;
            case 15: return C5;
            case 16: return D5;
            case 17: return E5;
            case 18: return B6;
            case 19: return D6;
            case 20: return F6;
            case 21: return A7;
            case 22: return D7;
            case 23: return G7;
            default: return null;
        }
    }
}
