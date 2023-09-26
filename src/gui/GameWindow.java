package gui;

import state.GameStateSubject;

import javax.swing.*;

public class GameWindow {

    private JFrame frame;
    private GameBoard board;

    public GameWindow(GameStateSubject subject) {
        frame = new JFrame();
        board = new GameBoard(subject);
        createBoard();
        createWindow();
    }

    private void createBoard() {
        frame.getContentPane().add(board);
        WindowUtil.setParentPanel(board);
    }

    private void createWindow() {
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public GameBoard getBoard() {
        return board;
    }

}
