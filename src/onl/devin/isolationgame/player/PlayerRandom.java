package onl.devin.isolationgame.player;

import onl.devin.isolationgame.board.GameBoard;
import onl.devin.isolationgame.board.GameCell;
import onl.devin.isolationgame.state.GameState;
import onl.devin.isolationgame.state.GameStateUpdater;
import onl.devin.isolationgame.util.GameBoardUtil;

import java.util.List;
import java.util.Random;

public class PlayerRandom implements PlayerType {

    private boolean mainStatePlayer;

    public PlayerRandom(boolean mainStatePlayer) {
        this.mainStatePlayer = mainStatePlayer;
    }

    @Override
    public PlayerRandom clone() {
        return new PlayerRandom(false);
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
        GameCell toCell = getRandomCell(gameState.getGameBoard(), fromCell);
        GameStateUpdater.movePlayer(gameState, fromCell, toCell);
    }

    @Override
    public void removeToken(GameState gameState) {
        GameCell toRemove = GameBoardUtil.randomTokenCell(gameState.getGameBoard());
        GameStateUpdater.removeToken(gameState, toRemove);
    }

    private GameCell getRandomCell(GameBoard gameBoard, GameCell fromCell) {
        List<GameCell> moves = GameBoardUtil.validMoves(gameBoard, fromCell);
        int randomIndex = new Random().nextInt(0, moves.size());
        return moves.get(randomIndex);
    }

}
