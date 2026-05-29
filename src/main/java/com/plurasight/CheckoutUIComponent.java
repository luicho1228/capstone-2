package com.plurasight;

import com.plurasight.Enums.*;

import java.sql.SQLOutput;
import java.util.*;

public class CheckoutUIComponent extends UIComponent implements Displayable{

    private Order order;
    private boolean isOrdercheckedout = false;
    public CheckoutUIComponent(Scanner scanner,Order order) {
        super(scanner);
        this.order = order;
    }

    private void displayOrder(){
        System.out.println(order.getOrderDetails());

    }

    @Override
    public void displayComponent() {
        System.out.println("CHECKOUT");
        boolean needsInput = true;
        do {
            displayOrder();
            System.out.println("1.Finish check-out" +
                    "\n2.Edit Order" +
                    "\n3.Remove item" +
                    "\n4.Go back");
            int userInput = getUserInput(scanner);
            switch (userInput) {
                case 1:
                    finishCheckout();
                    needsInput = false;
                    break;
                case 2:
                    Item itemToEdit = selectItemToEdit();
                    order.replaceItem(editOrder(itemToEdit),itemToEdit);
                    break;
                case 3:
                    Item itemToRemove = selectItemToEdit();
                    Item removedItem = removeOrder(itemToRemove);
                    System.out.println("This Item has been removed: " + removedItem);
                    break;
                case 4:
                    needsInput = false;
                    break;
            }
        }while (needsInput);
    }

    public Item selectItemToEdit(){
        System.out.println("Select item to edit");
        List<Item> items = order.getItemsInOrder();
        int count = 1;
        for (Item item: items){
            System.out.println("* " + count+ ". " + item.getItemHeader());
            count++;
        }
        Item itemToEdit = null;
        int userInput = getUserInput(scanner);
        if (userInput > 0 && userInput <= items.size()){
            itemToEdit = items.get(userInput - 1);
        }
        return itemToEdit;
    }

    public boolean isOrdercheckeout(){
        return this.isOrdercheckedout;
    }
    private void finishCheckout() {
        OrderFileManager.saveOrder(order);
        System.out.println("Receipt saved!");
        isOrdercheckedout = true;


    }
    private Item removeOrder(Item itemToRemove) {
        order.removeItem(itemToRemove);
        return itemToRemove;
    }
    private Item editOrder(Item itemToEdit) {

        if (itemToEdit instanceof Sandwich){
            Sandwich editedSandwich = (Sandwich) itemToEdit;
            System.out.println("1.Edit Sandwich size" +
                    "\n2.Edit bread choice" +
                    "\n3.Edit meat choice " +
                    "\n4.Edit Cheese choice " +
                    "\n5.Edit Topping selection " +
                    "\n6.Edit Sauce choice");
            int userInput = getUserInput(scanner);
            switch (userInput){
                case 1:
                    editSandwichSize(editedSandwich);
                    break;
                case 2:
                    editBreadChoice(editedSandwich);
                    break;
                case 3:
                    editMeatChoice(editedSandwich);
                    break;
                case 4:
                    editCheeseChoice(editedSandwich);
                    break;
                case 5:
                    editToppings(editedSandwich);
                    break;
                case 6:
                    editSauces(editedSandwich);
                    break;
                default:
            }
            return editedSandwich;
        }else if (itemToEdit instanceof Drinks){
            Drinks editedDrink =(Drinks)itemToEdit;
            System.out.println("Change editedDrink size:" +
                    "\n1.Small" +
                    "\n2.Medium" +
                    "\n3.Large");
            switch (getUserInput(scanner)){
                case 1:
                    editedDrink.setSize(Size.SMALL);
                    break;
                case 2:
                    editedDrink.setSize(Size.MEDIUM);
                    break;
                case 3:
                    editedDrink.setSize(Size.LARGE);
                    break;
                default:
            }
            return editedDrink;
        } else if (itemToEdit instanceof Chips) {
            Chips editedChips = (Chips) itemToEdit;
            ChipsType[] chipsTypes = ChipsType.values();
            System.out.println("select chips type:");
            int count = 1;
            for (ChipsType chips: chipsTypes){
                System.out.println(count + ". " + chips);
                count++;
            }
            int userInput = getUserInput(scanner);
            if (userInput > 0 && userInput <= ChipsType.values().length){
                editedChips.setChipsType(chipsTypes[userInput-1]);
            }
            return editedChips;
        }
        return null;
    }

