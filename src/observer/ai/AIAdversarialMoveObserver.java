package observer.ai;

import action.PlayerType;
import gui.GameCell;
import observer.Observer;
import state.GameState;
import state.GameStateType;
import state.GameStateHandler;
import state.GameStateUpdater;
import util.HeuristicUtil;

public class AIAdversarialMoveObserver implements Observer {

    @Override
    public void update(GameCell cell, GameStateHandler gameStateHandler) {
        GameState gameState = gameStateHandler.getGameState();
        if (gameState.getGameState() == GameStateType.IN_PROGRESS) {
            if (gameState.getCurrentPlayer().getPlayerType() == PlayerType.AI_ADVERSARIAL) {

                // add a pause so the user can watch AIs play vs each other
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // move the player
                GameCell fromCell = gameState.getCurrentPlayer().getCell();
                GameCell toCell = HeuristicUtil.getBestMove();
                GameStateUpdater.movePlayer(gameState, fromCell, toCell);
            }
        }
    }

}
