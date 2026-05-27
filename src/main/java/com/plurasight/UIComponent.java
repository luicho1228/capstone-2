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
}
