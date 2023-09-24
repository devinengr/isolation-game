package state;

import gui.GameCell;
import observer.Observer;

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

    // todo differentiate between cell click and ai move
    // todo maybe make cellClicked notify, and maybe make some aiFunc notify
    // todo if so, remove notifyObservers
    public void notifyObservers(GameCell cell) {
        for (Observer observer : observers) {
            observer.update(cell, gameStateHandler);
        }
    }

    public void cellClicked(GameCell cell) {
        notifyObservers(cell);
    }

}
