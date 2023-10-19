package util;

import board.CellState;
import board.GameBoard;
import board.GameCell;
import state.GameState;
import state.GameStateUpdater;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class HeuristicUtil {

    private HeuristicUtil() {}

    /**
     * breaks ties by picking a random cell to move to. this also
     * changes how an adversarial AI will remove a token from a
     * cell, so both its token removals and moves will be unbiased.
     * @param heuristics sorted heuristics list
     * @return a random cell from the best heuristics
     */
    private static int[] breakTies(List<Heuristic> heuristics) {
        int lastVal = Integer.MIN_VALUE;
        int numBest = 0;
        for (Heuristic heuristic : heuristics) {
            if (heuristic.getHeuristic() >= lastVal) {
                lastVal = heuristic.getHeuristic();
                numBest += 1;
            }
        }
        int randomBestIndex = new Random().nextInt(0, numBest);
        return new int[] { heuristics.get(randomBestIndex).getCellRow(),
                           heuristics.get(randomBestIndex).getCellCol() };
    }

    private static int[] getLargestHeuristic(List<Heuristic> heuristics) {
        if (heuristics.size() == 0) {
            // game has probably been won after the token remove
            return null;
        }

        Collections.sort(heuristics);
        Collections.reverse(heuristics);
        return breakTies(heuristics);
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
        List<Heuristic> heuristics = new ArrayList<>();
        GameCell fromCell = gameState.getCurrentPlayerCell();

        int numFromMoves = GameBoardUtil.numberOfValidMoves(gameState.getGameBoard(), fromCell);

        for (GameCell toCell : GameBoardUtil.validMoves(gameState.getGameBoard(), fromCell)) {

            int numToMoves = GameBoardUtil.numberOfValidMoves(gameState.getGameBoard(), toCell);
            int compared = numToMoves - numFromMoves;

            if (compared > 0) {
                heuristics.add(new Heuristic(1, toCell.getRow(), toCell.getCol()));
            } else if (compared == 0) {
                heuristics.add(new Heuristic(0, toCell.getRow(), toCell.getCol()));
            } else {
                heuristics.add(new Heuristic(-1, toCell.getRow(), toCell.getCol()));
            }
        }

        int[] coords = getLargestHeuristic(heuristics);

        if (coords == null) {
            // game has probably been won
            return null;
        }

        int row = coords[0];
        int col = coords[1];
        return gameState.getGameBoard().getCell(row, col);
    }

    public static GameCell getBestToken(GameState gameState) {
        // for each token in a 9x9 grid, where opponent is in center:
        //      copy the game state
        //      remove token
        //      move opponent
        //      check opponent's valid moves
        GameCell opponentCell = gameState.getWaitingPlayerCell();
        int validCurrent = GameBoardUtil.numberOfValidMoves(
                gameState.getGameBoard(),
                opponentCell);

        int rowMin = Math.max(0, opponentCell.getRow() - 2);
        int rowMax = Math.min(GameBoard.ROWS - 1, opponentCell.getRow() + 2);
        int colMin = Math.max(0, opponentCell.getCol() - 2);
        int colMax = Math.min(GameBoard.COLS - 1, opponentCell.getCol() + 2);

        List<Heuristic> heuristics = new ArrayList<>();

        // remove tokens in a 9x9 grid, then check the number of valid moves
        // the opponent has in its next best position:
        for (int row = rowMin; row <= rowMax; row++) {
            for (int col = colMin; col <= colMax; col++) {

                // clone the state and check that the cell about to be checked
                // contains a token that can be removed
                GameState newState = gameState.clone();
                GameCell tokenCell = newState.getGameBoard().getCell(row, col);

                if (tokenCell.getCellState() == CellState.TOKEN_STATE) {

                    // remove token
                    newState.getGameBoard().getCell(row, col).setCellState(CellState.BLANK_STATE);

                    // move opponent
                    // waiting player is opponent, let's fix that
                    newState.updateCurrentPlayer();

                    // opponent is now current player
                    // get the opponent's best move based on the best move heuristic
                    GameCell fromCell = newState.getCurrentPlayerCell();

                    GameCell toCell = HeuristicUtil.getBestMove(newState);
                    if (toCell != null) {
                        GameStateUpdater.movePlayer(newState, fromCell, toCell);
                    } else {
                        // game has been won when this token is removed.
                        // getBestMove() was null, meaning after the
                        // token was removed, the player has nowhere to move.
                        // do nothing. validNext will become 0 and the AI
                        // will win the game.
                    }

                    // check opponent's new set of valid moves based on its predicted move
                    int validNext = GameBoardUtil.numberOfValidMoves(
                            newState.getGameBoard(),
                            newState.getCurrentPlayerCell());

                    if (validNext == 0) {
                        heuristics.add(new Heuristic(100, row, col));
                    } else if (validNext < validCurrent) {
                        heuristics.add(new Heuristic(2, row, col));
                    } else if (validNext == validCurrent) {
                        heuristics.add(new Heuristic(0, row, col));
                    } else if (validNext > validCurrent) {
                        // todo temp block?
                        heuristics.add(new Heuristic(0, row, col));
                    }
                }
            }
        }

        int[] coords = getLargestHeuristic(heuristics);
        int row = coords[0];
        int col = coords[1];
        return gameState.getGameBoard().getCell(row, col);
    }

}
