package state;

import board.GameCell;
import player.Player;
import util.FirstHeuristicUtil;
import util.GameBoardUtil;
import util.Heuristic;
import util.HeuristicPairNode;

import java.util.List;

public class AdversarialSearch {

    private MockStateNode rootNode;
    private int counter = 0; // todo temp?

    // each level = a player's turn, in which player both maxes their moves and minimizes their opponent's moves
    // minimax
    // 225 initial states to evaluate.
    // 5 moves * 45 tokens to remove
    // todo call this at the beginning, to determine the adversarial AI's next move.
    // todo implement ID at each level, calling it each time an AI makes a move.
    public void adversarial(GameState gameState) {
        // todo null for HeuristicPair on root node: valid?
        rootNode = new MockStateNode(gameState, true, null);
        counter = 0; // todo temp?
        evaluateForEachLevel(gameState, rootNode, 3);
    }

    /**
     * implements minimax and iterates through the specified number of levels
     * from the current game state to evaluate the best move. the deeper you go,
     * the more accurately the AI can determine its moves to win.
     * @param level
     */
    private void evaluateForEachLevel(GameState gameState, MockStateNode parentNode, int level) {
        // for all possible moves for the current move:
        //      remove all (or limited number) of the best tokens. add tokens + move heuristics together.
        //      (these will represent a level)
        //
        //      for each of the tokens removed:
        //          calculate all of the opponent's moves (heuristics) and token removals for each (heuristics)
        //          add up the combined options
        //          (these will represent each node in a level)
        //
        // b-up values: sum them as they go up from the leaf nodes.
        //      combine move and token heuristic per player

        if (level == 0) {
            // we've reached our goal of how deep to go
            return;
        }

        System.out.println(counter);
        final int ONE_MILLION = 1000000;
        if (counter > ONE_MILLION) {
            return;
        }

        Player current = gameState.getCurrentPlayer();
        Player opponent = gameState.getWaitingPlayer();
        int validMoveCount = GameBoardUtil.numberOfValidMoves(
                gameState.getGameBoard(),
                gameState.getCurrentPlayerCell());

        // todo temp
        // todo maybe count how many iterations we're at to determine
        // todo when to cut off based on iterative deepening
        // todo counter parameter i mean

        // get all possible moves
        for (int i = 0; i < validMoveCount; i++) {
            List<Heuristic> moves = FirstHeuristicUtil.calculateFullBestMoveHeuristics(gameState);

            // iterate through all moves to get all possible token removes for each
            for (Heuristic move : moves) {
                List<Heuristic> tokens = FirstHeuristicUtil.calculateFullBestTokenHeuristics(gameState);

                // create a copy of the current state
                GameState newStateMove = gameState.clone();

                // move player
                GameCell fromCell = newStateMove.getCurrentPlayerCell();
                GameCell toCell = newStateMove.getGameBoard().getCell(
                        move.getCellRow(), move.getCellCol());
                GameStateUpdater.movePlayer(newStateMove, fromCell, toCell);

                // iterate through all possible token removes to establish their nodes.
                // add each pair of move + token to a node and add them to the tree.
                // this loop will hit all possible combinations of moves + token removals
                // in a level. go deeper by setting the game state for each node,
                // then call this method on each respective game state, decrementing the
                // level counter.
                for (Heuristic token : tokens) {
                    HeuristicPairNode pair = new HeuristicPairNode(move, token);

                    // create a copy of the current state
                    GameState newStateToken = newStateMove.clone();

                    // remove token
                    GameCell toRemove = newStateToken.getGameBoard().getCell(
                            token.getCellRow(), token.getCellCol());
                    GameStateUpdater.removeToken(newStateToken, toRemove);

                    // now we have both move + token heuristics and updated game states.
                    // add them to a new MockStateNode object and put them in the
                    // minimax tree.
                    MockStateNode node = new MockStateNode(newStateToken, false, pair);
                    parentNode.addChildNode(node);

                    // for each node, iterate a level deeper and attach child states/nodes.
                    // use the results to calculate the backed-up values.
                    // todo min/max? which is which and how do we increment them?

                    counter += 1; // todo temp?
                    evaluateForEachLevel(newStateToken, node, level - 1);
                }
            }
        }
    }

}
