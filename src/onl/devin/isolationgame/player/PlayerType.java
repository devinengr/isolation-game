package onl.devin.isolationgame.player;

import onl.devin.isolationgame.state.GameState;

public interface PlayerType {

    void move(GameState gameState);
    void removeToken(GameState gameState);

    PlayerType clone();

}
