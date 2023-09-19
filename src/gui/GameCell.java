package gui;

import tile.Tile;
import javax.swing.*;
import java.awt.*;

public class GameCell extends JPanel {

    private Tile tile;

    public GameCell(Tile tile, int tileWidth, int tileHeight) {
        this.setPreferredSize(new Dimension(tileWidth, tileHeight));
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(tile.getTileState().getColor());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

}
