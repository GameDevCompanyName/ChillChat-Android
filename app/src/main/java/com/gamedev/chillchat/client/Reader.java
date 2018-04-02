package com.gamedev.chillchat.client;

import android.os.AsyncTask;
import android.util.Log;
import com.gamedev.chillchat.client.utils.ClientMessage;

import java.io.*;

public class Reader extends AsyncTask<BufferedReader, String, Void> {

    @Override
    protected Void doInBackground(BufferedReader... bufferedReaders) {
        BufferedReader in = bufferedReaders[0];
        try {
            while (!isCancelled()) {
                String str = in.readLine();
                publishProgress(str);
            }
            Log.d("MYERROR", "READER STOPED");
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
        if (values.length != 0)
            ClientMessage.read(values[0]);
    }

}
