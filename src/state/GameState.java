package state;

import board.GameBoard;
import board.GameCell;
import player.Player;

public class GameState {

    private GameStateType gameStateType;
    private GameBoard gameBoard;

    // todo references to players contained by GameCells. these are pointers
    // todo which are not to be cloned. upon clone, point these to the new
    // todo cells
    // private Player player1;
    // private Player player2;
    // todo only implement the above if viable after getting this version working
    // todo if implementing, implement for the player cells as well

    public GameState() {
        this.gameStateType = GameStateType.PLAYER_SELECT;
        this.gameBoard = new GameBoard();
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    private Player findCurrentPlayer() {
        for (GameCell cells[] : gameBoard.getCells()) {
            for (GameCell cell : cells) {
                if (cell.getPlayer() != null) {
                    if (cell.getPlayer().isCurrentPlayer()) {
                        return cell.getPlayer();
                    }
                }
            }
        }
        return null;
    }

    private Player findWaitingPlayer() {
        for (GameCell cells[] : gameBoard.getCells()) {
            for (GameCell cell : cells) {
                if (cell.getPlayer() != null) {
                    if (!cell.getPlayer().isCurrentPlayer()) {
                        return cell.getPlayer();
                    }
                }
            }
        }
        return null;
    }

    public Player getCurrentPlayer() {
        return findCurrentPlayer();
    }

    public Player getWaitingPlayer() {
        return findWaitingPlayer();
    }

    public GameStateType getGameStateType() {
        return gameStateType;
    }

    public void updateCurrentPlayer() {
        Player currentPlayer = findCurrentPlayer();
        Player waitingPlayer = findWaitingPlayer();
        currentPlayer.setCurrentPlayer(false);
        waitingPlayer.setCurrentPlayer(true);
    }

    public void setGameStateType(GameStateType gameStateType) {
        this.gameStateType = gameStateType;
    }

    public GameCell getCurrentPlayerCell() {
        for (GameCell cells[] : gameBoard.getCells()) {
            for (GameCell cell : cells) {
                if (cell.getPlayer() != null) {
                    if (cell.getPlayer().isCurrentPlayer()) {
                        return cell;
                    }
                }
            }
        }
        return null;
    }

    public GameCell getWaitingPlayerCell() {
        for (GameCell cells[] : gameBoard.getCells()) {
            for (GameCell cell : cells) {
                if (cell.getPlayer() != null) {
                    if (!cell.getPlayer().isCurrentPlayer()) {
                        return cell;
                    }
                }
            }
        }
        return null;
    }

}
