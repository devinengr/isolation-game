package player;

import state.GameState;

public interface PlayerType {

    void move(GameState gameState);
    void removeToken(GameState gameState);

    PlayerType clone();

}
