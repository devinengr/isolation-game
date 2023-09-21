package gui;

import tile.Tile;
import tile.TileState;

import javax.swing.*;

public final class GameBoard {

    private static GameBoard gameBoard;

    public static final int ROWS = 6;
    public static final int COLS = 8;

    private GameCell[][] grid;
    private int tileWidth;
    private int tileHeight;

    private GameBoard() {
        grid = new GameCell[ROWS][COLS];
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
        return grid[row][col].getTile();
    }

    public void setTile(TileState tileState, int row, int col) {
        grid[row][col].getTile().setTileState(tileState);
    }

    public void initializeBoard(JPanel panel) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                GameCell cell = new GameCell(new Tile(TileState.TOKEN_STATE, row, col), row, col, tileWidth, tileHeight);
                cell.addMouseListener(cell);
                panel.add(cell);
                grid[row][col] = cell;
            }
        }
    }

    public static GameBoard getSingleton() {
        if (gameBoard == null) {
            gameBoard = new GameBoard();
        }
        return gameBoard;
    }

}
