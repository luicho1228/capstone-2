package com.plurasight;

import com.plurasight.Enums.*;

import java.util.HashSet;

/**
 * Represents a custom sandwich item in the point-of-sale system.
 * This class encapsulates all sandwich structural components, handles complex
 * pricing calculations based on sizing tiers, and generates formatted structural receipts.
 *
 * @author Luis Vasquez
 */
public class Sandwich extends Item {

    private final Bread bread;
    private final Size size;
    private final Cheese cheese;
    private final boolean extraCheese;
    private final Meat meat;
    private final boolean extraMeat;
    private final HashSet<Topping> toppings;
    private final HashSet<Sauce> sauces;
    private String sandwichName = "Custom Sandwich";

    /**
     * Constructs a complete, immutable instance of a Sandwich configuration.
     *
     * @param bread        The chosen bread type.
     * @param size         The chosen sandwich length profile.
     * @param meat         The base meat choice.
     * @param extraMeat    True if an extra portion of meat should be charged and added.
     * @param cheese       The base cheese choice.
     * @param extraCheese  True if an extra portion of cheese should be charged and added.
     * @param toppings     A set of chosen garnishes.
     * @param sauces       A set of chosen sauces.
     */
    public Sandwich(Bread bread, Size size, Meat meat, boolean extraMeat, Cheese cheese, boolean extraCheese,HashSet<Topping> toppings,HashSet<Sauce> sauces) {
        this.bread = bread;
        this.size = size;
        this.meat = meat;
        this.extraMeat = extraMeat;
        this.cheese = cheese;
        this.extraCheese = extraCheese;
        this.toppings = toppings;
        this.sauces = sauces;
    }

    /**
     * Calculates the cumulative dollar value of the sandwich by combining the size base cost,
     * base premium ingredients, and any corresponding extra modifications.
     *
     * @return The complete calculated price of the entire sandwich structure.
     */
    @Override
    public double getValue() {
        return getSizeValue() + getMeatValue() + getExtraMeatValue() + getCheeseValue() + getExtraCheeseValue();
    }

    /**
     * Formats and compiles a detailed string representation of the sandwich itemized receipt,
     * including ingredient breakdowns, individual add-on calculations, and subtotal layouts.
     *
     * @return A tab-formatted text block suitable for terminal or ticket display output.
     */
    @Override
    public String getDetails() {
        String taps = "\n\t* ";
        String subTaps ="\n\t\t";

        String extraMeatDisplay = extraMeat ? "Extra Meat" : "No-Extra Meat";
        String extraCheeseDisplay = extraCheese ? "Extra Cheese" : "No-Extra Cheese";
        String breadDisplay = getBreadSize()+ " " + bread + " Bread";

        StringBuilder details = new StringBuilder();

        //print general sandwich details
        details.append(sandwichName).append(taps).append(breadDisplay).append(UIComponent.formatTaps(breadDisplay)).append(getSizeValue());
        details.append(taps).append(meat).append(UIComponent.formatTaps(meat.toString())).append(getMeatValue());
        details.append(subTaps).append(extraMeatDisplay).append(UIComponent.formatTaps("* " + extraMeatDisplay)).append(getExtraMeatValue());
        details.append(taps).append(cheese).append(UIComponent.formatTaps(cheese.toString())).append(getCheeseValue());
        details.append(subTaps).append(extraCheeseDisplay).append(UIComponent.formatTaps("* " + extraCheeseDisplay)).append(getExtraCheeseValue());

        //Process toppings
        details.append(taps).append("Toppings");
        if (!(toppings.isEmpty())) {
            StringBuilder toppingString = new StringBuilder();
            int toppingCount = 1;
            for (Topping topping : toppings) {
                if (toppingCount % 3 == 0) {
                    toppingString.append(subTaps);
                }
                toppingString.append(topping.toString()).append(", ");
                toppingCount++;
            }
            details.append(subTaps).append(toppingString);
        }else {
            details.append(subTaps).append("No Toppings");
        }

        //process Sauces
        details.append(taps).append("Sauces");
        if (!(sauces.isEmpty())) {
            StringBuilder sauceString = new StringBuilder();
            int sauceCount = 1;
            for (Sauce sauce : sauces) {
                if (sauceCount % 3 == 0) {
                    sauceString.append(subTaps);
                }
                sauceString.append(sauce.toString()).append(", ");
                sauceCount++;
            }
            details.append(subTaps).append(sauceString);
        } else {
            details.append(subTaps).append("No Sauce");
        }

        //Process subtotal
        String subTotal = "Subtotal:";
        details.append("\n\n").append(subTotal).append(UIComponent.formatTaps("\t* " + subTotal)).append("$").append(getValue());

        return details.toString();
    }

