package ar.fiuba.tdd.tp.server.motor.games;


import ar.fiuba.tdd.tp.server.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.shared.Message;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    protected List<GameCommand> commands = new ArrayList<GameCommand>();

    abstract boolean isGameOver();

    abstract String getGameOverMessage();

    public String getWelcomeMessage() {
        String gameName = this.getGameName();
        return Message.WELCOME.getText().concat(gameName);
    }

    public String getGameName() {
        //Obtengo el nombre de la clase. Esto lo toma de la clase que se crea posta (AbrirPuerta2), no de Game.
        return this.getClass().getSimpleName();
    }

    /* template method : */
    public String processInput(String input) {

        for (GameCommand command : commands) {
            if (input.equals(command.getIdentifier())) {
                String response = command.execute();
                if (isGameOver()) {
                    response = getGameOverMessage();
                }
                return response;
            }
        }
        return "Invalid command";
    }
}

