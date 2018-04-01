package com.gamedev.chillchat.GUI;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.gamedev.chillchat.Client.ClientMessage;
import com.gamedev.chillchat.GUI.objects.Message;
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

        scrollView = findViewById(R.id.scrollView);

        llmain = findViewById(R.id.layout);
        send = findViewById(R.id.button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(input.getText().toString().equals("") && input.getText().toString().length() <= 255)) {
                    String s = input.getText().toString();
                    client.sendMessage(ClientMessage.messageSend(s));
                }
                input.setText("");
            }
        });
        input = findViewById(R.id.input_text);
        activities.put("ChatActivity", this);
    }

    public void showMassage(String name, String text, String color) {
        //TODO
        final Message message = new Message(this, name, text, Integer.parseInt(color));
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_message);
        message.setOnTouchListener(new View.OnTouchListener() {

            private boolean revers = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                message.startAnimation(animation);
                if (revers){
                    message.setBackgroundColor(Color.argb(50, 214, 76, 78));
                    revers = false;
                }
                else{
                    message.setBackgroundColor(Color.argb(50, 55, 168, 237));
                    revers = true;
                }
                return false;
            }
        });
//        message.setOnClickListener(new View.OnClickListener() {
//
//            private boolean revers = false;
//
//            @Override
//            public void onClick(View v) {
//                message.startAnimation(animation);
//                if (revers){
//                    message.setBackgroundColor(Color.argb(50, 214, 76, 78));
//                    revers = false;
//                }
//                else{
//                    message.setBackgroundColor(Color.argb(50, 55, 168, 237));
//                    revers = true;
//                }
//            }
//        });
        llmain.addView(message);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }

    public void showMessage(String text) {
        llmain.addView(new Message(this, text));
        scrollView.fullScroll(View.FOCUS_DOWN);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("MYERROR", "PRESS BACK");
        client.destroy();
//        System.exit(1);
        Log.d("MYERROR", "PRESS BACK END");
    }
}
