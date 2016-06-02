package ar.fiuba.tdd.tp.engine.motor2;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    GameState state = GameState.Ready;
    private ArrayList<CommandWin> winnersCommands = new ArrayList<CommandWin>();
    private HashMap<String, Command> executableCommands = new HashMap<String, Command>();

    public void setWinnersCommands(CommandWin commandWin) {
        this.winnersCommands.add(commandWin);
    }

    public void setExecutableCommands(Command command) {
        this.executableCommands.put(command.getName(),command);
    }

    public void setCommandWin(Container container, String statusWin) {
        CommandWin win = new CommandWin();
        win.setComponent(container);
        win.setWinnableCommand((HashMap<String, Container> components)-> container.checkStatus(statusWin));
        this.setWinnersCommands(win);
    }

    public String execute(String condition) {
        this.state = GameState.InProgress;
        try {
            if (this.executableCommands.containsKey(condition)) {
                return this.executableCommands.get(condition).execute();
            } else {
                return "Invalid command: ".concat(condition);
            }
        } finally {
            this.checkIfGameWin();
        }
    }

    public boolean endGame() {
        return ((this.state == GameState.Won) || (this.state == GameState.Lost));
    }

    public String getFinalMessage() {
        return this.state.getMessage();
    }

    public GameState getState() {
        return this.state;
    }

    public void checkIfGameWin() {
        for (CommandWin command : this.winnersCommands) {
            if (!command.win()) {
                return ;
            }
        }
        this.state = GameState.Won;
    }

    public void loseGame() {
        this.state = GameState.Lost;
    }

    public void setState(GameState newState) {
        this.state = newState;
    }
}