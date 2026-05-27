package com.plurasight;

import com.plurasight.Enums.*;

import java.util.List;

public class Sandwich extends Item {

    private Bread bread;
    private Size size;
    private Cheese cheese;
    boolean extraCheese;
    private Meat meat;
    private boolean extraMeat;
    private List<Topping> toppings;
    private List<Sauce>sauces;
    // List<Topping> toppings,List<Sauce>sauces
    public Sandwich(Bread bread, Size size, Meat meat, boolean extraMeat, Cheese cheese, boolean extraCheese){
        this.bread = bread;
        this.size = size;
        this.meat = meat;
        this.extraMeat = extraMeat;
        this.cheese = cheese;
        this.extraCheese = extraCheese;
        this.toppings = toppings;
        this.sauces = sauces;
    }

    private double getExtraMeatValue(){
        double extraMeatValue = 0.0;
        switch (size){
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

    private double getExtraCheeseValue(){
        double extraCheeseValue = 0.0;
        switch (size){
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

    private double getMeatValue(){
        double meatValue = 0.0;
        switch (size){
            case SMALL:
                if (meat != Meat.NO_MEAT){
                    meatValue = 1;
                }
                break;
            case MEDIUM:
                if (meat != Meat.NO_MEAT){
                    meatValue = 2;
                }
                break;
            case LARGE:
                if (meat != Meat.NO_MEAT){
                    meatValue = 3;
                }
                break;
        }
        return meatValue;
    }

    private double getCheeseValue(){
        double cheeseValue = 0.0;
        switch (size){
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

    private String getBreadSize(){
        return switch (size) {
            case SMALL -> "4\"";
            case MEDIUM -> "8\"";
            case LARGE -> "12\"";
        };
    }
    private double getSizeValue(){
        return switch (size) {
            case SMALL -> 5.5;
            case MEDIUM -> 7;
            case LARGE -> 8.5;
        };
    }



    @Override
    public double getValue() {
        return getSizeValue()+ getMeatValue() + getExtraMeatValue() + getCheeseValue() + getExtraCheeseValue();
    }

    @Override
    public String getDetails() {
        String xtraCheese ="No-Extra Cheese";
        String xtraMeat ="No-Extra Meat";
        if (extraMeat){
            xtraMeat = "Extra Meat";
        }
        if (extraCheese){
            xtraCheese = "extra cheese";
        }
        return String.format("Custom Sandwich:" +
                        "\n\t* %s %s bread\t\t\t\t$%.2f"+
                        "\n\t* %s\t\t\t\t\t\t$%.2f" +
                        "\n\t\t%s\t\t\t\t\t$%.2f" +
                        "\n\t* %s\t\t\t\t\t\t$%.2f" +
                        "\n\t\t%s\t\t\t\t\t$%.2f" +
                        "\n\t* Subtotal:\t\t\t\t\t\t $%.2f",getBreadSize(),bread.toString(),getSizeValue()
                ,meat.toString(),getMeatValue(),xtraMeat,getExtraMeatValue(), cheese.toString(),getCheeseValue(), xtraCheese,getExtraCheeseValue(),getValue());
    }
}
