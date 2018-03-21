package com.gamedev.chillchat.GUI.objects;


import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gamedev.chillchat.GUI.ChatActivity;

public class Massage {

    private String name, text;
    private int color;

    private TextView first, second;
    private LinearLayout panel;

    private ChatActivity chatActivity;

    public Massage(ChatActivity chatActivity, String name, String text, int color) {
        this.chatActivity = chatActivity;
        this.name = name;
        this.text = text;
        this.color = color;
    }

    public void create() {
        panel = new LinearLayout(chatActivity);
        panel.setOrientation(0);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        first = new TextView(chatActivity);
        first.setText(name + ": ");
        first.setTextSize(20);
        first.setTextColor(Color.parseColor(chooseColor(color)));

        second = new TextView(chatActivity);
        second.setText(text);
        second.setTextSize(20);
        second.setTextColor(Color.parseColor("#fafafa"));
//        second.setAutoLinkMask(Linkify.WEB_URLS);
//        second.setLinksClickable(true);

        panel.addView(first, lp);
        panel.addView(second, lp);
    }

    public LinearLayout show() {
        return panel;
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
