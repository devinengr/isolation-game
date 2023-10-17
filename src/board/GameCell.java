package board;

import gui.GUICell;

public class GameCell {

    private GameBoard gameBoard;
    private int col;
    private int row;
    private CellState cellState;
    private GUICell guiCell;

    public GameCell(GameBoard gameBoard, int row, int col) {
        this.gameBoard = gameBoard;
        this.row = row;
        this.col = col;
        this.cellState = CellState.TOKEN_STATE;
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

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

}
