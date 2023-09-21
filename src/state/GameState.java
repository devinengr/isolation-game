package state;

import gui.GameBoard;
import gui.GameCell;
import tile.Tile;
import tile.TileState;

import java.util.ArrayList;
import java.util.List;

public final class GameState {

    private static GameState gameState;

    private List<Player> players;
    private int currentPlayerIndex = 0;
    private MoveType currentMove;

    private GameState() {
        players = new ArrayList<>();
        currentMove = MoveType.MOVE_PLAYER_TOKEN;
    }

    public static GameState getSingleton() {
        if (gameState == null) {
            gameState = new GameState();
        }
        return gameState;
    }

    private void initializePlayer(Tile tile) {
        tile.setTileState(TileState.PLAYER_TOKEN_STATE);
        Player player = new Player(PlayerType.YOU, tile);
        player.setTile(tile);
        players.add(player);
    }

    public void initializeGame() {
        GameBoard board = GameBoard.getSingleton();
        initializePlayer(board.getTile(2, 0));
        initializePlayer(board.getTile(GameBoard.ROWS - 3, GameBoard.COLS - 1));
    }

    public void updateState(Tile tile) {
        Player player = players.get(currentPlayerIndex);
        if (player.getPlayerType() == PlayerType.YOU) {
            switch (gameState.getCurrentMove()) {
                case MOVE_PLAYER_TOKEN:
                    if (validateTokenMove(tile)) {
                        movePlayer(player, tile);
                    }
                    break;
                case REMOVE_TILE_TOKEN:
                    if (validateTokenRemoval(tile)) {
                        removeToken(tile);
                        Player newPlayer = players.get(currentPlayerIndex);
                        if (!playerCanMove(newPlayer)) {
                            // todo this player has lost the game
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void movePlayer(Player player, Tile tile) {
        tile.setTileState(TileState.PLAYER_TOKEN_STATE);
        player.getTile().setTileState(TileState.TOKEN_STATE);
        player.setTile(tile);
        currentMove = MoveType.REMOVE_TILE_TOKEN;
    }

    private void removeToken(Tile tile) {
        tile.setTileState(TileState.BLANK_STATE);
        currentMove = MoveType.MOVE_PLAYER_TOKEN;
        updatePlayerTurn();
    }

    private boolean playerCanMove(Player player) {
        Tile tile = player.getTile();
        for (int rowOffset = -1; rowOffset < 1; rowOffset++) {
            for (int colOffset = -1; colOffset < 1; colOffset++) {
                int row = tile.getX() + colOffset;
                int col = tile.getY() + rowOffset;
                if (row >= 0 && row < GameBoard.ROWS) {
                    if (col >= 0 && col < GameBoard.COLS) {
                        Tile toCheck = GameBoard.getSingleton().getTile(row, col);
                        if (toCheck.getTileState() == TileState.TOKEN_STATE) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean validateTokenMove(Tile tile) {
        Player p = players.get(currentPlayerIndex);
        int playerX = p.getTile().getX();
        int playerY = p.getTile().getY();
        int destinationX = tile.getX();
        int destinationY = tile.getY();
        if (Math.abs(destinationX - playerX) <= 1 &&
                Math.abs(destinationY - playerY) <= 1) {
            switch (tile.getTileState()) {
                case TOKEN_STATE:
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    private boolean validateTokenRemoval(Tile tile) {
        if (tile.getTileState() == TileState.TOKEN_STATE) {
            return true;
        }
        return false;
    }

    private void updatePlayerTurn() {
        updatePlayerIndex();
    }

    private void updatePlayerIndex() {
        if (currentPlayerIndex + 1 >= players.size()) {
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex += 1;
        }
    }

    public void setCurrentMove(MoveType type) {
        this.currentMove = type;
    }

    public MoveType getCurrentMove() {
        return currentMove;
    }

}
