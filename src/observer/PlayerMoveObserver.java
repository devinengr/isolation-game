package observer;

import action.PlayerType;
import gui.CellState;
import gui.GameCell;
import action.MoveType;
import util.GameBoardUtil;
import state.GameState;
import state.GameStateHandler;

public class PlayerMoveObserver implements Observer {

    @Override
    public void update(GameCell toCell, GameStateHandler gameStateHandler) {
        if (gameStateHandler.getGameState() == GameState.IN_PROGRESS) {
            if (gameStateHandler.getCurrentMove() == MoveType.MOVE_PLAYER_TOKEN) {
                if (gameStateHandler.getCurrentPlayer().getPlayerType() == PlayerType.YOU) {
                    GameCell fromCell = gameStateHandler.getCurrentPlayer().getCell();
                    if (GameBoardUtil.validateMove(fromCell, toCell)) {
                        updateGameState(gameStateHandler, fromCell, toCell);
                    }
                }
            }
        }
    }

    private void updateGameState(GameStateHandler gameStateHandler, GameCell fromCell, GameCell toCell) {
        gameStateHandler.setCurrentMove(MoveType.REMOVE_TILE_TOKEN);
        gameStateHandler.getCurrentPlayer().setCell(toCell);
        fromCell.setCellState(CellState.TOKEN_STATE);
        toCell.setCellState(CellState.PLAYER_STATE);
    }

}
