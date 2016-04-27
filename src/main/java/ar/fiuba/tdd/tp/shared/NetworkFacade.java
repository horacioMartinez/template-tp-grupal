package ar.fiuba.tdd.tp.shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jorlando on 26/04/16.
 */
public abstract class NetworkFacade {

    protected static final String ENCODING = "UTF-8";
    public String lastMessageReceived = null;
    protected PrintWriter outputStream;
    protected BufferedReader inputStream;

    public NetworkFacade() {
    }

    public abstract void initConnection(int port) throws IOException;

    public String receiveMessage() throws IOException {
        return inputStream.readLine();
    }

    public void sendMessage(String msg) {
        outputStream.println(msg);
    }

    public boolean continuesReceivingMessages() {
        try {
            this.lastMessageReceived = this.receiveMessage();
        } catch (IOException io) {
            return false;
        }
        return (this.lastMessageReceived != null);
    }

    public String getLastMessageReceived() {
        return this.lastMessageReceived;
    }
}