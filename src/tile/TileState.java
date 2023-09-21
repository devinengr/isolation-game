package tile;

import state.Player;

import java.awt.*;

public enum TileState {

    PLAYER_TOKEN_STATE(Color.LIGHT_GRAY, null),
    PLAYER_START_STATE(Color.GREEN, null),
    BLANK_STATE(Color.GRAY, null),
    START_STATE(Color.YELLOW, null),
    TOKEN_STATE(Color.CYAN, null),
    ;

    private Color color;
    private Player player;

    TileState(Color color, Player player) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
