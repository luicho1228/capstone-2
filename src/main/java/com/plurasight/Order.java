package com.plurasight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String customerName = "No-Name";
    private int orderNumber;
    private LocalDateTime dateTimeCreated;
    private DateTimeFormatter formatter;
    List<Item>items;

    public Order(){
        items = new ArrayList<>();
        dateTimeCreated = LocalDateTime.now();
        generateOrderNumber();
    }

    public String getReceiptName(){
        formatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");
        return formatter.format(dateTimeCreated) + ".txt";
    }

    public List<Item> getItemsInOrder(){
        return this.items;
    }
    public void addItem(Item newItem){
        this.items.add(newItem);
    }
    public void removeItem(Item item){
        this.items.remove(item);
    }
    public String getOrderDetails(){
        //todo return detail list of items and their toppings woth price
        StringBuilder stringBuilder = new StringBuilder();
        formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy-hh:mm");
        stringBuilder.append("Order number: ").append(this.orderNumber).append("\n").append(this.customerName).append("\t\t\t\t\t").append("Date created: ").append(formatter.format(this.dateTimeCreated)).append("\n");
        stringBuilder.append("---------------------------------------------------\n");
        for (Item item: items){
            stringBuilder.append(item.getDetails()).append("\n");
        }
        stringBuilder.append("---------------------------------------------------\n");
        stringBuilder.append("Total:\t\t\t\t\t\t\t\t\t").append(getTotalValue());
        return stringBuilder.toString();
       // formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy-hh:mm");
        //return String.format("%d|%s|%s",orderNumber,customerName,formatter.format(dateTimeCreated));
    }
    public double getTotalValue(){
        //todo return total cost of tis order
        double totalValue = 0.0;
        for (Item item: items){
            totalValue += item.getValue();
        }
        return totalValue;
    }
    public int getItemsQuantity(){
        return this.items.size();
    }
    private void generateOrderNumber(){
        orderNumber = (int)(Math.random()*100000);
    }
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

}
