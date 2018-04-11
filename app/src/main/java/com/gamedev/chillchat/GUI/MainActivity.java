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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        this.overridePendingTransition(R.anim.activity_main_in, R.anim.activity_main_out);
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

    private void check() {
        if (client.isConnection()) {
            if (client.isFirstClick()) {
                client.start();
                client.setFirstClick(false);
            }
            Pattern p = Pattern.compile("[^А-Яа-яA-Za-z0-9_-]");
            Matcher mL = p.matcher(login.getText().toString());
            Matcher mP = p.matcher(pass.getText().toString());
            if (login.getText().toString().equals("") || pass.getText().toString().equals("") || mL.find() || mP.find()) {
                Toast.makeText(MainActivity.this,
                        "Логин или пароль не должны быть пустыми или содержать недопустимые символы",
                        LENGTH_LONG).show();
            } else {
                myName = login.getText().toString();
                client.sendMessage(ClientMessage.versionSend(VERSION), ClientMessage.loginAttemptSend(login.getText().toString(), pass.getText().toString()));
            }

        } else {
            Toast.makeText(MainActivity.this,
                    "Не удалось установить соединение",
                    LENGTH_LONG).show();
        }
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
