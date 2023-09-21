package observer;

import gui.GameCell;
import state.MoveValidator;

public class PlayerMoveObserver implements Observer {

    private MoveValidator moveValidator;

    public PlayerMoveObserver(MoveValidator moveValidator) {
        this.moveValidator = moveValidator;
    }

    @Override
    public void update(GameCell cell, GameStateHandler gameStateHandler) {

    }

}
