package com.gamedev.chillchat.GUI.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gamedev.chillchat.R;

import static com.gamedev.chillchat.Manager.LOG;

public class Wall extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOG, "CREATE WALL");
        View view = inflater.inflate(R.layout.wall_fragment,
                container, false);

        return view;
    }

    @Override
    public void onDestroyView() {
        Log.d(LOG, "DESTROY WALL");
        super.onDestroyView();
    }
}
