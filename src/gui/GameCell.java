package gui;

import javax.swing.*;
import java.awt.*;

public class GameCell extends JPanel {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    private int x;
    private int y;
    private CellState cellState;

    public GameCell(int x, int y) {
        initialize(x, y);
        createPanelComponents();
    }

    private void initialize(int x, int y) {
        this.x = x;
        this.y = y;
        this.cellState = CellState.TOKEN_STATE;
    }

    private void createPanelComponents() {
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(cellState.getColor());
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

}
