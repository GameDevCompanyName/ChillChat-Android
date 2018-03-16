package com.gamedev.chillchat;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Manager {

    public static HashMap<String, AppCompatActivity> activities = new HashMap<>();

    public static void add(String key, AppCompatActivity activity){
        activities.put(key, activity);
    }

    public static AppCompatActivity getActivity(String key){
        return activities.get(key);
    }
}
