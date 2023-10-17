package gui;

import board.GameCell;

import javax.swing.*;
import java.awt.*;

public class GUICell extends JPanel {

    private GameCell cell;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    public GUICell(GameCell cell) {
        this.cell = cell;
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
    }

    public GameCell getGameCell() {
        return cell;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(cell.getCellState().getColor());
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

}
