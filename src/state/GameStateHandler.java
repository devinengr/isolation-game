package state;

import player.MoveType;

public class GameStateHandler {

    private GameState gameState;
    private GameStateSubject subject;

    public GameStateHandler(GameStateSubject subject, GameState gameState) {
        this.gameState = gameState;
        this.subject = subject;
        gameState.setCurrentMove(MoveType.MOVE_PLAYER_TOKEN);
    }

    public GameStateSubject getSubject() {
        return subject;
    }

    public GameState getGameState() {
        return gameState;
    }

}
