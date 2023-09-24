package observer;

import action.MoveType;
import action.MoveValidator;
import action.Player;
import gui.CellState;
import gui.GameCell;
import state.GameState;
import state.GameStateHandler;

public class TokenRemoveObserver implements Observer {

    private MoveValidator moveValidator;

    public TokenRemoveObserver(MoveValidator moveValidator) {
        this.moveValidator = moveValidator;
    }

    @Override
    public void update(GameCell cell, GameStateHandler gameStateHandler) {
        if (gameStateHandler.getGameState() == GameState.IN_PROGRESS) {
            if (gameStateHandler.getCurrentMove() == MoveType.REMOVE_TILE_TOKEN) {
                if (cell.getCellState() == CellState.TOKEN_STATE) {
                    updateGameState(gameStateHandler, cell);
                }
            }
        }
    }

    private void updateGameState(GameStateHandler gameStateHandler, GameCell cell) {
        gameStateHandler.updateCurrentPlayer();
        gameStateHandler.setCurrentMove(MoveType.MOVE_PLAYER_TOKEN);
        cell.setCellState(CellState.BLANK_STATE);
        if (moveValidator.numberOfValidMoves(gameStateHandler.getCurrentPlayer().getCell()) <= 0) {
            gameStateHandler.setGameState(GameState.GAME_OVER);
        }
    }

}
