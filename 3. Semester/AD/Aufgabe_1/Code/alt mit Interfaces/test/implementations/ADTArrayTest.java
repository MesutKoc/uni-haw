/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Toschka
 */

package implementations;

import implementations.ADTArray;
import interfaces.AbstractArray;
import interfaces.AbstractList;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ADTArrayTest {
    
    private AbstractArray array_1 = ADTArray.initA();
    private AbstractArray array_2 = ADTArray.initA();
    private AbstractArray array_3 = ADTArray.initA();
    private AbstractArray array_4 = ADTArray.initA();
    private AbstractArray array_5 = ADTArray.initA();
    private AbstractArray array_6 = ADTArray.initA();
    
    
public ADTArrayTest(){}    
    
//Fill Array with Elements
@Before public void counter(){

     for(int i = 0; i <= 19; i++){array_4.setA(i, i);}
     
     for(int i = 0; i <=59; i++){array_5.setA(i, i);}
    
}

@Test
public void LengthA(){
    
    assertEquals(array_1.lengthA(),-1);
    assertEquals(array_2.lengthA(),-1);
    assertEquals(array_3.lengthA(),-1);
    assertEquals(array_5.lengthA(),-1);
    
    //Die Erzeugung der Elemente erfolgt in der Methode Counter.
    assertEquals(array_4.lengthA(),20); //hMM
    assertEquals(array_5.lengthA(),60); //hm
    
    //Manuelle Einfügung der Elemente
    array_1.setA(1,0);
    array_2.setA(5,1);
    array_3.setA(0,1);
    array_3.setA(1,2);
    array_3.setA(2,3);
    
    assertEquals(array_1.lengthA(),2);
    assertEquals(array_2.lengthA(),6);
    assertEquals(array_3.lengthA(),3);
}

public void testSetA(){
    //Set of an Element + Controll of Existance 
    array_1.setA(2,2);
    array_1.setA(3,1);
    assertEquals(array_1.getA(2),2);
    assertEquals(array_1.getA(3),1);

    //Überschreibung des bereits benutzen Indexes.
    array_1.setA(2,5);
    array_1.setA(3,6);
    assertEquals(array_1.getA(2),5);
    assertEquals(array_1.getA(3),6);
     
    
    array_2.setA(2,2);
    array_2.setA(3,1);
    array_2.setA(2,2);
    array_2.setA(3,1);
    
    array_3.setA(0,1);
    array_3.setA(2,1);
    array_3.setA(3,1);
    array_3.setA(6,1);
    
    assertEquals(array_2.getA(2),2);

    
}

public void testGetA(){
    
    //Positive Value
    
    assertEquals(array_4.getA(10), 10);
    assertEquals(array_4.getA(11), 11);
    assertEquals(array_4.getA(12), 12);
    assertEquals(array_4.getA(13), 13);
    assertEquals(array_4.getA(14), 14);
    assertEquals(array_5.getA(59), 59);
    
    //Negative Tests Value
    
    assertEquals(array_4.getA(21), -1);
    assertEquals(array_4.getA(22), -1);
    assertEquals(array_4.getA(23), -1);
    assertEquals(array_4.getA(24), -1);
            
            
}


}
