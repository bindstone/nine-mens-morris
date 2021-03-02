package com.codingame.game;

import com.codingame.gameengine.core.AbstractPlayer.TimeoutException;
import com.codingame.gameengine.core.AbstractReferee;
import com.codingame.gameengine.core.GameManager;
import com.codingame.gameengine.core.MultiplayerGameManager;
import com.codingame.gameengine.core.Tooltip;
import com.codingame.gameengine.module.endscreen.EndScreenModule;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.codingame.gameengine.module.entities.Sprite;
import com.codingame.gameengine.module.entities.Text;
import com.codingame.gameengine.module.toggle.ToggleModule;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.util.List;
import java.util.Objects;

public class Referee extends AbstractReferee {
    @Inject
    private MultiplayerGameManager<Player> gameManager;
    @Inject
    private GraphicEntityModule graphicEntityModule;
    @Inject
    private EndScreenModule endScreenModule;
    @Inject
    private Provider<Board> boardProvider;
    @Inject
    private ToggleModule toggleModule;

    private Board board;

    @Override
    public void init() {

        drawBackground();
        drawHud();
        drawBoard();

        gameManager.setFrameDuration(600);
        gameManager.setMaxTurns(200);
        gameManager.setTurnMaxTime(50);

        board.init();
    }

    private void drawBackground() {
        graphicEntityModule.createSprite()
                .setImage("Background.jpg")
                .setAnchor(0);
    }

    private void drawBoard() {

        board = boardProvider.get();

        toggleModule.displayOnToggleState(
                graphicEntityModule
                        .createSprite()
                        .setImage("board.png")
                        .setX(950)
                        .setY(550)
                        .setAnchor(0.5),
                "debugToggle", false);
        toggleModule.displayOnToggleState(
                graphicEntityModule
                        .createSprite()
                        .setImage("board_dbg.png")
                        .setX(950)
                        .setY(550)
                        .setAnchor(0.5),
                "debugToggle", true);

        /**
        graphicEntityModule
                .createSprite()
                .setImage("board.png")
                .setX(950)
                .setY(550)
                .setAnchor(0.5);
        **/

        graphicEntityModule
                .createSprite()
                .setImage("starter.png")
                .setX(300)
                .setY(500)
                .setAnchor(0.5);

        graphicEntityModule
                .createSprite()
                .setImage("starter.png")
                .setX(1600)
                .setY(500)
                .setAnchor(0.5);
    }

    private void drawHud() {
        for (Player player : gameManager.getPlayers()) {
            int x = player.getIndex() == 0 ? 280 : 1920 - 300;
            int y = 150;

            graphicEntityModule
                    .createRectangle()
                    .setWidth(140)
                    .setHeight(140)
                    .setX(x - 70)
                    .setY(y - 70)
                    .setLineWidth(0)
                    .setFillColor(player.getColorToken());

            graphicEntityModule
                    .createRectangle()
                    .setWidth(120)
                    .setHeight(120)
                    .setX(x - 60)
                    .setY(y - 60)
                    .setLineWidth(0)
                    .setFillColor(0xffffff);

            Text text = graphicEntityModule.createText(player.getNicknameToken())
                    .setX(x)
                    .setY(y + 120)
                    .setZIndex(20)
                    .setFontSize(40)
                    .setFillColor(0xffffff)
                    .setAnchor(0.5);

            Sprite avatar = graphicEntityModule.createSprite()
                    .setX(x)
                    .setY(y)
                    .setZIndex(20)
                    .setImage(player.getAvatarToken())
                    .setAnchor(0.5)
                    .setBaseHeight(116)
                    .setBaseWidth(116);

            player.hud = graphicEntityModule.createGroup(text, avatar);
        }
    }

    @Override
    public void gameTurn(int turn) {
        Player player = gameManager.getPlayer((turn + 1) % gameManager.getPlayerCount());
        Player op = gameManager.getPlayer(turn % gameManager.getPlayerCount());

        if (turn == 1 || turn == 2) {
            player.sendInputLine(String.valueOf(player.getIndex()));
        }

        if (Objects.isNull(board.opMove)) {
            player.sendInputLine("-");
        }else {
            player.sendInputLine(board.opMove);
        }
        player.sendInputLine(board.printBoard());

        sendMoves(player, board.getMoves(player));

        player.execute();
        try {
            final Action action = player.getAction();
            gameManager.addToGameSummary(String.format("Player %s played (%s)", action.player.getNicknameToken(), action.toString()));

            board.validateAction(action);
            board.exec(action);
            board.opMove = action.move();
            if (action.command.contains("TAKE")) {
                gameManager.addTooltip(new Tooltip(player.getIndex(), "TAKE STONE"));
            }

            if (turn == 200) {
                setDraw(player, op);
            }

            if (board.whiteTake > 6 || board.blackTake > 6) {
                setWinner(player);
            }

            if (board.getMoves(op).isEmpty()) {
                setWinner(player);
            }

        } catch (NumberFormatException e) {
            player.deactivate("Wrong output!");
            player.setScore(-1);
            endGame();
        } catch (TimeoutException e) {
            gameManager.addToGameSummary(GameManager.formatErrorMessage(player.getNicknameToken() + " timeout!"));
            player.deactivate(player.getNicknameToken() + " timeout!");
            player.setScore(-1);
            endGame();
        } catch (InvalidAction e) {
            player.deactivate(e.getMessage());
            player.setScore(-1);
            endGame();
        }
    }

    private void setDraw(Player player, Player op) {
        gameManager.addToGameSummary("DRAW after 200 Turns");
        player.setScore(2);
        op.setScore(2);
        endGame();
    }

    private void sendMoves(Player player, List<String> moves) {
        player.sendInputLine(String.valueOf(moves.size()));
        for (String move : moves) {
            player.sendInputLine(move);
        }
    }

    private void setWinner(Player player) {
        gameManager.addToGameSummary(GameManager.formatSuccessMessage(player.getNicknameToken() + " won!"));
        player.setScore(5);
        endGame();
    }

    private void endGame() {
        gameManager.endGame();

        Player p0 = gameManager.getPlayers().get(0);
        Player p1 = gameManager.getPlayers().get(1);
        if (p0.getScore() > p1.getScore()) {
            p1.hud.setAlpha(0.3);
        }
        if (p0.getScore() < p1.getScore()) {
            p0.hud.setAlpha(0.3);
        }
    }

    @Override
    public void onEnd() {
        int[] scores = gameManager.getPlayers().stream().mapToInt(p -> p.getScore()).toArray();
        int score0 = gameManager.getPlayers().get(0).getScore();
        String[] texts = {String.valueOf(scores[0]), String.valueOf(scores[1])};
        String end_png = score0 == 1 ? "end_draw.png" : score0 == 2 ? "end_player1.png" : "end_player2.png";
        endScreenModule.setTitleRankingsSprite(end_png);
        endScreenModule.setScores(scores, texts);
    }
}
