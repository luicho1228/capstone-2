package com.plurasight;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrderUIComponent extends UIComponent implements Displayable{

    private int input = 0;
    public OrderUIComponent(Scanner scanner) {
        super(scanner);
    }

    public int getInput(){
        return input;
    }

    @Override
    public void displayComponent() {
        String[] optionArray ={"Add Sandwich", "Add Signature Sandwich",
                "Add Drink", "Add Chips", "Add Customer Name to the order",
                "Checkout", "Cancel Order"};

        input = getUserInputFromMenu(optionArray,false);
    }
}
