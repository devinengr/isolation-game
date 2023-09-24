package gui;

import javax.swing.*;
import java.awt.*;

public class GameCell extends JPanel {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    private GameBoard gameBoard;
    private int col;
    private int row;
    private CellState cellState;

    public GameCell(GameBoard gameBoard, int row, int col) {
        this.gameBoard = gameBoard;
        initialize(row, col);
        createPanelComponents();
    }

    private void initialize(int row, int col) {
        this.row = row;
        this.col = col;
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
        this.repaint();
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

}
