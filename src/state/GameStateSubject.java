package state;

import action.PlayerType;
import gui.GameCell;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class GameStateSubject {

    private List<Observer> observers = new ArrayList<>();
    private GameStateHandler gameStateHandler;

    public GameStateSubject(GameState gameState) {
        this.gameStateHandler = new GameStateHandler(this, gameState);
    }

    public GameStateHandler getGameStateHandler() {
        return gameStateHandler;
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(GameCell cell) {
        for (Observer observer : observers) {
            observer.update(cell, gameStateHandler);
        }
    }

    public void cellClicked(GameCell cell) {
        if (gameStateHandler.getGameState().getCurrentPlayer().getPlayerType() == PlayerType.YOU) {
            notifyObservers(cell);
        }
    }

}
