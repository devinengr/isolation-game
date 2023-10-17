package player;

import board.GameCell;
import state.GameState;
import state.GameStateUpdater;
import util.HeuristicUtil;

public class PlayerAdversarial implements PlayerType {

    /**
     * add a pause so the user can watch AIs play vs each other
     */
    private void pause() {
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
        GameCell toCell = HeuristicUtil.getBestMove();
        GameStateUpdater.movePlayer(gameState, fromCell, toCell);
    }

    @Override
    public void removeToken(GameState gameState) {
        GameCell toRemove = HeuristicUtil.getBestToken();
        GameStateUpdater.removeToken(gameState, toRemove);
    }

}
