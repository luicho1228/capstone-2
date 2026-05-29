package com.plurasight;

import java.util.Scanner;


public class UIManager {

    private final Scanner scanner = new Scanner(System.in);
    private Order currentOrder = null;

    public void Launch(){
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


    private void addCustomerNameToOrder() {
        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine();
        currentOrder.setCustomerName(customerName);
    }

}
