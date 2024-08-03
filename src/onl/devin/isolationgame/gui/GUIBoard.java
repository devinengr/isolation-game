package onl.devin.isolationgame.gui;

import onl.devin.isolationgame.board.GameBoard;
import onl.devin.isolationgame.board.GameCell;
import onl.devin.isolationgame.player.PlayerType;
import onl.devin.isolationgame.player.PlayerYou;
import onl.devin.isolationgame.state.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIBoard extends JPanel {

    private GameBoard gameBoard;

    /**
     * initialize the gui. take in the game state to grab:
     * -> a list of players which are users.
     * -> the game board.
     * this is to register them with the gui to determine, when the
     * user clicks, whose turn it is and if the user can click, and to
     * map the click with the user's move.
     * @param gameState the main game state
     */
    public GUIBoard(GameState gameState) {
        this.gameBoard = gameState.getGameBoard();

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
                        new Thread(() -> {
                            PlayerType p1 = gameState.getCurrentPlayer().getPlayerType();
                            PlayerType p2 = gameState.getWaitingPlayer().getPlayerType();
                            if (p1 instanceof PlayerYou) {
                                ((PlayerYou) p1).cellClicked(gameState, cell);
                            }
                            if (p2 instanceof PlayerYou) {
                                ((PlayerYou) p2).cellClicked(gameState, cell);
                            }
                        }).start();
                    }
                });
            }
        }
    }

}
