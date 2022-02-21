package com.xgen.interview;

import java.util.*;

/*
Class for the ShoppingCart in Norway.
The branches there prefer price printed before item.
*/


public class ShoppingCartNorway extends ShoppingCart {

    public ShoppingCartNorway(Pricer pricer){
        super(pricer);
    }

 
    @Override 
    void printFormat(Map.Entry<String,Integer> item, String priceString) {
        System.out.println(priceString + " - " + contents.get(item.getKey()) + " - " + item.getKey());
    }
}
