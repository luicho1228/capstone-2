package com.plurasight.UserInterface;

import com.plurasight.Models.Displayable;
import com.plurasight.Enums.Size;
import com.plurasight.Models.Item;
import com.plurasight.Models.Drink;

import java.util.Scanner;

/**
 * This class handles the drink selection screen.
 * It lets the user choose a drink size and creates a Drink item.
 */
public class DrinkUIComponent extends UIComponent implements Displayable {

    private Item item;

    /**
     * Creates a DrinkUIComponent.
     *
     * @param scanner The Scanner used to read user input.
     */
    public DrinkUIComponent(Scanner scanner) {
        super(scanner);
    }

    /**
     * Starts the drink creation process.
     * It shows the drink screen and creates a Drink item.
     *
     * @return A Drink item with the selected size.
     */
    private Item addDrink() {
        System.out.println("ADD DRINK");
        return new Drink(selectSize());
    }

    /**
     * Lets the user select a drink size.
     *
     * @return The Size selected by the user.
     */
    private Size selectSize() {
        return selectEnumOption(Size.values(), "Select Size:");
    }

    /**
     * Displays the drink selection screen.
     * After the user selects a size, the created drink is saved.
     */
    @Override
    public void displayComponent() {
        item = addDrink();
    }

    /**
     * Gets the drink item created by this component.
     *
     * @return The selected drink item.
     */
    public Item getItem() {
        return this.item;
    }
}