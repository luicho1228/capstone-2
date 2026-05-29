package com.plurasight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final LocalDateTime dateTimeCreated;
    private static final DateTimeFormatter ORDER_DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    private static final DateTimeFormatter RECEIPT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
    private final List<Item> items;

    private String customerName = "No-Name";
    private int orderNumber;

    public Order() {
        items = new ArrayList<>();
        dateTimeCreated = LocalDateTime.now();
        generateOrderNumber();
    }

    public void addItem(Item newItem) {
        this.items.add(newItem);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public void replaceItem(Item itemToReplace, Item newItem) {
        int index = items.indexOf(itemToReplace);
        if (index != -1) {
            items.set(index, newItem);
        }
    }

    public String getOrderDetails() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order number: ").append(this.orderNumber).append("\n").append(this.customerName).append("\t\t\t\t\t").append("Date created: ").append(ORDER_DATE_FORMATTER.format(this.dateTimeCreated)).append("\n");
        stringBuilder.append("---------------------------------------------------\n");
        System.out.println("Item count: " + getItemCount());
        for (Item item : items) {
            stringBuilder.append(item.getDetails()).append("\n");
        }
        stringBuilder.append("---------------------------------------------------\n");
        String totalString = "Total:";
        stringBuilder.append(totalString).append(UIComponent.formatTaps(totalString.length() - Item.getTaps().length())).append("$").append(String.format("%.2f", getTotalPrice()));
        return stringBuilder.toString();
    }

    public String getReceiptFileName() {
        return RECEIPT_DATE_FORMATTER.format(dateTimeCreated) + ".txt";
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public double getTotalPrice() {
        double totalValue = 0.0;
        for (Item item : items) {
            totalValue += item.getValue();
        }
        return totalValue;
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    private void generateOrderNumber() {
        orderNumber = (int) (Math.random() * 100000);
    }

    public int getItemCount() {
        return this.items.size();
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
