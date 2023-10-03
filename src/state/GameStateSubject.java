package state;

import action.PlayerType;
import gui.GameBoard;
import gui.GameCell;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class GameStateSubject {

    private List<Observer> observers = new ArrayList<>();
    private GameStateHandler gameStateHandler;

    public GameStateSubject() {
        this.gameStateHandler = new GameStateHandler(this);
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
        if (gameStateHandler.getCurrentPlayer().getPlayerType() == PlayerType.YOU) {
            notifyObservers(cell);
        }
    }

}
