package com.plurasight.UserInterface;

import java.util.*;

/**
 * This class is the parent class for the UI components.
 * It stores the Scanner and has helper methods that other UI classes can use.
 * These helpers are used for menus, enum selections, yes/no questions, and item selection.
 */
public class UIComponent {

    protected final static int BORDER_LENGTH = 100;
    private final static String TAPS = "\n\t* ";
    private final static String SUB_TAPS = "\n\t\t";
    protected Scanner scanner;

    /**
     * Creates a UIComponent.
     *
     * @param scanner The Scanner used to read user input.
     */
    public UIComponent(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Creates spaces used to line up receipt details.
     *
     * @param stringLength The length of the text that needs spacing.
     * @return A String with spaces used for formatting.
     */
    public static String formatPadding(int stringLength) {
        int receiptDetailPadding = 50;
        int tapLength = Math.abs(receiptDetailPadding - stringLength);
        StringBuilder tabs = new StringBuilder();

        for (int i = 0; i <= tapLength; i++) {
            tabs.append(" ");
        }

        return tabs.toString();
    }

    /**
     * Reads a number from the user.
     * It also clears the rest of the line after reading the number.
     *
     * @param scanner The Scanner used to read input.
     * @return The number entered by the user.
     */
    public static int getUserInput(Scanner scanner) {
        System.out.println("Enter input:");
        int userInput = scanner.nextInt();
        scanner.nextLine();
        return userInput;
    }

    public static String getBorder(){
        System.err.println("border is printing");
        String border = "";
        for (int i = 0; i < BORDER_LENGTH; i++) {
            border += "-";
        }
        border += "\n";
        return border;
    }

    public static void displayBorders() {
        for (int i = 0; i < BORDER_LENGTH; i++) {
            System.out.print("-");
        }
        System.out.print("\n");
    }

    /**
     * Gets the spacing used for main item details.
     *
     * @return A String used to format item details.
     */
    public static String getTaps() {
        return TAPS;
    }

    /**
     * Gets the spacing used for extra item details.
     *
     * @return A String used to format sub-details.
     */
    public static String getSubTaps() {
        return SUB_TAPS;
    }

    /**
     * Shows a menu and gets the option selected by the user.
     *
     * @param menu The menu options to display.
     * @return The menu option selected by the user.
     */
    protected int getUserInputFromMenu(String[] menu) {
        do {
            System.out.println("Select from the options:");

            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + ". " + menu[i]);
            }

            displayBorders();
            try {
                int userInput = getUserInput(scanner);

                if (userInput > 0 && userInput <= menu.length) {
                    return userInput;
                } else {
                    displayErrorMessage();
                }

            } catch (InputMismatchException ime) {
                displayErrorMessage();
                scanner.nextLine();
            }
        } while (true);
    }

    /**
     * Asks the user a yes or no question.
     * Option 1 returns true and option 2 returns false.
     *
     * @param prompt The question shown to the user.
     * @return true if the user selects 1, false if the user selects 2.
     */
    protected boolean getBooleanFromPrompt(String prompt) {
        do {
            System.out.println(prompt);

            try {
                int userInput = getUserInput(scanner);

                if (userInput == 1) {
                    return true;
                } else if (userInput == 2) {
                    return false;
                } else {
                    displayErrorMessage();
                }
            } catch (InputMismatchException ime) {
                displayErrorMessage();
                scanner.nextLine();
            }
        } while (true);
    }

    /**
     * Shows enum options and lets the user select one.
     * This method can be used with any enum type.
     *
     * @param options The enum options to display.
     * @param prompt  The message shown before the options.
     * @param <T>     The enum type being selected.
     * @return The enum value selected by the user.
     */
    protected <T extends Enum<T>> T selectEnumOption(T[] options, String prompt) {
        boolean needsInput = true;
        T selectedEnum = null;

        while (needsInput) {
            System.out.println(prompt);

            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            displayBorders();
            try {
                int userInput = getUserInput(scanner);

                if (userInput > 0 && userInput <= options.length) {
                    selectedEnum = options[userInput - 1];
                    needsInput = false;
                } else {
                    displayErrorMessage();
                }
            } catch (InputMismatchException ime) {
                displayErrorMessage();
                scanner.nextLine();
            }
        }

        return selectedEnum;
    }

    /**
     * Shows enum options and lets the user select more than one.
     * The user can enter 0 when they are done selecting.
     *
     * @param options The enum options to display.
     * @param prompt  The message shown before the options.
     * @param <T>     The enum type being selected.
     * @return A HashSet with the enum values selected by the user.
     */
    protected <T extends Enum<T>> HashSet<T> selectMultipleEnumFromOptions(T[] options, String prompt) {
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
            displayBorders();

            System.out.println("0. Done adding");

            try {
                int userInput = getUserInput(scanner);

                if (userInput > 0 && userInput <= enumOptions.size()) {
                    T chosenEnum = enumOptions.remove(userInput - 1);
                    selectedEnums.add(chosenEnum);
                } else if (userInput == 0) {
                    break;
                } else {
                    displayErrorMessage();
                }
            } catch (InputMismatchException ime) {
                displayErrorMessage();
                scanner.nextLine();
            }
        }

        return selectedEnums;
    }

    protected void displayErrorMessage() {
        System.err.println("Error Invalid Selection: Select a numeric value from the options provided");
    }
}