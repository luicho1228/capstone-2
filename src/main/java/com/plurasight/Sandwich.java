package com.plurasight;

import com.plurasight.Enums.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a custom sandwich item in the point-of-sale system.
 * This class encapsulates all sandwich structural components, handles complex
 * pricing calculations based on sizing tiers, and generates formatted structural receipts.
 *
 * @author Luis Vasquez
 */
public class Sandwich extends Item {

    private boolean isToasted;
    private Bread bread;
    private Size size;
    private Cheese cheese;
    private boolean extraCheese;
    private Meat meat;
    private boolean extraMeat;
    private Set<Topping> toppings;
    private Set<Sauce> sauces;
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
    public Sandwich(Bread bread, Size size,boolean isToasted ,Meat meat, boolean extraMeat, Cheese cheese, boolean extraCheese,HashSet<Topping> toppings,HashSet<Sauce> sauces) {
        this.bread = bread;
        this.size = size;
        this.meat = meat;
        this.extraMeat = extraMeat;
        this.cheese = cheese;
        this.extraCheese = extraCheese;
        this.toppings = toppings;
        this.sauces = sauces;
        this.isToasted = isToasted;
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
        String extraMeatDisplay = extraMeat ? "Extra Meat" : "No-Extra Meat";
        String extraCheeseDisplay = extraCheese ? "Extra Cheese" : "No-Extra Cheese";
        String isToastedDisplay = isToasted ? "Toasted" : "Not-Toasted";
        String breadDisplay = isToastedDisplay + " " + getBreadSize()+ " " + bread + " Bread";

        //print general sandwich details
        StringBuilder details = new StringBuilder();
        details.append(sandwichName).append(UIComponent.formatPadding(sandwichName.length() - getTaps().length())).append("$").append(getValue());
        details.append(getTaps()).append(breadDisplay).append(UIComponent.formatPadding(breadDisplay.length())).append(getSizeValue());
        details.append(getTaps()).append(meat).append(UIComponent.formatPadding(meat.toString().length())).append(getMeatValue());
        details.append(getSubTaps()).append(extraMeatDisplay).append(UIComponent.formatPadding(("* " + extraMeatDisplay).length())).append(getExtraMeatValue());
        details.append(getTaps()).append(cheese).append(UIComponent.formatPadding(cheese.toString().length())).append(getCheeseValue());
        details.append(getSubTaps()).append(extraCheeseDisplay).append(UIComponent.formatPadding(("* " + extraCheeseDisplay).length())).append(getExtraCheeseValue());

        //Process toppings
        details.append(getTaps()).append("Toppings");
        if (!(toppings.isEmpty())) {
            StringBuilder toppingString = new StringBuilder();
            int toppingCount = 1;
            for (Topping topping : toppings) {
                if (toppingCount % 3 == 0) {
                    toppingString.append(getSubTaps());
                }
                toppingString.append(topping.toString()).append(", ");
                toppingCount++;
            }
            details.append(getSubTaps()).append(toppingString);
        }else {
            details.append(getSubTaps()).append("No Toppings");
        }

        //process Sauces
        details.append(getTaps()).append("Sauces");
        if (!(sauces.isEmpty())) {
            StringBuilder sauceString = new StringBuilder();
            int sauceCount = 1;
            for (Sauce sauce : sauces) {
                if (sauceCount % 3 == 0) {
                    sauceString.append(getSubTaps());
                }
                sauceString.append(sauce.toString()).append(", ");
                sauceCount++;
            }
            details.append(getSubTaps()).append(sauceString);
        } else {
            details.append(getSubTaps()).append("No Sauce");
        }

        return details.toString();
    }

    @Override
    public String getItemHeader() {
        return sandwichName + "{ "+getBreadSize()+" "+bread+","+meat+","+cheese+","+toppings.toString()+","+sauces+","+"$"+getValue()+ "}" ;
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
     * Retrieves the value of the base sandwich size selection.
     *
     * @return a double representing The base size cost of the sandwich item.
     */
    private double getSizeValue() {
        return switch (size) {
            case SMALL -> 5.5;
            case MEDIUM -> 7;
            case LARGE -> 8.5;
        };
    }

    /**
     * Calculates base meat cost according to the sandwich size.
     *
     * @return a double representing the total cost addition assigned to base meat provisions.
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
     * @return a double representing The total extra meat cost.
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
     * Calculates fundamental cheese premiums according to the sandwich size.
     *
     * @return  a double representing the total cost of base cheese addition.
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
     * Calculates supplementary markup overheads required when user wants extra cheese in their sandwich.
     *
     * @return a double representing the total extra cheese cost.
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

    public void setToast(boolean isToasted){
        this.isToasted = isToasted;
    }

    public void setBread(Bread bread) {
        this.bread = bread;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setMeat(Meat meat) {
        this.meat = meat;
    }

    public void setCheese(Cheese cheese) {
        this.cheese = cheese;
    }

    public void setToppings(HashSet<Topping> toppings) {
        this.toppings = toppings;
    }

    public void setSauces(HashSet<Sauce> sauces) {
        this.sauces = sauces;
    }
}
