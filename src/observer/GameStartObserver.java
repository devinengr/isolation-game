package observer;

import action.Player;
import gui.GameCell;
import gui.gui.WindowUtil;
import state.GameState;
import state.GameStateType;
import state.GameStateHandler;
import state.GameStateUpdater;

public class GameStartObserver implements Observer {

    @Override
    public void update(GameCell cell, GameStateHandler gameStateHandler) {
        GameState gameState = gameStateHandler.getGameState();
        if (gameState.getGameState() == GameStateType.PLAYER_SELECT) {
            GameStateUpdater.startGame(gameState);
        }
    }



}
