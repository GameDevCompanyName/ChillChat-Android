package com.gamedev.chillchat.client.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.PrintWriter;

import static com.gamedev.chillchat.Manager.LOG;

public class Sender extends AsyncTask<Object, Void, Void> {

    @Override
    protected Void doInBackground(Object... objects) {
        Log.d("MYERROR", "WRITE");
        PrintWriter out = (PrintWriter) objects[0];
        String[] strings = (String[]) objects[1];
        for (String s : strings) {
            Log.d(LOG, s);
            out.println(s);
        }
        return null;
    }
}