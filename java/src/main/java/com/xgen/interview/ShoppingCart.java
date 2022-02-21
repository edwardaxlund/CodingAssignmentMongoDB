package com.xgen.interview;

import java.util.*;



/**
    Abstact class for a shoppingcart, enabling different formats of printing using Template method 
 */
public abstract class ShoppingCart implements IShoppingCart {
    public Pricer pricer;
    protected LinkedHashMap<String,Integer> contents = new LinkedHashMap<>(); //Used linkedhashmap to keep ordering
    double total;

    public ShoppingCart(Pricer pricer) {
        this.pricer = pricer;
        total = 0;
    }

    public void addItem(String itemType, int number) {
        if (!contents.containsKey(itemType)) {
            contents.put(itemType, number);
        } else {
            contents.computeIfPresent(itemType,(k, v) -> v + number);
        }
    }

    /*
    Updated printreciept method that prints items in order they were added and the total
    */
    
    public void printReceipt() {

        contents.entrySet().stream().forEach(e-> {
            double price = e.getValue() * (double)pricer.getPrice(e.getKey())/100;
            total = total + price;
            printFormat(e,String.format(Locale.ENGLISH,"€%.2f",price));
        });

        System.out.println("Total - " + String.format(Locale.ENGLISH,"€%.2f",total));

    }

    /*
    Template method to choose printing format
    */

    abstract void printFormat(Map.Entry<String,Integer> item, String priceString);
}
