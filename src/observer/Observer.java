package observer;

import gui.GameCell;
import state.GameStateHandler;

public interface Observer {

    void update(GameCell cell, GameStateHandler gameStateHandler);

}
