package observer;

import state.MoveType;
import state.Player;

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

}
