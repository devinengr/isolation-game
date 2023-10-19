package state;

public class AdversarialSearch {

    private MockStateNode rootNode;

    // each level = a player's turn, in which player both maxes their moves and minimizes their opponent's moves
    // minimax
    // 225 initial states to evaluate.
    // 5 moves * 45 tokens to remove
    // todo call this at the beginning, to determine the adversarial AI's next move.
    // todo implement ID at each level, calling it each time an AI makes a move.
    public void adversarial(GameState gameState) {
        rootNode = new MockStateNode(gameState, true);
        goDeep(5);
    }

    /**
     * implements minimax and iterates through the specified number of levels
     * from the current game state to evaluate the best move. the deeper you go,
     * the more accurately the AI can determine its moves to win.
     * @param level
     */
    private void goDeep(int level) {
        // todo
        // for all possible moves for the current move:
        //      remove all (or limited number) of the best tokens. add tokens + move heuristics together.
        //      (these will represent a level)
        //      todo IDEA: make heuristics function return the entire list, and use that list.
        //
        //      for each of the tokens removed:
        //          calculate all of the opponent's moves (heuristics) and token removals for each (heuristics)
        //          add up the combined options
        //          (these will represent each node in a level)
        //
        //
        // b-up values: sum them as they go up.
        //      combine move and token heuristic per player

        for (int i = 0; i < level; i++) {

        }
    }

}
