package com.plurasight;

import com.plurasight.Enums.ChipsType;

public class Chips extends Item{
    private final ChipsType chipsType;

    public Chips(ChipsType chipsType){
        this.chipsType = chipsType;
    }

    @Override
    public double getValue() {
        return 1.5;
    }

    @Override
    public String getDetails() {
        String chipsString = "Chips";
        return chipsString + UIComponent.formatTaps(chipsString.length() - getTaps().length()) + "$" + getValue() +
                getTaps() + chipsType;

    }

    @Override
    public String getItemHeader() {
        return "";
    }
}
