package com.plurasight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String customerName;
    private int orderNumber;
    private LocalDateTime dateTimeCreated;
    List<Item>items;

    public Order(Item item){
        items = new ArrayList<>();
        items.add(item);
        dateTimeCreated = LocalDateTime.now();
        generateOrderNumber();
    }

    private void generateOrderNumber(){
        orderNumber = (int)(Math.random()*100000);
    }
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    public void addItem(Item newItem){
        this.items.add(newItem);
    }
    public void removeItem(Item item){
        this.items.remove(item);
    }
    public String getOrderDetails(){
        //todo return detail list of items and their toppings woth price
        return String.format("Order number: %04d \ndate created: %s",orderNumber,dateTimeCreated.toString());
    }
    public double getTotalValue(){
        //todo return total cost of tis order
        double totalValue = 0.0;
        for (Item item: items){
            totalValue += item.getValue();
        }
        return totalValue;
    }
    public int getItemQuantity(){
        return this.items.size();
    }

}
