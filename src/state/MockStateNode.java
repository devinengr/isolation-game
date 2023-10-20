package state;

import util.HeuristicPairNode;

import java.util.ArrayList;
import java.util.List;

public class MockStateNode {

    private boolean rootNode;
    private List<MockStateNode> childNodes = new ArrayList<>();
    private MockStateNode parentNode;
    private GameState gameState;
    private HeuristicPairNode heuristics;

    public MockStateNode(GameState state, boolean rootNode, HeuristicPairNode heuristics) {
        this.parentNode = null;
        this.gameState = state;
        this.rootNode = rootNode;
        this.heuristics = heuristics;
    }

    public HeuristicPairNode getHeuristics() {
        return heuristics;
    }

    public boolean isRootNode() {
        return rootNode;
    }

    public MockStateNode getParentNode() {
        return parentNode;
    }

    public void addChildNode(MockStateNode node) {
        childNodes.add(node);
        node.parentNode = this;
    }

}
