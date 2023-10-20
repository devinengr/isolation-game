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
                new Object[] {"You", "AI Random", "AI H1", "AI H2"},
                "");
        if (result == -1) {
            return getPlayerType(player);
        }
        return switch (result) {
            case 1 -> new PlayerRandom(true);
            case 2 -> new PlayerAdversarialH1(true);
            case 3 -> new PlayerAdversarialH2(true);
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
