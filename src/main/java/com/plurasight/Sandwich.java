package com.plurasight;

import java.util.List;

public class Sandwich implements Item{

    private Bread bread;
    private Size size;
    private Cheese cheese;
    boolean extraCheese;
    private Meat meat;
    boolean extraMeat;
    List<Topping> toppings;
    List<Sauce>sauces;
    List<Side>sides;
    private double value;

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


    public double getSizeValue(){
        double sizeValue = 0.0;
        switch (size){
            case SMALL -> sizeValue = 5.5;
            case MEDIUM -> sizeValue = 7;
            case LARGE -> sizeValue = 8.5;
        }
        return sizeValue;
    }

    @Override
    public double getValue() {
        //todo simplify getValue process
        double value = 0.0;
        switch (size){
            case SMALL:
                value = 5.5;
                if (meat != Meat.NO_MEAT){
                    value += 1;
                    if (extraMeat){
                        value+= 0.50;
                    }
                }
                if (cheese != Cheese.NO_CHEESE){
                    value += 0.75;
                    if (extraCheese){
                        value += 0.30;
                    }
                }
                break;
            case MEDIUM:
                value = 7;

                if (meat != Meat.NO_MEAT){
                    value += 2;
                    if (extraMeat){
                        value+= 1;
                    }
                }
                if (cheese != Cheese.NO_CHEESE){
                    value += 1.5;
                    if (extraCheese){
                        value += 0.60;
                    }
                }
                break;
            case LARGE:
                value = 8.5;
                if (meat != Meat.NO_MEAT){
                    value += 3;
                    if (extraMeat){
                        value+= 1.50;
                    }
                }
                if (cheese != Cheese.NO_CHEESE){
                    value += 2.25;
                    if (extraCheese){
                        value += 0.90;
                    }
                }
                break;
        }

        return value;
    }

    @Override
    public String getDetails() {
        //todo implement the detailreceipt
        return String.format("Sandwich|%s|%s|%s|%b|%s|%b",size.toString(),bread.toString()
                ,meat.toString(),extraMeat, cheese.toString(), extraCheese);
    }
}
