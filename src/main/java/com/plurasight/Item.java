package com.plurasight;

abstract class Item {
    private final static String taps = "\n\t* ";
    private final static String subTaps ="\n\t\t";

    public abstract double getValue();
    public abstract String getDetails();

    public static String getTaps() {
        return taps;
    }
    public static String getSubTaps() {
        return subTaps;
    }

    public abstract String getItemHeader();
}
