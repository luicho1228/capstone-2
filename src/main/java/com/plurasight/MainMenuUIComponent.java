package com.plurasight;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenuUIComponent extends UIComponent implements Displayable {

    private  int input = 0;

    public MainMenuUIComponent(Scanner scanner) {
        super(scanner);
    }

    public int getAction(){
        return input;
    }

    @Override
    public void displayComponent() {String[] optionsArray = {"New Order", "Exit"};
       input = getUserInputFromMenu(optionsArray,false);

    }
}
