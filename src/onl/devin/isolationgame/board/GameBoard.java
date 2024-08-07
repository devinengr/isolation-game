package onl.devin.isolationgame.board;

public class GameBoard {

    public static final int ROWS = 6;
    public static final int COLS = 8;

    private GameCell[][] grid;

    public GameBoard() {
        this.grid = new GameCell[ROWS][COLS];

        // create board
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                // create cell
                GameCell cell = new GameCell(row, col);
                grid[row][col] = cell;
            }
        }
    }

    @Override
    public GameBoard clone() {
        GameBoard newBoard = new GameBoard();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                newBoard.grid[row][col] = grid[row][col].clone();
            }
        }
        return newBoard;
    }

    public GameCell[][] getCells() {
        return grid;
    }

    public GameCell getCell(int row, int col) {
        if (row < 0 ||  row >= ROWS || col < 0 || col >= COLS) {
            return null;
        }
        return grid[row][col];
    }

    public void reset() {
         for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                grid[row][col].setCellState(CellState.TOKEN_STATE);
                grid[row][col].setPlayer(null);
            }
        }
    }

}
