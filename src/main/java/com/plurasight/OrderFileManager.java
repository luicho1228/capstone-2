package com.plurasight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class handles saving orders to a receipt file.
 * It uses the order details and writes them into a text file.
 */
public class OrderFileManager {

    private final static String RECEIPT_FILE_PATH = "src/main/resources/Receipts/";

    /**
     * Private constructor so this class cannot be created as an object.
     * This class only uses static methods.
     */
    private OrderFileManager() {
    }

    /**
     * Saves the order as a receipt text file.
     * The file name is created from the order receipt file name.
     *
     * @param order The order that will be saved.
     */
    public static void saveOrder(Order order) {
        String receipt = RECEIPT_FILE_PATH + order.getReceiptFileName();

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(receipt));
            bufferedWriter.write(order.getOrderDetails());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
