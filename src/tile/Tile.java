package tile;

public class Tile {

    private TileState tileState;

    public Tile(TileState tileState) {
        this.tileState = tileState;
    }

    public TileState getTileState() {
        return tileState;
    }

    public void setTileState(TileState tileState) {
        this.tileState = tileState;
    }

}
