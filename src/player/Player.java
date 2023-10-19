package player;

import state.GameState;

public class Player {

    private PlayerType playerType;
    private boolean currentPlayer = false;
    private int playerNumber;

    public Player(PlayerType playerType, int playerNumber) {
        this.playerType = playerType;
        this.playerNumber = playerNumber;
    }

    @Override
    public Player clone() {
        Player newPlayer = new Player(
                playerType.clone(),
                playerNumber
        );
        newPlayer.currentPlayer = currentPlayer;
        return newPlayer;
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

    public boolean isCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

}
