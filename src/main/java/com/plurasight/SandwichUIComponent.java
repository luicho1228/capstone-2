package com.plurasight;

import com.plurasight.Enums.*;

import java.util.*;

/**
 * This class represents a User Interface component. It handles the user inputs
 * to create a sandwich object.
 * It also displays prompts and can return the created sandwich with the getItem() method.
 *
 * @author Luis Vasquez
 */
public class SandwichUIComponent extends UIComponent implements Displayable {

    /**
     * The sandwich item being configured or retrieved by this component.
     */
    private Item item;

    /**
     * Constructs a new SandwichUIComponent with the specified scanner and initial user input.
     *
     * @param scanner The Scanner object used to capture user keyboard input.
     * @author Luis Vasquez
     */
    public SandwichUIComponent(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void displayComponent() {
        item = buildSandwich();
    }

    public Item getItem() {
        return this.item;
    }

    /**
     * This method handles the creation of the sandwich based on user inputs.
     *
     * @return A new Sandwich object matching the user's choices.
     * @author Luis Vasquez
     */
    private Item buildSandwich() {
        System.out.println("ADD SANDWICH");
        Bread bread = selectBread();
        Size size = selectSize();
        boolean toasted = isToasted();
        Meat meat = selectMeat();
        boolean extraMeat = askForExtraMeat(meat);
        Cheese cheese = selectCheese();
        boolean extraCheese = askForExtraCheese(cheese);
        HashSet<Topping> toppings = selectToppings();
        HashSet<Sauce> sauces = selectSauces();
        return new Sandwich(bread, size,toasted,meat, extraMeat, cheese, extraCheese, toppings, sauces);

    }

    private boolean isToasted(){
      return getBooleanFromPrompt("Do you want your sandwich toasted? " +
              "\n1.yes \t\t\t\t 2.No");
    }

    /**
     * Prompts the user to select a sandwich size from the available options.
     *
     * @return The selected Size enum value (SMALL, MEDIUM, or LARGE).
     * @author Luis Vasquez
     */
    private Size selectSize() {
       return selectEnumOption(Size.values(),"Select Bread Size:");
    }

    /**
     * Prompts the user to select a type of bread for the sandwich.
     *
     * @return The selected Bread enum type.
     * @author Luis Vasquez
     */
    private Bread selectBread() {
       return selectEnumOption(Bread.values(),"Select Bread:");
    }

    /**
     * Prompts the user to select a type of meat to add to the sandwich.
     *
     * @return The selected Meat enum type.
     * @author Luis Vasquez
     */
    private Meat selectMeat() {
       return selectEnumOption(Meat.values(),"Select Meat:");
    }

    /**
     * This method prompts the user if they want extra selectedMeat.
     *
     * @param selectedMeat This parameter is used to detect if the user selected a selectedMeat already; if not, it doesn't run.
     * @return true if the user selected yes, false if the user selected no.
     * @author Luis Vasquez
     */
    private boolean askForExtraMeat(Meat selectedMeat) {
        if (!(selectedMeat == Meat.NO_MEAT)) {
            return getBooleanFromPrompt("\tDo you want extra Meat?\n1.Yes\t\t\t2.No");
        }
        return false;
    }

    /**
     * This method prompts the user to select from a list of cheese selections.
     * The user can only choose one type of cheese.
     * This cheese is added to the sandwich in the receipt.
     *
     * @return A Cheese type.
     * @author Luis Vasquez
     */
    private Cheese selectCheese() {
       return selectEnumOption(Cheese.values(),"Select Cheese:");
    }

    /**
     * This method prompts the user if they want extra selectedCheese.
     *
     * @param selectedCheese This parameter is used to detect if the user selected a selectedCheese already; if not, it doesn't run.
     * @return true if the user selected yes, false if the user selected no.
     * @author Luis Vasquez
     */
    private boolean askForExtraCheese(Cheese selectedCheese) {
        if (!(selectedCheese == Cheese.NO_CHEESE)) {
            return getBooleanFromPrompt("\tDo you want extra Cheese?\n1.Yes\t\t\t2.No");
        }
        return false;
    }

    /**
     * This method prompts the user to select from a list of toppings to add to the sandwich item.
     *
     * @return A HashSet of all toppings selected by the user.
     * @author Luis Vasquez
     */
    private HashSet<Topping> selectToppings() {
       return selectMultipleEnumFromOptions(Topping.values(),"Add Toppings");
    }

    /**
     * This method prompts the user to select from a list of sauces to add to the sandwich item.
     *
     * @return A HashSet of all sauces selected by the user.
     * @author Luis Vasquez
     */
    private HashSet<Sauce> selectSauces() {
       return selectMultipleEnumFromOptions(Sauce.values(),"Add Sauces");
    }
}
