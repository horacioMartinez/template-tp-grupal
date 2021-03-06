package ar.fiuba.tdd.tp.engine.motor2;

/**
 * Created by jorlando on 17/05/16.
 */

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    Game game;
    Container container;

    @Before
    public void setUp() {
        game = new Game();

        State doorState = new State();
        doorState.setState("open",true);
        container = new Container("Door");
        container.setState(doorState);
    }

    @Test
    public void testExecuteInvalidCommand() {
        assertEquals(game.execute("test"), "Invalid command");
    }

    @Test
    public void testSetCommandWinTrue() {
        game.setCommandWin(container, "open");
        game.execute("test");
        assertTrue(game.endGame());
    }

    @Test
    public void testSetCommandWinFalse() {
        game.setCommandWin(container, "invalid");
        assertFalse(game.endGame());
    }

    @Test
    public void testGameStateReady() {
        assertEquals( GameState.Ready, game.getState());
    }

    @Test
    public void testGameStateInProgress() {
        game.setCommandWin(new Container("test"), "open");
        game.execute("invalid command");
        assertEquals(GameState.InProgress, game.getState());
    }

    @Test
    public void testGameStateWin() {
        game.execute("something");
        assertNotEquals(GameState.Won, game.getState());
    }

    @Test
    public void testSetStateOk() {
        game.setState(GameState.Won);
        assertEquals(GameState.Won,game.getState());
    }

    @Test
    public void testFinalMessage() {
        game.setState(GameState.Won);
        assertEquals(game.getFinalMessage(),"WON THE GAME");
    }
}
