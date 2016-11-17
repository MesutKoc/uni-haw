/*
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */

package implementations;

import interfaces.AbstractList;
import interfaces.AbstractArray;

class ADTArray implements AbstractArray {
    /* ============================
     * CONFIG
     * ============================
    */
    private static final int arrayBegin = 0;     // Das Array beginnt bei 0
    private int dummyElem = 0;

    private final AbstractList list = ADTList.create();

    /* ============================
     * PRIVATE KONSTRUKTOR
     * ============================
    */
    private ADTArray(){}

    /* ============================
     * PUBLIC KONSTRUKTOR
     * ============================
    */
    public static AbstractArray initA(){
        return new ADTArray();
    }

    /* ============================
     * Function: setA(int pos, int elem)
     * ============================
    */
    @Override
    public void setA(int pos, int elem) {
        if (pos > list.laenge()) {
            for (int i = list.laenge()+1; i <= pos; i++)
                list.insert(dummyElem, i);
        }
        list.insert(elem, pos + 1);
    }

    /* ============================
     * Function: getA(int pos)
     * ============================
    */
    @Override
    public int getA(int pos) {
        return (pos < list.laenge() ? list.retrieve(pos+1) : -1);
    }

    /* ============================
     * Function: lengthA()
     * ============================
    */
    @Override
    public int lengthA() {
        if (list.laenge() == 0) return list.laenge()-1;
        else return list.laenge();
    }

    /* ============================
     * Function: inRange(int position)
     * ============================
    */
    private boolean inRange(int position){
        return ( (arrayBegin <= position) && (position <= lengthA()) );
    }
}