    /**
     * Updates the identifier name of the sandwich.
     *
     * @param name The new custom display name for this sandwich string line.
     */
    public void setSandwichName(String name){
        this.sandwichName = name;
    }

    /**
     * Maps the assigned sandwich size enum profile to its physical inch representation text.
     *
     * @return A string literal indicating the length of the bread item (e.g. 4", 8", 12").
     */
    private String getBreadSize() {
        return switch (size) {
            case SMALL -> "4\"";
            case MEDIUM -> "8\"";
            case LARGE -> "12\"";
        };
    }

    /**
     * Retrieves the structural base financial value tied purely to the sandwich size selection.
     *
     * @return The base monetary rate of the shell sandwich item.
     */
    private double getSizeValue() {
        return switch (size) {
            case SMALL -> 5.5;
            case MEDIUM -> 7;
            case LARGE -> 8.5;
        };
    }

    /**
     * Calculates base meat premiums according to the relative size constraint tiering profiles.
     *
     * @return The total cost addition assigned to base meat provisions.
     */
    private double getMeatValue() {
        double meatValue = 0.0;
        switch (size) {
            case SMALL:
                if (meat != Meat.NO_MEAT) {
                    meatValue = 1;
                }
                break;
            case MEDIUM:
                if (meat != Meat.NO_MEAT) {
                    meatValue = 2;
                }
                break;
            case LARGE:
                if (meat != Meat.NO_MEAT) {
                    meatValue = 3;
                }
                break;
        }
        return meatValue;
    }

    /**
     * Calculates supplementary markup overheads required when adding a double meat package.
     *
     * @return The total extra meat cost modification evaluated against current tier bounds.
     */
    private double getExtraMeatValue() {
        double extraMeatValue = 0.0;
        switch (size) {
            case SMALL:
                if (extraMeat) {
                    extraMeatValue = 0.5;
                }
                break;
            case MEDIUM:
                if (extraMeat) {
                    extraMeatValue = 1;
                }
                break;
            case LARGE:
                if (extraMeat) {
                    extraMeatValue = 1.5;
                }
                break;
        }
        return extraMeatValue;
    }

    /**
     * Calculates fundamental cheese premiums according to the relative size constraint tiering profiles.
     *
     * @return The total cost addition assigned to base cheese provisions.
     */
    private double getCheeseValue() {
        double cheeseValue = 0.0;
        switch (size) {
            case SMALL:
                if (cheese != Cheese.NO_CHEESE) {
                    cheeseValue = 0.75;
                }
                break;
            case MEDIUM:
                if (cheese != Cheese.NO_CHEESE) {
                    cheeseValue = 1.5;
                }
                break;
            case LARGE:
                if (cheese != Cheese.NO_CHEESE) {
                    cheeseValue = 2.25;
                }
                break;
        }
        return cheeseValue;
    }

    /**
     * Calculates supplementary markup overheads required when adding a double cheese package.
     *
     * @return The total extra cheese cost modification evaluated against current tier bounds.
     */
    private double getExtraCheeseValue() {
        double extraCheeseValue = 0.0;
        switch (size) {
            case SMALL:
                if (extraCheese) {
                    extraCheeseValue = 0.3;
                }
                break;
            case MEDIUM:
                if (extraCheese) {
                    extraCheeseValue = 0.6;
                }
                break;
            case LARGE:
                if (extraCheese) {
                    extraCheeseValue = 0.9;
                }
                break;
        }
        return extraCheeseValue;
    }
}
