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


import interfaces.AbstractQueue;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class ADTQueueTest {
    
    private AbstractQueue queue_1 = ADTQueue.createQ();
    private AbstractQueue queue_2 = ADTQueue.createQ();
    private AbstractQueue queue_3 = ADTQueue.createQ();
    private AbstractQueue queue_4 = ADTQueue.createQ();
    private AbstractQueue queue_5 = ADTQueue.createQ();
    private AbstractQueue queue_6 = ADTQueue.createQ();

    
    public ADTQueueTest(){}
    
    @Test
    public void isEmpty(){
        
    //isEmpty
        
        assertEquals(queue_1.isEmptyQ(),true);
        assertEquals(queue_2.isEmptyQ(),true);    
        assertEquals(queue_3.isEmptyQ(),true);    
        assertEquals(queue_4.isEmptyQ(),true);    
        assertEquals(queue_5.isEmptyQ(),true);    
        assertEquals(queue_6.isEmptyQ(),true);    
    
    //Negative Tests
        
        queue_1.enQueue(3);
        assertEquals(queue_1.isEmptyQ(),false);
        queue_2.enQueue(4);
        assertEquals(queue_2.isEmptyQ(),false);
        queue_3.enQueue(5  );
        assertEquals(queue_3.isEmptyQ(),false);
        
    }
    
    //Enqueue
    
    public void enQueue(){
        
        for(int i = 1; i<= 10; i++){queue_1.enQueue(i);}
        for(int i = 2; i<= 20; i++){queue_2.enQueue(i);}
        for(int i = 3; i<= 60; i++){queue_3.enQueue(i);}
        for(int i = 4; i<= 80; i++){queue_4.enQueue(i);}
        for(int i = 5; i<= 100; i++){queue_5.enQueue(i*2);}
        
        //Control of inserting Elements.
        
        assertEquals(queue_1.front(),1);
        
    }
    
        //front
    @Test
    public void front(){
            
        assertEquals(queue_1.front(), 1);
        assertEquals(queue_2.front(), 2);
        assertEquals(queue_3.front(), 3);
        assertEquals(queue_4.front(), 4);
        assertEquals(queue_5.front(), 5);
        assertEquals(queue_6.front(),-1);
    }
    
    //Dequeue    
    public void deQueue(){
        
            //Test auf Dequeue und Front
        queue_1.deQueue();
        assertEquals(queue_1.front(),2);
        queue_1.deQueue();
        assertEquals(queue_1.front(),3);
        queue_1.deQueue();
        assertEquals(queue_1.front(),4);
        queue_1.deQueue();
        assertEquals(queue_1.front(),5);
        
            //Einer Art Negativer Test auf Deque
        assertEquals(queue_6.front(),-1);
        queue_6.deQueue();
        assertEquals(queue_6.isEmptyQ(),true);
        
    
    }
    
}
    
    
    
    
    
