package onl.devin.isolationgame.util;

import onl.devin.isolationgame.board.CellState;
import onl.devin.isolationgame.board.GameBoard;
import onl.devin.isolationgame.board.GameCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class GameBoardUtil {

    private GameBoardUtil() {}

    public static List<GameCell> tokenCells(GameBoard gameBoard) {
        List<GameCell> tokenCells = new ArrayList<>();
        for (int row = 0; row < GameBoard.ROWS; row++) {
            for (int col = 0; col < GameBoard.COLS; col++) {
                GameCell cell = gameBoard.getCell(row, col);
                if (cell.getCellState() == CellState.TOKEN_STATE) {
                    tokenCells.add(cell);
                }
            }
        }
        return tokenCells;
    }

    public static GameCell randomTokenCell(GameBoard gameBoard) {
        List<GameCell> tokenCells = tokenCells(gameBoard);
        int randomIndex = new Random().nextInt(0, tokenCells.size());
        return tokenCells.get(randomIndex);
    }

    public static boolean validateMove(GameBoard gameBoard, GameCell fromCell, GameCell toCell) {
        List<GameCell> validMoves = validMoves(gameBoard, fromCell);
        return validMoves.contains(toCell);
    }

    public static int numberOfValidMoves(GameBoard gameBoard, GameCell fromCell) {
        List<GameCell> validMoves = validMoves(gameBoard, fromCell);
        return validMoves.size();
    }

    public static List<GameCell> validMoves(GameBoard gameBoard, GameCell fromCell) {
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
