package com.plurasight;

import com.plurasight.Enums.Bread;
import com.plurasight.Enums.Cheese;
import com.plurasight.Enums.Meat;
import com.plurasight.Enums.Size;

import java.util.Scanner;

public class SandwichUIComponent extends UIComponent implements Displayable {

    private Item item;
    public SandwichUIComponent(Scanner scanner, int userInput){
        super(scanner,userInput);
    }

    private Item addSandwich(){
        System.out.println("ADD SANDWICH");
        System.out.println("\tSelect Bread:");
        Bread bread = addBread();
        Size size = chooseSize();
        Meat meat = addMeat();
        System.out.println("\tDo you want extra Meat?\n1.Yes\t\t\t2.No");
        userInput = scanner.nextInt();
        scanner.nextLine();
        boolean extraMeat = false;
        if (userInput == 1){
            extraMeat = true;
        }
        Cheese cheese = addCheese();
        System.out.println("Do you want extra Cheese?\n1.Yes\t\t\t2.No");
        userInput = scanner.nextInt();
        scanner.nextLine();
        boolean extraCheese = false;
        if (userInput == 1){
            extraCheese = true;
        }
        return new Sandwich(bread,size,meat,extraMeat,cheese,extraCheese);

    }
    private Cheese addCheese(){

        Cheese[] cheeseList = Cheese.values();
        int count = 1;
        for (Cheese c: cheeseList){
            System.out.println(count+". "+ c);
            count++;
        }
        userInput = scanner.nextInt();
        scanner.nextLine();
        Cheese cheese= null;
        boolean isRunning =true;
        do {
            switch (userInput) {
                case 1:
                    cheese = Cheese.AMERICAN;
                    isRunning = false;
                    break;
                case 2:
                    cheese = Cheese.PROVOLONE;
                    isRunning =false;
                    break;
                case 3:
                    cheese = Cheese.CHEDDAR;
                    isRunning = false;
                    break;
                case 4:
                    cheese = Cheese.SWISS;
                    isRunning = false;
                    break;
                case 5:
                    cheese = Cheese.NO_CHEESE;
                    isRunning = false;
                default:
            }
        }while(isRunning);
        return cheese;
    }
    private Size chooseSize(){
        Size[] sizes = Size.values();
        int count = 1;
        for (Size s: sizes){
            System.out.println(count+". "+ s);
            count++;
        }
        userInput = scanner.nextInt();
        scanner.nextLine();
        Size size= null;
        boolean isRunning =true;
        do {
            switch (userInput) {
                case 1:
                    size = Size.SMALL;
                    isRunning = false;
                    break;
                case 2:
                    size = Size.MEDIUM;
                    isRunning =false;
                    break;
                case 3:
                    size = Size.LARGE;
                    isRunning = false;
                    break;
                default:
            }
        }while(isRunning);


        return size;
    }
    private Bread addBread(){

        Bread[] breadList = Bread.values();
        int count = 1;
        for (Bread b: breadList){
            System.out.println(count+". "+ b);
            count++;
        }
        userInput = scanner.nextInt();
        scanner.nextLine();
        Bread bread= null;
        boolean isRunning =true;
        do {
            switch (userInput) {
                case 1:
                    bread = Bread.WHITE;
                    isRunning = false;
                    break;
                case 2:
                    bread = Bread.WHEAT;
                    isRunning =false;
                    break;
                case 3:
                    bread = Bread.RYE;
                    isRunning = false;
                    break;
                case 4:
                    bread = Bread.WRAP;
                    isRunning = false;
                    break;
                default:
            }
        }while(isRunning);

        return bread;
    }

    private Meat addMeat(){
        Meat[] meats = Meat.values();
        int count  = 1;
        for (Meat m: meats){
            System.out.println(count+". "+ m);
            count++;
        }
        userInput = scanner.nextInt();
        scanner.nextLine();
        Meat meat = null;
        boolean isRunning = true;
        do {
            switch (userInput) {
                case 1:
                    meat = Meat.STEAK;
                    isRunning = false;
                    break;
                case 2:
                    meat = Meat.HAM;
                    isRunning = false;
                    break;
                case 3:
                    meat = Meat.SALAMI;
                    isRunning = false;
                    break;
                case 4:
                    meat = Meat.ROAST_BEEF;
                    isRunning = false;
                    break;
                case 5:
                    meat = Meat.CHICKEN;
                    isRunning = false;
                    break;
                case 6:
                    meat = Meat.BACON;
                    isRunning = false;
                    break;
                case 7:
                    meat = Meat.NO_MEAT;
                    isRunning = false;
                    break;
                default:
                    System.out.println("Select from the option provided");
            }
        }while (isRunning);
        return  meat;
    }


    @Override
    public void displayComponent() {
        item = addSandwich();
    }

    public Item getItem() {
        return this.item;
    }
}
