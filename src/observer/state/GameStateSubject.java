package observer.state;

import gui.GameCell;
import observer.Observer;
import observer.state.GameStateHandler;

import java.util.ArrayList;
import java.util.List;

public class GameStateSubject {

    private List<Observer> observers = new ArrayList<>();
    private GameStateHandler gameStateHandler;

    public GameStateSubject() {
        this.gameStateHandler = new GameStateHandler();
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
        notifyObservers(cell);
    }

}
