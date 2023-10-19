package player;

import board.CellState;
import board.GameCell;
import state.GameState;
import state.GameStateUpdater;
import util.GameBoardUtil;

public class PlayerYou implements PlayerType {

    private boolean canClickCell = false;
    private boolean cellClicked = false;
    private GameCell cell = null;

    /**
     * add a pause while waiting for the user to click a cell
     * so the machine doesn't go bonkers checking the variables
     * millions of times per second.
     */
    private void pause() {
        try {
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * registers the click with this object if this object
     * is currently set to register clicks (can register clicks
     * once the move method has been called and waiting)
     * @param cell
     */
    public void cellClicked(GameCell cell) {
        if (canClickCell) {
            cellClicked = true;
            this.cell = cell;
        }
    }

    private void startMove() {
        canClickCell = true;
    }

    private void doneMove() {
        cellClicked = false;
        canClickCell = false;
        this.cell = null;
    }

    /**
     * starts listening for a cell click, and waits until the
     * player clicks a cell. once the player clicks a valid
     * cell, the method will exit.
     * @param gameState game state
     */
    @Override
    public void move(GameState gameState) {
        startMove();
        // wait for cell click
        // once clicked, validate move and break from the loop
        while (true) {
            if (cellClicked) {
                System.out.println("got it");
                GameCell fromCell = gameState.getCurrentPlayer().getCell();
                if (GameBoardUtil.validateMove(fromCell, cell)) {
                    GameStateUpdater.movePlayer(gameState, fromCell, cell);
                    break;
                }
            }
            pause();
        }
        doneMove();
    }

    @Override
    public void removeToken(GameState gameState) {
        startMove();
        // wait for cell click
        // once clicked, validate move and break from the loop
        while (true) {
            if (cellClicked) {
                System.out.println("got it");
                if (cell.getCellState() == CellState.TOKEN_STATE) {
                    GameStateUpdater.removeToken(gameState, cell);
                    break;
                }
            }
            pause();
        }
        doneMove();
    }

}
