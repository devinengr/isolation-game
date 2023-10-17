package util;

import action.Player;
import gui.GameCell;
import state.GameStateHandler;

import java.util.*;

public final class HeuristicUtil {

    private static GameStateHandler gameStateHandler;

    private HeuristicUtil() {}

    public static void setGameStateHandler(GameStateHandler gameStateHandler) {
        HeuristicUtil.gameStateHandler = gameStateHandler;
    }

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
    public static GameCell getBestMove() {
        /**
         * todo this will be used for hypothetical board states
         * todo implement a mock board and a way to manipulate it,
         * todo then pass in the preferred board to check here
         * todo don't use current player / prev player, use cells instead
         */
        GameCell fromCell = gameStateHandler.getGameState().getCurrentPlayer().getCell();
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

    public static GameCell getBestToken() {
        /**
         * todo this is incorrect. validNext represents the valid moves of the location
         * todo of the next cell, given that the opponent moved to that cell.
         *
         * todo this is where adversarial search comes in (i think)
         */
        Player opponent = gameStateHandler.getGameState().getWaitingPlayer();
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
