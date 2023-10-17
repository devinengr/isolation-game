package gui;

import board.GameBoard;
import board.GameCell;
import state.GameStateSubject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIBoard extends JPanel {

    private GameBoard gameBoard;

    public GUIBoard(GameStateSubject subject) {
        this.gameBoard = subject.getGameStateHandler().getGameState().getGameBoard();

        // set the panel layout
        this.setLayout(new GridLayout(GameBoard.ROWS, GameBoard.COLS));

        // initialize each gui cell component
        for (int row = 0; row < GameBoard.ROWS; row++) {
            for (int col = 0; col < GameBoard.COLS; col++) {

                // create the gui cell component
                GameCell cell = gameBoard.getCell(row, col);
                GUICell guiCell = new GUICell(cell);

                // set the gui cell component so it can be updated in the UI
                cell.setGUICell(guiCell);

                // add the component to the main panel
                this.add(guiCell);

                // register the gui cell to the main panel to read mouse clicks
                guiCell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        new Thread(() -> subject.cellClicked(cell)).start();
                    }
                });
            }
        }
    }

}
