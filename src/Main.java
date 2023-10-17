import gui.GameBoard;
import gui.gui.GUIWindow;
import observer.*;
import observer.TokenRemoveObserver;
import observer.PlayerMoveObserver;
import observer.ai.AIAdversarialMoveObserver;
import observer.ai.AIAdversarialTokenObserver;
import observer.ai.AIRandomMoveObserver;
import observer.ai.AIRandomTokenObserver;
import state.GameState;
import state.GameStateSubject;
import util.GameBoardUtil;
import util.HeuristicUtil;

import javax.swing.*;

public class Main implements Runnable {

    private GameStateSubject subject;
    private GUIWindow window;
    private GameBoard board;
    private GameState gameState;

    @Override
    public void run() {
        initialize();
        registerObservers();
        begin();
    }

    private void initialize() {
        gameState = new GameState();
        board = gameState.getGameBoard();
        subject = new GameStateSubject(gameState);
        window = new GUIWindow(subject);

        GameBoardUtil.setGameBoard(board);
        HeuristicUtil.setGameStateHandler(subject.getGameStateHandler());
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
