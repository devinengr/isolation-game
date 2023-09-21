import gui.GameBoard;
import gui.GameWindow;
import state.GameState;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameWindow window = new GameWindow();
            window.create();
            GameState gameState = GameState.getSingleton();
            gameState.initializeGame();
        });
    }

}
