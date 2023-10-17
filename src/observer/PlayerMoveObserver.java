package observer;

import action.PlayerType;
import gui.GameCell;
import action.MoveType;
import state.GameState;
import state.GameStateUpdater;
import util.GameBoardUtil;
import state.GameStateType;
import state.GameStateHandler;

public class PlayerMoveObserver implements Observer {

    @Override
    public void update(GameCell toCell, GameStateHandler gameStateHandler) {
        GameState gameState = gameStateHandler.getGameState();
        if (gameState.getGameState() == GameStateType.IN_PROGRESS) {
            if (gameState.getCurrentMove() == MoveType.MOVE_PLAYER_TOKEN) {
                if (gameState.getCurrentPlayer().getPlayerType() == PlayerType.YOU) {
                    GameCell fromCell = gameState.getCurrentPlayer().getCell();
                    if (GameBoardUtil.validateMove(fromCell, toCell)) {
                        GameStateUpdater.movePlayer(gameState, fromCell, toCell);
                    }
                }
            }
        }
    }

}
