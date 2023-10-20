import board.GameBoard;
import gui.GUIWindow;
import state.GameState;
import state.GameStateType;
import state.GameStateUpdater;

import javax.swing.*;

public class Main implements Runnable {

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
        window = new GUIWindow(gameState);
    }

    private void beginLoop() {
        // set initial game state
        GameStateUpdater.playerSelect(gameState);
        int numMoves = 0;
        int trial = 1;

        while (true) {
            if (gameState.getGameStateType() == GameStateType.PLAYER_SELECT) {

                System.out.format("Trial #: %d\n", trial);

                GameStateUpdater.startGame(gameState);

            } else if (gameState.getGameStateType() == GameStateType.IN_PROGRESS) {

                gameState.getCurrentPlayer().move(gameState);
                gameState.getCurrentPlayer().removeToken(gameState);

                numMoves += 1;

                GameStateUpdater.checkGameOverStatus(gameState);

            } else if (gameState.getGameStateType() == GameStateType.GAME_OVER) {

                System.out.format("Moves: %d\n", numMoves);
                trial += 1;

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
