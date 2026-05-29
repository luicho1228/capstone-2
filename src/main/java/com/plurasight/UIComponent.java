package com.plurasight;

import java.util.Scanner;

public class UIComponent{


    protected Scanner scanner;
    private int padding;
    private String stringColor;

    public UIComponent(Scanner scanner){
        this.scanner = scanner;
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

    public static String formatPadding(int stringLength){
        int receiptDetailPadding = 50;
        int tapLength = Math.abs(receiptDetailPadding - stringLength);
        StringBuilder tabs = new StringBuilder();
        for (int i = 0; i <= tapLength;i++){
            tabs.append(" ");
        }
        return tabs.toString();
    }
    public static int getUserInput(Scanner scanner){
        int userInput = scanner.nextInt();
        scanner.nextLine();
        return userInput;
    }
}