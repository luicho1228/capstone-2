package com.plurasight;

import com.plurasight.Enums.*;

import java.util.HashSet;

public class SignatureSandwich extends Sandwich{
    /**
     * Constructs a complete, immutable instance of a Sandwich configuration.
     *
     * @param bread       The chosen bread type.
     * @param size        The chosen sandwich length profile.
     * @param meat        The base meat choice.
     * @param extraMeat   True if an extra portion of meat should be charged and added.
     * @param cheese      The base cheese choice.
     * @param extraCheese True if an extra portion of cheese should be charged and added.
     * @param toppings    A set of chosen garnishes.
     * @param sauces      A set of chosen sauces.
     */
    public SignatureSandwich(Bread bread, Size size, boolean isToasted, Meat meat, boolean extraMeat, Cheese cheese, boolean extraCheese, HashSet<Topping> toppings, HashSet<Sauce> sauces) {
        super(bread, size, isToasted, meat, extraMeat, cheese, extraCheese, toppings, sauces);
    }
}
