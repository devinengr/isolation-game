package tile;

public enum Direction {

    NORTH(0,1),
    EAST(1,0),
    SOUTH(0,-1),
    WEST(-1,0),
    NORTHWEST(-1,1),
    NORTHEAST(1,1),
    SOUTHWEST(-1,-1),
    SOUTHEAST(1,-1),
    ;

    private int x;
    private int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
