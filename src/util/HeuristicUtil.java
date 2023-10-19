package util;

import board.GameCell;
import player.Player;
import state.GameState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class HeuristicUtil {

    private HeuristicUtil() {}

    private static GameCell getLargestHeuristic(List<Heuristic> heuristics) {
        Collections.sort(heuristics);
        Collections.reverse(heuristics);
        return heuristics.get(0).getGameCell();
    }

    /**
     * utility function. stores all surrounding cells in a hashmap.
     * heuristic:
     * curr - next > 0: +1.
     * curr - next = 0: +0.
     * curr - next < 0: -1.
     *
     * @return hashmap containing cells and their heuristics.
     */
    public static GameCell getBestMove(GameState gameState) {
        GameCell fromCell = gameState.getCurrentPlayer().getCell();
        int numFromMoves = GameBoardUtil.numberOfValidMoves(fromCell);
        List<Heuristic> heuristics = new ArrayList<>();
        for (GameCell toCell : GameBoardUtil.validMoves(fromCell)) {
            int numToMoves = GameBoardUtil.numberOfValidMoves(fromCell);
            int compared = numFromMoves - numToMoves;
            if (compared > 0) {
                heuristics.add(new Heuristic(1, toCell));
            } else if (compared == 0) {
                heuristics.add(new Heuristic(0, toCell));
            } else {
                heuristics.add(new Heuristic(-1, toCell));
            }
        }
        return getLargestHeuristic(heuristics);
    }

    public static GameCell getBestToken(GameState gameState) {
        /**
         * todo this is incorrect. validNext represents the valid moves of the location
         * todo of the next cell, given that the opponent moved to that cell.
         *
         * todo this is where adversarial search comes in (i think)
         */

        GameState newState = new GameState();

        Player opponent = gameState.getWaitingPlayer();
        int validCurrent = GameBoardUtil.numberOfValidMoves(opponent.getCell());
        List<Heuristic> heuristics = new ArrayList<>();
        for (GameCell next : GameBoardUtil.validMoves(opponent.getCell())) {
            int validNext = GameBoardUtil.numberOfValidMoves(next);
            if (validNext == 0) {
                heuristics.add(new Heuristic(100, next));
            } else if (validNext < validCurrent) {
                heuristics.add(new Heuristic(2, next));
            } else if (validNext == validCurrent) {
                heuristics.add(new Heuristic(0, next));
            }

        }
        return getLargestHeuristic(heuristics);
    }

}
