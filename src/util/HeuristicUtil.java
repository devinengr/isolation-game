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
        GameCell fromCell = gameState.getCurrentPlayerCell();
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

        // todo create the game state clone here (maybe)

        GameCell opponentCell = gameState.getWaitingPlayerCell();
        int validCurrent = GameBoardUtil.numberOfValidMoves(opponentCell);
        List<Heuristic> heuristics = new ArrayList<>();
        for (GameCell next : GameBoardUtil.validMoves(opponentCell)) {
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
