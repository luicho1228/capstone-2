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
                   \t1.New Order
                   \t2.Exit""");
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

        newOrder = new Order();
        boolean isRunning = true;
        do {
            System.out.println("ORDER SCREEN");
            System.out.println("""
                \t1.Add Sandwich
                \t2.Add Drink
                \t3.Add Chips
                \t4.Checkout
                \t0.Cancel Order""");
            userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput){
                case 1:
                    SandwichUIComponent sandwichUI= new SandwichUIComponent(scanner,userInput);
                    sandwichUI.displayComponent();
                    newOrder.addItem(sandwichUI.getItem());
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
        System.out.println(newOrder.getOrderDetails());

    }

    private void addChips() {
    }

    private void addDrink() {
    }





}
