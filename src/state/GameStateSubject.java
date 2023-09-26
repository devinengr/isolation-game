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

    public void aiMoved(GameCell cell) {
        notifyObservers(cell);
        // todo implement way to run through an AI observer
        // todo make it work cleanly for 2 AI observers (so they can each play w/o manual cell clicks)
        // todo add a bit of a delay between AI moves
        // todo add a AIObserver
        //      todo put code in updatePlayer in GameStateHandler to call notifyObservers
        //      todo add a state to check if current state is PlayerChanged
        //      todo have AIObserver check PlayerChanged
        //      todo check if updated current player type is an AI
        //      todo if so, run through AI algorithm
        //      todo call aiClicked
    }

}
