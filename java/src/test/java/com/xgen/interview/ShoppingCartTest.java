package com.xgen.interview;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;


public class ShoppingCartTest {

    private ShoppingCart scSweden;
    private ShoppingCart scNorway;
    private final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
    private final PrintStream stdOut = System.out;


    /*
    Setup method that initiates the Printstream and one shoppingcart of each type. 
    */
    @Before
    public void setUp(){
        scSweden = new ShoppingCartSweden(new Pricer());
        scNorway = new ShoppingCartNorway(new Pricer());
        System.setOut(new PrintStream(myOut));
    }

    /*
    TearDown method for shoppingcart, clear stdOut so new input can be taken in.
    */
    @After
    public void tearDown(){
        System.setOut(stdOut);
    }

    /*
    Test that the correct total is printed in the correct format.
    */

    @Test
    public void correctTotal(){
        scSweden.addItem("apple", 7);
        scSweden.addItem("banana", 5);

        scSweden.printReceipt();

        String result = myOut.toString();

        Scanner scanner = new Scanner(result);

        String total = "";
        
        while(scanner.hasNext()){
            total = scanner.nextLine();                     //Checks last line of reciept for total
        }
        scanner.close();


        assertEquals("Total - €17.00",total); 
    }
    
    /*
    Test that the correct item is printed in the correct format for Sweden.
    */
    @Test
    public void canAddAnItem() {

        scSweden.addItem("apple", 1);

        scSweden.printReceipt();

        String result = myOut.toString();

        Scanner scanner = new Scanner(result);

        String firstItem = scanner.nextLine();                  //Checks first line of reciept for item print out 

        scanner.close();

        assertEquals("apple - 1 - €1.00", firstItem);
    }

    /*
    Test that the correct item is printed in the correct format for Norway.
    */

    @Test
    public void canAddItemNorway() {

        scNorway.addItem("apple", 1);

        scNorway.printReceipt();

        String result = myOut.toString();

        Scanner scanner = new Scanner(result);

        String firstItem = scanner.nextLine();

        scanner.close();

        assertEquals("€1.00 - 1 - apple", firstItem);
    }

    /*
    Test that the correct item is printed in the correct format for Norway.
    */

    @Test
    public void canAddMoreThanOneItem() {

        scSweden.addItem("apple", 2);

        scSweden.printReceipt();

        String result = myOut.toString();

        Scanner scanner = new Scanner(result);

        String twoItems = scanner.nextLine();

        scanner.close();

        assertEquals("apple - 2 - €2.00", twoItems);
    }

    /*
    Test that items are printed in the order they are added.
    */
    @Test
    public void canAddDifferentItemsInOrder() {

        scSweden.addItem("apple", 2);
        scSweden.addItem("banana", 1);
        scSweden.addItem("orange", 2);

        scSweden.printReceipt();

        String result = myOut.toString();

        Scanner scanner = new Scanner(result);

        String items = scanner.nextLine();

        assertEquals("apple - 2 - €2.00",items);
        
        items = scanner.nextLine();

        assertEquals("banana - 1 - €2.00",items);

        items = scanner.nextLine();

        assertEquals("orange - 2 - €5.00",items);

        scanner.close();
     
    
    }

    /*
    Test that checks the entire reciept format including adding a mystery item.
    Will have to be changed everytime the code changes.
    */

    @Test
    public void testEntireReceipt() {

        scSweden.addItem("apple", 3);
        scSweden.addItem("crisps", 2);
        scSweden.addItem("orange", 1);
        scSweden.addItem("banana", 2);

        scSweden.printReceipt();
        assertEquals(String.format("apple - 3 - €3.00%ncrisps - 2 - €0.00%norange - 1 - €2.50%nbanana - 2 - €4.00%nTotal - €9.50%n"), myOut.toString());
    }
}


