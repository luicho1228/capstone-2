package com.plurasight;

import com.plurasight.Enums.Bread;
import com.plurasight.Enums.Cheese;
import com.plurasight.Enums.Meat;
import com.plurasight.Enums.Size;

import java.util.Scanner;


public class UIManager {

    private Scanner scanner = new Scanner(System.in);
    Order newOrder = null;
    private int userInput = 0;
    public void displayMainMenu() {
        boolean isRunning = true;
        do {
            System.out.println("MAIN MENU");
            System.out.println("""
                    1.New Order
                    2.Exit""");
            userInput = scanner.nextInt();
            scanner.nextLine();
           switch (userInput){
               case 1:
                   newOrder();
                   break;
               case 2:
                   isRunning = false;
                   break;
               default:
                   System.out.println("Select an option provided.");
           }
        } while (isRunning);
    }


    public void newOrder(){

        boolean isRunning = true;
        do {
            System.out.println("ORDER SCREEN");
            System.out.println("""
                1.Add Sandwich
                2.Add Drink
                3.Add Chips
                4.Checkout
                0.Cancel Order""");
            userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput){
                case 1:
                    newOrder = new Order(addSandwich());
                    break;
                case 2:
                    addDrink();
                    break;
                case 3:
                    addChips();
                    break;
                case 4:
                    checkout();
                    OrderFileManager.saveOrder(newOrder);
                    break;
                case 0 :
                    isRunning = false;
                    break;
                default:
                    System.out.println("Select an option provided");
            }

        }while (isRunning);


    }

    private void checkout() {
        System.out.println("CHECKOUT");
        System.out.println("\n"+"|Item|\t\t\t\t\t\tPrice");
        StringBuilder stringBuilder = new StringBuilder();
        for (Item item: newOrder.items){
            if (item instanceof Sandwich){
                Sandwich sandwich = (Sandwich)item;
               stringBuilder.append("\n* ").append(sandwich.getSize()).append("\t").append(sandwich.getBread()).append("\t\t\t").append(sandwich.getValue());
               stringBuilder.append("\n* ").append(sandwich.getMeat()).append("\n\t").append(sandwich.isExtraMeat());
               stringBuilder.append("\n* ").append(sandwich.getCheese()).append("\n\t").append(sandwich.isExtraCheese());
            }
        }
        System.out.println(stringBuilder);

    }

    private void addChips() {
    }

    private void addDrink() {
    }

    public Item addSandwich(){
        System.out.println("ADD SANDWICH");
        System.out.println("Select Bread:");
        Bread bread = addBread();
        Size size = chooseSize();
        Meat meat = addMeat();
        System.out.println("Do you want extra Meat?\n1.Yes\t\t\t2.No");
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
    public Cheese addCheese(){

        Cheese[] cheeseList = Cheese.values();
        int count = 1;
        for (Cheese c: cheeseList){
            System.out.println("\n"+count+". "+ c);
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
    public Size chooseSize(){
        Size[] sizes = Size.values();
        int count = 1;
        for (Size s: sizes){
            System.out.println("\n"+count+". "+ s);
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
    public Bread addBread(){

        Bread[] breadList = Bread.values();
        int count = 1;
        for (Bread b: breadList){
            System.out.println("\n"+count+". "+ b);
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
    public Meat addMeat(){
        Meat[] meats = Meat.values();
        int count  = 1;
        for (Meat m: meats){
            System.out.println("\n"+count+". "+ m);
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


    public void displayOrder(Order order) {
        for (Item item : order.getItemsInOrder()) {
            if (item instanceof Sandwich) {
                Meat meat = ((Sandwich) item).getMeat();
            }
        }
    }


}
