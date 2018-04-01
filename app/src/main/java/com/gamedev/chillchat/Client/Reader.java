package com.gamedev.chillchat.Client;

import android.os.AsyncTask;
import android.util.Log;
import com.gamedev.chillchat.GUI.MainActivity;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Queue;

import static com.gamedev.chillchat.Manager.activities;

public class Reader extends AsyncTask<BufferedReader, String, Void> {

    @Override
    protected Void doInBackground(BufferedReader... bufferedReaders) {
        BufferedReader in = bufferedReaders[0];
        try {
            Log.d("MYERROR", "POLUCHILOS");
            while (!isCancelled()) {
                String str = in.readLine();
                Log.d("MYERROR", "READ");
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

}
