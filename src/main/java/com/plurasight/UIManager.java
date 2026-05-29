package com.plurasight;

import java.util.Scanner;


public class UIManager {

    private final Scanner scanner = new Scanner(System.in);
    private Order newOrder = null;

    public void displayMainMenu() {
        boolean isRunning = true;
        do {
            System.out.println("MAIN MENU");
            System.out.println("""
                   \t1.New Order
                   \t2.Exit""");
            int userInput = UIComponent.getUserInput(scanner);
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
            int userInput = UIComponent.getUserInput(scanner);
            switch (userInput){
                case 1:
                    SandwichUIComponent sandwichUI= new SandwichUIComponent(scanner);
                    sandwichUI.displayComponent();
                    newOrder.addItem(sandwichUI.getItem());
                    break;
                case 2:
                    DrinkUIComponent drinkUi = new DrinkUIComponent(scanner);
                    drinkUi.displayComponent();
                    newOrder.addItem(drinkUi.getItem());
                    break;
                case 3:
                    ChipsUIComponent chipsUIComponent = new ChipsUIComponent(scanner);
                    chipsUIComponent.displayComponent();;
                    newOrder.addItem(chipsUIComponent.getItem());
                    break;
                case 4:
                    CheckoutUIComponent checkoutUIComponent = new CheckoutUIComponent(scanner,newOrder);
                    checkoutUIComponent.displayComponent();
                    if (checkoutUIComponent.isOrdercheckeout()) {
                        newOrder = null;
                        isRunning = false;
                    }
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






}
