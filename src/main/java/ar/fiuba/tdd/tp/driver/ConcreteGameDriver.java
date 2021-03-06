package ar.fiuba.tdd.tp.driver;

import ar.fiuba.tdd.tp.engine.motor2.Game;
import ar.fiuba.tdd.tp.engine.motor2.GameBuilder;
import ar.fiuba.tdd.tp.engine.motor2.GameState;
import ar.fiuba.tdd.tp.server.PlayerConnection;

/**
 * Created by jorlando on 19/05/16.
 */
public class ConcreteGameDriver implements GameDriver {

    Game game = null;
    private String packageOfGames = "ar.fiuba.tdd.tp.engine.motor2.games.";

    public void initGame(String gameName) throws GameLoadFailedException {
        try {
            Class<?> gameClass = Class.forName(packageOfGames.concat(gameName));
            GameBuilder builder = (GameBuilder) gameClass.newInstance();
            game = builder.build();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new GameLoadFailedException("No se pudo instanciar el juego: ".concat(gameName));
        }
    }

    public String sendCommand(String cmd) {
        return game.execute(cmd);
    }

    public String sendCommand(String cmd, PlayerConnection player) {
        return game.execute(cmd, player);
    }

    public GameState getCurrentState() {
        return game.getState();
    }

    public Game getGame() {
        return this.game;
    }
}
