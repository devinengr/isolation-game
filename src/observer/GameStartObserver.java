package observer;

import action.Player;
import gui.CellState;
import gui.GameBoard;
import gui.GameCell;
import gui.WindowUtil;
import state.GameState;
import state.GameStateHandler;

public class GameStartObserver implements Observer {

    private Player player1;
    private Player player2;
    private GameCell cell1;
    private GameCell cell2;

    @Override
    public void update(GameCell cell, GameStateHandler gameStateHandler) {
        if (gameStateHandler.getGameState() == GameState.PLAYER_SELECT) {
            initialize(cell);
            updateGameState(gameStateHandler);
        }
    }

    private void initialize(GameCell cell) {
        this.cell1 = cell.getGameBoard().getCell(3, 0);
        this.cell2 = cell.getGameBoard().getCell(GameBoard.ROWS - 2, GameBoard.COLS -1);
        this.player1 = createPlayer(cell1, 1);
        this.player2 = createPlayer(cell2, 2);
    }

    private Player createPlayer(GameCell cell, int number) {
        Player player = new Player(null, cell, number);
        player.setPlayerType(WindowUtil.getPlayerType(player));
        return player;
    }

    private void updateGameState(GameStateHandler gameStateHandler) {
        gameStateHandler.setPlayer1(player1);
        gameStateHandler.setPlayer2(player2);
        gameStateHandler.setCurrentPlayer(player1);
        cell1.setCellState(CellState.PLAYER_STATE);
        cell2.setCellState(CellState.PLAYER_STATE);
        gameStateHandler.setGameState(GameState.IN_PROGRESS);
    }

}
