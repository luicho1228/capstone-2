package com.plurasight;

import com.plurasight.Enums.Size;

public class Drink extends Item {

    private Size size;

    public Drink(Size size) {
        this.size = size;
    }

    public void setSize(Size size){
        this.size =size;
    }

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

    @Override
    public String getDetails() {
        String drinkName = "Drink";

        return drinkName + UIComponent.formatPadding(drinkName.length() - getTaps().length()) + "$" + getValue() +
                getTaps() + size.toString();
    }

    @Override
    public String getItemHeader() {
        return "Drink {Size: " + size +", $"+getValue()+"}";
    }
}
