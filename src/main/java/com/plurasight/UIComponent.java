package com.plurasight;

import java.util.*;

public class UIComponent{

    protected Scanner scanner;


    public UIComponent(Scanner scanner){
        this.scanner = scanner;
    }

    public static String formatPadding(int stringLength){
        int receiptDetailPadding = 50;
        int tapLength = Math.abs(receiptDetailPadding - stringLength);
        StringBuilder tabs = new StringBuilder();
        for (int i = 0; i <= tapLength;i++){
            tabs.append(" ");
        }
        return tabs.toString();
    }

    protected int getUserInputFromMenu(String[] menu , boolean indexZeroAllowed){
        do {
            System.out.println("Select from the options:");
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + ". " + menu[i]);
            }
            try {
                int userInput = getUserInput(scanner);
                if (!indexZeroAllowed) {
                    if (userInput > 0 && userInput <= menu.length) {
                        return userInput;
                    } else {
                        System.err.println("Select an option provided");
                    }
                }else {
                    if (userInput >= 0 && userInput <= menu.length) {
                        return userInput;
                    } else {
                        System.err.println("Select an option provided");
                    }
                }
            } catch (InputMismatchException ime) {
                System.err.println("Error: Select a numeric value from the options provided");
                scanner.nextLine();
            }
        }while (true);
    }

    protected boolean getBooleanFromPrompt(String prompt){
        do {
            System.out.println(prompt);
            try {
                int userInput = getUserInput(scanner);
                if (userInput == 1) {
                    return true;
                } else if (userInput == 2) {
                    return false;
                } else {
                    System.err.println("Enter correct value!");
                }
            }catch (InputMismatchException ime){
                System.err.println("Error: Select a numeric value from the options provided");
                scanner.nextLine();
            }
        } while (true);

    }

    protected <T extends Enum<T>> T selectEnumOptions(T[] options, String prompt){
        boolean needsInput = true;
        T selectedEnum = null;
        while (needsInput){
            System.out.println(prompt);
            for (int i = 0; i< options.length; i++){
                System.out.println((i+1) + ". " + options[i]);
            }
            try {
            int userInput = getUserInput(scanner);
            if (userInput > 0 && userInput <= options.length){
                selectedEnum = options[userInput -1];
                needsInput =false;
            }else {
                System.err.println("Invalid Selection. Please choose one of the option provided.");
            }
            }catch (InputMismatchException ime){
                System.err.println("Error: Select a numeric value from the options provided");
                scanner.nextLine();
            }
        }
        return selectedEnum;
    }

    protected <T extends Enum<T>> HashSet<T> selectMultipleEnumFromOptions(T[] options,String prompt) {
        List<T> enumOptions = new ArrayList<>(Arrays.asList(options));
        HashSet<T> selectedEnums = new HashSet<>();

        System.out.println(prompt);

        while (!enumOptions.isEmpty()) {
            System.out.println("Select 0 to stop adding");
            System.out.println("Select from options: ");

            int count = 1;
            for (T e : enumOptions) {
                System.out.println(count + ". " + e);
                count++;
            }

            System.out.println("0. Done adding");

            try {
                int userInput = getUserInput(scanner);
                if (userInput > 0 && userInput <= enumOptions.size()) {
                    T chosenEnum = enumOptions.remove(userInput - 1);
                    selectedEnums.add(chosenEnum);
                } else if (userInput == 0) {
                    break;
                } else {
                    System.err.println("Invalid selection, try again select from the options provided");
                }
            }catch (InputMismatchException ime){
                System.err.println("Error: Select a numeric value from the options provided");
                scanner.nextLine();
            }
        }
        return selectedEnums;
    }

    public static int getUserInput(Scanner scanner){
        int userInput = scanner.nextInt();
        scanner.nextLine();
        return userInput;
    }
}