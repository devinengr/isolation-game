package tile;

import gui.GameCell;

public class Tile {

    private TileState tileState;
    private int x;
    private int y;

    public Tile(TileState tileState, int x, int y) {
        this.tileState = tileState;
        this.x = x;
        this.y = y;
    }

    public void setTileState(TileState tileState) {
        this.tileState = tileState;
    }

    public TileState getTileState() {
        return tileState;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
