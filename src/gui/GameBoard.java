package gui;

import observer.state.GameStateSubject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoard extends JPanel {

    public static final int ROWS = 6;
    public static final int COLS = 8;

    private GameStateSubject subject;
    private GameCell[][] grid;

    public GameBoard(GameStateSubject subject) {
        initialize(subject);
        createPanel();
        createCellGrid();
    }

    private void initialize(GameStateSubject subject) {
        this.subject = subject;
        this.grid = new GameCell[ROWS][COLS];
    }

    private void createPanel() {
        this.setLayout(new GridLayout(ROWS, COLS));
    }

    private void createCellGrid() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                GameCell cell = createCell(row, col);
                addCellComponent(cell);
            }
        }
    }

    private GameCell createCell(int row, int col) {
        GameCell cell = new GameCell(row, col);
        grid[row][col] = cell;
        return cell;
    }

    private void addCellComponent(GameCell cell) {
        this.add(cell);
        cell.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                subject.cellClicked(cell);
            }
        });
    }

    public GameCell getCell(int x, int y) {
        if (x < 0 ||  x >= ROWS || y < 0 || y >= COLS) {
            return null;
        }
        return grid[x][y];
    }

}
