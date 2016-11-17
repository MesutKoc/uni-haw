/**
 * Copyright (C) <2016>  @author Mesut, Igor and Anton
 */

package ADT;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ADTHashmapTest {
    public ADTHashmap testVar,
                      testVar2,
                      testVar3,
                      testVar4;
    
    public ADTHashmapTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {
        
        /*===============================================
         *testVar mit Linearem Sondieren                *
         *testVar2 mit Quadratischem Sondieren          *
         *testVar3 mit Double-Hashing                   *
         *===============================================
        */
        testVar = ADTHashmap.create(201, ADTHashmap.Strategy.L);
        testVar2 = ADTHashmap.create(201, ADTHashmap.Strategy.Q);
        testVar3 = ADTHashmap.create(201, ADTHashmap.Strategy.B);
        testVar4 = ADTHashmap.create(201, ADTHashmap.Strategy.L);
    }
    
    /**
     * Test of create method, of class ADTHashmap.
     */
    @Test
    public void testInsert(){
        
            //insert
        testVar.insert("2");
        testVar2.insert("3");
        testVar2.insert("2");
        testVar3.insert("1");
        testVar3.insert("2");
        testVar3.insert("5");
//      System.out.println(testVar.isEmpty());
        
    }    
            //empty
    @Test
    public void testEmpty(){
        testVar.insert("1");
        testVar2.insert("2");
        testVar3.insert("3");
        
        assertEquals(testVar.isEmpty(),false);  
        assertEquals(testVar2.isEmpty(),false);  
        assertEquals(testVar3.isEmpty(),false);
        assertEquals(testVar4.isEmpty(),true);  
        
//      System.out.println(testVar.isEmpty());
//      System.out.println(testVar2.isEmpty());
//      System.out.println(testVar3.isEmpty());
    }
    
    @Test
    public void testFind(){            
            //find
        testVar.insert("1");
        testVar.insert("2");
        testVar.insert("test");
        testVar.insert("test");
        testVar2.insert("17");
        testVar2.insert("17");
        testVar2.insert("46");
        testVar2.insert("3");
        testVar3.insert("test2");
        testVar3.insert("test2");
        testVar3.insert("test2");
            
        assertEquals(testVar.find("test"),2);
        assertEquals(testVar.find("1"),1);
        assertEquals(testVar.find("2"),1);
        assertEquals(testVar2.find("17"),2);
        assertEquals(testVar2.find("46"),1);
        assertEquals(testVar2.find("3"),1);
        assertEquals(testVar3.find("test2"),3);
    }
//    @Test
//    public void testContains(){            
//            //contains
//        testVar.insert("1");
//        testVar.insert("2");
//        testVar.insert("test");
//        testVar.insert("test");
//        testVar2.insert("17");
//        testVar2.insert("17");
//        testVar2.insert("46");
//        testVar2.insert("3");
//        testVar3.insert("test2");
//        testVar3.insert("test2");
//        testVar3.insert("test2");
//        
//        assertEquals(testVar.contains("test"),true);
//        assertEquals(testVar.contains("1"),true);
//        assertEquals(testVar.contains("2"),true);
//        assertEquals(testVar2.contains("17"),true);
//        assertEquals(testVar2.contains("46"),true);
//        assertEquals(testVar2.contains("3"),true);
//        assertEquals(testVar3.contains("test2"),true);
//        assertEquals(testVar4.contains("hello"),false);
//        
//    }
    
    @Test
    public void testSize(){            
            //size
        testVar.insert("1");
        testVar.insert("2");
        testVar.insert("test");
        testVar.insert("test");
        testVar2.insert("17");
        testVar2.insert("17");
        testVar2.insert("15");
        testVar2.insert("46");
        testVar2.insert("3");
        testVar3.insert("test2");
        testVar3.insert("test2");
        testVar3.insert("test2");
        
        
        assertEquals(testVar.size(),3);
        assertEquals(testVar2.size(),4);
        assertEquals(testVar3.size(),1);
        assertEquals(testVar4.size(),0);
    }
    
   
}
