package state;

import tile.Tile;

public class Player {

    private PlayerType playerType;
    private int playerNumber;
    private Tile tile;

    public Player(PlayerType playerType, Tile tile, int playerNumber) {
        this.playerType = playerType;
        this.tile = tile;
        this.playerNumber = playerNumber;
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

    public int getPlayerNumber() {
        return playerNumber;
    }

}
