package gui;

import observer.state.GameStateSubject;

import javax.swing.*;

public class GameWindow {

    private JFrame frame;
    private GameBoard board;

    public GameWindow(GameStateSubject subject) {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame();
            board = new GameBoard(subject);
            createBoard();
            createWindow();
        });
    }

    private void createBoard() {
        frame.getContentPane().add(board);
    }

    private void createWindow() {
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public GameBoard getBoard() {
        return board;
    }

//
//    public void showWinnerMenu(Player winner, List<Player> players) {
//        int result = JOptionPane.showConfirmDialog(
//                panel,
//                String.format("Player " + winner.getPlayerType() + " won! Play again?"),
//                "Game Over",
//                JOptionPane.YES_NO_OPTION,
//                JOptionPane.INFORMATION_MESSAGE
//        );
//        switch (result) {
//            case 0:
//                GameBoard.getSingleton().initializeBoard(panel);
//                GameState.getSingleton().initializeGame(players.get(0).getPlayerType(),
//                                                        players.get(1).getPlayerType());
//                break;
//            case 1:
//                System.exit(0);
//                break;
//        }
//    }
//
//    public PlayerType showPlayerSelectionMenu(int playerNumber) {
//        int result = JOptionPane.showOptionDialog(
//                panel,
//                String.format("What player type will Player %d be?", playerNumber),
//                "Player Chooser",
//                JOptionPane.OK_OPTION,
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                new Object[] {"You", "Randy", "Luigi"},
//                "");
//        switch (result) {
//            case 1:
//                return PlayerType.RANDOM;
//            case 2:
//                return PlayerType.AI;
//            default:
//                return PlayerType.YOU;
//        }
//    }

}
