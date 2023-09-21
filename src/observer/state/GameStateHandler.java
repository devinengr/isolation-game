package observer.state;

import observer.action.MoveType;
import observer.action.Player;

public class GameStateHandler {

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private MoveType currentMove;
    private GameState gameState;

    public GameStateHandler() {
        this.gameState = GameState.PLAYER_SELECT;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public MoveType getCurrentMove() {
        return currentMove;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setCurrentMove(MoveType currentMove) {
        this.currentMove = currentMove;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

}
