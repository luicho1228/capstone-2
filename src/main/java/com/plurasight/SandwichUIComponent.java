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
     * @param userInput The initial integer input state from the user.
     * @author Luis Vasquez
     */
    public SandwichUIComponent(Scanner scanner, int userInput) {
        super(scanner, userInput);
    }

    /**
     * This method prompts the user if they want extra meat.
     *
     * @param meat This parameter is used to detect if the user selected a meat already; if not, it doesn't run.
     * @return true if the user selected yes, false if the user selected no.
     * @author Luis Vasquez
     */
    private boolean askForExtraMeat(Meat meat) {
        boolean extraMeat = false;
        if (!(meat == Meat.NO_MEAT)) {
            boolean isRunning = true;
            do {
                System.out.println("\tDo you want extra Meat?\n1.Yes\t\t\t2.No");
                userInput = scanner.nextInt();
                scanner.nextLine();
                if (userInput == 1) {
                    extraMeat = true;
                    isRunning = false;
                } else if (userInput == 2) {
                    isRunning = false;
                } else {
                    System.out.println("Enter correct value!");
                }
            } while (isRunning);
        }
        return extraMeat;
    }

    /**
     * This method prompts the user if they want extra cheese.
     *
     * @param cheese This parameter is used to detect if the user selected a cheese already; if not, it doesn't run.
     * @return true if the user selected yes, false if the user selected no.
     * @author Luis Vasquez
     */
    private boolean askForExtraCheese(Cheese cheese) {

        boolean extraCheese = false;
        if (!(cheese == Cheese.NO_CHEESE)) {
            boolean isRunning = true;
            do {
                System.out.println("\tDo you want extra Cheese?\n1.Yes\t\t\t2.No");
                userInput = scanner.nextInt();
                scanner.nextLine();
                if (userInput == 1) {
                    extraCheese = true;
                    isRunning = false;
                } else if (userInput == 2) {
                    isRunning = false;
                } else {
                    System.out.println("Enter correct value!");
                }
            } while (isRunning);
        }
        return extraCheese;
    }

    /**
     * This method handles the creation of the sandwich based on user inputs.
     *
     * @return A new Sandwich object matching the user's choices.
     * @author Luis Vasquez
     */
    private Item addSandwich() {
        System.out.println("ADD SANDWICH");
        System.out.println("\tSelect Bread:");
        Bread bread = addBread();
        Size size = chooseSize();
        Meat meat = addMeat();
        boolean extraMeat = askForExtraMeat(meat);
        Cheese cheese = addCheese();
        boolean extraCheese = askForExtraCheese(cheese);
        HashSet<Topping> toppings = addToppings();
        HashSet<Sauce> sauces = addSauces();
        return new Sandwich(bread, size, meat, extraMeat, cheese, extraCheese, toppings, sauces);

    }

    /**
     * This method prompts the user to select from a list of sauces to add to the sandwich item.
     *
     * @return A HashSet of all sauces selected by the user.
     * @author Luis Vasquez
     */
    private HashSet<Sauce> addSauces() {
        List<Sauce> sauces = new ArrayList<>(Arrays.asList(Sauce.values()));
        HashSet<Sauce> selectedSauces = new HashSet<>();
        System.out.println("ADD SAUCES");
        while (!sauces.isEmpty()) {
            System.out.println("Select 0 to stop adding sauces.");
            System.out.println("Select Sauces: ");
            System.out.println("0. Done adding sauces");
            int count = 1;
            for (Sauce sauce : sauces) {
                System.out.println(count + ". " + sauce);
                count++;
            }
            userInput = scanner.nextInt();
            scanner.nextLine();
            if (userInput > 0 && userInput <= sauces.size()) {
                Sauce chosenSauce = sauces.remove(userInput - 1);
                selectedSauces.add(chosenSauce);
            } else if (userInput == 0) {
                break;
            } else {
                System.out.println("Invalid selection");
            }
        }
        return selectedSauces;
    }

    /**
     * This method prompts the user to select from a list of toppings to add to the sandwich item.
     *
     * @return A HashSet of all toppings selected by the user.
     * @author Luis Vasquez
     */
    private HashSet<Topping> addToppings() {
        List<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.values()));
        HashSet<Topping> selectedToppings = new HashSet<>();
        System.out.println("ADD TOPPINGS");
        while (!toppings.isEmpty()) {
            System.out.println("Select 0 to stop adding toppings.");
            System.out.println("Select Toppings: ");
            System.out.println("0. Done adding sauces");
            int count = 1;
            for (Topping topping : toppings) {
                System.out.println(count + ". " + topping);
                count++;
            }
            userInput = scanner.nextInt();
            scanner.nextLine();
            if (userInput > 0 && userInput <= toppings.size()) {
                Topping chosenTopping = toppings.remove(userInput - 1);
                selectedToppings.add(chosenTopping);
            } else if (userInput == 0) {
                break;
            } else {
                System.out.println("Invalid selection");
            }
        }
        return selectedToppings;

    }

    /**
     * This method prompts the user to select from a list of cheese selections.
     * The user can only choose one type of cheese.
     * This cheese is added to the sandwich in the receipt.
     *
     * @return A Cheese type.
     * @author Luis Vasquez
     */
    private Cheese addCheese() {
        Cheese[] cheeseList = Cheese.values();
        Cheese cheese = null;
        boolean isRunning = true;
        do {
            int count = 1;
            for (Cheese c : cheeseList) {
                System.out.println(count + ". " + c);
                count++;
            }
            userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput) {
                case 1:
                    cheese = Cheese.AMERICAN;
                    isRunning = false;
                    break;
                case 2:
                    cheese = Cheese.PROVOLONE;
                    isRunning = false;
                    break;
                case 3:
                    cheese = Cheese.CHEDDAR;
                    isRunning = false;
                    break;
                case 4:
                    cheese = Cheese.SWISS;
                    isRunning = false;
                    break;
                case 5:
                    cheese = Cheese.NO_CHEESE;
                    isRunning = false;
                default:
            }
        } while (isRunning);
        return cheese;
    }

    /**
     * Prompts the user to select a sandwich size from the available options.
     *
     * @return The selected Size enum value (SMALL, MEDIUM, or LARGE).
     * @author Luis Vasquez
     */
    private Size chooseSize() {
        Size[] sizes = Size.values();
        Size size = null;
        boolean isRunning = true;
        do {
            int count = 1;
            for (Size s : sizes) {
                System.out.println(count + ". " + s);
                count++;
            }
            userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput) {
                case 1:
                    size = Size.SMALL;
                    isRunning = false;
                    break;
                case 2:
                    size = Size.MEDIUM;
                    isRunning = false;
                    break;
                case 3:
                    size = Size.LARGE;
                    isRunning = false;
                    break;
                default:
            }
        } while (isRunning);


        return size;
    }

    /**
     * Prompts the user to select a type of bread for the sandwich.
     *
     * @return The selected Bread enum type.
     * @author Luis Vasquez
     */
    private Bread addBread() {
        Bread[] breadList = Bread.values();
        Bread bread = null;
        boolean isRunning = true;
        do {
            int count = 1;
            for (Bread b : breadList) {
                System.out.println(count + ". " + b);
                count++;
            }
            userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput) {
                case 1:
                    bread = Bread.WHITE;
                    isRunning = false;
                    break;
                case 2:
                    bread = Bread.WHEAT;
                    isRunning = false;
                    break;
                case 3:
                    bread = Bread.RYE;
                    isRunning = false;
                    break;
                case 4:
                    bread = Bread.WRAP;
                    isRunning = false;
                    break;
                default:
            }
        } while (isRunning);

        return bread;
    }

    /**
     * Prompts the user to select a type of meat to add to the sandwich.
     *
     * @return The selected Meat enum type.
     * @author Luis Vasquez
     */
    private Meat addMeat() {
        Meat[] meats = Meat.values();
        Meat meat = null;
        boolean isRunning = true;
        do {
            int count = 1;
            for (Meat m : meats) {
                System.out.println(count + ". " + m);
                count++;
            }
            userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput) {
                case 1:
                    meat = Meat.STEAK;
                    isRunning = false;
                    break;
                case 2:
                    meat = Meat.HAM;
                    isRunning = false;
                    break;
                case 3:
                    meat = Meat.SALAMI;
                    isRunning = false;
                    break;
                case 4:
                    meat = Meat.ROAST_BEEF;
                    isRunning = false;
                    break;
                case 5:
                    meat = Meat.CHICKEN;
                    isRunning = false;
                    break;
                case 6:
                    meat = Meat.BACON;
                    isRunning = false;
                    break;
                case 7:
                    meat = Meat.NO_MEAT;
                    isRunning = false;
                    break;
                default:
                    System.out.println("Select from the option provided");
            }
        } while (isRunning);
        return meat;
    }

    @Override
    public void displayComponent() {
        item = addSandwich();
    }

    public Item getItem() {
        return this.item;
    }
}
