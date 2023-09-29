package observer;

import action.MoveType;
import util.GameBoardUtil;
import action.PlayerType;
import gui.CellState;
import gui.GameCell;
import state.GameState;
import state.GameStateHandler;

public class TokenRemoveObserver implements Observer {

    @Override
    public void update(GameCell cell, GameStateHandler gameStateHandler) {
        if (gameStateHandler.getGameState() == GameState.IN_PROGRESS) {
            if (gameStateHandler.getCurrentMove() == MoveType.REMOVE_TILE_TOKEN) {
                if (gameStateHandler.getCurrentPlayer().getPlayerType() == PlayerType.YOU) {
                    if (cell.getCellState() == CellState.TOKEN_STATE) {
                        updateGameState(gameStateHandler, cell);
                    }
                }
            }
        }
    }

    private void updateGameState(GameStateHandler gameStateHandler, GameCell cell) {
        gameStateHandler.updateCurrentPlayer();
        gameStateHandler.setCurrentMove(MoveType.MOVE_PLAYER_TOKEN);
        cell.setCellState(CellState.BLANK_STATE);
        if (GameBoardUtil.numberOfValidMoves(gameStateHandler.getCurrentPlayer().getCell()) <= 0) {
            gameStateHandler.setGameState(GameState.GAME_OVER);
        }
    }

}
