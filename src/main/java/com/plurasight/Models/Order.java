package com.plurasight.Models;

import com.plurasight.UserInterface.UIComponent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an order in the POS system.
 * An order stores the customer name, order number, date created,
 * and the list of items added by the user.
 */
public class Order {

    private static final DateTimeFormatter ORDER_DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    private static final DateTimeFormatter RECEIPT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    private final LocalDateTime createdAt;
    private final List<Item> items;
    private final int orderNumber;
    private String customerName = "No-Name";

    /**
     * Creates a new Order object.
     * It also sets the creation date, creates the item list,
     * and generates an order number.
     */
    public Order() {
        items = new ArrayList<>();
        createdAt = LocalDateTime.now();
        orderNumber = generateOrderNumber();
    }

    /**
     * Adds an item to the order.
     *
     * @param item The item that will be added to the order.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Removes an item from the order.
     *
     * @param item The item that will be removed from the order.
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Gets the full order details for the receipt.
     * This includes the order number, customer name, date,
     * item count, item details, and total price.
     *
     * @return A formatted String with the order details.
     */
    public String getOrderDetails() {
        StringBuilder orderDetails = new StringBuilder();

        orderDetails.append("Order number: ").append(orderNumber).append("\n").append(customerName).append("\t\t\t\t\t").append("Date created: ").append(ORDER_DATE_FORMATTER.format(createdAt)).append("\n");

        orderDetails.append("---------------------------------------------------\n");
        orderDetails.append("Item count: ").append(getItemCount()).append("\n");

        for (Item item : items) {
            orderDetails.append(item.getDetails()).append("\n");
        }

        orderDetails.append("---------------------------------------------------\n");

        String totalLabel = "Total:";

        orderDetails.append(totalLabel).append(UIComponent.formatPadding(totalLabel.length() - Item.getTaps().length())).append("$").append(String.format("%.2f", getTotalPrice()));

        return orderDetails.toString();
    }

    /**
     * Gets the file name used to save the receipt.
     *
     * @return The receipt file name.
     */
    public String getReceiptFileName() {
        return RECEIPT_DATE_FORMATTER.format(createdAt) + ".txt";
    }

    /**
     * Gets a copy of the items in the order.
     *
     * @return A list with the items in the order.
     */
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Calculates the total price of the order.
     *
     * @return The total price of all items in the order.
     */
    public double getTotalPrice() {
        double totalPrice = 0.0;

        for (Item item : items) {
            totalPrice += item.getValue();
        }

        return totalPrice;
    }

    /**
     * Checks if the order has no items.
     *
     * @return true if the order is empty, false if it has items.
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Gets the number of items in the order.
     *
     * @return The amount of items in the order.
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * Sets the customer's name for the order.
     *
     * @param customerName The name of the customer.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Generates a random order number.
     *
     * @return A random order number.
     */
    private int generateOrderNumber() {
        return (int) (Math.random() * 100000);
    }
}
