package state;

import action.MoveType;
import action.Player;

public class GameStateHandler {

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private MoveType currentMove;
    private GameState gameState;
    private GameStateSubject subject;

    public GameStateHandler(GameStateSubject subject) {
        this.gameState = GameState.PLAYER_SELECT;
        this.currentMove = MoveType.MOVE_PLAYER_TOKEN;
        this.subject = subject;
    }

    public GameStateSubject getSubject() {
        return subject;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
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

    public Player getWaitingPlayer() {
        if (currentPlayer == player1) {
            return player2;
        }
        return player1;
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

    public void updateCurrentPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public void setCurrentMove(MoveType currentMove) {
        this.currentMove = currentMove;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

}
