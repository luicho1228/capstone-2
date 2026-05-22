package com.plurasight;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Sandwich sandwish = new Sandwich(Bread.WHITE,Size.SMALL,Meat.CHICKEN,false,Cheese.CHEDDAR,false);
        Order order = new Order(sandwish);
        Drinks drinks = new Drinks(Size.LARGE,Flavors.GRAPE);
        order.addItem(drinks);
        System.out.println(order.getItemQuantity() + " total value is: $" + order.getTotalValue());
    }
}