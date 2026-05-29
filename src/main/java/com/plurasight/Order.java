package com.plurasight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String customerName = "No-Name";
    private int orderNumber;
    private final LocalDateTime dateTimeCreated;
    private final DateTimeFormatter receiptDateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
    private final ArrayList<Item>items;

    public Order(){
        items = new ArrayList<>();
        dateTimeCreated = LocalDateTime.now();
        generateOrderNumber();
    }

    public String getReceiptName(){
        return receiptDateFormatter.format(dateTimeCreated) + ".txt";
    }
    public void replaceItem(Item newItem,Item itemToReplace){
        for (Item item:items){
            if (item.equals(itemToReplace)){
                int index = items.indexOf(item);
                items.set(index,newItem);
            }
        }
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
        StringBuilder stringBuilder = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy-HH:mm");
        stringBuilder.append("Order number: ").append(this.orderNumber).append("\n").append(this.customerName).append("\t\t\t\t\t").append("Date created: ").append(formatter.format(this.dateTimeCreated)).append("\n");
        stringBuilder.append("---------------------------------------------------\n");
        for (Item item: items){
            stringBuilder.append(item.getDetails()).append("\n");
        }
        stringBuilder.append("---------------------------------------------------\n");
        String totalString = "Total:";
        stringBuilder.append(totalString).append(UIComponent.formatTaps(totalString.length() - Item.getTaps().length())).append("$").append(getTotalValue());
        return stringBuilder.toString();
    }
    public double getTotalValue(){
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
