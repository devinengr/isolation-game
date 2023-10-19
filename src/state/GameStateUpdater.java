package state;

import board.CellState;
import board.GameBoard;
import board.GameCell;
import gui.WindowUtil;
import player.Player;
import util.GameBoardUtil;

public final class GameStateUpdater {

    private GameStateUpdater() {}

    public static void movePlayer(GameState state, GameCell fromCell, GameCell toCell) {
        // change cell's player data
        Player currentPlayer = fromCell.getPlayer();
        fromCell.setPlayer(null);
        toCell.setPlayer(currentPlayer);

        // set cell states
        fromCell.setCellState(CellState.TOKEN_STATE);
        toCell.setCellState(CellState.PLAYER_STATE);
    }

    public static void removeToken(GameState state, GameCell toRemove) {
        state.updateCurrentPlayer();
        toRemove.setCellState(CellState.BLANK_STATE);
    }

    public static void startGame(GameState state) {
        // initialize cells and players
        GameCell cell1 = state.getGameBoard().getCell(2, 0);
        GameCell cell2 = state.getGameBoard().getCell(GameBoard.ROWS - 3, GameBoard.COLS - 1);
        Player player1 = createPlayer(cell1, 1);
        Player player2 = createPlayer(cell2, 2);

        // initialize game state
        player1.setCurrentPlayer(true);
        player2.setCurrentPlayer(false);
        cell1.setPlayer(player1);
        cell2.setPlayer(player2);
        cell1.setCellState(CellState.PLAYER_STATE);
        cell2.setCellState(CellState.PLAYER_STATE);
        state.setGameStateType(GameStateType.IN_PROGRESS);
    }

    private static Player createPlayer(GameCell cell, int number) {
        Player player = new Player(null, number);
        player.setPlayerType(WindowUtil.getPlayerType(player));
        return player;
    }

    public static void checkGameOverStatus(GameState state) {
        if (GameBoardUtil.numberOfValidMoves(state.getCurrentPlayerCell()) <= 0) {
            state.setGameStateType(GameStateType.GAME_OVER);
        }
    }

    public static void gameOver(GameState gameState) {
        gameState.updateCurrentPlayer();
        if (WindowUtil.getPlayAgain(gameState.getCurrentPlayer())) {
            playerSelect(gameState);
        } else {
            System.exit(0);
        }
    }

    public static void playerSelect(GameState gameState) {
        gameState.getGameBoard().reset();
        gameState.setGameStateType(GameStateType.PLAYER_SELECT);
    }

}
