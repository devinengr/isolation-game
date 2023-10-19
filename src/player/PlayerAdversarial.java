package player;

import board.GameCell;
import state.GameState;
import state.GameStateUpdater;
import util.HeuristicUtil;

public class PlayerAdversarial implements PlayerType {

    /**
     * add a pause so the user can watch AIs play vs each other
     */
    private void pause() { // todo make this conditional on main game state to prevent mock states from being hung
        try {
            Thread.sleep(300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move(GameState gameState) {
        pause();
        GameCell fromCell = gameState.getCurrentPlayer().getCell();
        GameCell toCell = HeuristicUtil.getBestMove(gameState);
        GameStateUpdater.movePlayer(gameState, fromCell, toCell);
    }

    @Override
    public void removeToken(GameState gameState) {
        GameCell toRemove = HeuristicUtil.getBestToken(gameState);
        GameStateUpdater.removeToken(gameState, toRemove);
    }

}
