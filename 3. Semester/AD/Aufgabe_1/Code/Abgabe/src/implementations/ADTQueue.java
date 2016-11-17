/**
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */

package implementations;

class ADTQueue {
    /* ============================
     * CONFIG
     * ============================
    */
    private ADTStack inStack = ADTStack.createS();
    private ADTStack outStack = ADTStack.createS();

    /* ============================
     * PRIVATE KONSTRUKTOR                     
     * ============================
    */
    private ADTQueue(){}
    
    /* ============================
     * PUBLIC KONSTRUKTOR                     
     * ============================
    */
    public static ADTQueue createQ(){
        return new ADTQueue();
    }

    /* ============================
     * Function: isEmptyQ()
     * ============================
    */   
    public boolean isEmptyQ() {
        return inStack.isEmptyS() && outStack.isEmptyS();
    }

    /* ============================
     * Function: enQueue(int elem)
     * ============================
    */
    public ADTQueue enqueue(int elem) {
        ADTQueue temp = createQ();
        
        temp.inStack = inStack.push(elem);
        temp.outStack = outStack;
        
        return temp;
    }

    /* ============================
     * Function: deQueue()
     * ============================
    */
    public ADTQueue dequeue() {
        ADTQueue temp = createQ();
        temp.inStack = inStack;
        temp.outStack = outStack;
        
        ADTStack mirror = ADTStack.createS();
        
        if (temp.outStack.isEmptyS()) temp = inOutTransfer(temp.inStack);
        
        while (!temp.outStack.isEmptyS()) {
            mirror = mirror.push(temp.outStack.top());
            temp.outStack = temp.outStack.pop();
        }
        
        mirror = mirror.pop();
        
        while (!mirror.isEmptyS()) {
            temp.outStack = temp.outStack.push(mirror.top());
            mirror = mirror.pop();
        }
        
        if (temp.outStack.isEmptyS()) temp = inOutTransfer(temp.inStack);
        
        return temp;
    }

    /* ============================
     * Function: front()
     * ============================
    */
    public int front() {
        ADTQueue temp = createQ(); 
        temp.inStack = inStack;
        temp.outStack = outStack;
        
        ADTStack mirror = ADTStack.createS();
        
        if (temp.outStack.isEmptyS()) temp = inOutTransfer(temp.inStack);
        
        while (!temp.outStack.isEmptyS()) {
            mirror = mirror.push(temp.outStack.top());
            temp.outStack = temp.outStack.pop();
        }
        
        return mirror.top();
    }
    
    private ADTQueue inOutTransfer(ADTStack in) {
        ADTQueue temp = createQ();
        temp.inStack = in;
        temp.outStack = in;

        while (!temp.inStack.isEmptyS()) {
            temp.inStack = temp.inStack.pop();
        }
        
        return temp;
    }
    

    public void print(){
         inStack.print();
         outStack.print();
    }
    
    /* ============================
     * Function: equals(Object o)
     * ============================
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ADTQueue)) return false;

        ADTQueue copy = this;
        ADTQueue object = (ADTQueue) o;
        
        while (!copy.isEmptyQ() && !object.isEmptyQ()) {
            if (copy.front() != object.front()) return false;
            copy = copy.dequeue();
            object = object.dequeue();
        }
        
        return true;
    }
}