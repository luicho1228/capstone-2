package com.plurasight;

import java.util.Scanner;

/**
 * This class handles the order menu screen.
 * It lets the user choose what they want to add or do with the current order.
 */
public class OrderUIComponent extends UIComponent implements Displayable {

    private int input = 0;

    /**
     * Creates an OrderUIComponent.
     *
     * @param scanner The Scanner used to read user input.
     */
    public OrderUIComponent(Scanner scanner) {
        super(scanner);
    }

    /**
     * Gets the option selected by the user.
     *
     * @return The selected order menu option.
     */
    public int getInput() {
        return input;
    }

    /**
     * Displays the order menu and saves the user's choice.
     * The user can add items, add a customer name, checkout, or cancel the order.
     */
    @Override
    public void displayComponent() {
        String[] optionArray = {"Add Sandwich", "Add Signature Sandwich", "Add Drink", "Add Chips", "Add Customer Name to the order", "Checkout", "Cancel Order"};

        input = getUserInputFromMenu(optionArray, false);
    }
}
