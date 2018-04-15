package com.gamedev.chillchat.GUI;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.*;
import com.gamedev.chillchat.GUI.fragments.Chat;
import com.gamedev.chillchat.GUI.fragments.SearchRoom;
import com.gamedev.chillchat.GUI.fragments.Wall;
import com.gamedev.chillchat.R;

import static com.gamedev.chillchat.Manager.*;

public class ActivityMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private Chat chat = null;
    private Wall wall = null;
    private SearchRoom searchRoom = null;
    private Fragment lastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chat = new Chat();
        wall = new Wall();
        searchRoom = new SearchRoom();
        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.layout_chat, chat)
                .commit();
        lastFragment = chat;

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        activities.put("ActivityMain", this);
    }

    public Chat getChat() {
        return chat;
    }

    public Wall getWall() {
        return wall;
    }

    public void toRoom() {
        fragmentManager.beginTransaction()
                .remove(wall)
                .remove(chat)
                .remove(lastFragment)
                .commit();
        chat = new Chat();
        fragmentManager.beginTransaction()
                .add(R.id.layout_chat, chat)
                .commit();
        lastFragment = chat;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        int id = item.getItemId();

        switch (id) {
            case R.id.nav_chat:
                if (!item.isChecked())
                    showChat();
                closeDrawer(drawer);
                break;
            case R.id.nav_settings:
                Toast.makeText(this, "Clicked settings", Toast.LENGTH_LONG).show();
                if (drawer.isDrawerOpen(GravityCompat.START))
                    drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_wall:
                Toast.makeText(this, "Clicked wall", Toast.LENGTH_LONG).show();
                closeDrawer(drawer);
//                showWall();
//                closeDrawer(drawer);
                break;
            case R.id.nav_search_room:
                if (!item.isChecked())
                    showSearchRoom();
                closeDrawer(drawer);
                break;
        }

        return true;
    }

    private void showSearchRoom() {
        Log.d(LOG, "SHOW SEARCH ROOM");
        if (!searchRoom.isAdded())
            fragmentManager.beginTransaction()
                    .hide(lastFragment)
                    .add(R.id.layout_chat, searchRoom)
                    .commit();
        else fragmentManager.beginTransaction()
                .hide(lastFragment)
                .show(searchRoom)
                .commit();
        lastFragment = searchRoom;
    }

    private void showWall() {
        Log.d(LOG, "SHOW WALL");
        if (!wall.isAdded())
            fragmentManager.beginTransaction()
                    .hide(lastFragment)
                    .add(R.id.layout_chat, wall)
                    .commit();
        else fragmentManager.beginTransaction()
                .hide(lastFragment)
                .show(wall)
                .commit();
        lastFragment = wall;
    }

    private void showChat() {
        Log.d(LOG, "SHOW CHAT");
        fragmentManager.beginTransaction()
                .hide(lastFragment)
                .show(chat)
                .commit();
        lastFragment = chat;
    }

    private void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        client.destroy();
        activities.remove("ActivityMain");
    }

}
