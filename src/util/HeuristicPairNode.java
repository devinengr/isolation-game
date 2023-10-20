package util;

public class HeuristicPairNode {

    private Heuristic moveHeuristic;
    private Heuristic tokenHeuristic;

    public HeuristicPairNode(Heuristic moveHeuristic, Heuristic tokenHeuristic) {
        this.moveHeuristic = moveHeuristic;
        this.tokenHeuristic = tokenHeuristic;
    }

    public Heuristic getMoveHeuristic() {
        return moveHeuristic;
    }

    public Heuristic getTokenHeuristic() {
        return tokenHeuristic;
    }

}
