package com.plurasight;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Sandwich sandwish = new Sandwich(Bread.WHITE,Size.SMALL,Meat.CHICKEN,false,Cheese.CHEDDAR,false);
        System.out.println(sandwish.getDetails());
    }
}