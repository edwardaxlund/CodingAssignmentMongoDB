package com.xgen.interview;

import com.xgen.interview.Pricer;
import com.xgen.interview.ShoppingCart;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class ShoppingCartTest {

    private ShoppingCart sc;

    @Before
    public void setUp(){
        sc = new ShoppingCartSweden(new Pricer());
    }
    
    
    @Test
    public void canAddAnItem() {
        
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.addItem("apple", 1);


        sc.printReceipt();
        assertEquals(String.format("apple - 1 - €1.00%nThank you for shopping with us, your total was €1.00%n"), myOut.toString());
    }

    @Test
    public void canAddMoreThanOneItem() {

        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("apple - 2 - €2.00%nThank you for shopping with us, your total was €2.00%n"), myOut.toString());
    }

    @Test
    public void canAddDifferentItemsInOrder() {

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);
        sc.addItem("orange", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String result = myOut.toString();

        
        assertEquals(String.format("apple - 2 - €2.00%nbanana - 1 - €2.00%norange - 2 - €5.00%nThank you for shopping with us, your total was €9.00%n"),result);
     
    
    }

        @Test
    public void doesntExplodeOnMysteryItem() {

        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("crisps - 2 - €0.00%nThank you for shopping with us, your total was €0.00%n"), myOut.toString());
    }
}


