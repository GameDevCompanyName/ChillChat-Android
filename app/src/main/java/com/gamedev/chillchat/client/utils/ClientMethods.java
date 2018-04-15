package com.gamedev.chillchat.client.utils;

import android.util.Log;
import com.gamedev.chillchat.GUI.ActivityMain;
import com.gamedev.chillchat.GUI.ActivityLogin;
import com.gamedev.chillchat.GUI.fragments.Chat;

import static com.gamedev.chillchat.Manager.*;

public class ClientMethods {

    public static void clientVersionRequestReceived() {
        //TODO
    }

    public static void loginWrongErrorReceived() {
        ((ActivityLogin) activities.get("ActivityLogin")).wrongPass();
    }

    public static void loginAlreadyErrorReceived() {
        ((ActivityLogin) activities.get("ActivityLogin")).userAlreadyOnline();
    }

    public static void loginSuccessReceived() {
        ((ActivityLogin) activities.get("ActivityLogin")).goToChat();
    }

    public static void userRegistrationSuccessReceived() {
        //TODO
    }

    public static void userColorReceived(String login, String color) {
        if (activities.get("ActivityMain") != null && login.equals(myName)) {
            Chat chat = ((ActivityMain) activities.get("ActivityMain")).getChat();
            chat.changeColorMessage(chooseColor(Integer.parseInt(color)));
        } else {
            myColor = chooseColor(Integer.parseInt(color));
        }
    }

    public static void userMessageReceived(String login, String message, String color) {
        Chat chat = ((ActivityMain) activities.get("ActivityMain")).getChat();
        chat.showUserMessage(login, message, color);
    }

    public static void userActionReceived(String login, String action) {
        //TODO
    }

    public static void userDisconnectReceived(String reason) {
//        clientWindow.disconnectedByReason(reason);
//        client.destroy();
    }

    public static void serverMessageReceived(String message) {
        if (activities.get("ActivityMain") != null && ((ActivityMain) activities.get("ActivityMain")).getChat() != null) {
            Chat chat = ((ActivityMain) activities.get("ActivityMain")).getChat();
            chat.showServerMessage(message);
        }
    }

    public static void serverEventReceived(String event) {
        //((ActivityMain) activities.get("ActivityMain")).showMessage(event);
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

    public static void userRoomChanged(String roomId, String nameRoom) {
        if (activities.get("ActivityMain") != null)
            ((ActivityMain) activities.get("ActivityMain")).toRoom();
        try {
            ((ActivityMain) activities.get("ActivityMain")).getChat().showServerMessage("Вы подключились к комнате " + nameRoom);
        } catch (Exception e) {

        }
    }
}