package com.plurasight.UserInterface;

import com.plurasight.Models.Chips;
import com.plurasight.Models.Displayable;
import com.plurasight.Enums.ChipsType;
import com.plurasight.Models.Item;

import java.util.Scanner;

/**
 * This class handles the chips selection screen.
 * It lets the user choose a chips type and creates a Chips item.
 */
public class ChipsUIComponent extends UIComponent implements Displayable {

    private Item item;

    /**
     * Creates a ChipsUIComponent.
     *
     * @param scanner The Scanner used to read user input.
     */
    public ChipsUIComponent(Scanner scanner) {
        super(scanner);
    }

    /**
     * Lets the user select a chips type and creates a Chips item.
     *
     * @return A Chips item with the selected chips type.
     */
    private Item selectChips() {
        return new Chips(selectEnumOption(ChipsType.values(), "Select Chips type"));
    }

    /**
     * Displays the chips selection screen.
     * After the user selects a chips type, the created item is saved.
     */
    @Override
    public void displayComponent() {
        displayBorders();
        System.out.println("   ____ _     _             __  __                  \n" +
                "  / ___| |__ (_)_ __  ___  |  \\/  | ___ _ __  _   _ \n" +
                " | |   | '_ \\| | '_ \\/ __| | |\\/| |/ _ \\ '_ \\| | | |\n" +
                " | |___| | | | | |_) \\__ \\ | |  | |  __/ | | | |_| |\n" +
                "  \\____|_| |_|_| .__/|___/ |_|  |_|\\___|_| |_|\\__,_|\n" +
                "               |_|                                  ");
        item = selectChips();
    }

    public Item getItem() {
        return this.item;
    }
}
