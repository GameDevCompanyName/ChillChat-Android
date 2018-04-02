package com.gamedev.chillchat;

import android.support.v7.app.AppCompatActivity;
import com.gamedev.chillchat.client.ConsoleClient;

import java.util.HashMap;

public class Manager {

    public static final String VERSION = "AC0.1";

    public static String IP = "192.168.10.142";  //138.68.74.16
    public static int PORT = 16261; //1488  //16261

    public static ConsoleClient client = new ConsoleClient();
    public static HashMap<String, AppCompatActivity> activities = new HashMap<>();

    public static String lastName = "TEST";
    public static String myName;
    public static int myColor;

    public static String chooseColor(int id) {
        String result;
        switch (id) {
            case 1:
                result = "#f44336";
                break;
            case 2:
                result = "#3f51b5";
                break;
            case 3:
                result = "#29b6f6";
                break;
            case 4:
                result = "#ff5722";
                break;
            case 5:
                result = "#4caf50";
                break;
            case 6:
                result = "#8bc34a";
                break;
            case 7:
                result = "#ffeb3b";
                break;
            case 8:
                result = "#ec407a";
                break;
            case 9:
                result = "#26a69a";
                break;
            default:
                result = "#546e7a";
                break;
        }
        return result;
    }

}
