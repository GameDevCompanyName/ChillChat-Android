package com.gamedev.chillchat.GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.gamedev.chillchat.Manager;
import com.gamedev.chillchat.R;

public class MenuActivity extends AppCompatActivity {

    Button local, global;
    TextView current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        current = findViewById(R.id.settings_current);
        current.setText("IP: " + Manager.IP + "\nPORT: " + Manager.PORT);

        local = findViewById(R.id.button_local);
        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Manager.IP = "192.168.10.142";
                Manager.PORT = 16261;
                current.setText("IP: " + Manager.IP + "\nPORT: " + Manager.PORT);
            }
        });
        global = findViewById(R.id.button_global);
        global.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Manager.IP = "138.68.74.16";
                Manager.PORT = 1488;
                current.setText("IP: " + Manager.IP + "\nPORT: " + Manager.PORT);
            }
        });
    }

}
