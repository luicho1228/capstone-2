package com.plurasight;

import com.plurasight.Enums.Flavor;
import com.plurasight.Enums.Size;

import java.util.Scanner;

public class DrinkUIComponent extends UIComponent implements Displayable {

    private Item item;
    public DrinkUIComponent(Scanner scanner, int userInput){
        super(scanner,userInput);

    }

    private Item addDrink(){
        System.out.println("ADD DRINK");
        return new Drinks(selectSize(),addFlavor());
    }

    private Size selectSize(){
        System.out.println("Select Size:");
        int count = 1;
        for (Size size: Size.values()){
            System.out.println(count+". " + size);
            count++;
        }
        userInput = scanner.nextInt();
        scanner.nextLine();
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

    private Flavor addFlavor(){
        System.out.println("Select Drink:");
        int count = 1;
        for (Flavor flavor: Flavor.values()){
            System.out.println(count+". " + flavor);
            count++;
        }
        userInput = scanner.nextInt();
        scanner.nextLine();
        Flavor flavor = null;
        switch (userInput){
            case 1:
                flavor = Flavor.COCA_COLA;
                break;
            case 2:
                flavor = Flavor.PEPSI;
                break;
            case 3:
                flavor = Flavor.ORANGE;
                break;
            case 4:
                flavor = Flavor.GRAPE;
                break;
            case 5:
                flavor = Flavor.GINGER_ALE;
                break;
            case 6:
                flavor = Flavor.SPRITE;
                break;
            default:
        }

        return flavor;
    }

    @Override
    public void displayComponent() {
        item = addDrink();
    }
    public Item getItem(){
        return this.item;
    }
}
