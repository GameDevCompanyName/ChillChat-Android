package com.gamedev.chillchat.GUI;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.gamedev.chillchat.Client.JsonHandler;
import com.gamedev.chillchat.GUI.objects.Massage;
import com.gamedev.chillchat.R;

import static com.gamedev.chillchat.Manager.activities;
import static com.gamedev.chillchat.Manager.client;

public class ChatActivity extends AppCompatActivity {

    private LinearLayout llmain;
    private Button send;
    private EditText input;

    private ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        scrollView = (ScrollView) findViewById(R.id.scrollView);

        llmain = (LinearLayout) findViewById(R.id.layout);
        send = (Button) findViewById(R.id.button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(input.getText().toString().equals("") && input.getText().toString().length() <= 255)) {
                    String s = input.getText().toString();
                    client.sendMessage(JsonHandler.getString(s));
                }
                input.setText("");
            }
        });
        input = (EditText) findViewById(R.id.input_text);
        activities.put("ChatActivity", this);
    }

    public void showMassage(String name, String text, int color) {
        Massage massage = new Massage(this, name, text, color);
        massage.create();
        llmain.addView(massage.show());
        scrollView.fullScroll(View.FOCUS_DOWN);
    }

    @Override
    public void onBackPressed() {
        Log.d("MYERROR", "PRESS BACK");
        client.destroy();
        System.exit(1);
        Log.d("MYERROR", "PRESS BACK END");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(1);
    }
}
