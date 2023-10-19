package board;

import gui.GUICell;
import player.Player;

public class GameCell {

    private int row;
    private int col;
    private CellState cellState;
    private Player player;

    // todo refreshes the gui when state is updated.
    // todo so, do not clone this object. set the cloned object's gui cell to null.
    private GUICell guiCell;

    public GameCell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellState = CellState.TOKEN_STATE;
    }

    @Override
    public GameCell clone() {
        GameCell newCell = new GameCell(row, col);
        newCell.cellState = cellState; // enums are weird
        newCell.player = player == null ? null : player.clone();
        return newCell;
    }

    /**
     * use this function if this cell is associated with the main state.
     * this allows the cell to repaint the gui cell when its state is updated.
     */
    public void setGUICell(GUICell guiCell) {
        this.guiCell = guiCell;
    }

    public CellState getCellState() {
        return cellState;
    }

    /**
     * sets the cell state. if this cell is associated with a gui cell,
     * it will repaint the gui cell to update the UI.
     * @param cellState the cell state to set the cell to
     */
    public void setCellState(CellState cellState) {
        this.cellState = cellState;
        if (guiCell != null) {
            guiCell.repaint();
        }
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
