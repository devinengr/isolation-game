package observer.ai;

import action.MoveType;
import action.PlayerType;
import gui.CellState;
import gui.GameCell;
import observer.Observer;
import state.GameState;
import state.GameStateHandler;
import util.GameBoardUtil;

public class AIAdversarialTokenObserver implements Observer {

    @Override
    public void update(GameCell cell, GameStateHandler gameStateHandler) {
        if (gameStateHandler.getGameState() == GameState.IN_PROGRESS) {
            if (gameStateHandler.getCurrentPlayer().getPlayerType() == PlayerType.AI_ADVERSARIAL) {
//                GameCell toRemove = GameBoardUtil.randomTokenCell();
//                updateGameState(gameStateHandler, toRemove);
                // todo implement
            }
        }
    }

    private void updateGameState(GameStateHandler gameStateHandler, GameCell cell) {
        gameStateHandler.updateCurrentPlayer();
        gameStateHandler.setCurrentMove(MoveType.MOVE_PLAYER_TOKEN);
        cell.setCellState(CellState.BLANK_STATE);
        if (GameBoardUtil.numberOfValidMoves(gameStateHandler.getCurrentPlayer().getCell()) <= 0) {
            gameStateHandler.setGameState(GameState.GAME_OVER);
        } else {
            gameStateHandler.getSubject().notifyObservers(cell.getGameBoard().getCell(0, 0));
        }
    }

}