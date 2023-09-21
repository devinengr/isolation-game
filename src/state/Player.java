package state;

import tile.Tile;

public class Player {

    private PlayerType playerType;
    private Tile tile;

    public Player(PlayerType playerType, Tile tile) {
        this.playerType = playerType;
        this.tile = tile;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }

}
