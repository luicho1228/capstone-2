package com.plurasight;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private String customerName;
    private int orderNumber;
    private LocalDate dateCreated;
    List<Item>items;

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
    public double getTotalCost(){
        //todo return total cost of tis order
        return 0;
    }
    public int getItemQuantity(){
        return this.items.size();
    }

}
