package util;

import board.CellState;
import board.GameBoard;
import board.GameCell;
import state.GameState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SecondHeuristicUtil {

    private SecondHeuristicUtil() {}

    /**
     * breaks ties by picking a random cell to move to. this also
     * changes how an adversarial AI will remove a token from a
     * cell, so both its token removals and moves will be unbiased.
     * @param heuristics sorted heuristics list
     * @return a random cell from the best heuristics
     */
    public static int[] breakTies(List<Heuristic> heuristics) {
        return FirstHeuristicUtil.breakTies(heuristics);
    }

    public static int[] getLargestHeuristic(List<Heuristic> heuristics) {
        return FirstHeuristicUtil.getLargestHeuristic(heuristics);
    }

    public static List<Heuristic> calculateFullBestMoveHeuristics(GameState gameState) {
        return FirstHeuristicUtil.calculateFullBestMoveHeuristics(gameState);
    }

    /**
     * gets the token heuristic. calculates the number of cells
     * with 3 or more non-empty adjacent cells. this should be
     * added to the move heuristic and compared using minimax.
     * @param gameState game state
     * @return a list of heuristics, containing the heuristic value
     *          in each, as well as the coordinates of each cell that
     *          is a token that can be removed
     */
    public static List<Heuristic> calculateFullBestTokenHeuristics(GameState gameState) {
        List<Heuristic> heuristics = new ArrayList<>();
        List<GameCell> cells = new ArrayList<>();
        List<GameCell> allTokens = new ArrayList<>();

        int heuristic = 0;

        // calculate the number of cells with 3+ non-empty cells adjacent
        for (int row = 0; row < GameBoard.ROWS; row++) {
            for (int col = 0; col < GameBoard.COLS; col++) {

                int adjacentNonEmpty = 0;

                if (gameState.getGameBoard()
                        .getCell(row, col).getCellState()
                        == CellState.TOKEN_STATE) {

                    // loop through the surrounding cells and count the number of non-empty
                    for (int rowI = row - 1; rowI <= row + 1; rowI++) {
                        for (int colI = col - 1; colI <= col + 1; colI++) {
                            GameCell cell = gameState.getGameBoard().getCell(rowI, colI);
                            if (cell != null) {
                                if (cell.getCellState() == CellState.TOKEN_STATE) {
                                    if (!allTokens.contains(cell)) {
                                        allTokens.add(cell);
                                    }

                                    adjacentNonEmpty += 1;
                                    if (adjacentNonEmpty >= 3) {
                                        // add the original cell to the list of possible cells to remove
                                        cells.add(gameState.getGameBoard().getCell(row, col));
                                        break;
                                    }
                                }
                            }
                        }
                        if (adjacentNonEmpty >= 3) {
                            break;
                        }
                    }

                    if (adjacentNonEmpty >= 3) {
                        heuristic += 1;
                    }
                }
            }
        }

        for (GameCell cell : cells) {
            Heuristic h = new Heuristic(heuristic, cell.getRow(), cell.getCol());
            heuristics.add(h);
        }

        // if there were no tokens that had 3 adjacent tokens,
        // pick a random token
        if (heuristics.size() == 0) {
            Collections.shuffle(allTokens);
            heuristics.add(new Heuristic(0, allTokens.get(0).getRow(), allTokens.get(0).getCol()));
        }

        return heuristics;
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
        return FirstHeuristicUtil.getBestMove(gameState);
    }

}
