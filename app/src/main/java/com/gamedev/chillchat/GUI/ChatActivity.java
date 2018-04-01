package com.gamedev.chillchat.GUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.gamedev.chillchat.Client.ClientMessage;
import com.gamedev.chillchat.GUI.objects.Massage;
import com.gamedev.chillchat.R;

import static com.gamedev.chillchat.Manager.activities;

public class ChatActivity extends AppCompatActivity {

    private MainActivity mainActivity = (MainActivity) activities.get("MainActivity");

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
                    mainActivity.getClient().sendMessage(ClientMessage.messageSend(s));
                }
                input.setText("");
            }
        });
        input = findViewById(R.id.input_text);
        activities.put("ChatActivity", this);
    }

    public void showMassage(String name, String text, String color) {
        //TODO
        llmain.addView(new Massage(this, name, text, Integer.parseInt(color)));
        scrollView.fullScroll(View.FOCUS_DOWN);
    }

    public void showMessage(String text){
        llmain.addView(new Massage(this, text));
        scrollView.fullScroll(View.FOCUS_DOWN);
    }

    @Override
    public void onBackPressed() {
        Log.d("MYERROR", "PRESS BACK");
        mainActivity.getClient().destroy();
        System.exit(1);
        Log.d("MYERROR", "PRESS BACK END");
    }
}
