package com.xgen.interview;


import java.util.*;

/*
Class for the ShoppingCart in Sweden.
The branches there prefer item printed before price.
*/

public class ShoppingCartSweden extends ShoppingCart {

    public ShoppingCartSweden(Pricer pricer){
        super(pricer);
    }

    @Override 
    void printFormat(Map.Entry<String,Integer> item, String priceString) {
        System.out.println(item.getKey() + " - " + contents.get(item.getKey()) + " - " + priceString);
    }
}
