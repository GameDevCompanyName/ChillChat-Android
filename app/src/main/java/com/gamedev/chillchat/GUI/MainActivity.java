package com.gamedev.chillchat.GUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.gamedev.chillchat.Client.ClientMessage;
import com.gamedev.chillchat.Client.ConsoleClient;
import com.gamedev.chillchat.R;

import static com.gamedev.chillchat.Manager.*;


public class MainActivity extends AppCompatActivity {

    private ConsoleClient client;

    private Button signIn; //TODO
    private EditText login, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activities.put("MainActivity", this);

        client = new ConsoleClient(IP, PORT);
        client.setConnection();

        signIn = findViewById(R.id.sign_in_button);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (client.setConnection()) {
                    client.start();
                    client.sendMessage(ClientMessage.versionSend(VERSION),
                            ClientMessage.loginAttemptSend(login.getText().toString(), pass.getText().toString()));
                } else {
                    //client.setConnection();
                    //TODO
                }
            }
        });
        login = findViewById(R.id.login_edit);
        pass = findViewById(R.id.pass_edit);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_settings) {
            //Intent intent = new Intent(this, MenuActivity.class);
           // startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToChat() {
        Log.d("MYERROR", "CREATE CHAT START");
        Intent intent = new Intent(this, ChatActivity.class);
        Log.d("MYERROR", "CREATE CHAT END");
        this.startActivity(intent);
        Log.d("MYERROR", "CREATE CHAT END END");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            client.destroy();
        } catch (Exception e) {
            Log.d("MYERROR", "LEHA DEBIL");
        }

    }

    public ConsoleClient getClient() {
        return client;
    }
}
