package player;

import board.GameCell;
import state.GameState;

public class Player {

    private PlayerType playerType;
    private int playerNumber;
    private GameCell cell;

    public Player(PlayerType playerType, GameCell cell, int playerNumber) {
        this.playerType = playerType;
        this.cell = cell;
        this.playerNumber = playerNumber;
    }

    @Override
    public Player clone() {
        // primitive types aren't objects, no need to clone
        Player newPlayer = new Player(
                playerType.clone(),
                cell.clone(),
                playerNumber
        );
    }

    public void move(GameState gameState) {
        playerType.move(gameState);
    }

    public void removeToken(GameState gameState) {
        playerType.removeToken(gameState);
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
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
