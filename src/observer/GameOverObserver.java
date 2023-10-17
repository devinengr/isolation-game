package observer;

import gui.GameCell;
import gui.gui.WindowUtil;
import state.GameState;
import state.GameStateType;
import state.GameStateHandler;
import state.GameStateUpdater;

public class GameOverObserver implements Observer {

    @Override
    public void update(GameCell cell, GameStateHandler gameStateHandler) {
        GameState gameState = gameStateHandler.getGameState();
        if (gameState.getGameState() == GameStateType.GAME_OVER) {
            gameState.updateCurrentPlayer();
            if (WindowUtil.getPlayAgain(gameState.getCurrentPlayer())) {
                GameStateUpdater.playerSelect(gameState, cell);
            } else {
                System.exit(0);
            }
        }
    }



}
