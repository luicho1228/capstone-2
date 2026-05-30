package com.plurasight.Models;

/**
 * This abstract class represents an item in an order.
 * Other classes * This abstract class represents an item in an order.
 * Other classes like Sandwich, Drink, and Chips inherit from this class.
 * Each item must have a price, details for the receipt, and a short header.
 */
public abstract class Item {

    /**
     * Gets the price of the item.
     *
     * @return The price of the item.
     */
    public abstract double getValue();

    /**
     * Gets the full details of the item.
     * This is used when printing the order receipt.
     *
     * @return A formatted String with the item details.
     */
    public abstract String getDetails();

    /**
     * Gets a short summary of the item.
     * This is used when showing items in menus or order lists.
     *
     * @return A short String that describes the item.
     */
    public abstract String getItemHeader();
}
