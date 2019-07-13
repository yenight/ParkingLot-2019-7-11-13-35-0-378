package com.thoughtworks.tdd;

public class Ticket {
    private boolean isUsed = false;

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
