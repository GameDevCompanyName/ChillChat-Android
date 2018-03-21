package com.gamedev.chillchat.Client;

import android.os.AsyncTask;
import android.util.Log;
import com.gamedev.chillchat.GUI.ChatActivity;
import com.gamedev.chillchat.GUI.MainActivity;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;

import static com.gamedev.chillchat.Manager.activities;
import static com.gamedev.chillchat.Manager.firstClick;
import static com.gamedev.chillchat.Manager.logged;

public class ConsoleClient {

    private Socket socket;

    private BufferedReader in;
    private PrintWriter out;
    public Resender resender;

    private String IP;
    private int PORT;

    private boolean stoped = true;

    public ConsoleClient(String IP, int PORT) {
        this.IP = IP;
        this.PORT = PORT;
    }

    public void start() {
        TryConnect tryConnect = new TryConnect();
        Log.d("MYERROR", "CREATE TRY CONNECT");
        try {
            socket = tryConnect.execute().get();
            Log.d("MYERROR", "SOCKET");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
            Log.d("MYERROR", "IN");
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")), true);
            Log.d("MYERROR", "OUT");
            resender = new Resender();
            Log.d("MYERROR", "CREATE RESENDER");
            resender.execute(in);
            Log.d("MYERROR", "START RESENDER");
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Не удалось установить соединение!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Не смог создаьб потоки входа/выхода!");
            e.printStackTrace();
        }
    }

    public void tryLogin(String l, String p) {
        sendMessage(JsonHandler.getString(l, p));
    }

    public void sendMessage(String formedMessage) {
        out.println(formedMessage);
    }

    private class TryConnect extends AsyncTask<Void, Void, Socket> {
        @Override
        protected Socket doInBackground(Void... voids) {
            Socket s = null;
            try {
                Log.d("MYERROR", "TRY");
                s = new Socket(IP, PORT);
                Log.d("MYERROR", "TRY END");
            } catch (IOException e) {
                Log.d("MYERROR", "CATCH");
                System.exit(1);
                e.printStackTrace();
            }
            return s;
        }
    }

    private class Resender extends AsyncTask<BufferedReader, JSONObject, Void> {

        @Override
        protected Void doInBackground(BufferedReader... bufferedReaders) {
            BufferedReader bufferedReader = bufferedReaders[0];

            try {
                while (!isCancelled()) {
                    Log.d("MYERROR", "ЦИКЛЛЛЛЛ");
                    String str = bufferedReader.readLine();
                    JSONObject message = (JSONObject) JSONValue.parse(str);
                    Log.d("MYERROR", String.valueOf(isCancelled()) + " 1");
                    publishProgress(message);
                    Log.d("MYERROR", String.valueOf(isCancelled()) + " 2");
                }
                Log.d("MYERROR", "DONE");
            } catch (IOException e) {
                Log.d("MYERROR", "Не получилось получить сообщение");
                e.printStackTrace();
            }
            Log.d("MYERROR", "END");
            return null;
        }

        @Override
        protected void onProgressUpdate(JSONObject... values) {
            super.onProgressUpdate();
            checkMessage(values[0]);
        }
    }

    private void checkMessage(JSONObject message) {
        if (message.get("type").equals("3")) {
            checkUser(message.get("response").toString());
        }

        if (logged) {
            if (message.get("type").equals("1")) {

                String name = message.get("name").toString();
                String text = message.get("text").toString();
                Integer color = Integer.parseInt(message.get("color").toString());
                showMassage(name, text, color);
            }

            try {
                if (message.get("type").equals("2")) {
                    Log.d("MYERROR", "SERVER");
                    String text = message.get("text").toString();
                    showMassage("SERVER", text, 9);
                }
            }
            catch (Exception e){
                Log.d("MYERROR", "Не вышло:(");
            }

        }
    }

    private void checkUser(String text) {
        if (text.equals("-1")) {
            wrongPass();
        } else if (text.equals("-2")) {
            userAlreadyOnServer();
        } else {
            logged = true;
            goToChat();
        }
    }

    private void wrongPass() {
        //Notification
    }

    private void userAlreadyOnServer() {
        //Notification
    }

    private void goToChat() {
        ((MainActivity) activities.get("MainActivity")).goToChat();
    }

    private void showMassage(final String name, final String text, final int color) {
        ((ChatActivity) activities.get("ChatActivity")).showMassage(name, text, color);
    }

    public void destroy() {
        Log.d("MYERROR", "START DESTROY");
        logged = false;
        firstClick = true;
        resender.cancel(true);
        sendMessage(JsonHandler.getString("До встречи!"));
        Log.d("MYERROR", "END DESTROY");
    }

}