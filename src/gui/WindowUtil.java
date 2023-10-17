package gui;

import player.Player;
import player.PlayerType;

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
                new Object[] {"You", "Randy", "Luigi"},
                "");
        if (result == -1) {
            return getPlayerType(player);
        }
        return PlayerType.values()[result];
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
