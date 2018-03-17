package com.gamedev.chillchat.Client;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.provider.Telephony;
import android.util.Log;
import com.gamedev.chillchat.GUI.ChatActivity;
import com.gamedev.chillchat.GUI.MainActivity;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;

import static com.gamedev.chillchat.Manager.activities;

public class ConsoleClient {

    private Socket socket;

    private BufferedReader in;
    private PrintWriter out;
    public ConsoleResender resender;

    private String IP;
    private int PORT;

    private boolean stoped = true;

    public ConsoleClient(String IP, int PORT) {
        this.IP = IP;
        this.PORT = PORT;
    }

    public void start() {
        TryConnect tryConnect = new TryConnect();
        try {
            socket = tryConnect.execute().get();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")), true);
            resender = new ConsoleResender(in);
            resender.start();
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
                s = new Socket(IP, PORT);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return s;
        }
    }

    private class Resender extends AsyncTask<BufferedReader, Void, Void> {

        @Override
        protected Void doInBackground(BufferedReader... bufferedReaders) {
            BufferedReader bufferedReader = bufferedReaders[0];

            try {
                while (stoped) {

                    if (!stoped) {
                        Log.d("MYERROR", "IF DONE");
                        return null;
                    }

                    String str = in.readLine();

                    JSONObject message = (JSONObject) JSONValue.parse(str);


                    if (message.get("type").equals("3")) {
                        checkUser(message.get("response").toString());
                    }

                    if (message.get("type").equals("1")) {

                        String name = message.get("name").toString();
                        String text = message.get("text").toString();
                        Integer color = Integer.parseInt(message.get("color").toString());
                        showMassage(name, text);

                    }
                }
                Log.d("MYERROR", "DONE");
            }
            catch (IOException e){
                Log.d("MYERROR", "Не получилось получить сообщение");
                e.printStackTrace();
            }
            return null;
        }
    }

    private class ConsoleResender extends Thread {

        private BufferedReader in;

        private ConsoleResender(BufferedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            try {
                while (stoped) {

                    if (!stoped) {
                        Log.d("MYERROR", "DONE");
                        return;
                    }

                    String str = in.readLine();

                    JSONObject message = (JSONObject) JSONValue.parse(str);


                    if (message.get("type").equals("3")) {
                        checkUser(message.get("response").toString());
                    }

                    if (message.get("type").equals("1")) {

                        String name = message.get("name").toString();
                        String text = message.get("text").toString();
                        Integer color = Integer.parseInt(message.get("color").toString());
                        showMassage(name, text);

                    }
                    System.out.println(stoped);
                }

            } catch (IOException e) {
                System.err.println("Ошибка при получении сообщения.");
                e.printStackTrace();
            }
        }


    }

    private void checkUser(String text) {
        if (text.equals("-1")) {
            wrongPass();
        }

        if (text.equals("-2")) {
            userAlreadyOnServer();
        } else {
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

    private void showMassage(final String name, final String text) {

        System.out.println(Thread.currentThread());
        System.out.println(Thread.activeCount());

        Runnable r = new Runnable() {
            @Override
            public void run() {
                ((ChatActivity) activities.get("ChatActivity")).showMassage(name, text);
            }
        };
        ((ChatActivity) activities.get("ChatActivity")).runOnUiThread(r);
        System.out.println(Thread.currentThread());
    }

}