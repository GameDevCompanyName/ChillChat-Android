package com.gamedev.chillchat.GUI.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.gamedev.chillchat.GUI.ActivityMain;
import com.gamedev.chillchat.R;
import com.gamedev.chillchat.client.utils.ClientMessage;

import static com.gamedev.chillchat.Manager.LOG;
import static com.gamedev.chillchat.Manager.client;

public class SearchRoom extends Fragment {

    private Button main, besedka, afk;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOG, "CREATE SEARCH ROOM");
        View view = inflater.inflate(R.layout.search_room_fragment,
                container, false);

        main = (Button) view.findViewById(R.id.button_main_room);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.sendMessage(ClientMessage.roomChangeRequestSend("0"));
            }
        });
        besedka = view.findViewById(R.id.button_desedka_room);
        besedka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.sendMessage(ClientMessage.roomChangeRequestSend("1"));
            }
        });
        afk = view.findViewById(R.id.button_afk_room);
        afk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.sendMessage(ClientMessage.roomChangeRequestSend("2"));
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        Log.d(LOG, "DESTROY SEARCH ROOM");
        super.onDestroyView();
    }
}
