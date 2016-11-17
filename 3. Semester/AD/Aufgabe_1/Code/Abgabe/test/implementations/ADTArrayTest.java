/**
 *
 * @author Mesut, Igor and Anton
 */


package implementations;

import org.junit.Test;
import static org.junit.Assert.*;



public class ADTArrayTest {

    public ADTArrayTest(){}
    
    
    @Test
    public void test_initA(){
    
        //Init(create) Array.
        ADTArray Array = ADTArray.initA();
        ADTArray expResult = ADTArray.initA();
        assertEquals(Array, expResult);
        
        System.out.println("Init \n ok");
        
    }
    
    @Test
    public void test_setA(){
    
        //Setting elements 
        ADTArray Array = ADTArray.initA().setA(0,0).setA(1, 1);
        ADTArray expResult = ADTArray.initA().setA(0,0).setA(1, 1);
        assertEquals(Array, expResult);
        
        //overwrite elements
        ADTArray Array2 = ADTArray.initA().setA(0,0).setA(1, 1);
        ADTArray expResult2 = ADTArray.initA().setA(0,0).setA(1, 2);
        ADTArray ov_array = Array2.setA(1, 2);
        assertEquals(ov_array, expResult2);
        
        //attempt to set elements on illegal position.
        ADTArray Array3 = ADTArray.initA().setA(0,0).setA(1, 1);
        ADTArray expResult3 = ADTArray.initA().setA(0,0).setA(1, 1);
        ADTArray ov_array2 = Array3.setA(-1, 2);
        assertEquals(ov_array2, expResult3);
        
        System.out.println("Set \n ok");
    }
    
    @Test
    public void test_getA(){
    
        //Getting elements with help of positions.
        ADTArray Array = ADTArray.initA().setA(0,0).setA(1, 1);
        int expResult = 1;
        int get_elem = Array.getA(1);
        assertEquals(get_elem, expResult);
        
        //Illegal position.
        ADTArray Array2 = ADTArray.initA().setA(0,0).setA(1, 1).setA(2, 2);
        int expResult2 = -1;
        int get_elem2 = Array2.getA(-1);
        assertEquals(get_elem2, expResult2);
        
        //Empty array.
        ADTArray Array3 = ADTArray.initA();
        int expResult3 = 0;
        int get_elem3 = Array3.getA(0);
        assertEquals(get_elem3, expResult3);
        
        
        System.out.println("Get \n ok");
    }
   
    @Test
    public void test_lengthA(){
    
        //Empty array delivers value -1.
        ADTArray Array = ADTArray.initA();
        int expResult = -1;
        int le_array = Array.lengthA();
        assertEquals(le_array, expResult);
        
        //with elements in array
        ADTArray Array2 = ADTArray.initA().setA(0, 0).setA(1, 1).setA(2, 2).setA(3, 3);
        int expResult2 = 4;
        int le_array2 = Array2.lengthA();
        assertEquals(le_array2, expResult2);
        
        System.out.println("Length \n ok");
    }
    
}
