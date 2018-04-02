package com.gamedev.chillchat.GUI.objects;


import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.util.Linkify;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gamedev.chillchat.GUI.ChatActivity;
import com.gamedev.chillchat.R;

import static com.gamedev.chillchat.Manager.activities;
import static com.gamedev.chillchat.Manager.chooseColor;
import static com.gamedev.chillchat.Manager.lastName;

public class Message extends LinearLayout {

    private TextView nameView, textView;

    public Message(Context context, String name, String text, int color) {
        super(context);
        ContextThemeWrapper nameStyle = new ContextThemeWrapper(context, R.style.NameStyle);
        ContextThemeWrapper textStyle = new ContextThemeWrapper(context, R.style.TextStyle);

        int intColor = Color.parseColor(chooseColor(color));
        if (!lastName.equals(name)) {
            nameView = new TextView(nameStyle, null, 0);
            nameView.setText(name);
            nameView.setTextColor(intColor);
            addView(nameView);
            lastName = name;
        }
        int Alpha = (intColor >> 24) & 0xff; // or color »> 24
        int Red = (intColor >> 16) & 0xff;
        int Green = (intColor >> 8) & 0xff;
        int Blue = (intColor ) & 0xff;

        textView = new TextView(textStyle, null, 0);
        textView.setText(text);
        textView.setTextColor(Color.parseColor("#fafafa"));
        Linkify.addLinks(textView, Linkify.ALL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackground(Drawable.createFromPath(String.valueOf(R.drawable.back_message)));
        }
        this.setBackgroundColor(Color.argb(30, Red, Green, Blue));
        addView(textView);
    }

    public Message(ChatActivity chatActivity, String text){
        super(chatActivity);

        LinearLayout.LayoutParams lpText = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        textView = new TextView(chatActivity);
        textView.setText(text);
        textView.setTextSize(23);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.parseColor("#66bb6a"));
        Linkify.addLinks(textView, Linkify.ALL);
        addView(textView, lpText);
        lastName = "SERVER";
    }

}
