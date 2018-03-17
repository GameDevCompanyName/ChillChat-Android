package com.gamedev.chillchat;

import android.support.v7.app.AppCompatActivity;
import com.gamedev.chillchat.Client.ConsoleClient;

import java.util.HashMap;

public class Manager {

    private static String IP = "192.168.10.142";  //138.68.74.16
    private static int PORT = 16261;

    public static HashMap<String, AppCompatActivity> activities = new HashMap<>();
    public static ConsoleClient client = new ConsoleClient(IP, PORT);

}
