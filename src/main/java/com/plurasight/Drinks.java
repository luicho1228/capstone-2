package com.plurasight;

public class Drinks implements Item {

    Flavors flavor;
    Size size;

    public Drinks(Size size, Flavors flavor){
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
        return String.format("%s|%s",size.toString(),flavor.toString());
    }
}
