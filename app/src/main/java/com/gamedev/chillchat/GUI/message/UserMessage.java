package com.gamedev.chillchat.GUI.message;

import android.content.Context;
import android.graphics.Color;
import android.text.util.Linkify;
import android.view.ContextThemeWrapper;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gamedev.chillchat.R;

import static com.gamedev.chillchat.Manager.chooseColor;

public class UserMessage extends LinearLayout{

    private TextView nameView;
    private int intColor, alpha, red, green, blue;

    private ContextThemeWrapper nameStyle, textStyle;

    public UserMessage(Context context, String name, String text, int color) {
        super(context);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setOrientation(VERTICAL);

        nameStyle= new ContextThemeWrapper(context, R.style.NameStyle);
        textStyle = new ContextThemeWrapper(context, R.style.TextStyle);

        toARGB(chooseColor(color));

        nameView = new TextView(nameStyle, null, 0);
        nameView.setText(name);
        nameView.setTextColor(intColor);

        TextView textView = new TextView(textStyle, null, 0);
        textView.setText(text);
        textView.setTextColor(Color.WHITE);
        Linkify.addLinks(textView, Linkify.ALL);

        setBackgroundColor(Color.argb(30, red, green, blue));

        addView(nameView);
        addView(textView);
    }

    public void addText(String text){
        TextView textView = new TextView(textStyle, null, 0);
        textView.setText(text);
        textView.setTextColor(Color.WHITE);
        Linkify.addLinks(textView, Linkify.ALL);
        addView(textView);
    }

    public void changeColor(String color){
        toARGB(color);
        nameView.setTextColor(intColor);
        setBackgroundColor(Color.argb(30, red, green, blue));
    }

    private void toARGB(String color){
        intColor = Color.parseColor(color);
        alpha = (intColor >> 24) & 0xff; // or color »> 24
        red = (intColor >> 16) & 0xff;
        green = (intColor >> 8) & 0xff;
        blue = (intColor ) & 0xff;
    }
}
