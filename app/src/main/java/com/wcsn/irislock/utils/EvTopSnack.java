package com.wcsn.irislock.utils;

/**
 * Created by suiyue on 2016/5/29 0029.
 */
public class EvTopSnack {
    private String text;
    private int textColor;
    private int bgColor;

    public static EvTopSnack successSnack(String text){
        return new EvTopSnack(text,0xff000000,0xFF9400FF);
    }
    public static EvTopSnack failSnack(String text){
        return new EvTopSnack(text,0xffffffff,0xffff0000);
    }

    public EvTopSnack(String text, int textColor, int bgColor) {
        this.text = text;
        this.textColor = textColor;
        this.bgColor = bgColor;
    }

    public String getText() {
        return text;
    }
}
