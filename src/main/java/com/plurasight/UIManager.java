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
                \t2.Add Signature Sandwich
                \t3.Add Drink
                \t4.Add Chips
                \t5.Checkout
                \t0.Cancel Order""");
            int userInput = UIComponent.getUserInput(scanner);
            switch (userInput){
                case 1:
                    SandwichUIComponent sandwichUI= new SandwichUIComponent(scanner);
                    sandwichUI.displayComponent();
                    newOrder.addItem(sandwichUI.getItem());
                    break;
                case 2:

                    

                    break;
                case 3:
                    DrinkUIComponent drinkUi = new DrinkUIComponent(scanner);
                    drinkUi.displayComponent();
                    newOrder.addItem(drinkUi.getItem());
                    break;
                case 4:
                    ChipsUIComponent chipsUIComponent = new ChipsUIComponent(scanner);
                    chipsUIComponent.displayComponent();;
                    newOrder.addItem(chipsUIComponent.getItem());
                    break;
                case 5:
                    if (!(newOrder.isEmpty())) {
                        CheckoutUIComponent checkoutUIComponent = new CheckoutUIComponent(scanner, newOrder);
                        checkoutUIComponent.displayComponent();
                        if (checkoutUIComponent.isOrderCheckedout()) {
                            newOrder = null;
                            isRunning = false;
                        }
                    }else {
                        System.out.println("There must be at least ONE item in your order to checkout");
                    }
                    break;
                case 0 :
                    newOrder = null;
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
