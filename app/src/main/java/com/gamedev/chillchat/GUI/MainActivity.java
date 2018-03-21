package com.gamedev.chillchat.GUI;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.gamedev.chillchat.Client.JsonHandler;
import com.gamedev.chillchat.R;


import java.net.URL;
import java.net.URLConnection;

import static com.gamedev.chillchat.Manager.activities;
import static com.gamedev.chillchat.Manager.client;
import static com.gamedev.chillchat.Manager.firstClick;


public class MainActivity extends AppCompatActivity {

    private Button signIn;
    private EditText login, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signIn = (Button) findViewById(R.id.sign_in_button);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstClick) {
                    client.start();
                    firstClick = false;
                }
                client.tryLogin(login.getText().toString(), pass.getText().toString());
            }
        });
        login = (EditText) findViewById(R.id.login_edit);
        pass = (EditText) findViewById(R.id.pass_edit);
        activities.put("MainActivity", this);
    }

    public void goToChat() {
        Intent intent = new Intent(this, ChatActivity.class);
        this.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //client.destroy();
        System.exit(1);
    }

}
