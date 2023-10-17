package state;

import action.MoveType;
import action.Player;
import gui.GameBoard;

public class GameState {

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private MoveType currentMove;
    private GameStateType gameStateType;
    private GameBoard gameBoard;

    public GameState() {
        this.gameStateType = GameStateType.PLAYER_SELECT;
        this.currentMove = MoveType.MOVE_PLAYER_TOKEN;
        this.gameBoard = new GameBoard();
    }

    public GameBoard getGameBoard() {
        return gameBoard;
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

    public GameStateType getGameState() {
        return gameStateType;
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

    public void setGameState(GameStateType gameStateType) {
        this.gameStateType = gameStateType;
    }

}
