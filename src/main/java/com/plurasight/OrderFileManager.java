package com.plurasight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OrderFileManager {
    private final static String receiptFilePath = "src/main/resources/Receipts/";
    private OrderFileManager(){}

    public static void saveOrder(Order order){
        String receipt = receiptFilePath + order.getReceiptFileName();
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(receipt));
            bufferedWriter.write(order.getOrderDetails());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
