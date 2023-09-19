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
        int tileWidth = GameBoard.getSingleton().getTileWidth();
        int tileHeight = GameBoard.getSingleton().getTileHeight();

        for (int i = 0; i < 48; i++) {
            panel.add(new GameCell());
        }

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
