package com.plurasight;

import com.plurasight.Enums.ChipsType;

public class Chips extends Item{
    private ChipsType chipsType;

    public Chips(ChipsType chipsType){
        this.chipsType = chipsType;
    }

    @Override
    public double getValue() {
        return 1.5;
    }

    @Override
    public String getDetails() {
        return "Chips: \t\t\t&"+getValue()+
                "\n"+ this.chipsType;
    }
}
