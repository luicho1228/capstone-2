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
    private String breadSize;
    private double sizeValue;
    private double meatValue;
    private double cheeseValue;
    private double extraMeatValue;
    private double extraCheeseValue;
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
        setValues();
    }

    private void setValues(){
        switch (size){
            case SMALL:
                sizeValue = 5.5;
                breadSize = "4\"";
                if (meat != Meat.NO_MEAT){
                    meatValue = 1;
                }
                if (extraMeat) {
                    extraMeatValue = 0.5;
                }
                if (cheese != Cheese.NO_CHEESE) {
                    cheeseValue = 0.75;
                }
                if (extraCheese) {
                    extraCheeseValue = 0.3;
                }
                break;
            case MEDIUM:
                sizeValue = 7;
                breadSize = "8\"";
                if (meat != Meat.NO_MEAT){
                    meatValue = 2;
                }
                if (extraMeat) {
                    extraMeatValue = 1;
                }
                if (cheese != Cheese.NO_CHEESE) {
                    cheeseValue = 1.5;
                }
                if (extraCheese) {
                    extraCheeseValue = 0.6;
                }
                break;
            case LARGE:
                sizeValue = 8.5;
                breadSize = "12\"";
                if (meat != Meat.NO_MEAT){
                    meatValue = 3;
                }
                if (extraMeat) {
                    extraMeatValue = 1.5;
                }
                if (cheese != Cheese.NO_CHEESE) {
                    cheeseValue = 2.25;
                }
                if (extraCheese) {
                    extraCheeseValue = 0.9;
                }
                break;
        }
    }

    public Bread getBread() {
        return bread;
    }

    public Size getSize() {
        return size;
    }

    public Cheese getCheese() {
        return cheese;
    }

    public boolean isExtraCheese() {
        return extraCheese;
    }

    public Meat getMeat() {
        return meat;
    }

    public boolean isExtraMeat() {
        return extraMeat;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public List<Sauce> getSauces() {
        return sauces;
    }

    @Override
    public double getValue() {
        return sizeValue + meatValue + extraMeatValue + cheeseValue + extraCheeseValue;
    }

    @Override
    public String getDetails() {
        //todo implement the detailreceipt
//        String xtraCheese ="No-Extra Cheese";
//        String xtraMeat ="No-Extra Meat";
//        if (extraMeat){
//            xtraMeat = "Extra Meat";
//        }
//        if (extraCheese){
//            xtraCheese = "extra cheese";
//        }
//
//        String sandwichDetails = String.format("Sandwich:" +
//                        "\n\t* %s %s bread\t\t\t\t$%.2f"+
//                        "\n\t* %s\t\t\t\t\t\t$%.2f" +
//                        "\n\t\t* %s\t\t\t\t\t$%.2f" +
//                        "\n\t* %s\t\t\t\t\t\t$%.2f" +
//                        "\n\t\t* %s\t\t\t\t\t$%.2f" +
//                        "\n\t* Total:\t\t\t\t\t\t $%.2f",breadSize,bread.toString(),sizeValue
//                ,meat.toString(),meatValue,xtraMeat,extraMeatValue, cheese.toString(),cheeseValue, xtraCheese,extraCheeseValue,getValue());
//
        return String.format("Sandwich|%S|%s|%s|%b|%s|%b",size,bread,meat,extraMeat,cheese,extraCheese);
    }
}
