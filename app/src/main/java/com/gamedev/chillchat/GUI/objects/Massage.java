package com.gamedev.chillchat.GUI.objects;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gamedev.chillchat.GUI.ChatActivity;
import com.gamedev.chillchat.R;

import static com.gamedev.chillchat.Manager.lastName;

public class Massage extends LinearLayout {

    private String name, text;
    private int color;

    private TextView first, second;

    private ChatActivity chatActivity;

    public Massage(ChatActivity chatActivity, String name, String text, int color) {
        super(chatActivity);
        this.chatActivity = chatActivity;
        this.name = name;
        this.text = text;
        this.color = color;
        setOrientation(VERTICAL);
        LayoutParams lm = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lm.setMargins(20, 0, 20, 10);
        setLayoutParams(lm);


        LayoutParams lpName = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        LayoutParams lpText = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lpText.setMargins(30, 0, 0, 0);

        if (!lastName.equals(name)) {
            first = new TextView(chatActivity);
            first.setText(name);
            first.setTextSize(23);
            first.setTextColor(Color.parseColor(chooseColor(color)));
            addView(first, lpName);
            lastName = name;
        }

        second = new TextView(chatActivity);
        second.setText(text);
        second.setTextSize(20);
        second.setTextColor(Color.parseColor("#fafafa"));
        Linkify.addLinks(second, Linkify.ALL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackground(Drawable.createFromPath(String.valueOf(R.drawable.back_message)));
        }
        this.setBackgroundColor(Color.argb(50, 214, 76, 78));
        addView(second, lpText);
    }

    public Massage(ChatActivity chatActivity, String text){
        super(chatActivity);
        this.chatActivity = chatActivity;
        this.text = text;

        LinearLayout.LayoutParams lpText = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        second = new TextView(chatActivity);
        second.setText(text);
        second.setTextSize(23);
        second.setGravity(Gravity.CENTER);
        second.setTextColor(Color.parseColor("#66bb6a"));
        Linkify.addLinks(second, Linkify.ALL);
        addView(second, lpText);
        lastName = "SERVER";
    }

    private String chooseColor(int id) {
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
