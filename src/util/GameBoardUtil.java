package util;

import gui.CellState;
import gui.GameBoard;
import gui.GameCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class GameBoardUtil {

    private static GameBoard gameBoard;

    private GameBoardUtil() {}

    public static void setGameBoard(GameBoard gameBoard) {
        GameBoardUtil.gameBoard = gameBoard;
    }

    public static List<GameCell> tokenCells() {
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

    public static GameCell randomTokenCell() {
        List<GameCell> tokenCells = tokenCells();
        int randomIndex = new Random().nextInt(0, tokenCells.size());
        return tokenCells.get(randomIndex);
    }

    public static boolean validateMove(GameCell fromCell, GameCell toCell) {
        List<GameCell> validMoves = validMoves(fromCell);
        return validMoves.contains(toCell);
    }

    public static int numberOfValidMoves(GameCell fromCell) {
        List<GameCell> validMoves = validMoves(fromCell);
        return validMoves.size();
    }

    public static List<GameCell> validMoves(GameCell fromCell) {
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
