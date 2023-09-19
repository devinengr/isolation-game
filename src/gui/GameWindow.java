package gui;

import javax.swing.*;
import java.awt.*;

public class GameWindow {

    private JFrame frame;
    private JPanel panel;

    public GameWindow() {
        frame = new JFrame();
        panel = new JPanel();
        panel.setLayout(new GridLayout(
                GameBoard.ROWS,
                GameBoard.COLS));
        frame.getContentPane().add(panel);
    }

    public void create() {
        for (int row = 0; row < GameBoard.ROWS; row++) {
            for (int col = 0; col < GameBoard.COLS; col++) {
                panel.add(GameBoard.getSingleton().getGameCell(row, col));
            }
        }
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
