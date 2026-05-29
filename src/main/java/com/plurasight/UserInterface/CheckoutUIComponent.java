package com.plurasight.UserInterface;

import com.plurasight.Enums.*;
import com.plurasight.Models.*;

import java.util.List;
import java.util.Scanner;

/**
 * This class handles the checkout screen for the order.
 * It lets the user see the order, edit items, remove items,
 * finish checkout, or go back to the order menu.
 */
public class CheckoutUIComponent extends UIComponent implements Displayable {

    private final Order order;
    private boolean orderCheckedOut = false;

    /**
     * Creates a CheckoutUIComponent.
     *
     * @param scanner The Scanner used to read user input.
     * @param order The order that is being checked out.
     */
    public CheckoutUIComponent(Scanner scanner, Order order) {
        super(scanner);
        this.order = order;
    }

    /**
     * Displays the checkout menu and lets the user choose what to do.
     * The user can finish checkout, edit the order, remove an item, or go back.
     */
    @Override
    public void displayComponent() {
        displayBorders();
        System.out.println("   ____ _               _       ___        _   \n" +
                "  / ___| |__   ___  ___| | __  / _ \\ _   _| |_ \n" +
                " | |   | '_ \\ / _ \\/ __| |/ / | | | | | | | __|\n" +
                " | |___| | | |  __/ (__|   <  | |_| | |_| | |_ \n" +
                "  \\____|_| |_|\\___|\\___|_|\\_\\  \\___/ \\__,_|\\__|\n" +
                "                                               ");

        boolean needsInput = true;

        do {
            System.out.println(order.getOrderDetails());

            String[] menuOptions = {
                    "Confirm Order",
                    "Edit Order",
                    "Remove Item",
                    "Go Back"
            };

            switch (getUserInputFromMenu(menuOptions)) {
                case 1:
                    processCheckout();
                    needsInput = false;
                    break;
                case 2:
                    if (!order.isEmpty()) {
                        Item itemToEdit = selectItemFromOrder();
                        editItem(itemToEdit);
                    } else {
                        System.err.println("There are no items to edit.");
                    }
                    break;
                case 3:
                    if (!order.isEmpty()) {
                        Item itemToRemove = selectItemFromOrder();
                        removeItemFromOrder(itemToRemove);

                        if (order.isEmpty()) {
                            System.err.println("All items were removed. The order must have at least one item.");
                            needsInput = false;
                        }
                    } else {
                        System.err.println("There are no items to remove.");
                    }
                    break;
                case 4:
                    needsInput = false;
                    break;
            }

        } while (needsInput);
    }

    /**
     * Checks if the order was checked out.
     *
     * @return true if the order was checked out, false if it was not.
     */
    public boolean isOrderCheckedOut() {
        return orderCheckedOut;
    }

    /**
     * Shows all the items in the order and lets the user pick one.
     *
     * @return The item selected by the user.
     */
    private Item selectItemFromOrder() {
        List<Item> items = order.getItems();
        String[] itemOptions = new String[items.size()];

        for (int i = 0; i < items.size(); i++) {
            itemOptions[i] = items.get(i).getItemHeader();
        }

        int selectedItem = getUserInputFromMenu(itemOptions);
        return items.get(selectedItem - 1);
    }

    /**
     * Saves the order receipt and marks the order as checked out.
     */
    private void processCheckout() {
        OrderFileManager.saveOrder(order);
        System.out.println("Receipt saved!");
        orderCheckedOut = true;
    }

    /**
     * Removes an item from the order.
     *
     * @param itemToRemove The item that will be removed from the order.
     */
    private void removeItemFromOrder(Item itemToRemove) {
        order.removeItem(itemToRemove);
        System.out.println("This item has been removed: " + itemToRemove.getItemHeader());
    }

    /**
     * Checks what type of item was selected and sends it to the correct edit method.
     *
     * @param itemToEdit The item that the user wants to edit.
     */
    private void editItem(Item itemToEdit) {
        if (itemToEdit instanceof Sandwich) {
            editSandwich((Sandwich) itemToEdit);
        } else if (itemToEdit instanceof Drink) {
            editDrink((Drink) itemToEdit);
        } else if (itemToEdit instanceof Chips) {
            editChips((Chips) itemToEdit);
        } else {
            System.err.println("This item type cannot be edited.");
        }
    }

    /**
     * Displays the sandwich edit menu and lets the user change sandwich options.
     *
     * @param sandwichToEdit The sandwich that the user wants to edit.
     */
    private void editSandwich(Sandwich sandwichToEdit) {
        String[] menuOptions = {
                "Edit Sandwich Size",
                "Edit Bread Choice",
                "Change Bread Toast",
                "Edit Meat Choice",
                "Edit Cheese Choice",
                "Edit Topping Selection",
                "Edit Sauce Selection",
                "Go Back"
        };

        boolean needsInput = true;

        do {
            switch (getUserInputFromMenu(menuOptions)) {
                case 1:
                    sandwichToEdit.setSize(selectEnumOption(Size.values(), "Select sandwich size:"));
                    break;
                case 2:
                    sandwichToEdit.setBread(selectEnumOption(Bread.values(), "Select bread:"));
                    break;
                case 3:
                    sandwichToEdit.setToast(getBooleanFromPrompt("Do you want the sandwich toasted?\n1.Yes\t\t\t2.No"));
                    break;
                case 4:
                    sandwichToEdit.setMeat(selectEnumOption(Meat.values(), "Select meat:"));
                    break;
                case 5:
                    sandwichToEdit.setCheese(selectEnumOption(Cheese.values(), "Select cheese:"));
                    break;
                case 6:
                    sandwichToEdit.setToppings(selectMultipleEnumFromOptions(Topping.values(), "ADD TOPPINGS"));
                    break;
                case 7:
                    sandwichToEdit.setSauces(selectMultipleEnumFromOptions(Sauce.values(), "ADD SAUCES"));
                    break;
                case 8:
                    needsInput = false;
                    break;
            }
        } while (needsInput);
    }

    /**
     * Lets the user change the size of a drink.
     *
     * @param drinkToEdit The drink that the user wants to edit.
     */
    private void editDrink(Drink drinkToEdit) {
        drinkToEdit.setSize(selectEnumOption(Size.values(), "Select drink size:"));
    }

    /**
     * Lets the user change the type of chips.
     *
     * @param chipsToEdit The chips item that the user wants to edit.
     */
    private void editChips(Chips chipsToEdit) {
        chipsToEdit.setChipsType(selectEnumOption(ChipsType.values(), "Select chips type:"));
    }
}