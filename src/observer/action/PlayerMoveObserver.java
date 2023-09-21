package observer.action;

import gui.CellState;
import gui.GameCell;
import observer.Observer;
import observer.state.GameState;
import observer.state.GameStateHandler;

public class PlayerMoveObserver implements Observer {

    private MoveValidator moveValidator;

    public PlayerMoveObserver(MoveValidator moveValidator) {
        this.moveValidator = moveValidator;
    }

    @Override
    public void update(GameCell toCell, GameStateHandler gameStateHandler) {
        if (gameStateHandler.getGameState() == GameState.IN_PROGRESS) {
            if (gameStateHandler.getCurrentMove() == MoveType.MOVE_PLAYER_TOKEN) {
                GameCell fromCell = gameStateHandler.getCurrentPlayer().getCell();
                if (moveValidator.validateMove(fromCell, toCell)) {
                    gameStateHandler.setCurrentMove(MoveType.REMOVE_TILE_TOKEN);
                    gameStateHandler.getCurrentPlayer().setCell(toCell);
                    fromCell.setCellState(CellState.TOKEN_STATE);
                    toCell.setCellState(CellState.PLAYER_STATE);
                }
            }
        }
    }

}
