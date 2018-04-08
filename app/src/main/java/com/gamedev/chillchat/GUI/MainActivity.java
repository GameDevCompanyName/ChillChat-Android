package com.gamedev.chillchat.GUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.gamedev.chillchat.client.utils.ClientMessage;
import com.gamedev.chillchat.R;

import static android.widget.Toast.LENGTH_LONG;
import static com.gamedev.chillchat.Manager.*;


public class MainActivity extends AppCompatActivity {

    private Button signIn;
    private EditText login, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activities.put("MainActivity", this);

        signIn = findViewById(R.id.sign_in_button);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
        login = findViewById(R.id.login_edit);
        pass = findViewById(R.id.pass_edit);
    }

    @Override
    protected void onStart() {
        super.onStart();
        client.setFirstClick(true);
        if (client.isConnection()) {
//            try {
//                client.getSocket().close();
//                Log.d("MYERROR", "CLOSED SOCKET");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            client.setSocket(null);
        }
        client.setConnection(IP, String.valueOf(PORT));
    }

    public void goToChat() {
        Intent intent = new Intent(this, ChatActivity.class);
        this.startActivity(intent);
    }

    public void wrongPass() {
        Toast.makeText(MainActivity.this,
                "Не верный пароль",
                LENGTH_LONG).show();
    }

    public void userAlreadyOnline() {
        Toast.makeText(MainActivity.this,
                "Пользователь уже в сети",
                LENGTH_LONG).show();
    }

    private void check(){
        if (client.isConnection()) {
            if (client.isFirstClick()) {
                client.start();
                client.setFirstClick(false);
            }
            if (login.getText().toString().equals("") ||
                    pass.getText().toString().equals("") ||
                    login.getText().toString().contains(" ") ||
                    login.getText().toString().contains("/") ||
                    login.getText().toString().contains("\\") ||
                    pass.getText().toString().contains("/") ||
                    pass.getText().toString().contains("\\")) {
                Toast.makeText(MainActivity.this,
                        "Логин или пароль не должны быть пустыми или содержать недопустимые символы",
                        LENGTH_LONG).show();
            } else
                myName = login.getText().toString();
                client.sendMessage(ClientMessage.versionSend(VERSION), ClientMessage.loginAttemptSend(login.getText().toString(), pass.getText().toString()));

        } else {
            Toast.makeText(MainActivity.this,
                    "Не удалось установить соединение",
                    LENGTH_LONG).show();
        }
    }

    public void sendVersion(){
        client.sendMessage(ClientMessage.versionSend(VERSION));
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
}
