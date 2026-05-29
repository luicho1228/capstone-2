package com.plurasight;

import com.plurasight.Enums.ChipsType;

import java.util.Scanner;

public class ChipsUIComponent extends UIComponent implements Displayable {


    private Item item;
    public ChipsUIComponent(Scanner scanner) {
        super(scanner);
    }

    private Item selectChips(){
      return new Chips(selectEnumOptions(ChipsType.values(),"Select Chips type"));
    }

    @Override
    public void displayComponent() {
        item = selectChips();
    }
    public Item getItem(){
        return this.item;
    }
}
