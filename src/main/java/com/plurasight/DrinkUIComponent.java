package com.plurasight;

import com.plurasight.Enums.Size;

import java.util.Scanner;

public class DrinkUIComponent extends UIComponent implements Displayable {

    private Item item;
    public DrinkUIComponent(Scanner scanner){
        super(scanner);

    }

    private Item addDrink(){
        System.out.println("ADD DRINK");
        return new Drink(selectSize());
    }

    private Size selectSize(){
        return selectEnumOption(Size.values(),"Select Size:");
    }

    @Override
    public void displayComponent() {
        item = addDrink();
    }
    public Item getItem(){
        return this.item;
    }
}
