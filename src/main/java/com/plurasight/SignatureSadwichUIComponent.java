package com.plurasight;

import com.plurasight.Enums.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class SignatureSadwichUIComponent extends UIComponent implements Displayable{

    private final List<SignatureSandwich> signatureSandwiches;

    public SignatureSadwichUIComponent(Scanner scanner) {
        super(scanner);
        signatureSandwiches = new ArrayList<>();
        loadSignatureSandwiches();
    }

    private void loadSignatureSandwiches(){
        HashSet<Topping> toppings = new HashSet<>(List.of(Topping.LETTUCE,Topping.TOMATOES));
        HashSet<Sauce> sauces = new HashSet<>(List.of(Sauce.RANCH));
        SignatureSandwich blt = new SignatureSandwich(Bread.WHITE, Size.SMALL,true, Meat.BACON,false, Cheese.CHEDDAR,false,toppings,sauces);
        blt.setSandwichName("BLT");
        signatureSandwiches.add(blt);

        toppings = new HashSet<>(List.of(Topping.PEPPERS));
        sauces = new HashSet<>(List.of(Sauce.MAYO));
        SignatureSandwich phillyCheeseSteak = new SignatureSandwich(Bread.WHITE,Size.SMALL,true,Meat.STEAK,false,Cheese.AMERICAN,false,toppings,sauces);
        phillyCheeseSteak.setSandwichName("Philly Cheese Steak");
        signatureSandwiches.add(phillyCheeseSteak);
    }

    private void displaySignatureSandwiches(){

        System.out.println("SIGNATURE SANDWICHES");
        for (SignatureSandwich signatureSandwich: signatureSandwiches){
            signatureSandwich.getDetails();
        }
    }

    @Override
    public void displayComponent() {

        displaySignatureSandwiches();
    }
}
