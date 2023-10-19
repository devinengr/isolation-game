package state;

import java.util.HashMap;
import java.util.Map;

public class MockStateNode {

    private boolean rootState;
    private Map<MockStateNode, Integer> childStates = new HashMap<>();
    private MockStateNode parentState;
    private GameState gameState;

    public MockStateNode(GameState state, boolean rootState) {
        this.parentState = null;
        this.gameState = state;
        this.rootState = rootState;
    }

    public MockStateNode(MockStateNode parentState, GameState state) {
        this.parentState = parentState;
        this.rootState = false;
        this.gameState = state;
    }

    public boolean isRootState() {
        return rootState;
    }

    public MockStateNode getParentState() {
        return parentState;
    }

    public void addChildState(GameState updatedState) {
        MockStateNode node = new MockStateNode(this, updatedState);
        childStates.put(node, -1); // -1 means the heuristic has not yet been calculated
    }

}
