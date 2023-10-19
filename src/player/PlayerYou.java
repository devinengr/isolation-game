package player;

import board.CellState;
import board.GameCell;
import state.GameState;
import state.GameStateUpdater;
import util.GameBoardUtil;

public class PlayerYou implements PlayerType {

    private boolean canMove = false;
    private boolean canRemoveToken = false;
    private boolean cellValidated = false;

    @Override
    public PlayerYou clone() {
        PlayerYou newPlayer = new PlayerYou();
        newPlayer.canMove = canMove;
        newPlayer.canRemoveToken = canRemoveToken;
        newPlayer.cellValidated = cellValidated;
        return newPlayer;
    }

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
    public void cellClicked(GameState gameState, GameCell cell) {
        if (canMove) {
            System.out.println("got it"); // todo temp
            GameCell fromCell = gameState.getCurrentPlayerCell();
            if (GameBoardUtil.validateMove(gameState.getGameBoard(), fromCell, cell)) {
                GameStateUpdater.movePlayer(gameState, fromCell, cell);
                cellValidated = true;
            }
        }

        else if (canRemoveToken) {
            System.out.println("got it"); // todo temp
            if (cell.getCellState() == CellState.TOKEN_STATE) {
                GameStateUpdater.removeToken(gameState, cell);
                cellValidated = true;
            }
        }
    }

    /**
     * starts listening for a cell click, and waits until the
     * player clicks a cell. once the player clicks a valid
     * cell, the method will exit.
     * @param gameState game state
     */
    @Override
    public void move(GameState gameState) {
        canMove = true;
        while (!cellValidated) {
            // wait until cell has been clicked before moving on
            pause();
        }
        cellValidated = false;
        canMove = false;
    }

    @Override
    public void removeToken(GameState gameState) {
        canRemoveToken = true;
        while (!cellValidated) {
            // wait until cell has been clicked before moving on
            pause();
        }
        cellValidated = false;
        canRemoveToken = false;
    }

}
