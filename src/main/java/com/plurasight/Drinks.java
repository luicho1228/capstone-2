package com.plurasight;

import com.plurasight.Enums.Flavor;
import com.plurasight.Enums.Size;

public class Drinks extends Item {

    Flavor flavor;
    Size size;

    public Drinks(Size size, Flavor flavor){
        this.size = size;
        this.flavor = flavor;
    }

    @Override
    public double getValue() {
        double value = 0.0;
        switch (size){
            case SMALL -> value = 2;
            case MEDIUM -> value = 2.5;
            case LARGE -> value = 3;
        }
        return value;
    }

    @Override
    public String getDetails() {
        return String.format("Drink:" +
                "\n\t* %s\t\t\t\t\t\t\t$%.2f" +
                "\n\t* %s",size.toString(),getValue(),flavor.toString());
    }
}
