package com.xgen.interview;

import java.util.*;


public class ShoppingCartNorway extends ShoppingCart {
    private Map<String, Integer> contents = new LinkedHashMap<>();

    public ShoppingCartNorway(Pricer pricer){
        super(pricer);
    }

    public void addItem(String itemType, int number) {
        if (!contents.containsKey(itemType)) {
            contents.put(itemType, number);
        } else {
            int existing = contents.get(itemType);
            contents.put(itemType, existing + number);
        }
    }

    public void printReceipt() {
        double total = 0;

        for(Map.Entry<String, Integer> item: contents.entrySet()){
            int price = pricer.getPrice(item.getKey()) * contents.get(item.getKey());
            double priceDouble = Double.valueOf(price) / 100;
            total = total + priceDouble; 
            String priceString = String.format(Locale.ENGLISH,"€%.2f", priceDouble);
            System.out.println(contents.get(item.getKey()) + " - " + item.getKey() + " - " + priceString);
        }

        System.out.println("Thank you for shopping with us, your total was " + String.format(Locale.ENGLISH,"€%.2f",total));


    }
}
