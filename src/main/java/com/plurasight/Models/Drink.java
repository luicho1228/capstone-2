package com.plurasight.Models;

import com.plurasight.Enums.Size;
import com.plurasight.UserInterface.UIComponent;

/**
 * This class represents a drink item in the order.
 * A drink has a size and the price depends on the selected size.
 */
public class Drink extends Item {

    private Size size;

    /**
     * Creates a Drink object with the selected size.
     *
     * @param size The size selected for the drink.
     */
    public Drink(Size size) {
        this.size = size;
    }

    /**
     * Changes the size of the drink.
     *
     * @param size The new size for the drink.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Gets the price of the drink based on its size.
     *
     * @return The price of the drink.
     */
    @Override
    public double getValue() {
        double value = 0.0;
        switch (size) {
            case SMALL -> value = 2;
            case MEDIUM -> value = 2.5;
            case LARGE -> value = 3;
        }
        return value;
    }

    /**
     * Gets the full drink details for the receipt.
     *
     * @return A formatted String with the drink name, price, and size.
     */
    @Override
    public String getDetails() {
        String drinkName = "Drink";

        return drinkName + UIComponent.formatPadding(drinkName.length() - getTaps().length()) + "$" + getValue() + getTaps() + size.toString();
    }

    /**
     * Gets a short summary of the drink item.
     * This is used when showing the item in a menu or order list.
     *
     * @return A short String with the drink size and price.
     */
    @Override
    public String getItemHeader() {
        return "Drink {Size: " + size + ", $" + getValue() + "}";
    }
}
