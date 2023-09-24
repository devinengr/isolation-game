package action;

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
        int row = fromCell.getRow();
        int col = fromCell.getCol();
        for (int rowI = row - 1; rowI <= row + 1; rowI++) {
            for (int colI = col - 1; colI <= col + 1; colI++) {
                GameCell cell = gameBoard.getCell(rowI, colI);
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
