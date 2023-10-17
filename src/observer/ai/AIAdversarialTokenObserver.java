package observer.ai;

import action.PlayerType;
import gui.GameCell;
import observer.Observer;
import state.GameState;
import state.GameStateType;
import state.GameStateHandler;
import state.GameStateUpdater;
import util.HeuristicUtil;

public class AIAdversarialTokenObserver implements Observer {

    @Override
    public void update(GameCell cell, GameStateHandler gameStateHandler) {
        GameState gameState = gameStateHandler.getGameState();
        if (gameState.getGameState() == GameStateType.IN_PROGRESS) {
            if (gameState.getCurrentPlayer().getPlayerType() == PlayerType.AI_ADVERSARIAL) {
                GameCell toRemove = HeuristicUtil.getBestToken();
                GameStateUpdater.removeToken(gameState, toRemove);
            }
        }
    }

}