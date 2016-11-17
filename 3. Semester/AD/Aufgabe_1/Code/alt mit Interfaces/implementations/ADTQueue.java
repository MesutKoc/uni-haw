/*
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */

package implementations;

import interfaces.AbstractStack;
import interfaces.AbstractQueue;

class ADTQueue implements AbstractQueue {
    /* ============================
     * CONFIG
     * ============================
    */
    private final AbstractStack inStack = ADTStack.createS();
    private final AbstractStack outStack = ADTStack.createS();

    /* ============================
     * PRIVATE KONSTRUKTOR                     
     * ============================
    */
    private ADTQueue(){}
    
    /* ============================
     * PUBLIC KONSTRUKTOR                     
     * ============================
    */
    public static AbstractQueue createQ(){
        return new ADTQueue();
    }

    /* ============================
     * Function: isEmptyQ()
     * ============================
    */
    @Override
    public boolean isEmptyQ() {
        return (inStack.isEmptyS() && outStack.isEmptyS());
    }

    /* ============================
     * Function: enQueue(int elem)
     * ============================
    */
    @Override
    public void enQueue(int elem) {
        inStack.push(elem);
    }

    /* ============================
     * Function: deQueue()
     * ============================
    */
    @Override
    public void deQueue() {
        if (outStack.isEmptyS()) { inOutTransfer(inStack, outStack); }
        outStack.pop();
    }

    /* ============================
     * Function: front()
     * ============================
    */
    @Override
    public int front() {
        if (outStack.isEmptyS()) { inOutTransfer(inStack, outStack); }
        return outStack.top();
    }

    /* ============================
     * Function: transfer(AbstractStack inStack, AbstractStack outStack)
     * ============================
    */
    private void inOutTransfer(AbstractStack inStack, AbstractStack outStack) {
        while (!inStack.isEmptyS()) {
            outStack.push(inStack.top());
            inStack.pop();
        }
    }
}