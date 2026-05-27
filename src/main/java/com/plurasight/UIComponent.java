package com.plurasight;

import java.util.Scanner;

public class UIComponent{


    protected Scanner scanner;
    protected int userInput;
    private int padding;
    private String stringColor;

    public UIComponent(Scanner scanner, int userInput){
        this.scanner = scanner;
        this.userInput = userInput;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public String getStringColor() {
        return stringColor;
    }

    public void setStringColor(String stringColor) {
        this.stringColor = stringColor;
    }
    public static String formatTaps(String string){
        int receiptDetailPadding = 50;
        int stringLength = string.length();
        int tapLength = receiptDetailPadding - stringLength;
        StringBuilder tabs = new StringBuilder();
        for (int i = 0; i <= tapLength;i++){
            tabs.append(" ");
        }
        return tabs.toString();
    }
}