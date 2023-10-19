package player;

import board.GameCell;
import state.GameState;
import state.GameStateUpdater;
import util.GameBoardUtil;

import java.util.List;
import java.util.Random;

public class PlayerRandom implements PlayerType {

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
        GameCell toCell = getRandomCell(fromCell);
        GameStateUpdater.movePlayer(gameState, fromCell, toCell);
    }

    @Override
    public void removeToken(GameState gameState) {
        GameCell toRemove = GameBoardUtil.randomTokenCell();
        GameStateUpdater.removeToken(gameState, toRemove);
    }

    private GameCell getRandomCell(GameCell fromCell) {
        List<GameCell> moves = GameBoardUtil.validMoves(fromCell);
        int randomIndex = new Random().nextInt(0, moves.size());
        return moves.get(randomIndex);
    }

}
