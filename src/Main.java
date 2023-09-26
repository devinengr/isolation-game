import gui.GameBoard;
import gui.GameWindow;
import observer.*;
import observer.TokenRemoveObserver;
import observer.PlayerMoveObserver;
import observer.ai.AIRandomMoveObserver;
import observer.ai.AIRandomTokenObserver;
import state.GameStateSubject;
import action.MoveValidator;

import javax.swing.*;

public class Main implements Runnable {

    private GameStateSubject subject;
    private MoveValidator validator;
    private GameWindow window;
    private GameBoard board;

    @Override
    public void run() {
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
        subject.registerObserver(new GameStartObserver());
        subject.registerObserver(new AIRandomMoveObserver(validator));
        subject.registerObserver(new AIRandomTokenObserver(validator));
        subject.registerObserver(new GameOverObserver());
    }

    private void begin() {
        subject.notifyObservers(board.getCell(0, 0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Thread(new Main()).start();
        });
    }

}
