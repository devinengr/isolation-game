package observer;

import gui.GameCell;
import gui.WindowUtil;
import state.GameState;
import state.GameStateHandler;

public class GameOverObserver implements Observer {

    @Override
    public void update(GameCell cell, GameStateHandler gameStateHandler) {
        if (gameStateHandler.getGameState() == GameState.GAME_OVER) {
            gameStateHandler.updateCurrentPlayer();
            if (WindowUtil.getPlayAgain(gameStateHandler.getCurrentPlayer())) {
                updateGameState(gameStateHandler, cell);
            } else {
                System.exit(0);
            }
        }
    }

    private void updateGameState(GameStateHandler gameStateHandler, GameCell cell) {
        cell.getGameBoard().reset();
        gameStateHandler.setGameState(GameState.PLAYER_SELECT);
    }

}
