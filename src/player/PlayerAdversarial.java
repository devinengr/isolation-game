package player;

import board.GameCell;
import state.GameState;
import state.GameStateUpdater;
import util.FirstHeuristicUtil;

public class PlayerAdversarial implements PlayerType {

    private boolean mainStatePlayer;

    @Override
    public PlayerAdversarial clone() {
        return new PlayerAdversarial(false);
    }

    public PlayerAdversarial(boolean mainStatePlayer) {
        this.mainStatePlayer = mainStatePlayer;
    }

    /**
     * add a pause so the user can watch AIs play vs each other
     */
    private void pause() {
        // check before pausing to prevent long pauses in non-main states being checked
        if (mainStatePlayer) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void move(GameState gameState) {
        pause();
        GameCell fromCell = gameState.getCurrentPlayerCell();
        GameCell toCell = FirstHeuristicUtil.getBestMove(gameState);
        GameStateUpdater.movePlayer(gameState, fromCell, toCell);
    }

    @Override
    public void removeToken(GameState gameState) {
        GameCell toRemove = FirstHeuristicUtil.getBestToken(gameState);
        GameStateUpdater.removeToken(gameState, toRemove);
    }

}
