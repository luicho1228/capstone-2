package com.plurasight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private static final DateTimeFormatter ORDER_DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    private static final DateTimeFormatter RECEIPT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
    private final LocalDateTime createdAt;
    private final List<Item> items;

    private String customerName = "No-Name";
    private final int orderNumber;

    public Order() {
        items = new ArrayList<>();
        createdAt = LocalDateTime.now();
        orderNumber = generateOrderNumber();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void replaceItem(Item itemToReplace, Item newItem) {
        int index = items.indexOf(itemToReplace);
        if (index != -1) {
            items.set(index, newItem);
        }
    }

    public String getOrderDetails() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Order number: ").append(orderNumber).append("\n").append(customerName).append("\t\t\t\t\t").append("Date created: ").append(ORDER_DATE_FORMATTER.format(createdAt)).append("\n");
        orderDetails.append("---------------------------------------------------\n");
        orderDetails.append("Item count: ").append(getItemCount());
        for (Item item : items) {
            orderDetails.append(item.getDetails()).append("\n");
        }
        orderDetails.append("---------------------------------------------------\n");
        String totalLabel = "Total:";
        orderDetails.append(totalLabel).append(UIComponent.formatTaps(totalLabel.length() - Item.getTaps().length())).append("$").append(String.format("%.2f", getTotalPrice()));
        return orderDetails.toString();
    }

    public String getReceiptFileName() {
        return RECEIPT_DATE_FORMATTER.format(createdAt) + ".txt";
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Item item : items) {
            totalPrice += item.getValue();
        }
        return totalPrice;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getItemCount() {
        return items.size();
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    private int generateOrderNumber() {
        return (int) (Math.random() * 100000);
    }

}
