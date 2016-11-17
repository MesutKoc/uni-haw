/*
 * Copyright (C) 2015 Mesut
 */

package implementations;

import interfaces.AbstractList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ADTListTest {
    private AbstractList list1 = ADTList.create();
    private AbstractList list2 = ADTList.create();
    private AbstractList list3 = ADTList.create();
    private AbstractList list4 = ADTList.create();
    
    public ADTListTest() {}
    
    @BeforeClass public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before public void setUp() {}
    
    @After public void tearDown() {}

    /**
     * Test of create method, of class ADTList.
     */
    @Test public void testCreate() {
        AbstractList list5 = ADTList.create();
        assertEquals(list5.laenge(),0);
    }

    /**
     * Test of isEmpty method, of class ADTList.
     */
    @Test public void testIsEmpty() {
        // True
        assertTrue(list1.isEmpty());
        assertTrue(list2.isEmpty());
        assertTrue(list3.isEmpty());
        assertTrue(list4.isEmpty());
        
        // False
        list1.insert(1, 1);
        list2.insert(1, 1);
        list3.insert(1, 1);
        list4.insert(1, 1);
        assertFalse(list1.isEmpty());
        assertFalse(list2.isEmpty());
        assertFalse(list3.isEmpty());
        assertFalse(list4.isEmpty());
    }

    /**
     * Test of laenge method, of class ADTList.
     */
    @Test public void testLaenge() {
        assertEquals(list1.laenge(),0);
        assertEquals(list2.laenge(),0);
        assertEquals(list3.laenge(),0);
        assertEquals(list4.laenge(),0);
        
        list1.insert(1, 1);
        list2.insert(1, 1);
        list3.insert(1, 1);
        list4.insert(1, 1);
        list4.insert(2, 2);
        
        assertEquals(list1.laenge(),1);
        assertEquals(list2.laenge(),1);
        assertEquals(list3.laenge(),1);
        assertEquals(list4.laenge(),2);
    }

    /**
     * Test of insert method, of class ADTList.
     */
    @Test public void testInsert() {	
        // Inser + Test
        list1.insert(10, 1);
        assertEquals(10, list1.retrieve(1));
        // Inser + Test
        list2.insert(100, 0);
        assertEquals(-1, list2.retrieve(0));
        // Inser + Test
        list3.insert(1, 1);
        list3.insert(2, 2);
        assertEquals(1, list3.retrieve(1));
        assertEquals(2, list3.retrieve(2));
    }

    /**
     * Test of delete method, of class ADTList.
     */
    @Test public void testDelete() {
        // insert
        list1.insert(15,1);
        list1.insert(30,2);
        list1.insert(45,3);
        // delete
        list1.delete(2);
        //test
        assertEquals(-1,list1.find(30));
        assertEquals(1,list1.find(15));
        assertEquals(2,list1.find(45));
        //test
        list1.delete(3);
        assertEquals(1,list1.laenge());
    }

    /**
     * Test of find method, of class ADTList.
     */
    @Test public void testFind() {
        // insert
        list1.insert(15,1);
        list1.insert(30,2);
        list1.insert(45,3);
        // test
        assertEquals(-1,list1.find(600));
        assertEquals(1,list1.find(15));
        assertEquals(3,list1.find(45));
    }

    /**
     * Test of retrieve method, of class ADTList.
     */
    @Test public void testRetrieve() {
        // fill the list
        list1.insert(15,1);
        list1.insert(30,2);
        list1.insert(45,3);
        // creat a empty list
        AbstractList emptyList = ADTList.create();
        //tests
        assertEquals(-1,emptyList.retrieve(1));
        assertEquals(-1,list1.retrieve(0));
        assertEquals(15,list1.retrieve(1));
        assertEquals(30,list1.retrieve(2));
        assertEquals(45,list1.retrieve(3));
    }

    /**
     * Test of concat method, of class ADTList.
     */
    @Test public void testConcat() {
        AbstractList listOfConcat = ADTList.create();
        AbstractList emptyList = ADTList.create();
        // Test: [].conact([])
        list1.concat(listOfConcat);
        assertEquals(listOfConcat.laenge(),emptyList.laenge());
        //Test: [ ].concat([1,2]) => [1,2]
        list1.insert(1, 1);
        list1.insert(2, 2);
        listOfConcat.concat(list1);
        list2.insert(1, 1);
        list2.insert(2, 2);
        assertEquals(listOfConcat.laenge(),list2.laenge());
    }  
}
