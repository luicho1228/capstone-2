package com.plurasight.UserInterface;

import com.plurasight.Models.Displayable;
import com.plurasight.Enums.*;
import com.plurasight.Models.SignatureSandwich;

import java.util.*;

/**
 * This class handles the signature sandwich selection screen.
 * It loads the available signature sandwiches and lets the user choose one.
 */
public class SignatureSandwichUIComponent extends UIComponent implements Displayable {

    private final List<SignatureSandwich> signatureSandwiches;
    private SignatureSandwich signatureSandwich;

    /**
     * Creates a SignatureSandwichUIComponent.
     * It also loads the signature sandwiches that the user can choose from.
     *
     * @param scanner The Scanner used to read user input.
     */
    public SignatureSandwichUIComponent(Scanner scanner) {
        super(scanner);
        signatureSandwiches = new ArrayList<>();
        loadSignatureSandwiches();
    }

    /**
     * Creates the default signature sandwiches and adds them to the list.
     * These sandwiches are shown to the user when they open the signature sandwich menu.
     */
    private void loadSignatureSandwiches() {
        HashSet<Topping> toppings = new HashSet<>(List.of(Topping.LETTUCE, Topping.TOMATOES));
        HashSet<Sauce> sauces = new HashSet<>(List.of(Sauce.RANCH));
        SignatureSandwich blt = new SignatureSandwich(Bread.WHITE, Size.SMALL, true, Meat.BACON, false, Cheese.CHEDDAR, false, toppings, sauces);
        blt.setSandwichName("BLT");
        signatureSandwiches.add(blt);

        toppings = new HashSet<>(List.of(Topping.PEPPERS));
        sauces = new HashSet<>(List.of(Sauce.MAYO));
        SignatureSandwich phillyCheeseSteak = new SignatureSandwich(Bread.WHITE, Size.SMALL, true, Meat.STEAK, false, Cheese.AMERICAN, false, toppings, sauces);
        phillyCheeseSteak.setSandwichName("Philly Cheese Steak");
        signatureSandwiches.add(phillyCheeseSteak);
    }

    /**
     * Displays all the signature sandwiches to the user.
     * Each sandwich is shown with a number so the user can select one.
     */
    private void displaySignatureSandwiches() {
        displayBorders();
        System.out.println("  ____  _                   _                    ____                  _          _      _     \n" +
                " / ___|(_) __ _ _ __   __ _| |_ _   _ _ __ ___  / ___|  __ _ _ __   __| |_      _(_) ___| |__  \n" +
                " \\___ \\| |/ _` | '_ \\ / _` | __| | | | '__/ _ \\ \\___ \\ / _` | '_ \\ / _` \\ \\ /\\ / / |/ __| '_ \\ \n" +
                "  ___) | | (_| | | | | (_| | |_| |_| | | |  __/  ___) | (_| | | | | (_| |\\ V  V /| | (__| | | |\n" +
                " |____/|_|\\__, |_| |_|\\__,_|\\__|\\__,_|_|  \\___| |____/ \\__,_|_| |_|\\__,_| \\_/\\_/ |_|\\___|_| |_|\n" +
                "          |___/                 |  \\/  | ___ _ __  _   _                                       \n" +
                "                                | |\\/| |/ _ \\ '_ \\| | | |                                      \n" +
                "                                | |  | |  __/ | | | |_| |                                      \n" +
                "                                |_|  |_|\\___|_| |_|\\__,_|                                      \n" +
                "                                                                                               ");
        System.out.println("Select a signature sandwich:");
        int count = 1;
        for (SignatureSandwich signatureSandwich : signatureSandwiches) {
            System.out.println(count + ". " + signatureSandwich.getDetails());
            count++;
        }
        displayBorders();
    }

    /**
     * Gets the signature sandwich selected by the user.
     *
     * @return The selected signature sandwich.
     */
    public SignatureSandwich getSignatureSandwich() {
        return this.signatureSandwich;
    }

    /**
     * Displays the signature sandwich menu and saves the user's selection.
     * If the user enters an invalid value, it asks again.
     */
    @Override
    public void displayComponent() {
        do {
            displaySignatureSandwiches();
            try {
                int userInput = getUserInput(scanner);
                if (userInput > 0 && userInput <= signatureSandwiches.size()) {
                    signatureSandwich = signatureSandwiches.get(userInput - 1);
                    break;
                } else {
                    System.err.println("Invalid Selection. Please choose one of the options provided.");
                }
            } catch (InputMismatchException ime) {
                System.err.println("Error: Select a numeric value from the options provided");
                scanner.nextLine();
            }
        } while (true);
    }
}

