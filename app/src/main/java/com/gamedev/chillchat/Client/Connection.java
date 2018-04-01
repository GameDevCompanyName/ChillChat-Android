package com.gamedev.chillchat.Client;

import android.os.AsyncTask;
import android.util.Log;
import com.gamedev.chillchat.GUI.MainActivity;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import static com.gamedev.chillchat.Manager.activities;

public class Connection extends AsyncTask<String, Void, Socket> {

    private Socket socket;

    private boolean connect = false;
    private int countConnect = 5;

    @Override
    protected Socket doInBackground(String... strings) {

        String ip = strings[0];
        int port = Integer.parseInt(strings[1]);

        int i = 0;
        while (!connect && i != countConnect) {
            socket = new Socket();
            InetSocketAddress sa = new InetSocketAddress(ip, port);
            try {
                socket.connect(sa, 5000);
                connect = true;
            } catch (IOException e) {
                Log.d("MYERROR", "NO CONNECT");//TODO
                e.printStackTrace();
            }
            i++;
        }
        if (!connect) return null;
        return socket;
    }

    @Override
    protected void onPostExecute(Socket socket) {
        super.onPostExecute(socket);
        ((MainActivity) activities.get("MainActivity")).getClient().setSocket(socket);
    }
}
