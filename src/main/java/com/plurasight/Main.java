package com.plurasight;

import com.plurasight.Enums.*;
public class Main {
    public static void main(String[] args) {
//        Sandwich sandwish = new Sandwich(Bread.WHITE, Size.SMALL, Meat.CHICKEN,false, Cheese.CHEDDAR,true);
//        Order order = new Order(sandwish);
//        Drinks drinks = new Drinks(Size.LARGE, Flavors.GRAPE);
//        order.addItem(drinks);
////        System.out.println(order.getItemQuantity() + " total value is: $" + order.getTotalValue());
////        System.out.println(sandwish.getDetails());
//        System.out.println(order.getOrderDetails());
        UIManager ui = new UIManager();
        ui.displayMainMenu();
    }
}