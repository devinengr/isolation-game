package util;

import board.GameCell;

public class Heuristic implements Comparable<Heuristic> {

    private int heuristic;
    private GameCell gameCell;

    public Heuristic(int heuristic, GameCell gameCell) {
        this.gameCell = gameCell;
    }

    public GameCell getGameCell() {
        return gameCell;
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
