package observer;

import action.MoveType;
import state.GameState;
import state.GameStateUpdater;
import action.PlayerType;
import gui.CellState;
import gui.GameCell;
import state.GameStateType;
import state.GameStateHandler;

public class TokenRemoveObserver implements Observer {

    @Override
    public void update(GameCell cell, GameStateHandler gameStateHandler) {
        GameState gameState = gameStateHandler.getGameState();
        if (gameState.getGameState() == GameStateType.IN_PROGRESS) {
            if (gameState.getCurrentMove() == MoveType.REMOVE_TILE_TOKEN) {
                if (gameState.getCurrentPlayer().getPlayerType() == PlayerType.YOU) {
                    if (cell.getCellState() == CellState.TOKEN_STATE) {
                        GameStateUpdater.removeToken(gameState, cell);
                    }
                }
            }
        }
    }

}
