package gui;

import state.GameState;
import state.Player;
import state.PlayerType;
import tile.Tile;
import tile.TileState;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameCell extends JPanel implements MouseListener {

    private int x;
    private int y;
    private int cellWidth;
    private int cellHeight;
    private Tile tile;

    public GameCell(Tile tile, int x, int y, int cellWidth, int cellHeight) {
        this.tile = tile;
        this.x = x;
        this.y = y;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
    }

    public Tile getTile() {
        return tile;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(tile.getTileState().getColor());
        g.fillRect(0, 0, cellWidth, cellHeight);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(cellWidth, cellHeight);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        GameState.getSingleton().updateState(GameBoard.getSingleton().getTile(x, y));
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
