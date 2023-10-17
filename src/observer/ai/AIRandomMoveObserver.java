package observer.ai;

import state.GameState;
import state.GameStateUpdater;
import util.GameBoardUtil;
import action.PlayerType;
import gui.GameCell;
import observer.Observer;
import state.GameStateType;
import state.GameStateHandler;

import java.util.List;
import java.util.Random;

public class AIRandomMoveObserver implements Observer {

    @Override
    public void update(GameCell cell, GameStateHandler gameStateHandler) {
        GameState gameState = gameStateHandler.getGameState();
        if (gameState.getGameState() == GameStateType.IN_PROGRESS) {
            if (gameState.getCurrentPlayer().getPlayerType() == PlayerType.AI_RANDOM) {

                // add a pause so the user can watch AIs play vs each other
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // move the player
                GameCell fromCell = gameState.getCurrentPlayer().getCell();
                GameCell toCell = getRandomCell(fromCell);
                GameStateUpdater.movePlayer(gameState, fromCell, toCell);
            }
        }
    }

    private GameCell getRandomCell(GameCell fromCell) {
        List<GameCell> moves = GameBoardUtil.validMoves(fromCell);
        int randomIndex = new Random().nextInt(0, moves.size());
        return moves.get(randomIndex);
    }

}
