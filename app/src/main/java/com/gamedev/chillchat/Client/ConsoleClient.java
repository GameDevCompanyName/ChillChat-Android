package com.gamedev.chillchat.Client;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutionException;

import static com.gamedev.chillchat.Manager.firstClick;

public class ConsoleClient {

    private String IP;
    private int PORT;

    private Socket socket;
    private Reader reader;

    public ConsoleClient(String IP, int PORT) {
        this.IP = IP;
        this.PORT = PORT;
    }

    public boolean setConnection() {
        if (socket != null) return true;
        new Connection().execute(IP, String.valueOf(PORT));
        return socket != null;
    }

    public void start() {
        reader = new Reader();
        reader.execute(socket);
    }

    public void sendMessage(String... strings){
        Reader.Sender sender;
        sender = reader.getSenderClass();
        sender.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, strings);
    }

    public void destroy() {//TODO
        sendMessage(ClientMessage.disconnectSend("Fuck off)"));
        reader.cancel(false);
    }

    public void setSocket(Socket socket){
        this.socket = socket;
    }



}