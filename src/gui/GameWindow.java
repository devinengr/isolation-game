package gui;

import tile.Tile;
import tile.TileState;

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
        GameBoard.getSingleton().initializeBoard(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
