package observer.ai;

import action.MoveType;
import action.MoveValidator;
import action.PlayerType;
import gui.CellState;
import gui.GameCell;
import observer.Observer;
import state.GameState;
import state.GameStateHandler;

import java.util.List;
import java.util.Random;

public class AIRandomMoveObserver implements Observer {

    private MoveValidator moveValidator;

    public AIRandomMoveObserver(MoveValidator moveValidator) {
        this.moveValidator = moveValidator;
    }

    @Override
    public void update(GameCell cell, GameStateHandler gameStateHandler) {
        if (gameStateHandler.getGameState() == GameState.IN_PROGRESS) {
            if (gameStateHandler.getCurrentPlayer().getPlayerType() == PlayerType.RANDOM) {
                GameCell fromCell = gameStateHandler.getCurrentPlayer().getCell();
                GameCell toCell = getRandomCell(fromCell);
                updateGameState(gameStateHandler, fromCell, toCell);
            }
        }
    }

    private GameCell getRandomCell(GameCell fromCell) {
        List<GameCell> moves = moveValidator.validMoves(fromCell);
        int randomIndex = new Random().nextInt(0, moves.size());
        return moves.get(randomIndex);
    }

    private void updateGameState(GameStateHandler gameStateHandler, GameCell fromCell, GameCell toCell) {
        gameStateHandler.setCurrentMove(MoveType.REMOVE_TILE_TOKEN);
        gameStateHandler.getCurrentPlayer().setCell(toCell);
        fromCell.setCellState(CellState.TOKEN_STATE);
        toCell.setCellState(CellState.PLAYER_STATE);
    }

}
