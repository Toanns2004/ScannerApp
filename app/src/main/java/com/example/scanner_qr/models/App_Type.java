package com.example.scanner_qr.models;

public class App_Type {
    private int background;
    private int icon;
    private String text;

    private boolean check;

    public App_Type(int background, int icon, String text) {
        this.background = background;
        this.icon = icon;
        this.text = text;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
