package com.gamedev.chillchat.GUI.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.gamedev.chillchat.GUI.message.ManagerMessages;
import com.gamedev.chillchat.GUI.message.ServerMessage;
import com.gamedev.chillchat.GUI.message.UserMessage;
import com.gamedev.chillchat.R;
import com.gamedev.chillchat.client.utils.ClientMessage;

import static com.gamedev.chillchat.Manager.LOG;
import static com.gamedev.chillchat.Manager.client;
import static com.gamedev.chillchat.Manager.myColor;

public class Chat extends Fragment{

    private ManagerMessages managerMessages;

    private ScrollView scrollView;
    private LinearLayout llmain;
    private ImageButton send;
    private EditText input;

    private String userColor = myColor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOG, "CREATE CHAT");

        View view = inflater.inflate(R.layout.chat_fragment,
                container, false);

        managerMessages = new ManagerMessages();

        scrollView = view.findViewById(R.id.scrollView);

        llmain = view.findViewById(R.id.space_chat);
        llmain.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });

        send = view.findViewById(R.id.button);
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
        input = view.findViewById(R.id.input_text);
        input.setTextColor(Color.parseColor(userColor));
        input.setHintTextColor(Color.parseColor(userColor));

        return view;
    }

    public void showUserMessage(String name, String text, String color) {
//        ContextThemeWrapper themeWrapper = new ContextThemeWrapper(this, R.style.UserMessageStyle);
        ContextThemeWrapper themeWrapper = new ContextThemeWrapper(getActivity(), R.style.MessageStyle);
        if (!managerMessages.getLastName().equals(name)) {
            final UserMessage userMessage = new UserMessage(themeWrapper, name, text, Integer.parseInt(color));
            final Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_message);
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
    }

    public void showServerMessage(String text) {
//        ContextThemeWrapper themeWrapper = new ContextThemeWrapper(this, R.style.ServerMessageStyle);
        ContextThemeWrapper themeWrapper = new ContextThemeWrapper(getActivity(), R.style.MessageStyle);
        if (!managerMessages.getLastName().equals("SERVER")) {
            ServerMessage serverMessage = new ServerMessage(themeWrapper, text, managerMessages.getServerColor());
            managerMessages.setLastName("SERVER");
            managerMessages.setLastMessage(serverMessage);
            llmain.addView(serverMessage);
        } else
            ((ServerMessage) managerMessages.getLastMessage()).addText(text);
    }

    public void changeColorMessage(String color) {
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
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(LOG, "DESTROY FRAGMENT");
    }
}
