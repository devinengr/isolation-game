package gui;

import tile.Tile;
import tile.TileState;

public final class GameBoard {

    private static GameBoard gameBoard;

    public static final int ROWS = 6;
    public static final int COLS = 8;

    private GameCell[][] grid;
    private int tileWidth;
    private int tileHeight;

    private GameBoard() {
        this.tileWidth = 50;
        this.tileHeight = 50;
        grid = new GameCell[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                // todo temp
                if (row % 2 == 0) {
                    grid[row][col] = new GameCell(new Tile(TileState.TOKEN_STATE), tileWidth, tileHeight);
                } else {
                    grid[row][col] = new GameCell(new Tile(TileState.START_STATE), tileWidth, tileHeight);
                }
                // todo
                // grid[row][col] = new GameCell(new Tile(TileState.TOKEN_STATE), tileWidth, tileHeight);
            }
        }
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public GameCell getGameCell(int row, int col) {
        return grid[row][col];
    }

    public Tile getTile(int row, int col) {
        return grid[row][col].getTile();
    }

    public static GameBoard getSingleton() {
        if (gameBoard == null) {
            gameBoard = new GameBoard();
        }
        return gameBoard;
    }

}
