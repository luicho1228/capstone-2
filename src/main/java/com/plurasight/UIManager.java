package com.plurasight;

import java.util.Scanner;

/**
 * This class manages the main flow of the program.
 * It controls the main menu, order menu, item screens, and checkout screen.
 */
public class UIManager {

    private final Scanner scanner = new Scanner(System.in);
    private Order currentOrder = null;

    /**
     * Starts the program.
     * It shows the main menu and lets the user start a new order or exit.
     */
    public void Launch() {
        MainMenuUIComponent mainMenuUIComponent = new MainMenuUIComponent(scanner);

        do {
            mainMenuUIComponent.displayComponent();
            int menuResults = mainMenuUIComponent.getAction();

            if (menuResults == 1) {
                orderUiInit();
            } else if (menuResults == 2) {
                System.out.println("Thank you! see you next time.");
                break;
            }
        } while (true);
    }

    /**
     * Starts a new order and shows the order menu.
     * The user can add items, add a customer name, checkout, or cancel the order.
     */
    private void orderUiInit() {
        OrderUIComponent orderUIComponent = new OrderUIComponent(scanner);
        currentOrder = new Order();

        boolean needsInput = true;

        do {
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
        } while (needsInput);
    }

    /**
     * Starts the checkout screen.
     * If the order is empty, it tells the user that at least one item is needed.
     *
     * @return false if checkout is finished, true if the user should return to the order menu.
     */
    private boolean checkOutUiInit() {
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

    /**
     * Starts the chips selection screen and adds the selected chips to the current order.
     */
    private void chipsUiInit() {
        ChipsUIComponent chipsUIComponent = new ChipsUIComponent(scanner);
        chipsUIComponent.displayComponent();
        currentOrder.addItem(chipsUIComponent.getItem());
    }

    /**
     * Starts the drink selection screen and adds the selected drink to the current order.
     */
    private void drinkUiInit() {
        DrinkUIComponent drinkUi = new DrinkUIComponent(scanner);
        drinkUi.displayComponent();
        currentOrder.addItem(drinkUi.getItem());
    }

    /**
     * Starts the signature sandwich selection screen
     * and adds the selected signature sandwich to the current order.
     */
    private void signatureSandwichUiInit() {
        SignatureSandwichUIComponent signatureSandwichUIComponent = new SignatureSandwichUIComponent(scanner);
        signatureSandwichUIComponent.displayComponent();
        currentOrder.addItem(signatureSandwichUIComponent.getSignatureSandwich());
    }

    /**
     * Starts the sandwich selection screen and adds the created sandwich to the current order.
     */
    private void sandwichUiInit() {
        SandwichUIComponent sandwichUI = new SandwichUIComponent(scanner);
        sandwichUI.displayComponent();
        currentOrder.addItem(sandwichUI.getItem());
    }

    /**
     * Asks the user for the customer name and saves it in the current order.
     */
    private void addCustomerNameToOrder() {
        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine();
        currentOrder.setCustomerName(customerName);
    }
}