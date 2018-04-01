package com.gamedev.chillchat.Client;

import android.os.AsyncTask;
import android.util.Log;
import com.gamedev.chillchat.GUI.MainActivity;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Queue;

import static com.gamedev.chillchat.Manager.activities;

public class Reader extends AsyncTask<Socket, String, Void> {

    private BufferedReader in;
    private PrintWriter out;

    @Override
    protected Void doInBackground(Socket... sockets) {
        Socket socket = sockets[0];
        try {
            Log.d("MYERROR", "POLUCHILOS");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")), true);
            while (!isCancelled()) {
                String str = in.readLine();
                publishProgress(str);
            }
            Log.d("MYERROR", "DONE");
        } catch (IOException e) {
            //TODO
            Log.d("MYERROR", "Не получилось получить сообщение");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate();
        Log.d("MYERROR", "PROGRESS");
        if (values.length != 0)
            ClientMessage.read(values[0]);
    }

    public Sender getSenderClass() {
        return new Sender();
    }

    public class Sender extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            Log.d("MYERROR", "WRITE");
            while (out == null) {
            }
            for (String s : strings)
                out.println(s);
            return null;
        }
    }

}
