package com.codingame.game;

import com.codingame.game.enumerations.FIELD;
import com.codingame.game.enumerations.STATE;
import com.codingame.game.factories.StoneFactory;
import com.codingame.gameengine.module.entities.Curve;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.codingame.gameengine.module.entities.Group;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.codingame.game.constants.Command.*;
import static com.codingame.game.constants.FieldMap.FIELD_MAPPER;
import static com.codingame.game.constants.FieldMap.FIELD_POSITION_MAPPER;
import static com.codingame.game.constants.MillMap.MILL_MAPPER;
import static com.codingame.game.constants.MoveMap.MOVE_MAPPER;
import static com.codingame.game.constants.StarterList.BLACK_STARTER;
import static com.codingame.game.constants.StarterList.WHITE_STARTER;
import static com.codingame.game.enumerations.STATE.*;

public class Board {

    List<STATE> fields;
    List<Group> boardStones;
    List<Group> whiteStones;
    List<Group> blackStones;
    int blackPlace = 0;
    int whitePlace = 0;
    int blackTake = 0;
    int whiteTake = 0;
    int possibleMoves = 0;

    @Inject
    private GraphicEntityModule graphicEntityModule;

    public void init() {
        fields = new ArrayList<>();
        boardStones = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            fields.add(FREE);
            boardStones.add(null);
        }
        whiteStones = new ArrayList<>();
        WHITE_STARTER.forEach(point -> {
            whiteStones.add(StoneFactory.createWhiteStone(graphicEntityModule, point));
        });
        blackStones = new ArrayList<>();
        BLACK_STARTER.forEach(point -> {
            blackStones.add(StoneFactory.createBlackStone(graphicEntityModule, point));
        });
    }

    List<String> getMoves(Player player) {
        STATE p = player.getIndex() == 0 ? PLAYER0 : PLAYER1;
        STATE op = player.getIndex() == 0 ? PLAYER1 : PLAYER0;
        int stonecounts = player.getIndex() == 0 ? blackTake : whiteTake;
        List<FIELD> takable = getTakable(op);
        List<String> rtn = new ArrayList<>();
        if ((player.getIndex() == 0 && whitePlace < 9) ||
                (player.getIndex() == 1 && blackPlace < 9)) {
            // PLACE
            for (int i = 0; i < 24; i++) {
                if (FREE.equals(fields.get(i))) {
                    if (isPlaceMill(FIELD.getField(i), p)) {
                        for (FIELD take : takable) {
                            rtn.add("PLACE&TAKE;" + FIELD.getField(i).name() + ";" + take.name());
                        }
                    } else {
                        rtn.add("PLACE;" + FIELD.getField(i).name());
                    }
                }
            }
        } else {
            // MOVE (JUMP)
            if (stonecounts == 6) {
                for (int i = 0; i < 24; i++) {
                    if (p.equals(fields.get(i))) {
                        for (int j = 0; j < 24; j++) {
                            if (FREE.equals(fields.get(j))) {
                                if (isMoveMill(FIELD.getField(i), FIELD.getField(j), p)) {
                                    for (FIELD take : takable) {
                                        rtn.add("MOVE&TAKE;" + FIELD.getField(i).name() + ";" + FIELD.getField(j).name() + ";" + take.name());
                                    }
                                } else {
                                    rtn.add("MOVE;" + FIELD.getField(i).name() + ";" + FIELD.getField(j).name());
                                }
                            }
                        }
                    }
                }
                // MOVE (SIMPLE)
            } else {
                for (int i = 0; i < 24; i++) {
                    if (p.equals(fields.get(i))) {
                        List<FIELD> movable = getMovable(FIELD.getField(i));
                        for (FIELD move : movable) {
                            if (isMoveMill(FIELD.getField(i), move, p)) {
                                for (FIELD take : takable) {
                                    rtn.add("MOVE&TAKE;" + FIELD.getField(i).name() + ";" + move.name() + ";" + take.name());
                                }
                            } else {
                                rtn.add("MOVE;" + FIELD.getField(i).name() + ";" + move.name());
                            }
                        }
                    }
                }
            }
        }
        possibleMoves = rtn.size();
        return rtn;
    }

    private List<FIELD> getMovable(FIELD field) {
        FIELD[] combos = MOVE_MAPPER.get(field);
        List<FIELD> rtn = new ArrayList<>();
        for (FIELD combi : combos) {
            if (fields.get(combi.pos) == FREE) {
                rtn.add(combi);
            }
        }
        return rtn;
    }

    private List<FIELD> getTakable(STATE player) {
        List<FIELD> rtn = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            if (player.equals(fields.get(i)) && !isPlaceMill(FIELD.getField(i), player)) {
                rtn.add(FIELD.getField(i));
            }
        }
        if (rtn.isEmpty()) {
            for (int i = 0; i < 24; i++) {
                if (player.equals(fields.get(i))) {
                    rtn.add(FIELD.getField(i));
                }
            }
        }
        return rtn;
    }

    void exec(Action action) throws InvalidAction {
        if ((PLACE.equals(action.command) || PLACE_AND_TAKE.equals(action.command))) {
            place(action);
        }
        if ((MOVE.equals(action.command) || MOVE_AND_TAKE.equals(action.command))) {
            move(action);
        }
    }

    void move(Action action) throws InvalidAction {
        Group source = boardStones.get(action.f1.pos);
        STATE p = action.player.getIndex() == 0 ? PLAYER0 : PLAYER1;

        if (source != null && action.f2 != null && FIELD_POSITION_MAPPER.get(action.f2) != null) {

            source.setX(FIELD_POSITION_MAPPER.get(action.f2).getX(), Curve.EASE_IN_AND_OUT);
            source.setY(FIELD_POSITION_MAPPER.get(action.f2).getY(), Curve.EASE_IN_AND_OUT);

            boardStones.set(action.f1.pos, null);
            boardStones.set(action.f2.pos, source);
            fields.set(action.f1.pos, FREE);
            fields.set(action.f2.pos, p);
        }

        if (MOVE_AND_TAKE.equals(action.command)) {
            if (action.player.getIndex() == 0) {
                Group take = boardStones.get(action.f3.pos);
                take.setX(WHITE_STARTER.get(whiteTake).getX(), Curve.EASE_IN_AND_OUT);
                take.setY(WHITE_STARTER.get(whiteTake).getY(), Curve.EASE_IN_AND_OUT);
                boardStones.set(action.f3.pos, null);
                fields.set(action.f3.pos, FREE);
                whiteTake++;
            } else {
                Group take = boardStones.get(action.f3.pos);
                take.setX(BLACK_STARTER.get(blackTake).getX(), Curve.EASE_IN_AND_OUT);
                take.setY(BLACK_STARTER.get(blackTake).getY(), Curve.EASE_IN_AND_OUT);
                boardStones.set(action.f3.pos, null);
                fields.set(action.f3.pos, FREE);
                blackTake++;
            }
        }
    }

    void place(Action action) throws InvalidAction {
        if (action.player.getIndex() == 0) {
            Group stone = whiteStones.get(whitePlace);
            whiteStones.set(whitePlace, null);
            stone.setX(FIELD_POSITION_MAPPER.get(action.f1).getX(), Curve.EASE_IN_AND_OUT);
            stone.setY(FIELD_POSITION_MAPPER.get(action.f1).getY(), Curve.EASE_IN_AND_OUT);
            boardStones.set(action.f1.pos, stone);
            fields.set(action.f1.pos, PLAYER0);
            whitePlace++;
            if (PLACE_AND_TAKE.equals(action.command)) {
                Group take = boardStones.get(action.f2.pos);
                take.setX(WHITE_STARTER.get(whiteTake).getX(), Curve.EASE_IN_AND_OUT);
                take.setY(WHITE_STARTER.get(whiteTake).getY(), Curve.EASE_IN_AND_OUT);
                boardStones.set(action.f2.pos, null);
                fields.set(action.f2.pos, FREE);
                whiteTake++;
            }
        } else {
            Group stone = blackStones.get(blackPlace);
            blackStones.set(blackPlace, null);
            stone.setX(FIELD_POSITION_MAPPER.get(action.f1).getX(), Curve.EASE_IN_AND_OUT);
            stone.setY(FIELD_POSITION_MAPPER.get(action.f1).getY(), Curve.EASE_IN_AND_OUT);
            boardStones.set(action.f1.pos, stone);
            fields.set(action.f1.pos, PLAYER1);
            blackPlace++;
            if (PLACE_AND_TAKE.equals(action.command)) {
                Group take = boardStones.get(action.f2.pos);
                take.setX(BLACK_STARTER.get(blackTake).getX(), Curve.EASE_IN_AND_OUT);
                take.setY(BLACK_STARTER.get(blackTake).getY(), Curve.EASE_IN_AND_OUT);
                boardStones.set(action.f2.pos, null);
                fields.set(action.f2.pos, FREE);
                blackTake++;
            }
        }
    }

    private boolean isPlaceMill(FIELD field, STATE player) {
        FIELD[][] combos = MILL_MAPPER.get(field);
        boolean rtn = false;
        for (FIELD[] combi : combos) {
            rtn = rtn || (fields.get(combi[0].pos) == player && fields.get(combi[1].pos) == player);
        }
        return rtn;
    }

    private boolean isMoveMill(FIELD fromField, FIELD toField, STATE player) {
        FIELD[][] combos = MILL_MAPPER.get(toField);
        boolean rtn = false;
        for (FIELD[] combi : combos) {
            rtn = rtn || (
                    (combi[0] != fromField && combi[1] != fromField) &&
                            (fields.get(combi[0].pos) == player && fields.get(combi[1].pos) == player));
        }
        return rtn;
    }


    public void validateAction(Action action) throws InvalidAction {
        // Validate Structure
        if (!(Objects.isNull(action.command) ||
                PLACE.equalsIgnoreCase(action.command) ||
                PLACE_AND_TAKE.equalsIgnoreCase(action.command) ||
                MOVE.equalsIgnoreCase(action.command) ||
                MOVE_AND_TAKE.equalsIgnoreCase(action.command))) {
            throw new InvalidAction("UNKNOWN COMMAND: [" + action.command + "]");
        }
        if (Objects.isNull(action.field1) || !FIELD_MAPPER.containsKey(action.field1)) {
            throw new InvalidAction("FIELD1 ERROR: [" + action.field1 + "]");
        }
        if ((PLACE_AND_TAKE.equalsIgnoreCase(action.command) ||
                MOVE.equalsIgnoreCase(action.command) ||
                MOVE_AND_TAKE.equalsIgnoreCase(action.command)) &&
                (Objects.isNull(action.field2) || !FIELD_MAPPER.containsKey(action.field2))) {
            throw new InvalidAction("FIELD2 ERROR: [" + action.field2 + "]");
        }
        if ((MOVE_AND_TAKE.equalsIgnoreCase(action.command)) &&
                (Objects.isNull(action.field3) || !FIELD_MAPPER.containsKey(action.field3))) {
            throw new InvalidAction("FIELD3 ERROR: [" + action.field3 + "]");
        }

        action.f1 = action.field1 == null ? null : FIELD_MAPPER.get(action.field1);
        action.f2 = action.field2 == null ? null : FIELD_MAPPER.get(action.field2);
        action.f3 = action.field3 == null ? null : FIELD_MAPPER.get(action.field3);

        // Validate Data
        if ((PLACE.equals(action.command) || PLACE_AND_TAKE.equals(action.command)) &&
                fields.get(action.f1.pos) != FREE) {
            throw new InvalidAction("NO PLACE MOVEMENTS POSSIBLE (Field not free)");
        }

        if (PLACE_AND_TAKE.equalsIgnoreCase(action.command) && Objects.isNull(action.f2)) {
            throw new InvalidAction("NO CAPTURE DEFINED (Miss Field2)");
        }

        if (MOVE_AND_TAKE.equalsIgnoreCase(action.command) && Objects.isNull(action.f3)) {
            throw new InvalidAction("NO CAPTURE DEFINED (Miss Field3)");
        }

        // PLACE VALIDATION
        if (PLACE.equals(action.command) || PLACE_AND_TAKE.equals(action.command)) {
            if (action.player.getIndex() == 0) {
                if (whitePlace > 8) {
                    throw new InvalidAction("NO PLACE MOVEMENTS POSSIBLE (All stones placed)");
                }
                if (PLACE.equals(action.command) && isPlaceMill(action.f1, PLAYER0)) {
                    throw new InvalidAction("A MILL WITHOUT TAKEN");
                }
                if (PLACE_AND_TAKE.equals(action.command) && !isPlaceMill(action.f1, PLAYER0)) {
                    throw new InvalidAction("NO MILL FOR TAKEN");
                }
                if (PLACE_AND_TAKE.equals(action.command)) {
                    List<FIELD> takable = getTakable(PLAYER1);
                    if(!takable.contains(action.f2)) {
                        throw new InvalidAction("STONE CANNOT BE TAKEN");
                    }
                }
            } else {
                if (blackPlace > 8) {
                    throw new InvalidAction("NO PLACE MOVEMENTS POSSIBLE (All stones placed)");
                }
                if (PLACE.equals(action.command) && isPlaceMill(action.f1, PLAYER1)) {
                    throw new InvalidAction("A MILL WITHOUT TAKEN");
                }
                if (PLACE_AND_TAKE.equals(action.command) && !isPlaceMill(action.f1, PLAYER1)) {
                    throw new InvalidAction("NO MILL FOR TAKEN");
                }
                if (PLACE_AND_TAKE.equals(action.command)) {
                    List<FIELD> takable = getTakable(PLAYER0);
                    if(!takable.contains(action.f2)) {
                        throw new InvalidAction("STONE CANNOT BE TAKEN");
                    }
                }
            }
        }

        // MOVE VALIDATIONS
        if (MOVE.equals(action.command) || MOVE_AND_TAKE.equals(action.command)) {
            if (fields.get(action.f2.pos) != FREE) {
                throw new InvalidAction("NO PLACE MOVEMENTS POSSIBLE (Field not free)");
            }

            if (action.player.getIndex() == 0) {
                if (blackTake < 6 && !Arrays.asList(MOVE_MAPPER.get(action.f1)).contains(action.f2)) {
                    throw new InvalidAction("INVALID MOVE (Field not in range)");
                }
                if (fields.get(action.f1.pos) != PLAYER0) {
                    throw new InvalidAction("TRY TO MOVE FALSE STONE (Opponent Stone)");
                }
                if (MOVE.equals(action.command) && isMoveMill(action.f1, action.f2, PLAYER0)) {
                    throw new InvalidAction("A MILL WITHOUT TAKEN");
                }
                if (MOVE_AND_TAKE.equals(action.command) && !isMoveMill(action.f1, action.f2, PLAYER0)) {
                    throw new InvalidAction("NO MILL FOR TAKEN");
                }
                if (PLACE_AND_TAKE.equals(action.command)) {
                    List<FIELD> takable = getTakable(PLAYER1);
                    if(!takable.contains(action.f3)) {
                        throw new InvalidAction("STONE CANNOT BE TAKEN");
                    }
                }
            } else {
                if (whiteTake < 6 && !Arrays.asList(MOVE_MAPPER.get(action.f1)).contains(action.f2)) {
                    throw new InvalidAction("INVALID MOVE (Field not in range)");
                }
                if (fields.get(action.f1.pos) != PLAYER1) {
                    throw new InvalidAction("TRY TO MOVE FALSE STONE (Opponent Stone)");
                }
                if (MOVE.equals(action.command) && isMoveMill(action.f1, action.f2, PLAYER1)) {
                    throw new InvalidAction("A MILL WITHOUT TAKEN");
                }
                if (MOVE_AND_TAKE.equals(action.command) && !isMoveMill(action.f1, action.f2, PLAYER1)) {
                    throw new InvalidAction("NO MILL FOR TAKEN");
                }
                if (PLACE_AND_TAKE.equals(action.command)) {
                    List<FIELD> takable = getTakable(PLAYER0);
                    if(!takable.contains(action.f3)) {
                        throw new InvalidAction("STONE CANNOT BE TAKEN");
                    }
                }
            }
        }
    }
}

