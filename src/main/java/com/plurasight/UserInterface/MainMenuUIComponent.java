package com.plurasight.UserInterface;

import com.plurasight.Models.Displayable;

import java.util.Scanner;

/**
 * This class handles the main menu screen.
 * It lets the user choose between starting a new order or exiting the program.
 */
public class MainMenuUIComponent extends UIComponent implements Displayable {

    private int input = 0;

    /**
     * Creates a MainMenuUIComponent.
     *
     * @param scanner The Scanner used to read user input.
     */
    public MainMenuUIComponent(Scanner scanner) {
        super(scanner);
    }

    /**
     * Gets the option selected by the user.
     *
     * @return The selected menu option.
     */
    public int getAction() {
        return input;
    }

    /**
     * Displays the main menu options and saves the user's choice.
     */
    @Override
    public void displayComponent() {
        displayBorders();
        System.out.println("  __  __       _         __  __                  \n" +
                " |  \\/  | __ _(_)_ __   |  \\/  | ___ _ __  _   _ \n" +
                " | |\\/| |/ _` | | '_ \\  | |\\/| |/ _ \\ '_ \\| | | |\n" +
                " | |  | | (_| | | | | | | |  | |  __/ | | | |_| |\n" +
                " |_|  |_|\\__,_|_|_| |_| |_|  |_|\\___|_| |_|\\__,_|\n" +
                "                                                 ");
        String[] optionsArray = {"New Order", "Exit"};
        input = getUserInputFromMenu(optionsArray);
    }
}
