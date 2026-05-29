package com.plurasight;

import java.util.InputMismatchException;
import java.util.Scanner;


public class UIManager {

    private final Scanner scanner = new Scanner(System.in);
    private Order currentOrder = null;

    private void orderUiInit(){
        OrderUIComponent orderUIComponent = new OrderUIComponent(scanner);
        currentOrder = new Order();
        boolean needsInput = true;
        do{
        orderUIComponent.displayComponent();
        switch (orderUIComponent.getInput()) {
            case 1:
               sandwichUiInit();
                break;
            case 2:
               signatureSandwichUiInit();
                break;
            case 3:
              drinkUiInit();
                break;
            case 4:
                chipsUiInit();
                break;
            case 5:
                addCustomerNameToOrder();
                break;
            case 6:
               needsInput = checkOutUiInit();
                break;
            case 7:
                needsInput = false;
                currentOrder = null;
                break;
        }
        }while (needsInput);
    }

    private boolean checkOutUiInit(){
        if (!(currentOrder.isEmpty())) {
            CheckoutUIComponent checkoutUIComponent = new CheckoutUIComponent(scanner, currentOrder);
            checkoutUIComponent.displayComponent();
            if (checkoutUIComponent.isOrderCheckedOut()) {
                currentOrder = null;
                return false;
            }
        } else {
            System.err.println("There must be at least ONE item in your order to checkout");
        }
        return true;
    }

    private void chipsUiInit(){
        ChipsUIComponent chipsUIComponent = new ChipsUIComponent(scanner);
        chipsUIComponent.displayComponent();
        currentOrder.addItem(chipsUIComponent.getItem());
    }

    private void drinkUiInit(){
        DrinkUIComponent drinkUi = new DrinkUIComponent(scanner);
        drinkUi.displayComponent();
        currentOrder.addItem(drinkUi.getItem());
    }

    private void signatureSandwichUiInit(){
        SignatureSandwichUIComponent signatureSandwichUIComponent = new SignatureSandwichUIComponent(scanner);
        signatureSandwichUIComponent.displayComponent();
        currentOrder.addItem(signatureSandwichUIComponent.getSignatureSandwich());
    }
    private void sandwichUiInit(){
        SandwichUIComponent sandwichUI = new SandwichUIComponent(scanner);
        sandwichUI.displayComponent();
        currentOrder.addItem(sandwichUI.getItem());
    }

    public void UiInit(){
        MainMenuUIComponent mainMenuUIComponent = new MainMenuUIComponent(scanner);
        do{
        mainMenuUIComponent.displayComponent();
        int menuResults = mainMenuUIComponent.getAction();;
        if (menuResults == 1){
           orderUiInit();
        } else if (menuResults == 2) {
            //Exit program elegantly
            System.out.println("Thank you! see you next time.");
            break;
        }}while (true);

    }

    private void addCustomerNameToOrder() {
        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine();
        currentOrder.setCustomerName(customerName);
    }

//    public void displayMainMenu() {
//        boolean isRunning = true;
//        do {
//            try {
//            System.out.println("MAIN MENU");
//            String[] optionsArray = {"New Order", "Exit"};
//            System.out.println("""
//                    \t1.New Order
//                    \t2.Exit""");
//
//                int userInput = UIComponent.getUserInput(scanner);
//                switch (userInput) {
//                    case 1:
//                        displayOrderMenu();
//                        break;
//                    case 2:
//                        isRunning = false;
//                        break;
//                    default:
//                        System.out.println("Select an option provided.");
//                }
//            } catch (InputMismatchException ime) {
//                System.err.println("Error: Select a numeric value from the options provided");
//                scanner.nextLine();
//            }
//        } while (isRunning);
//    }
//
//
//    public void displayOrderMenu() {
//
//        currentOrder = new Order();
//        boolean needsInput = true;
//        do {
//            System.out.println("ORDER SCREEN");
//            System.out.println("""
//                    \t1.Add Sandwich
//                    \t2.Add Signature Sandwich
//                    \t3.Add Drink
//                    \t4.Add Chips
//                    \t5.Add Customer Name to the order
//                    \t6.Checkout
//                    \t0.Cancel Order""");
//            try {
//                int userInput = UIComponent.getUserInput(scanner);
//                switch (userInput) {
//                    case 1:
//                        SandwichUIComponent sandwichUI = new SandwichUIComponent(scanner);
//                        sandwichUI.displayComponent();
//                        currentOrder.addItem(sandwichUI.getItem());
//                        break;
//                    case 2:
//
//                        SignatureSandwichUIComponent signatureSadwichUIComponent = new SignatureSandwichUIComponent(scanner);
//                        signatureSadwichUIComponent.displayComponent();
//                        currentOrder.addItem(signatureSadwichUIComponent.getSignatureSandwich());
//
//                        break;
//                    case 3:
//                        DrinkUIComponent drinkUi = new DrinkUIComponent(scanner);
//                        drinkUi.displayComponent();
//                        currentOrder.addItem(drinkUi.getItem());
//                        break;
//                    case 4:
//                        ChipsUIComponent chipsUIComponent = new ChipsUIComponent(scanner);
//                        chipsUIComponent.displayComponent();
//                        ;
//                        currentOrder.addItem(chipsUIComponent.getItem());
//                        break;
//                    case 5:
//                        addCustomerNameToOrder();
//                        break;
//                    case 6:
//                        if (!(currentOrder.isEmpty())) {
//                            CheckoutUIComponent checkoutUIComponent = new CheckoutUIComponent(scanner, currentOrder);
//                            checkoutUIComponent.displayComponent();
//                            if (checkoutUIComponent.isOrderCheckedOut()) {
//                                currentOrder = null;
//                                needsInput = false;
//                            }
//                        } else {
//                            System.out.println("There must be at least ONE item in your order to checkout");
//                        }
//                        break;
//                    case 0:
//                        currentOrder = null;
//                        needsInput = false;
//                        break;
//                    default:
//                        System.out.println("Select an option provided");
//                }
//            } catch (InputMismatchException ime) {
//                System.err.println("Error: Select a numeric value from the options provided");
//                scanner.nextLine();
//            }
//
//        } while (needsInput);
//
//    }


}
