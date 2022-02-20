package com.xgen.interview;

import java.util.*;


/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public abstract class ShoppingCart implements IShoppingCart {
    public Pricer pricer;

    public ShoppingCart(Pricer pricer) {
        this.pricer = pricer;
    }

    abstract public void addItem(String itemType, int number);
    


    abstract public void printReceipt();
}
