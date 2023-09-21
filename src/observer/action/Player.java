package observer.action;

import gui.GameCell;

public class Player {

    private PlayerType playerType;
    private int playerNumber;
    private GameCell cell;

    public Player(PlayerType playerType, GameCell cell, int playerNumber) {
        this.playerType = playerType;
        this.cell = cell;
        this.playerNumber = playerNumber;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setCell(GameCell cell) {
        this.cell = cell;
    }

    public GameCell getCell() {
        return cell;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

}
