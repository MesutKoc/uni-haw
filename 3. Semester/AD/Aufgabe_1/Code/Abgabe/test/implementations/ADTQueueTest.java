/**
 *
 * @author Mesut, Igor and Anton
 */

package implementations;

import org.junit.Test;
import static org.junit.Assert.*;

public class ADTQueueTest {

    public ADTQueueTest(){}
    
    @Test
    public void test_createQ(){
    
        //Creating a Queue.
        ADTQueue Queue = ADTQueue.createQ();
        ADTQueue expResult = ADTQueue.createQ();
        assertEquals(Queue, expResult);
        
        System.out.println("Create \n ok");
        
        
    }
    
    @Test
    public void test_isEmptyQ(){
    
        //Queue is empty.
        ADTQueue Queue = ADTQueue.createQ();
        boolean expResult = true;
        boolean queue_empty = Queue.isEmptyQ();
        assertEquals(queue_empty, expResult);
        
        //Queue is not empty.
        ADTQueue Queue2 = ADTQueue.createQ().enqueue(1);
        boolean expResult2 = false;
        boolean queue_empty2 = Queue2.isEmptyQ();
        assertEquals(queue_empty2, expResult2);
        
        System.out.println("Empty \n ok");
        
    }
    
    @Test
    public void test_enqueue(){
    
        //Inserting elements in empty queue.
        ADTQueue Queue = ADTQueue.createQ().enqueue(1);
        ADTQueue expResult = ADTQueue.createQ().enqueue(1);
        assertEquals(Queue, expResult);
        
        //Inserting elements in queue with already existing elements.
        ADTQueue Queue2 = ADTQueue.createQ().enqueue(1);
        ADTQueue expResult2 = ADTQueue.createQ().enqueue(1).enqueue(3);
        ADTQueue enqueue_queue = Queue2.enqueue(3);
        assertEquals(enqueue_queue, expResult2);
        
        ADTQueue Queue3 = ADTQueue.createQ().enqueue(1).enqueue(3);
        ADTQueue expResult3 = ADTQueue.createQ().enqueue(1).enqueue(3).enqueue(2);
        ADTQueue enqueue_queue2 = Queue3.enqueue(2);
        assertEquals(enqueue_queue2, expResult3);
        
        System.out.println("Enqueue \n ok");
        
    }
    
    @Test
    public void test_dequeue(){
    
        //Dequeue empty queue delivers queue.
        ADTQueue Queue = ADTQueue.createQ();
        ADTQueue expResult = ADTQueue.createQ();
        ADTQueue dequeue_queue = Queue.dequeue();
        assertEquals(dequeue_queue, expResult);
        
        //Dequeue queue with elements.
        ADTQueue Queue2 = ADTQueue.createQ().enqueue(2).enqueue(1).enqueue(3);
        ADTQueue expResult2 = ADTQueue.createQ().enqueue(1).enqueue(3);
        ADTQueue dequeue_queue2 = Queue2.dequeue();
        assertEquals(dequeue_queue2, expResult2);
        
        
        System.out.println("Dequeue \n ok");
    }
    
    @Test
    public void test_front(){
        
        //Empty queue delivers -1 as value.
        ADTQueue Queue = ADTQueue.createQ();
        int expResult = -1;
        int front_queue = Queue.front();
        assertEquals(front_queue,expResult);
        
        //Delivers the front element in this case 1.
        ADTQueue Queue2 = ADTQueue.createQ().enqueue(1).enqueue(2).enqueue(3);
        int expResult2 = 1;
        int front_queue2 = Queue2.front();
        assertEquals(front_queue2,expResult2);
        
        
        //After dequeue an element.(1)
        ADTQueue Queue3 = ADTQueue.createQ().enqueue(1).enqueue(2).enqueue(3);
        int expResult3 = 2;
        ADTQueue dequeue_queue = Queue3.dequeue();
        int front_queue3 = dequeue_queue.front();
        assertEquals(front_queue3,expResult3);
        
    
        System.out.println("Front \n ok");
    }
    
    
}
