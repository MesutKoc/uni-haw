/*
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */

package implementations;

import interfaces.AbstractList;
import interfaces.AbstractStack;

class ADTStack implements AbstractStack {
    /* ============================
     * CONFIG                     
     * ============================
    */
    private final AbstractList list = ADTList.create();

    /* ============================
     * PRIVATE KONSTRUKTOR                     
     * ============================
    */
    private ADTStack(){}
    
    /* ============================
     * PUBLIC KONSTRUKTOR
     * ============================
    */
    public static AbstractStack createS(){
        return new ADTStack();
    }

    /* ============================
     * Function: isEmptyS()
     * ============================
    */
    @Override
    public boolean isEmptyS() {
        return list.isEmpty();
    }

    /* ============================
     * Function: push(int elem)
     * ============================
    */
    @Override
    public void push(int elem) {
        list.insert(elem, list.laenge()+1);
    }
    
    /* ============================
     * Function: pop()
     * ============================
    */
    @Override
    public void pop() {
        list.delete(list.laenge());
    }
    
    /* ============================
     * Function: top()
     * ============================
    */
    @Override
    public int top() {
        return list.retrieve(list.laenge());
    }
}