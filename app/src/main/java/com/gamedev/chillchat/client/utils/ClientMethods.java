package com.gamedev.chillchat.client.utils;

import android.util.Log;
import com.gamedev.chillchat.GUI.ChatActivity;
import com.gamedev.chillchat.GUI.MainActivity;

import static com.gamedev.chillchat.Manager.*;

public class ClientMethods {

    public static void clientVersionRequestReceived() {
        //TODO
    }

    public static void loginWrongErrorReceived() {
        ((MainActivity) activities.get("MainActivity")).wrongPass();
    }

    public static void loginAlreadyErrorReceived() {
        ((MainActivity) activities.get("MainActivity")).userAlreadyOnline();
    }

    public static void loginSuccessReceived() {
        ((MainActivity) activities.get("MainActivity")).goToChat();
    }

    public static void userRegistrationSuccessReceived() {
        //TODO
    }

    public static void userColorReceived(String login, String color) {
        if (activities.get("ChatActivity") != null && login.equals(myName)){
            ((ChatActivity) activities.get("ChatActivity")).changeColor(chooseColor(Integer.parseInt(color)));
        } else {
            myColor = chooseColor(Integer.parseInt(color));
        }
    }

    public static void userMessageReceived(String login, String message, String color) {
        ((ChatActivity) activities.get("ChatActivity")).showUserMessage(login, message, color);
    }

    public static void userActionReceived(String login, String action) {
        //TODO
    }

    public static void userDisconnectReceived(String reason) {
//        clientWindow.disconnectedByReason(reason);
//        client.destroy();
    }

    public static void serverMessageReceived(String message) {
        if (activities.get("ChatActivity") != null)
            ((ChatActivity) activities.get("ChatActivity")).showServerMessage(message);
    }

    public static void serverEventReceived(String event) {
        //((ChatActivity) activities.get("ChatActivity")).showMessage(event);
    }

    public static void serverUserKickedReceived(String login, String reason) {
        serverMessageReceived(login + " отключен(-а) по причине: " + reason);
    }

    public static void serverUserLoginReceived(String login) {
        serverMessageReceived(login + " подключился(-ась)");
    }

    public static void serverUserDisconnectReceived(String login) {
        serverMessageReceived(login + " отключился(-ась)");
    }

    public static void serverEchoReceived() {
        if (client.getSocket() != null) {
            try {
                client.sendMessage(ClientMessage.echoSend());
            } catch (NullPointerException e) {
                Log.d("MYERROR", "Не удалось отпарвить PONG");
            }
        }
    }
}