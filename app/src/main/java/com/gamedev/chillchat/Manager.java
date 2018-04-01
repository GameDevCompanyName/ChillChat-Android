package com.gamedev.chillchat;

import android.support.v7.app.AppCompatActivity;
import com.gamedev.chillchat.Client.ConsoleClient;

import java.util.HashMap;

public class Manager {

    public static final String VERSION = "AC0.1";

    public static String IP = "138.68.74.16";  //138.68.74.16
    public static int PORT = 1488; //1488  //16261

    public static ConsoleClient client = new ConsoleClient();
    public static HashMap<String, AppCompatActivity> activities = new HashMap<>();

    public static String lastName = "TEST";

}
