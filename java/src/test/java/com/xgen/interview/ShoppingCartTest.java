package com.xgen.interview;

import com.xgen.interview.Pricer;
import com.xgen.interview.ShoppingCart;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class ShoppingCartTest {

    private ShoppingCart scSweden;

    @Before
    public void setUp(){
        scSweden = new ShoppingCartSweden(new Pricer());
    }
    
    
    @Test
    public void canAddAnItem() {
        
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        scSweden.addItem("apple", 1);


        scSweden.printReceipt();
        assertEquals(String.format("apple - 1 - €1.00%nThank you for shopping with us, your total was €1.00%n"), myOut.toString());
    }

    @Test
    public void canAddMoreThanOneItem() {

        scSweden.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        scSweden.printReceipt();
        assertEquals(String.format("apple - 2 - €2.00%nThank you for shopping with us, your total was €2.00%n"), myOut.toString());
    }

    @Test
    public void canAddDifferentItemsInOrder() {

        scSweden.addItem("apple", 2);
        scSweden.addItem("banana", 1);
        scSweden.addItem("orange", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        scSweden.printReceipt();

        String result = myOut.toString();

        
        assertEquals(String.format("apple - 2 - €2.00%nbanana - 1 - €2.00%norange - 2 - €5.00%nThank you for shopping with us, your total was €9.00%n"),result);
     
    
    }

    @Test
    public void doesntExplodeOnMysteryItem() {

        scSweden.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        scSweden.printReceipt();
        assertEquals(String.format("crisps - 2 - €0.00%nThank you for shopping with us, your total was €0.00%n"), myOut.toString());
    }
}


