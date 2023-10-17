package observer.ai;

import state.GameState;
import state.GameStateUpdater;
import util.GameBoardUtil;
import action.PlayerType;
import gui.GameCell;
import observer.Observer;
import state.GameStateType;
import state.GameStateHandler;

public class AIRandomTokenObserver implements Observer {

    @Override
    public void update(GameCell cell, GameStateHandler gameStateHandler) {
        GameState gameState = gameStateHandler.getGameState();
        if (gameState.getGameState() == GameStateType.IN_PROGRESS) {
            if (gameState.getCurrentPlayer().getPlayerType() == PlayerType.AI_RANDOM) {
                GameCell toRemove = GameBoardUtil.randomTokenCell();
                GameStateUpdater.removeToken(gameState, toRemove);
            }
        }
    }

}
