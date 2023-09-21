package observer;

import gui.GameCell;

public interface Observer {

    void update(GameCell cell, GameStateHandler gameStateHandler);

}
