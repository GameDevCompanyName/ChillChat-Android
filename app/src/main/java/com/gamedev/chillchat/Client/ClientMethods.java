package com.gamedev.chillchat.Client;

import com.gamedev.chillchat.GUI.ChatActivity;
import com.gamedev.chillchat.GUI.MainActivity;

import static com.gamedev.chillchat.Manager.activities;

public class ClientMethods {

    public static void clientVersionRequestReceived() {
        //TODO
    }

    public static void loginWrongErrorReceived() {
//        clientWindow.passWrongError();
    }

    public static void loginAlreadyErrorReceived() {
//        clientWindow.userAlreadyOnline();
    }

    public static void loginSuccessReceived() {
        ((MainActivity) activities.get("MainActivity")).goToChat();
    }

    public static void userRegistrationSuccessReceived() {
        //TODO
    }

    public static void userColorReceived(String login, String color) {
//        clientWindow.userColorRecieved(login, color);
    }

    public static void userMessageReceived(String login, String message, String color) {
        ((ChatActivity) activities.get("ChatActivity")).showMassage(login, message, color);
    }

    public static void userActionReceived(String login, String action) {
        //TODO
    }

    public static void serverMessageReceived(String message) {
        ((ChatActivity) activities.get("ChatActivity")).showMessage(message);
    }

    public static void serverEventReceived(String event) {
        //((ChatActivity) activities.get("ChatActivity")).showMessage(event);
    }

    public static void serverUserKickedReceived(String login, String reason) {
//        clientWindow.userKickedRecieved(login, reason);
    }

    public static void userDisconnectReceived(String reason) {
//        clientWindow.disconnectedByReason(reason);
    }

    public static void serverUserLoginReceived(String login) {
        if ((ChatActivity) activities.get("ChatActivity") != null)
            ((ChatActivity) activities.get("ChatActivity")).showMessage(login + " зашел в чатик");
    }

    public static void serverUserDisconnectReceived(String login) {
        ((ChatActivity) activities.get("ChatActivity")).showMessage(login + " ушел из чатика");
    }

}