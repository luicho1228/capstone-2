package com.plurasight;

import com.plurasight.Enums.Bread;
import com.plurasight.Enums.Cheese;
import com.plurasight.Enums.Meat;
import com.plurasight.Enums.Size;

import java.util.Scanner;


public class UIManager {

    private Scanner scanner = new Scanner(System.in);
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
        Order order;
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
                    order = new Order(addSandwich());
                    break;
                case 2:
                    addDrink();
                    break;
                case 3:
                    addChips();
                    break;
                case 4:
                    checkout();
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
        System.out.println();
        boolean extraMeat;

        Cheese cheese = addCheese();
        boolean extraCheese;
        return new Sandwich(bread,size,meat,extraMeat,cheese,extraCheese);

    }
    public Cheese addCheese(){

    }
    public Size chooseSize(){

    }
    public Bread addBread(){

    }
    public Meat addMeat(){
        Meat[] meats = Meat.values();
        for (Meat m: meats){
            System.out.println("\n" + m);
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
