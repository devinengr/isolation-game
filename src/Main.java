import gui.GameBoard;
import gui.GameWindow;
import observer.*;
import observer.TokenRemoveObserver;
import observer.PlayerMoveObserver;
import observer.ai.AIAdversarialMoveObserver;
import observer.ai.AIAdversarialTokenObserver;
import observer.ai.AIRandomMoveObserver;
import observer.ai.AIRandomTokenObserver;
import state.GameStateSubject;
import util.GameBoardUtil;

import javax.swing.*;

public class Main implements Runnable {

    private GameStateSubject subject;
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
        GameBoardUtil.setGameBoard(board);
    }

    private void registerObservers() {
        subject.registerObserver(new PlayerMoveObserver());
        subject.registerObserver(new TokenRemoveObserver());
        subject.registerObserver(new GameStartObserver());
        subject.registerObserver(new AIAdversarialMoveObserver());
        subject.registerObserver(new AIAdversarialTokenObserver());
        subject.registerObserver(new AIRandomMoveObserver());
        subject.registerObserver(new AIRandomTokenObserver());
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
