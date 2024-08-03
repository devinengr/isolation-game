package onl.devin.isolationgame.state;

import onl.devin.isolationgame.board.GameBoard;
import onl.devin.isolationgame.board.GameCell;
import onl.devin.isolationgame.player.Player;

public class GameState {

    private GameStateType gameStateType;
    private GameBoard gameBoard;

    public GameState() {
        this.gameStateType = GameStateType.PLAYER_SELECT;
        this.gameBoard = new GameBoard();
    }

    @Override
    public GameState clone() {
        GameState newState = new GameState();
        newState.gameStateType = gameStateType; // enums are weird
        newState.gameBoard = gameBoard.clone();
        return newState;
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
