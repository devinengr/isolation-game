package util;

public class Heuristic implements Comparable<Heuristic> {

    private int heuristic;

    // storing primitives because of reference issues w/ cloning
    private int cellRow;
    private int cellCol;

    public Heuristic(int heuristic, int cellRow, int cellCol) {
        this.heuristic = heuristic;
        this.cellRow = cellRow;
        this.cellCol = cellCol;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public int getCellRow() {
        return cellRow;
    }

    public int getCellCol() {
        return cellCol;
    }

    @Override
    public int compareTo(Heuristic o) {
        if (heuristic < o.heuristic) {
            return -1;
        } else if (heuristic > o.heuristic) {
            return 1;
        }
        return 0;
    }

}
