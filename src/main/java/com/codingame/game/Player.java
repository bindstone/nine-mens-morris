package com.codingame.game;

import com.codingame.gameengine.core.AbstractMultiplayerPlayer;
import com.codingame.gameengine.module.entities.Group;

public class Player extends AbstractMultiplayerPlayer {
    public Group hud;

    @Override
    public int getExpectedOutputLines() {
        return 1;
    }

    public Action getAction() throws TimeoutException, NumberFormatException, InvalidAction {
        String[] output = getOutputs().get(0).split(";");
        if (output.length == 2) {
            return new Action(this, output[0], output[1], null, null);
        } else if (output.length == 3) {
            return new Action(this, output[0], output[1], output[2], null);
        } else if (output.length == 4) {
            return new Action(this, output[0], output[1], output[2], output[3]);
        } else {
            throw new InvalidAction("Invalid number of parameters (" + output.length + ")");
        }
    }
}
