package tile;

public class Tile {

    private TileState tileState;

    public Tile(TileState tileState) {
        this.tileState = tileState;
    }

    public void updateTileState(TileState tileState) {
        this.tileState = tileState;
    }

}
