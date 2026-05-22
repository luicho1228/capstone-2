package com.plurasight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OrderFileManager {
    private static final String RECEIPT_FILE_PATH = "src/main/resources";
    private OrderFileManager(){}

    public static void saveOrder(Order order){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(RECEIPT_FILE_PATH));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
