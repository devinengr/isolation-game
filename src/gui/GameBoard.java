package gui;

import tile.Tile;

public final class GameBoard {

    private static GameBoard gameBoard;
    private static final int ROWS = 8;
    private static final int COLS = 6;

    private Tile[][] grid;

    private GameBoard() {
        grid = new Tile[ROWS][COLS];
    }

    public Tile getTile(int row, int col) {
        return grid[row][col];
    }

    public void setTile(Tile tile, int row, int col) {
        grid[row][col] = tile;
    }

    public static GameBoard getSingleton() {
        if (gameBoard == null) {
            gameBoard = new GameBoard();
        }
        return gameBoard;
    }

}
