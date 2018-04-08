package com.gamedev.chillchat.GUI.message;

import android.content.Context;
import android.graphics.Color;
import android.text.util.Linkify;
import android.view.ContextThemeWrapper;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gamedev.chillchat.R;

import static com.gamedev.chillchat.Manager.chooseColor;

public class ServerMessage extends LinearLayout {

    private int intColor, alpha, red, green, blue;

    private ContextThemeWrapper nameStyle, textStyle;

    public ServerMessage(Context context, String text, int color) {
        super(context);
        setOrientation(VERTICAL);

        nameStyle= new ContextThemeWrapper(context, R.style.NameStyle);
        textStyle = new ContextThemeWrapper(context, R.style.TextStyle);

        intColor = Color.parseColor(chooseColor(color));
        alpha = (intColor >> 24) & 0xff; // or color »> 24
        red = (intColor >> 16) & 0xff;
        green = (intColor >> 8) & 0xff;
        blue = (intColor ) & 0xff;

        TextView nameView = new TextView(nameStyle, null, 0);
        nameView.setText("SERVER");
        nameView.setTextColor(intColor);

        TextView textView = new TextView(textStyle, null, 0);
        textView.setText(text);
        textView.setTextColor(intColor);
        Linkify.addLinks(textView, Linkify.ALL);

        setBackgroundColor(Color.argb(30, red, green, blue));

        addView(nameView);
        addView(textView);
    }

    public void addText(String text){
        TextView textView = new TextView(textStyle, null, 0);
        textView.setText(text);
        textView.setTextColor(intColor);
        Linkify.addLinks(textView, Linkify.ALL);
        addView(textView);
    }
}
