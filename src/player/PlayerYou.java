package player;

import board.CellState;
import board.GameCell;
import state.GameState;
import state.GameStateUpdater;
import util.GameBoardUtil;

public class PlayerYou implements PlayerType {

    @Override
    public void move(GameState gameState) {
        // todo implement, wait for cell click
        GameCell fromCell = gameState.getCurrentPlayer().getCell();
        if (GameBoardUtil.validateMove(fromCell, toCell)) {
            GameStateUpdater.movePlayer(gameState, fromCell, toCell);
        }
    }

    @Override
    public void removeToken(GameState gameState) {
        // todo implement, wait for cell click
        if (cell.getCellState() == CellState.TOKEN_STATE) {
            GameStateUpdater.removeToken(gameState, cell);
        }
    }

}
