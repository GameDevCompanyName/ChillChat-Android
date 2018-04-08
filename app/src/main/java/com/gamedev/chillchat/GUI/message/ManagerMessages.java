package com.gamedev.chillchat.GUI.message;

import android.widget.LinearLayout;

public class ManagerMessages {

    private LinearLayout lastMessage;
    private String lastName;
    private int serverColor = 5;

    public ManagerMessages(){
        this.lastName = "";
        this.lastMessage = null;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLastMessage(LinearLayout lastMessage) {
        this.lastMessage = lastMessage;
    }

    public LinearLayout getLastMessage() {
        return lastMessage;
    }

    public int getServerColor() {
        return serverColor;
    }
}