    private void editSauces(Sandwich sandwichToEdit) {
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
            int userInput = getUserInput(scanner);
            if (userInput > 0 && userInput <= sauces.size()) {
                Sauce chosenSauce = sauces.remove(userInput - 1);
                selectedSauces.add(chosenSauce);
            } else if (userInput == 0) {
                break;
            } else {
                System.out.println("Invalid selection");
            }
        }
        sandwichToEdit.setSauces(selectedSauces);
    }

    private void editToppings(Sandwich sandwichToEdit) {
        List<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.values()));
        HashSet<Topping> selectedToppings = new HashSet<>();
        System.out.println("ADD TOPPINGS");
        while (!toppings.isEmpty()) {
            System.out.println("Select 0 to stop adding toppings.");
            System.out.println("Select Toppings: ");
            System.out.println("0. Done adding toppings");
            int count = 1;
            for (Topping topping : toppings) {
                System.out.println(count + ". " + topping);
                count++;
            }
            int userInput = getUserInput(scanner);
            if (userInput > 0 && userInput <= toppings.size()) {
                Topping chosenTopping = toppings.remove(userInput - 1);
                selectedToppings.add(chosenTopping);
            } else if (userInput == 0) {
                break;
            } else {
                System.out.println("Invalid selection");
            }
        }
        sandwichToEdit.setToppings(selectedToppings);
    }

    private void editCheeseChoice(Sandwich sandwichToEdit) {
        Cheese[] cheeseOptions = Cheese.values();
        boolean needsInput = true;
        do {
            int count = 1;
            for (Cheese c : cheeseOptions) {
                System.out.println(count + ". " + c);
                count++;
            }
            int userInput = getUserInput(scanner);
            if (userInput > 0 && userInput <= cheeseOptions.length) {
                sandwichToEdit.setCheese(cheeseOptions[userInput - 1]);
                needsInput = false;
            } else {
                System.out.println("Select correct value");
            }
        } while (needsInput);
    }

    private void editMeatChoice(Sandwich sandwichToEdit) {
        Meat[] meatOptions = Meat.values();
        boolean needsInput = true;
        do {
            int count = 1;
            for (Meat m : meatOptions) {
                System.out.println(count + ". " + m);
                count++;
            }
            int userInput = getUserInput(scanner);
            if (userInput > 0 && userInput <= meatOptions.length) {
                sandwichToEdit.setMeat(meatOptions[userInput - 1]);
                needsInput = false;
            } else {
                System.out.println("Select from the option provided");
            }
        } while (needsInput);
    }

    private void editBreadChoice(Sandwich sandwichToEdit) {
        Bread[] breadOptions = Bread.values();
        boolean needsInput = true;
        do {
            System.out.println("Select bread options:");
            int count = 1;
            for (Bread b : breadOptions) {
                System.out.println(count + ". " + b);
                count++;
            }
            int userInput = getUserInput(scanner);
            if (userInput > 0 && userInput <= breadOptions.length) {
                sandwichToEdit.setBread(breadOptions[userInput - 1]);
                needsInput = false;
            }
        } while (needsInput);
    }

    private Sandwich editSandwichSize(Sandwich sandwichToEdit) {
        System.out.println("Select Sandwich size: " +
                "\n1. 4\"Bread (Small)" +
                "\n2. 8\"Bread (Medium)" +
                "\n3. 12\"Bread (Large)");
        switch (getUserInput(scanner)){
            case 1:
                sandwichToEdit.setSize(Size.SMALL);
                break;
            case 2:
                sandwichToEdit.setSize(Size.MEDIUM);
                break;
            case 3:
                sandwichToEdit.setSize(Size.LARGE);
                break;
            default:
        }
        return sandwichToEdit;
    }


}
