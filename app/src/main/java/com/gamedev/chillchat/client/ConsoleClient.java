package com.gamedev.chillchat.client;

import android.os.AsyncTask;
import android.util.Log;
import com.gamedev.chillchat.client.utils.ClientMessage;
import com.gamedev.chillchat.client.utils.Connection;
import com.gamedev.chillchat.client.utils.Reader;
import com.gamedev.chillchat.client.utils.Sender;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import static com.gamedev.chillchat.Manager.LOG;

public class ConsoleClient {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Reader reader;

    private boolean firstClick = true;

    public void setConnection(String ip, String port) {
        if (socket == null) {
            new Connection().execute(ip, port);
        }
    }

    public boolean isConnection() {
        return socket != null;
    }

    public void start() {
        reader = new Reader();
        reader.execute(in);
    }

    public void sendMessage(String... strings) {
        Sender sender = new Sender();
        sender.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, out, strings);
    }

    public void destroy() {//TODO
        sendMessage(ClientMessage.disconnectSend("Fuck off)"));
        reader.cancel(false);
        Log.d(LOG, "DESTROY");
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public boolean isFirstClick() {
        return firstClick;
    }

    public void setFirstClick(boolean b){
        firstClick = b;
    }

    public Socket getSocket() {
        return socket;
    }
}