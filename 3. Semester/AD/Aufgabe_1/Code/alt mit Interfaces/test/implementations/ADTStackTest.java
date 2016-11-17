/*
 * Copyright (C) 2015 Mesut
 */
package implementations;

import interfaces.AbstractStack;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ADTStackTest {
    // FOR THE TEST VARABLE
    private AbstractStack stackOne;
    private AbstractStack stackTwo;
    private Random randomNumbers;
    private Set<Integer> numbers;
    
    public ADTStackTest() {}
    
    @BeforeClass public static void setUpClass() {}
    
    @AfterClass  public static void tearDownClass() {}
    
    @Before public void setUp() {
        // CREATE STACKSS
        stackOne = ADTStack.createS();
        stackTwo = ADTStack.createS();
        // CREATE RANDOM NUMBERS
        randomNumbers = new Random();
        numbers = new HashSet<>();
        // FILL THE HASHMAP
        while (numbers.size() < 100) numbers.add(randomNumbers.nextInt(90+10));
    }
    
    @After public void tearDown() {}

    /**
     * Test of isEmptyS method, of class ADTStack.
     */
    @Test public void testIsEmptyS() {
        // TEST OF TRUE
        assertEquals(true, stackOne.isEmptyS());
        assertEquals(true, stackTwo.isEmptyS());
        // Push for test of false
        stackOne.push(23);
        stackTwo.push(23);
        // TEST OF FALSE
        assertEquals(false, stackOne.isEmptyS());
        assertEquals(false, stackTwo.isEmptyS());
    }

    /**
     * Test of push method, of class ADTStack.
     */
    @Test public void testPush() {
        // TEST PUSH
        numbers.stream().map((num) -> { 
            stackOne.push(num);
            return num;
        }).forEach((num) -> {
            stackTwo.push(num);
        });
        // TEST
        assertEquals(stackOne.top(), stackTwo.top());
    }

    /**
     * Test of pop method, of class ADTStack.
     */
    @Test public void testPop() {
        // POP
        while (!stackOne.isEmptyS()) stackOne.pop();
        // POP DONE? YES
        assertEquals(true, stackOne.isEmptyS());
    }

    /**
     * Test of top method, of class ADTStack.
     */
    @Test public void testTop() {
        // CREATE
        for(int i = 1; i <= 20; i++){
            stackOne.push(i);
            stackTwo.push(i);
       }
        // TOP
        while (!stackOne.isEmptyS() && !stackTwo.isEmptyS()) {
            assertEquals(stackOne.top(), stackTwo.top());
            generatePop();
        }
        assertEquals(stackTwo.isEmptyS(), stackOne.isEmptyS());
    }
    
    /**
     * Test of generatePop method, of class ADTStack.
     */
    public void generatePop(){
        stackOne.pop();
        stackTwo.pop();
    }
}
