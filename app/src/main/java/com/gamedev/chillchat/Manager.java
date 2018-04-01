package com.gamedev.chillchat;

import android.support.v7.app.AppCompatActivity;
import com.gamedev.chillchat.Client.ConsoleClient;

import java.util.HashMap;

public class Manager {

    public static final String VERSION = "AC0.1";

    public static String IP = "192.168.10.142";  //138.68.74.16
    public static int PORT = 16261; //1488  //16261

    public static HashMap<String, AppCompatActivity> activities = new HashMap<>();

    public static boolean logged = false;
    public static boolean firstClick = true;

    public static String lastName = "TEST";

}
