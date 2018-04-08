package com.gamedev.chillchat.client.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.PrintWriter;

public class Sender extends AsyncTask<Object, Void, Void> {

    @Override
    protected Void doInBackground(Object... objects) {
        Log.d("MYERROR", "WRITE");
        PrintWriter out = (PrintWriter) objects[0];
        String[] strings = (String[]) objects[1];
        for (String s : strings)
            out.println(s);
        return null;
    }
}