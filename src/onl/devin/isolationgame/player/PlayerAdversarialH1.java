package onl.devin.isolationgame.player;

import onl.devin.isolationgame.board.CellState;
import onl.devin.isolationgame.board.GameBoard;
import onl.devin.isolationgame.board.GameCell;
import onl.devin.isolationgame.state.AdversarialH1;
import onl.devin.isolationgame.state.GameState;
import onl.devin.isolationgame.state.GameStateUpdater;
import onl.devin.isolationgame.state.MockStateNode;

public class PlayerAdversarialH1 implements PlayerType {

    private boolean mainStatePlayer;

    // cached value from adversarial search, called in move()
    private GameCell toRemove = null;

    @Override
    public PlayerAdversarialH1 clone() {
        return new PlayerAdversarialH1(false);
    }

    public PlayerAdversarialH1(boolean mainStatePlayer) {
        this.mainStatePlayer = mainStatePlayer;
    }

    /**
     * add a pause so the user can watch AIs play vs each other
     */
    private void pause() {
        // check before pausing to prevent long pauses in non-main states being checked
        if (mainStatePlayer) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void move(GameState gameState) {
        pause();
        GameCell fromCell = gameState.getCurrentPlayerCell();

        // begin adversarial search
        AdversarialH1 search = new AdversarialH1();
        MockStateNode nextState = search.adversarial(gameState);

        // get the target cell
        // we are now waiting player, as we made opponent current
        // during adversarial search within the bestTokenHeuristic function
        // (bad design, i know)
        GameCell targetCell = nextState.getGameState().getWaitingPlayerCell();
        // need the reference to the cell in the correct game state
        GameCell toCell = gameState.getGameBoard().getCell(
                targetCell.getRow(), targetCell.getCol());

        // check for the token cell by comparing the boards
        for (int row = 0; row < GameBoard.ROWS; row++) {
            for (int col = 0; col < GameBoard.COLS; col++) {
                GameCell c1 = gameState.getGameBoard().getCell(row, col);
                GameCell c2 = nextState.getGameState().getGameBoard().getCell(row, col);

                if (c1.getCellState() == CellState.TOKEN_STATE
                        && c2.getCellState() == CellState.BLANK_STATE) {
                    // this was the removed token
                    toRemove = c1;
                    break;
                }
            }
            if (toRemove != null) {
                // gotta break outta this one too (bad design i know)
                break;
            }
        }

        GameStateUpdater.movePlayer(gameState, fromCell, toCell);
    }

    @Override
    public void removeToken(GameState gameState) {
        GameStateUpdater.removeToken(gameState, toRemove);
        toRemove = null;
    }

}
