import gui.GameWindow;
import state.GameState;
import state.PlayerType;

import javax.swing.*;

public class Main {

    private void run() {
        SwingUtilities.invokeLater(() -> {
            GameWindow window = GameWindow.getSingleton();
            window.create();
            PlayerType playerType1 = window.showPlayerSelectionMenu(1);
            PlayerType playerType2 = window.showPlayerSelectionMenu(2);
            GameState gameState = GameState.getSingleton();
            gameState.initializeGame(playerType1, playerType2);
        });
    }

    public static void main(String[] args) {
        new Main().run();
    }

}
