package com.plurasight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String customerName;
    private int orderNumber;
    private LocalDate dateCreated;
    List<Item>items;

    public Order(Item item){
        items = new ArrayList<>();
        items.add(item);
    }

    public void addItem(Item newItem){
        this.items.add(newItem);
    }
    public void removeItem(Item item){
        this.items.remove(item);
    }
    public String getOrderDetails(){
        //todo return detail list of items and their toppings woth price
        return"";
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
