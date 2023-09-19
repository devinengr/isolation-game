package gui;

import tile.Tile;

public final class GameBoard {

    private static GameBoard gameBoard;

    public static final int ROWS = 8;
    public static final int COLS = 6;

    private Tile[][] grid;
    private int tileWidth;
    private int tileHeight;

    private GameBoard() {
        grid = new Tile[ROWS][COLS];
        tileWidth = 50;
        tileHeight = 50;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
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
