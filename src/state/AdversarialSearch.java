package state;

import board.GameCell;
import util.FirstHeuristicUtil;
import util.Heuristic;
import util.HeuristicPairNode;

import java.util.*;

public class AdversarialSearch {

    private static final int AB_MAX_MOVE_COUNT = 3;
    private static final int AB_MAX_TOKEN_COUNT = 5;
    private static final int ITERATIVE_DEEPENING_LEVELS = 4;

    /**
     * perform adversarial search. wreck the opponent without
     * grace nor honor (450mg caffeine at 12am btw)
     * @param gameState current (actual) game state
     * @return the MockStateNode with information about what to do next,
     *          derived from our friend Minimax
     */
    public MockStateNode adversarial(GameState gameState) {
        // clone the gameState, otherwise bad news
        GameState copiedState = gameState.clone();
        MockStateNode rootNode = new MockStateNode(copiedState, true, null);
        // build a game tree that will be minimax 'ed
        evaluateForEachLevel(rootNode);
        // rootNode now contains a bunch of optimal moves based on heuristics.
        // we use minimax to check the best one and back-up the values, getting
        // the best next move to make
        return backupValues(rootNode);
    }

    /**
     * back up the values and return the state containing the
     * best heuristic based on the level of depth we have checked
     * with the minimax algorithm.
     * @param rootNode the node with the current state
     * @return a node with the state that the AI should move to
     */
    private MockStateNode backupValues(MockStateNode rootNode) {
        // true since we are maximizing our move
        int rootVal = rootNode.getBackedUpValue(true);

        // get a child node associated with this value.
        // if there are multiple child nodes, pick a random one
        // to avoid bias.
        // this node contains a game state that the AI can get
        // the move and token removal options from.

        List<MockStateNode> matchingChildren = new ArrayList<>();

        for (MockStateNode child : rootNode.getChildNodes()) {
            // doesn't matter whether true or false because
            // the value is cached after the first run, but
            // false is technically correct as it's one
            // level down
            if (child.getBackedUpValue(false) == rootVal) {
                matchingChildren.add(child);
            }
        }

        int randomIndex = new Random().nextInt(matchingChildren.size());
        return matchingChildren.get(randomIndex);
    }

    /**
     * implements minimax and iterates through the specified number of levels
     * from the current game state to evaluate the best move. the deeper you go,
     * the more accurately the AI can determine its moves to win.
     * @param parentNode mock node of current game state
     */
    private void evaluateForEachLevel(MockStateNode parentNode) {
        Queue<MockStateNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(parentNode);
        MockStateNode next;

        // this loop will expand a single node per iteration
        while (!nodeQueue.isEmpty()) {
            // retrieve and remove the next node from the BFS queue to expand
            next = nodeQueue.poll();
            GameState gameState = next.getGameState();
            parentNode = next;

            // check if we have moved to the next level. if so, increment the level counter and
            // check if we reached the iterative deepening limit
            if (next.getNodeLevel() >= ITERATIVE_DEEPENING_LEVELS) {
                System.out.println("moved to next level");
                break;
            }

            // get a list of all possible moves and work from here
            List<Heuristic> moves = FirstHeuristicUtil.calculateFullBestMoveHeuristics(gameState);

            // similar to AB pruning, remove non-optimal heuristics to improve
            // efficiency, but allow a minimum set so that if there are only
            // non-optimal moves, the AI can still make a move
            Collections.sort(moves);
            Collections.reverse(moves);

            if (moves.size() == 0) {
                // they won, rip the losing player
                break;
            }
            int bestMove = moves.get(0).getHeuristic();
            while (moves.size() > AB_MAX_MOVE_COUNT) {
                moves.remove(moves.size() - 1);
            }

            // randomize the order to avoid bias

            // iterate through all moves to get all possible token removes for each
            for (Heuristic move : moves) {
                List<Heuristic> tokens = FirstHeuristicUtil.calculateFullBestTokenHeuristics(gameState);

                // similar to AB pruning, remove non-optimal heuristics to improve
                // efficiency, but allow a minimum set so that if there are only
                // non-optimal token removals, the AI can still remove a token
                Collections.sort(tokens);
                Collections.reverse(tokens);

                int bestToken = moves.get(0).getHeuristic();
                while (tokens.size() > AB_MAX_TOKEN_COUNT) {
                    tokens.remove(tokens.size() - 1);
                }

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
                // then call this method on each respective game state.
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

                    // add to the queue
                    nodeQueue.add(node);
                }
            }
        }
    }

}
