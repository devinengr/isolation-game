import board.GameBoard;
import gui.GUIWindow;
import state.GameState;
import state.GameStateSubject;
import state.GameStateType;
import state.GameStateUpdater;
import util.GameBoardUtil;
import util.HeuristicUtil;

import javax.swing.*;

public class Main implements Runnable {

    private GameStateSubject subject;
    private GUIWindow window;
    private GameBoard board;
    private GameState gameState;

    @Override
    public void run() {
        initialize();
        beginLoop();
    }

    private void initialize() {
        gameState = new GameState();
        board = gameState.getGameBoard();
        subject = new GameStateSubject(gameState);
        window = new GUIWindow(subject);

        GameBoardUtil.setGameBoard(board);
        HeuristicUtil.setGameStateHandler(subject.getGameStateHandler());
    }

    private void beginLoop() {
        // set initial game state
        GameStateUpdater.playerSelect(gameState);

        while (true) {
            if (gameState.getGameState() == GameStateType.PLAYER_SELECT) {
                GameStateUpdater.startGame(gameState);
            } else if (gameState.getGameState() == GameStateType.IN_PROGRESS) {
                gameState.getCurrentPlayer().move(gameState);
                gameState.getCurrentPlayer().removeToken(gameState);
            } else if (gameState.getGameState() == GameStateType.GAME_OVER) {
                GameStateUpdater.gameOver(gameState);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Thread(new Main()).start();
        });
    }

}
