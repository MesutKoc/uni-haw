/**
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */

package implementations;

class ADTArray {
    /* ============================
     * CONFIG
     * ============================
    */
    private static final int arrayBegin = 0;     // Das Array beginnt bei 0
    private int dummyElem = 0;

    private ADTList list = ADTList.create();

    /* ============================
     * PRIVATE KONSTRUKTOR
     * ============================
    */
    private ADTArray(){}

    /* ============================
     * PUBLIC KONSTRUKTOR
     * ============================
    */
    public static ADTArray initA(){
        return new ADTArray();
    }

    /* ============================
     * Function: setA(int pos, int elem)
     * ============================
    */
    
    public ADTArray setA(int pos, int elem) {
        
        ADTArray temp = this;
        
        if (pos > temp.list.laenge()) {
            for (int i = temp.list.laenge()+1; i <= pos; i++)
                temp.list = temp.list.insert(dummyElem, i);
        }
        temp.list = temp.list.delete(pos + 1);
        temp.list = temp.list.insert(elem, pos + 1);
        
        return temp;
    }

    /* ============================
     * Function: getA(int pos)
     * ============================
    */
    public int getA(int pos) {
        return (pos < list.laenge() ? list.retrieve(pos+1) : 0);
    }

    /* ============================
     * Function: lengthA()
     * ============================
    */
    public int lengthA() {
        return list.laenge() != 0 ? list.laenge() : -1;
    }

    /* ============================
     * Function: equals(Object o)
     * ============================
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ADTArray)) return false;

        ADTArray object = (ADTArray) o;
        if (lengthA() != object.lengthA()) return false;

        for (int i = arrayBegin; i<= lengthA(); i++)
            if (getA(i) != object.getA(i)) return false;

        return true;
    }
}