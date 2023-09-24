import gui.GameBoard;
import gui.GameWindow;
import observer.*;
import observer.TokenRemoveObserver;
import observer.PlayerMoveObserver;
import state.GameStateSubject;
import action.MoveValidator;

import javax.swing.*;

public class Main {

    private GameStateSubject subject;
    private MoveValidator validator;
    private GameWindow window;
    private GameBoard board;

    private void run() {
        initialize();
        registerObservers();
        begin();
    }

    private void initialize() {
        subject = new GameStateSubject();
        window = new GameWindow(subject);
        board = window.getBoard();
        validator = new MoveValidator(board);
    }

    private void registerObservers() {
        subject.registerObserver(new PlayerMoveObserver(validator));
        subject.registerObserver(new TokenRemoveObserver(validator));
        subject.registerObserver(new GameOverObserver());
        subject.registerObserver(new GameStartObserver());
    }

    private void begin() {
        subject.notifyObservers(board.getCell(0, 0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().run();
        });
    }

}
