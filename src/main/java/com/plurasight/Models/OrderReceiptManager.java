package com.plurasight.Models;

import com.plurasight.Enums.Sauce;
import com.plurasight.Enums.Topping;
import com.plurasight.UserInterface.UIComponent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

/**
 * This class handles saving orders to a receipt file.
 * It uses the order details and writes them into a text file.
 */
public class OrderReceiptManager {

    private final static String RECEIPT_FILE_PATH = "src/main/resources/Receipts";

    /**
     * Private constructor so this class cannot be created as an object.
     * This class only uses static methods.
     */
    private OrderReceiptManager() {
    }

    /**
     * Saves the order as a receipt text file.
     * The file name is created from the order receipt file name.
     *
     * @param order The order that will be saved.
     */
    public static void saveOrder(Order order) {
        File receiptsFolder = new File(RECEIPT_FILE_PATH);
        if (!receiptsFolder.exists()) {
            System.out.println("This is running");
            receiptsFolder.mkdirs();
        }
        String receipt = RECEIPT_FILE_PATH + "/" + order.getReceiptFileName();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(receipt));
            bufferedWriter.write(order.getOrderDetails());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatOrderReceipt(Order order) {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Order number: ").append(order.getOrderNumber()).append("\n").append(order.getCustomerName()).append("\t\t\t\t\t").append("Date created: ").append(order.getOrderReceiptDate()).append("\n");

        orderDetails.append(UIComponent.getBorder());

        orderDetails.append("Item count: ").append(order.getItemCount()).append("\n");
        for (Item item : order.getItems()) {
            orderDetails.append(item.getDetails()).append("\n");
        }

        orderDetails.append(UIComponent.getBorder());

        String totalLabel = "Total:";
        orderDetails.append(totalLabel).append(UIComponent.formatPadding(totalLabel.length() - UIComponent.getTaps().length())).append("$").append(String.format("%.2f", order.getTotalPrice()));

        return orderDetails.toString();
    }

    public static String formatItemReceipt(Item item) {
        StringBuilder details = new StringBuilder();

        //Formats item for sandwiches
        if (item instanceof Sandwich sandwich) {
            String extraMeatDisplay = sandwich.isExtraMeat() ? "Extra Meat" : "No-Extra Meat";
            String extraCheeseDisplay = sandwich.isExtraCheese() ? "Extra Cheese" : "No-Extra Cheese";
            String isToastedDisplay = sandwich.isToasted() ? "Toasted" : "Not-Toasted";
            String breadDisplay = isToastedDisplay + " " + sandwich.getBreadSize() + " " + sandwich.getBread() + " Bread";
            String sandwichName = sandwich.getSandwichName();

            //print general sandwich details
            details.append(sandwichName).append(UIComponent.formatPadding(sandwichName.length() - UIComponent.getTaps().length())).append("$").append(sandwich.getValue());
            details.append(UIComponent.getTaps()).append(breadDisplay).append(UIComponent.formatPadding(breadDisplay.length())).append(sandwich.getSizeValue());
            details.append(UIComponent.getTaps()).append(sandwich.getMeat()).append(UIComponent.formatPadding(sandwich.getMeat().toString().length())).append(sandwich.getMeatValue());
            details.append(UIComponent.getSubTaps()).append(extraMeatDisplay).append(UIComponent.formatPadding(("* " + extraMeatDisplay).length())).append(sandwich.getExtraMeatValue());
            details.append(UIComponent.getTaps()).append(sandwich.getCheese()).append(UIComponent.formatPadding(sandwich.getCheese().toString().length())).append(sandwich.getCheeseValue());
            details.append(UIComponent.getSubTaps()).append(extraCheeseDisplay).append(UIComponent.formatPadding(("* " + extraCheeseDisplay).length())).append(sandwich.getExtraCheeseValue());

            //Process toppings
            details.append(UIComponent.getTaps()).append("Toppings");
            Set<Topping> toppings = sandwich.getToppings();
            if (!(toppings.isEmpty())) {
                StringBuilder toppingString = new StringBuilder();
                int toppingCount = 1;
                for (Topping topping : toppings) {
                    if (toppingCount % 3 == 0) {
                        toppingString.append(UIComponent.getSubTaps());
                    }
                    toppingString.append(topping.toString()).append(", ");
                    toppingCount++;
                }
                details.append(UIComponent.getSubTaps()).append(toppingString);
            } else {
                details.append(UIComponent.getSubTaps()).append("No Toppings");
            }
            //process Sauces
            details.append(UIComponent.getTaps()).append("Sauces");
            Set<Sauce> sauces = sandwich.getSauces();
            if (!(sauces.isEmpty())) {
                StringBuilder sauceString = new StringBuilder();
                int sauceCount = 1;
                for (Sauce sauce : sauces) {
                    if (sauceCount % 3 == 0) {
                        sauceString.append(UIComponent.getSubTaps());
                    }
                    sauceString.append(sauce.toString()).append(", ");
                    sauceCount++;
                }
                details.append(UIComponent.getSubTaps()).append(sauceString);
            } else {
                details.append(UIComponent.getSubTaps()).append("No Sauce");
            }
        } else if (item instanceof Drink drink) {
            String drinkName = "Drink";
            details.append(drinkName).append(UIComponent.formatPadding(drinkName.length() - UIComponent.getTaps().length())).append(String.format("$%.2f", drink.getValue())).append(UIComponent.getTaps()).append(drink.getSize().toString());
        } else if (item instanceof Chips chips) {
            String chipsString = "Chips";
            details.append(chipsString).append(UIComponent.formatPadding(chipsString.length() - UIComponent.getTaps().length())).append(String.format("$%.2f", chips.getValue())).append(UIComponent.getTaps()).append(chips.getChipsType());
        }

        return details.toString();
    }

}
