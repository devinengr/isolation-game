package onl.devin.isolationgame.gui;

import onl.devin.isolationgame.state.GameState;

import javax.swing.*;

public class GUIWindow extends JFrame {

    private GUIBoard guiBoard;

    public GUIWindow(GameState gameState) {
        guiBoard = new GUIBoard(gameState);

        // set board as window's panel
        this.add(guiBoard);
        WindowUtil.setParentPanel(guiBoard);

        // set window settings
        this.pack();
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // show window
        this.setVisible(true);
    }

}
