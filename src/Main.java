import gui.GameBoard;
import gui.GameWindow;
import observer.*;
import observer.action.TokenRemoveObserver;
import observer.action.PlayerMoveObserver;
import observer.state.GameStateSubject;
import observer.action.MoveValidator;

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
        subject.registerObserver(new GameStartObserver());
        subject.registerObserver(new GameOverObserver());
        subject.registerObserver(new PlayerMoveObserver(validator));
        subject.registerObserver(new TokenRemoveObserver());
    }

    private void begin() {
        // todo tell the subject to call notifyObservers
        // todo have GameStartObserver check for the PLAYER_SELECT state
    }

    public static void main(String[] args) {
        new Main().run();
    }

}
