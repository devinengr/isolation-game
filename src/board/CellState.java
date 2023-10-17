package board;

import java.awt.*;

public enum CellState {

    PLAYER_STATE(Color.DARK_GRAY),
    BLANK_STATE(Color.WHITE),
    TOKEN_STATE(Color.LIGHT_GRAY),
    ;

    private Color color;

    CellState(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
