package state;

import util.HeuristicPairNode;

import java.util.ArrayList;
import java.util.List;

public class MockStateNode {

    private boolean backedUpSet = false;
    private int backedUpValue = 0;
    private int nodeLevel = 0;
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

    /**
     * recursive function that goes through all children to
     * calculate the backed-up value. grabs either the min
     * or max depending on the parameter provided.
     * if the function is called again, just return the
     * cached result in favor of speed
     * @param min if it's a min, otherwise it will get the max
     * @return backed-up value
     */
    public int getBackedUpValue(boolean min) {
        if (backedUpSet) {
            return backedUpValue;
        }

        backedUpSet = true;

        if (!childNodes.isEmpty()) {
            // recursive case
            if (min) {
                int minVal = Integer.MAX_VALUE;
                for (MockStateNode child : childNodes) {
                    int val = child.getBackedUpValue(!min);
                    if (val < minVal) {
                        minVal = val;
                    }
                }
                backedUpValue = minVal;
                return backedUpValue;
            } else {
                int maxVal = Integer.MIN_VALUE;
                for (MockStateNode child : childNodes) {
                    int val = child.getBackedUpValue(!min);
                    if (val > maxVal) {
                        maxVal = val;
                    }
                }
                backedUpValue = maxVal;
                return backedUpValue;
            }
        } else {
            // base case
            int move = heuristics.getMoveHeuristic().getHeuristic();
            int token = heuristics.getTokenHeuristic().getHeuristic();
            backedUpValue = move + token;
            return backedUpValue;
        }
    }

    public List<MockStateNode> getChildNodes() {
        return childNodes;
    }

    public int getNodeLevel() {
        return nodeLevel;
    }

    public GameState getGameState() {
        return gameState;
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
        node.nodeLevel = nodeLevel + 1;
    }

}
