package com.greenacademy.ga_finalprojecthm.model;

/**
 * Created by thepa on 10/30/2017.
 */

public class DataIcon {
    private int icon;
    private String title;

    public DataIcon(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
