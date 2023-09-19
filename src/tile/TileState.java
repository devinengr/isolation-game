package tile;

import java.awt.*;

public enum TileState {

    PLAYER_TOKEN_STATE (Color.PINK),
    PLAYER_START_STATE (Color.YELLOW),
    BLANK_STATE (Color.BLACK),
    START_STATE (Color.GRAY),
    TOKEN_STATE (Color.MAGENTA),
    ;

    private Color color;

    TileState(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
