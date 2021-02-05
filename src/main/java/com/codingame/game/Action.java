package com.codingame.game;

import com.codingame.game.enumerations.FIELD;

import java.util.Objects;

public class Action {
    public Player player;
    public String command;
    public String field1;
    public String field2;
    public String field3;
    public FIELD f1;
    public FIELD f2;
    public FIELD f3;

    public Action(Player player, String command, String field1, String field2, String field3) {
        this.player = player;
        this.command = command;
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public Action(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return Objects.equals(player, action.player) &&
                Objects.equals(command, action.command) &&
                Objects.equals(field1, action.field1) &&
                Objects.equals(field2, action.field2) &&
                Objects.equals(field3, action.field3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, command, field1, field2, field3);
    }

    @Override
    public String toString() {
        return ((command == null ? "**invalid**" : command) + " " +
                (field1 == null ? "**invalid**" : field1) + " " +
                (field2 == null ? "" : field2) + " " +
                (field3 == null ? "" : field3)).trim();
    }

    public String move() {
        return (command + ";" + field1 +
                (field2 == null ? "" : (";" + field2)) +
                (field3 == null ? "" : (";" + field3)));
    }
}