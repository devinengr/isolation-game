package gui;

import player.*;

import javax.swing.*;

public final class WindowUtil {

    private static JPanel parentPanel;

    private WindowUtil() {}

    public static void setParentPanel(JPanel parentPanel) {
        WindowUtil.parentPanel = parentPanel;
    }

    public static PlayerType getPlayerType(Player player) {
        int result = JOptionPane.showOptionDialog(
                parentPanel,
                String.format("What player type will Player %d be?", player.getPlayerNumber()),
                "Player Chooser",
                JOptionPane.OK_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[] {"You", "Random", "Adversarial"},
                "");
        if (result == -1) {
            return getPlayerType(player);
        }
        return switch (result) {
            case 1 -> new PlayerRandom();
            case 2 -> new PlayerAdversarial();
            default -> new PlayerYou();
        };
    }

    public static boolean getPlayAgain(Player winner) {
        int result = JOptionPane.showConfirmDialog(
                parentPanel,
                String.format("Player " + winner.getPlayerNumber() + " won! Play again?"),
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );
        return result == 0 ? true : false;
    }

}
