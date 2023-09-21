package observer.action;

import gui.CellState;
import gui.GameBoard;
import gui.GameCell;

import java.util.ArrayList;
import java.util.List;

public class MoveValidator {

    private GameBoard gameBoard;

    public MoveValidator(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public boolean validateMove(GameCell fromCell, GameCell toCell) {
        List<GameCell> validMoves = validMoves(fromCell);
        return validMoves.contains(toCell);
    }

    public int numberOfValidMoves(GameCell fromCell) {
        List<GameCell> validMoves = validMoves(fromCell);
        return validMoves.size();
    }

    public List<GameCell> validMoves(GameCell fromCell) {
        List<GameCell> validMoves = new ArrayList<>();
        int x = fromCell.getX();
        int y = fromCell.getY();
        for (int row = y - 1; row < y + 1; row++) {
            for (int col = x - 1; col < x + 1; col++) {
                GameCell cell = gameBoard.getCell(row, col);
                if (cell != null) {
                    if (cell.getCellState() == CellState.TOKEN_STATE) {
                        validMoves.add(cell);
                    }
                }
            }
        }
        return validMoves;
    }

}
