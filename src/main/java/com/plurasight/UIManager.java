package com.plurasight;

import java.util.InputMismatchException;
import java.util.Scanner;


public class UIManager {

    private final Scanner scanner = new Scanner(System.in);
    private Order currentOrder = null;

    public void displayMainMenu() {
        boolean isRunning = true;
        do {
            System.out.println("MAIN MENU");
            System.out.println("""
                    \t1.New Order
                    \t2.Exit""");
            try {
                int userInput = UIComponent.getUserInput(scanner);
                switch (userInput) {
                    case 1:
                        displayOrderMenu();
                        break;
                    case 2:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Select an option provided.");
                }
            } catch (InputMismatchException ime) {
                System.err.println("Error: Select a numeric value from the options provided");
                scanner.nextLine();
            }
        } while (isRunning);
    }


    public void displayOrderMenu() {

        currentOrder = new Order();
        boolean needsInput = true;
        do {
            System.out.println("ORDER SCREEN");
            System.out.println("""
                    \t1.Add Sandwich
                    \t2.Add Signature Sandwich
                    \t3.Add Drink
                    \t4.Add Chips
                    \t5.Add Customer Name to the order
                    \t6.Checkout
                    \t0.Cancel Order""");
            try {
                int userInput = UIComponent.getUserInput(scanner);
                switch (userInput) {
                    case 1:
                        SandwichUIComponent sandwichUI = new SandwichUIComponent(scanner);
                        sandwichUI.displayComponent();
                        currentOrder.addItem(sandwichUI.getItem());
                        break;
                    case 2:

                        SignatureSandwichUIComponent signatureSadwichUIComponent = new SignatureSandwichUIComponent(scanner);
                        signatureSadwichUIComponent.displayComponent();
                        currentOrder.addItem(signatureSadwichUIComponent.getSignatureSandwich());

                        break;
                    case 3:
                        DrinkUIComponent drinkUi = new DrinkUIComponent(scanner);
                        drinkUi.displayComponent();
                        currentOrder.addItem(drinkUi.getItem());
                        break;
                    case 4:
                        ChipsUIComponent chipsUIComponent = new ChipsUIComponent(scanner);
                        chipsUIComponent.displayComponent();
                        ;
                        currentOrder.addItem(chipsUIComponent.getItem());
                        break;
                    case 5:
                        addCustomerNameToOrder();
                        break;
                    case 6:
                        if (!(currentOrder.isEmpty())) {
                            CheckoutUIComponent checkoutUIComponent = new CheckoutUIComponent(scanner, currentOrder);
                            checkoutUIComponent.displayComponent();
                            if (checkoutUIComponent.isOrderCheckedOut()) {
                                currentOrder = null;
                                needsInput = false;
                            }
                        } else {
                            System.out.println("There must be at least ONE item in your order to checkout");
                        }
                        break;
                    case 0:
                        currentOrder = null;
                        needsInput = false;
                        break;
                    default:
                        System.out.println("Select an option provided");
                }
            } catch (InputMismatchException ime) {
                System.err.println("Error: Select a numeric value from the options provided");
                scanner.nextLine();
            }

        } while (needsInput);

    }

    private void addCustomerNameToOrder() {
        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine();
        currentOrder.setCustomerName(customerName);
    }
}
