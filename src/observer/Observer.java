package observer;

import gui.GameCell;
import observer.state.GameStateHandler;

public interface Observer {

    void update(GameCell cell, GameStateHandler gameStateHandler);

}
