/**
 *
 * @author Mesut, Igor and Anton
 */

package implementations;


import org.junit.Test;
import static org.junit.Assert.*;


public class ADTListTest {

    public ADTListTest(){}
    
    
    @Test
    public void test_create(){
        
        //Creating a new List.
        ADTList expResult = ADTList.create();
        ADTList Create_List = ADTList.create();
        assertEquals(expResult, Create_List);
        System.out.println("Create \n ok");
    }
    
    @Test
    public void test_isEmpty(){
        
        //After creating a List, we consider the List has to be empty.
        ADTList List = ADTList.create();
        boolean expResult = true;
        boolean Empty_List = List.isEmpty();
        assertEquals(expResult,Empty_List);
        
        
        //After inserting an Element, the List is not empty anymore.
        ADTList List2 = ADTList.create().insert(5,1);
        boolean expResult2 = false;
        boolean Empty_List2 = List2.isEmpty();
        assertEquals(expResult2, Empty_List2);
        
        System.out.println("Empty \n ok");
        
        
    }
    
    @Test
    public void test_laenge(){
        
        //A new created List without Elements has 0 as a length value.
        ADTList List = ADTList.create();
        int expResult = 0;
        int len_List = List.laenge();
        assertEquals(len_List, expResult);
        
        //After inserting two Elements, new value of List is 2.
        ADTList List2 = ADTList.create().insert(1,1).insert(2,2);
        int expResult2 = 2;
        int len_List2 = List2.laenge();
        assertEquals(len_List2, expResult2);
        
        //After deleting the 2. Position of the List, new lenght is 1.
        ADTList List3 = ADTList.create().insert(1, 1).insert(2, 2);
        int expResult3 = 1;
        ADTList del_List3 = List3.delete(2);
        int len_List3 = del_List3.laenge();
        assertEquals(expResult3, len_List3);
        
        System.out.println("Laenge \n ok");
        
        
    }
    
    @Test
    public void test_insert(){
        
        //Inserting Element in a List. 
        ADTList List = ADTList.create().insert(5, 1);
        ADTList expResult = ADTList.create().insert(5, 1);
        assertEquals(List, expResult);
        
        //Inserting Element in a List, changing Position delivers an empty List.
        ADTList List2 = ADTList.create().insert(1, 5);
        ADTList expResult2 = ADTList.create();
        assertEquals(List2, expResult2);
        
        //Inserting on existing Position, Element has to move to the right.
        ADTList List3 = ADTList.create().insert(5,1).insert(4,2);
        ADTList expResult3 = ADTList.create().insert(5,1).insert(6,2).insert(4,3);
        ADTList new_List3 = List3.insert(6,2);
        assertEquals(new_List3, expResult3);
    
        System.out.println("Insert \n ok");
        
    }
    
    @Test
    public void test_delete(){
        
        //Deleting a non existing position delivers an empty List.
        ADTList List = ADTList.create();
        ADTList expResult = ADTList.create();
        ADTList del_pos = List.delete(1);
        assertEquals(expResult, del_pos); 
        
        //After deleting the first position of list, list becomes empty.
        ADTList List2 = ADTList.create().insert(1, 1);
        ADTList expResult2 = ADTList.create();
        ADTList del_pos2 = List2.delete(1);
        assertEquals(expResult2, del_pos2);
        
        //After deleting 5, 6 has to move to the first position. 
        ADTList List3 = ADTList.create().insert(5, 1).insert(6, 2);
        ADTList expResult3 = ADTList.create().insert(6, 1);
        ADTList del_pos3 = List3.delete(1);
        assertEquals(expResult3, del_pos3);
        
        System.out.println("Delete \n ok");
    }
    
    @Test
    public void test_find(){
        
        //Searching for a non existing Element delivers -1 as a value.
        ADTList List = ADTList.create();
        int expResult = -1;
        int find_pos = List.find(5);
        assertEquals(find_pos, expResult);
        
        //Searching for Element in List, delivers the position of the element.
        ADTList List2 = ADTList.create().insert(6, 1).insert(5, 2);
        int expResult2 = 1;
        int find_pos2 = List2.find(6);
        assertEquals(expResult2, find_pos2);
        
        //Repeatable elements wont be considered. Important is the first located position.
        ADTList List3 = ADTList.create().insert(5, 1).insert(6, 2).insert(5, 3);
        int expResult3 = 1;
        int find_pos3 = List3.find(5);
        assertEquals(expResult3, find_pos3);
        
        System.out.println("Find \n ok");
        
    }
    
    @Test
    public void test_retrieve(){
        
        //Non existing position delivers -1 as a value.
        ADTList List = ADTList.create();
        int expResult = -1;
        int find_elem = List.retrieve(1);
        assertEquals(expResult, find_elem);
        
        //legal position delivers an element.
        ADTList List2 = ADTList.create().insert(5, 1).insert(6, 2);
        int expResult2 = 6;
        int find_elem2 = List2.retrieve(2);
        assertEquals(expResult2, find_elem2);
        
        //illegal position delivers -1 as a value.
        ADTList List3 = ADTList.create().insert(5, 1).insert(6, 2);
        int expResult3 = -1;
        int find_elem3 = List3.retrieve(3);
        assertEquals(expResult3, find_elem3);
        
        System.out.println("Retrieve \n ok");
        
    }
    
    @Test
    public void test_concat(){
    
        //Association of 2 Lists.
        ADTList List1 = ADTList.create();
        ADTList List2 = ADTList.create();
        ADTList expResult = ADTList.create();
        ADTList new_list = List1.concat(List2);
        assertEquals(new_list, expResult);
        
        //Frist List is Empty , second has Elements.
        ADTList List3 = ADTList.create();
        ADTList List4 = ADTList.create().insert(5, 1).insert(6, 2);
        ADTList expResult2 = ADTList.create().insert(5, 1).insert(6, 2);
        ADTList new_list2 = List3.concat(List4);
        assertEquals(new_list2, expResult2);
      
        //Second List is Empty , first has Elements.
        ADTList List5 = ADTList.create().insert(5, 1).insert(6, 2);
        ADTList List6 = ADTList.create();
        ADTList expResult3 = ADTList.create().insert(5, 1).insert(6, 2);
        ADTList new_list3 = List5.concat(List6);
        assertEquals(new_list3, expResult3);
        
        //Both Lists have Elements.
        ADTList List7 = ADTList.create().insert(5, 1).insert(6, 2);
        ADTList List8 = ADTList.create().insert(5, 1).insert(6, 2);
        ADTList expResult4 = ADTList.create().insert(5, 1).insert(6, 2).insert(5, 3).insert(6, 4);
        ADTList new_list4 = List7.concat(List8);
        assertEquals(new_list4, expResult4);
        
        System.out.println("Concat \n ok");
      
        
    }
    
}
