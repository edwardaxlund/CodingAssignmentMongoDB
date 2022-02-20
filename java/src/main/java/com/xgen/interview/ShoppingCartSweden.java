package com.xgen.interview;


import java.util.*;

public class ShoppingCartSweden extends ShoppingCart {
    private Map<String, Integer> contents = new LinkedHashMap<>();

    public ShoppingCartSweden(Pricer pricer){
        super(pricer);
    }

    public void printReceipt() {
        double total = 0;

        for(Map.Entry<String, Integer> item: contents.entrySet()){
            int price = pricer.getPrice(item.getKey()) * contents.get(item.getKey());
            double priceDouble = Double.valueOf(price) / 100;
            total = total + priceDouble; 
            String priceString = String.format(Locale.ENGLISH,"€%.2f", priceDouble);
            System.out.println(item.getKey() + " - " + contents.get(item.getKey()) + " - " + priceString);
        }

        System.out.println("Thank you for shopping with us, your total was " + String.format(Locale.ENGLISH,"€%.2f",total));


    }
}
