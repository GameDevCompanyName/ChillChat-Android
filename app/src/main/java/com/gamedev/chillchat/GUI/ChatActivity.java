package com.gamedev.chillchat.GUI;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.gamedev.chillchat.GUI.message.ManagerMessages;
import com.gamedev.chillchat.GUI.message.ServerMessage;
import com.gamedev.chillchat.GUI.message.UserMessage;
import com.gamedev.chillchat.client.utils.ClientMessage;
import com.gamedev.chillchat.R;

import static com.gamedev.chillchat.Manager.*;

public class ChatActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ManagerMessages managerMessages;

    private LinearLayout llmain;
    private Button send;
    private EditText input;
    private ScrollView scrollView;

    private String userColor = myColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        managerMessages = new ManagerMessages();

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
        input.setTextColor(Color.parseColor(userColor));
        input.setHintTextColor(Color.parseColor(userColor));
        activities.put("ChatActivity", this);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void showUserMessage(String name, String text, String color) {
//        ContextThemeWrapper themeWrapper = new ContextThemeWrapper(this, R.style.UserMessageStyle);
        ContextThemeWrapper themeWrapper = new ContextThemeWrapper(this, R.style.MessageStyle);
        if (!managerMessages.getLastName().equals(name)) {
            final UserMessage userMessage = new UserMessage(themeWrapper, name, text, Integer.parseInt(color));
            final Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_message);
            userMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userMessage.startAnimation(animation);
                }
            });
            managerMessages.setLastName(name);
            managerMessages.setLastMessage(userMessage);
            llmain.addView(userMessage);
        } else
            ((UserMessage) managerMessages.getLastMessage()).addText(text);
        scrollView.fullScroll(View.FOCUS_DOWN);

    }

    public void showServerMessage(String text) {
//        ContextThemeWrapper themeWrapper = new ContextThemeWrapper(this, R.style.ServerMessageStyle);
        ContextThemeWrapper themeWrapper = new ContextThemeWrapper(this, R.style.MessageStyle);
        if (!managerMessages.getLastName().equals("SERVER")) {
            ServerMessage serverMessage = new ServerMessage(themeWrapper, text, managerMessages.getServerColor());
            managerMessages.setLastName("SERVER");
            managerMessages.setLastMessage(serverMessage);
            llmain.addView(serverMessage);
        } else
            ((ServerMessage) managerMessages.getLastMessage()).addText(text);
        scrollView.fullScroll(View.FOCUS_DOWN);

    }

    public void changeColor(String color) {
        userColor = color;
        for (int i = 0; i < llmain.getChildCount(); i++) {
            try {
                UserMessage linearLayout = (UserMessage) llmain.getChildAt(i);
                linearLayout.changeColor(userColor);
            } catch (Exception e) {
                Log.d(LOG, "NOT USER MESSAGE");
            }
        }
        input.setTextColor(Color.parseColor(userColor));
        input.setHintTextColor(Color.parseColor(userColor));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        client.destroy();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);


        int id = item.getItemId();

        switch (id) {
            case R.id.nav_main:
                if (drawer.isDrawerOpen(GravityCompat.START))
                    drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_settings:
                Toast.makeText(this, "Clicked settings", Toast.LENGTH_LONG).show();
                break;
        }

        return true;
    }
}
