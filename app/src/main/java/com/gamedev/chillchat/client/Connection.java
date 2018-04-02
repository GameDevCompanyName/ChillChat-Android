package com.gamedev.chillchat.client;

import android.os.AsyncTask;
import android.util.Log;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;

import static com.gamedev.chillchat.Manager.client;

public class Connection extends AsyncTask<String, Void, Object[]> {

    @Override
    protected Object[] doInBackground(String... strings) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        boolean connect = false;
        int countConnect = -1;

        String ip = strings[0];
        int port = Integer.parseInt(strings[1]);

        int i = 0;
        while (!connect && i != countConnect) {
            socket = new Socket();
            InetSocketAddress sa = new InetSocketAddress(ip, port);
            try {
                socket.connect(sa, 1000);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")), true);
                connect = true;
            } catch (IOException e) {
                Log.d("MYERROR", "NO CONNECT");//TODO
                e.printStackTrace();
            }
            i++;
        }
        if (!connect) return null;
        return new Object[]{socket, in, out};
    }

    @Override
    protected void onPostExecute(Object... objects) {
        super.onPostExecute(objects);
        client.setSocket((Socket) objects[0]);
        client.setIn((BufferedReader) objects[1]);
        client.setOut((PrintWriter) objects[2]);
    }
}
