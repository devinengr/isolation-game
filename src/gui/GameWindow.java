package gui;

import state.GameState;
import state.Player;
import state.PlayerType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public final class GameWindow {

    private static GameWindow gameWindow;

    private JFrame frame;
    private JPanel panel;

    private GameWindow() {
        frame = new JFrame();
        panel = new JPanel();
        panel.setLayout(new GridLayout(
                GameBoard.ROWS,
                GameBoard.COLS));
        frame.getContentPane().add(panel);
    }

    public static GameWindow getSingleton() {
        if (gameWindow == null) {
            gameWindow = new GameWindow();
        }
        return gameWindow;
    }

    public void create() {
        GameBoard.getSingleton().initializeBoard(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void showWinnerMenu(Player winner, List<Player> players) {
        int result = JOptionPane.showConfirmDialog(
                panel,
                String.format("Player " + winner.getPlayerType() + " won! Play again?"),
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );
        switch (result) {
            case 0:
                GameBoard.getSingleton().initializeBoard(panel);
                GameState.getSingleton().initializeGame(players.get(0).getPlayerType(),
                                                        players.get(1).getPlayerType());
                break;
            case 1:
                System.exit(0);
                break;
        }
    }

    public PlayerType showPlayerSelectionMenu(int playerNumber) {
        int result = JOptionPane.showOptionDialog(
                panel,
                String.format("What player type will Player %d be?", playerNumber),
                "Player Chooser",
                JOptionPane.OK_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[] {"You", "Randy", "Luigi"},
                "");
        switch (result) {
            case 1:
                return PlayerType.RANDOM;
            case 2:
                return PlayerType.AI;
            default:
                return PlayerType.YOU;
        }
    }

}
