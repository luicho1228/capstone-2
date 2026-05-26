package com.plurasight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OrderFileManager {
    private static String receiptFilePath = "src/main/resources";
    private static String receiptName;
    private OrderFileManager(){}

    public static void saveOrder(Order order){
        receiptFilePath += order.getReceiptName();
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(receiptFilePath));
            bufferedWriter.write(order.getOrderDetails());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
