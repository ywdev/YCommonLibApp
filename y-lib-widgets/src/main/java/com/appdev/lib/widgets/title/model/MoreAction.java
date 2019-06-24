package com.appdev.lib.widgets.title.model;

public class MoreAction {
    private String name;
    private int icon = -1;
    private int color = -1;

    public MoreAction(String name, int icon,int color) {
        this.name = name;
        this.icon = icon;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
