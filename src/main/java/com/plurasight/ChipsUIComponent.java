package com.plurasight;

import com.plurasight.Enums.ChipsType;
import com.plurasight.Enums.Size;

import java.util.Scanner;

public class ChipsUIComponent extends UIComponent implements Displayable {


    private Item item;
    public ChipsUIComponent(Scanner scanner) {
        super(scanner);
    }

    private Item addChips(){
        System.out.println("ADD CHIPS");
        int count = 1;
        for (ChipsType chipsType: ChipsType.values()){
            System.out.println(count+". " + chipsType);
            count++;
        }
        int userInput = UIComponent.getUserInput(scanner);
        ChipsType chipsType = null;
        switch (userInput){
            case 1:
                chipsType =ChipsType.DORITOS_NACHO_CHEESE;
                break;
            case 2:
                chipsType =ChipsType.CLASSIC_POTATO_CHIPS;

                break;
            case 3:
                chipsType =ChipsType.BBQ_POTATO_CHIPS;
                break;
            case 4:
                chipsType =ChipsType.KETTLE_POTATO_CHIPS;
                break;
            case 5:
                chipsType =ChipsType.CHEETOS_JALAPENOS_CHEESE;
                break;
            case 6:
                chipsType =ChipsType.RUFFLES_CHEDDAR_SOURCREAM;
                break;
            default:
        }
        return new Chips(chipsType);
    }

    @Override
    public void displayComponent() {
        item = addChips();
    }
    public Item getItem(){
        return this.item;
    }
}
