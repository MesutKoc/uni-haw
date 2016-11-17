/**
 *
 * @author Mesut, Igor and Anton
 */

package implementations;

import org.junit.Test;
import static org.junit.Assert.*;

public class ADTStackTest {
    
    public ADTStackTest(){}
 
    @Test
    public void test_createS(){
    
        //Creating a new Stack.
        ADTStack expResult = ADTStack.createS();
        ADTStack new_stack = ADTStack.createS();
        assertEquals(expResult, new_stack);
        
        System.out.println("Create \n ok");
    }

    @Test
    public void test_isempty(){
    
        //Stack is empty.
        ADTStack Stack = ADTStack.createS();
        boolean expResult = true;
        boolean stack_empty = Stack.isEmptyS();
        assertEquals(expResult, stack_empty);
        
        //Stack is not empty.
        ADTStack Stack2 = ADTStack.createS().push(1);
        boolean expResult2 = false;
        boolean stack_empty2 = Stack2.isEmptyS();
        assertEquals(expResult2, stack_empty2);
        
        System.out.println("Empty \n ok");
        
    }

    @Test
    public void test_push(){
    
    
        //Pushing elements in empty stack.
        ADTStack Stack = ADTStack.createS().push(3).push(2);
        ADTStack expResult = ADTStack.createS().push(3).push(2);
        assertEquals(Stack, expResult); 
        
        //Pushing elements in a stack with existing elements. 
        ADTStack Stack2 = ADTStack.createS().push(3).push(2);
        ADTStack expResult2 = ADTStack.createS().push(3).push(2).push(4).push(1);
        ADTStack push_stack2 = Stack2.push(4).push(1);
        assertEquals(push_stack2, expResult2); 
        
        System.out.println("Push \n ok");
    }
    
    @Test
    public void test_pop(){
        
        //Pop elements 
        ADTStack Stack = ADTStack.createS().push(3).push(2);
        ADTStack expResult = ADTStack.createS().push(3);
        ADTStack pop_stack = Stack.pop();
        assertEquals(pop_stack, expResult); 
        
        //Poping empty Stack
        
        ADTStack Stack2 = ADTStack.createS();
        ADTStack expResult2 = ADTStack.createS();
        ADTStack pop_stack2 = Stack2.pop();
        assertEquals(pop_stack2, expResult2); 
        
        
        System.out.println("Pop \n ok");
        
    }
    
    @Test
    public void test_top(){
    
        //Empty Stack has no Top, delivers value -1
        ADTStack Stack = ADTStack.createS();
        int expResult = -1;
        int top_stack = Stack.top();
        assertEquals(top_stack, expResult); 
        
        //Identify top element in Stack.
        ADTStack Stack2 = ADTStack.createS().push(3).push(2);
        int expResult2 = 2;
        int top_stack2 = Stack2.top();
        assertEquals(top_stack2, expResult2); 
        
        //Identify top element after pushing element.
        ADTStack Stack3 = ADTStack.createS().push(3).push(2);
        int expResult3 = 1;
        ADTStack push_stack3 = Stack3.push(1);
        int top_stack3= push_stack3.top();
        assertEquals(top_stack3, expResult3); 
        
        //Identify top element after poping element.
        ADTStack Stack4 = ADTStack.createS().push(3).push(2);
        int expResult4 = 3;
        ADTStack pop_stack4 = Stack4.pop();
        int top_stack4= pop_stack4.top();
        assertEquals(top_stack4, expResult4); 
        
        
        System.out.println("Top \n ok");
    }
    
}
