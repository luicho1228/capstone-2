package com.plurasight.Models;

import com.plurasight.Enums.ChipsType;

/**
 * This class represents a chips item in the order.
 * A chips item has a chips type and a fixed price.
 */
public class Chips extends Item {
    private ChipsType chipsType;

    /**
     * Creates a Chips object with the selected chips type.
     *
     * @param chipsType The type of chips selected by the user.
     */
    public Chips(ChipsType chipsType) {
        this.chipsType = chipsType;
    }

    /**
     * Gets the price of the chips.
     *
     * @return The price of the chips.
     */
    @Override
    public double getValue() {
        return 1.5;
    }

    /**
     * Gets the full details of the chips for the receipt.
     *
     * @return A formatted String with the chips name, price, and type.
     */
    @Override
    public String getDetails() {
        return OrderReceiptManager.formatItemReceipt(this);
    }

    /**
     * Gets a short summary of the chips item.
     * This is used when showing the item in a menu or order list.
     *
     * @return A short String with the chips type and price.
     */
    @Override
    public String getItemHeader() {
        return "Chips {" + chipsType + ", $" + getValue() + "}";
    }

    public ChipsType getChipsType() {
        return chipsType;
    }

    /**
     * Changes the chips type.
     *
     * @param chipsType The new chips type.
     */
    public void setChipsType(ChipsType chipsType) {
        this.chipsType = chipsType;
    }
}
