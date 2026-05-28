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
        return new Drinks(selectSize());
    }

    private Size selectSize(){
        System.out.println("Select Size:");
        int count = 1;
        for (Size size: Size.values()){
            System.out.println(count+". " + size);
            count++;
        }
        int userInput = UIComponent.getUserInput(scanner);
        Size size = null;
        switch (userInput){
            case 1:
                size =Size.SMALL;
                break;
            case 2:
                size = Size.MEDIUM;
                break;
            case 3:
                size = Size.LARGE;
                break;
            default:
        }

        return size;
    }

    @Override
    public void displayComponent() {
        item = addDrink();
    }
    public Item getItem(){
        return this.item;
    }
}
