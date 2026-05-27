package com.plurasight;

import java.util.Scanner;

public class CheckoutUIComponent extends UIComponent implements Displayable{

    private Order order;
    public CheckoutUIComponent(Scanner scanner, int userInput,Order order) {
        super(scanner, userInput);
        this.order = order;
    }

    private void displayOrder(){
        System.out.println(order.getOrderDetails());

    }

    @Override
    public void displayComponent() {
        displayOrder();

    }
}
