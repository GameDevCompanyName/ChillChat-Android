package com.gamedev.chillchat.client.utils;

import android.os.AsyncTask;
import android.util.Log;
import com.gamedev.chillchat.client.utils.ClientMessage;

import java.io.*;

import static com.gamedev.chillchat.Manager.LOG;

public class Reader extends AsyncTask<BufferedReader, String, Void> {

    @Override
    protected Void doInBackground(BufferedReader... bufferedReaders) {
        BufferedReader in = bufferedReaders[0];
        try {
            Log.d(LOG, "READER CREATE");
            while (!isCancelled()) {
                String str = in.readLine();
                Log.d(LOG, "READ");
                Log.d(LOG, str);
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
