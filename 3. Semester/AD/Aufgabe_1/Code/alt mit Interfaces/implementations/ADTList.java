/*
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */
package implementations;

import interfaces.AbstractList;
import java.util.Arrays;

class ADTList implements AbstractList {
    /* ============================
     * CONFIG                     
     * ============================
    */
    private static final int listSize = 32;     // Die Größe 32 an Integers
    private static final int listBegin = 1;     // Die Liste beginnt bei 1
    private int listLength = 0;
  
    /* ============================
     * CREATE ARRAY WITH LIST SIZE                     
     * ============================
    */
    private int listArray[] = new int[listSize];
    
    /* ============================
     * PRIVATE KONSTRUKTOR                     
     * ============================
    */
    private ADTList(){}
    
    /* ============================
     * PUBLIC KONSTRUKTOR                     
     * ============================
    */
    public static AbstractList create(){
        return new ADTList();
    }
    
    /* ============================
     * Function: isEmpty()
     * ============================
    */
    @Override
    public boolean isEmpty() {
        return laenge() == 0;
    }

    /* ============================
     * Function: laenge()
     * ============================
    */
    @Override
    public int laenge() {
        return listLength;
    }
    
    /* ============================
     * Function: insert(int elem, int pos)
     * ============================
    */
    //@Override
    public void insert(int elem, int pos) {
        if ( !inRange(pos) ) return; // wenn eine ungültige Position
        if ( pos + laenge() > listArray.length) expandArray(pos);
        
        listLength++;
        System.arraycopy(listArray, pos - 1, listArray, pos, laenge() - pos);
	listArray[pos - 1] = elem;       
    }

    /* ============================
     * Function: delete(int pos)
     * ============================
    */
    @Override
    public void delete(int pos){
        if( !inRange(pos) ) return;
 
        for (int i = pos; i <= laenge(); i++) {
            int tmp = listArray[i];
            listArray[i - 1] = tmp;
        }
        listLength--;
    }
    
    /* ============================
     * Function: find(int elem)
     * ============================
    */
    @Override
    public int find(int elem) {
        for (int idx = 0; idx <= laenge(); idx++)
            if( listArray[idx] == elem ) return idx + listBegin;
        
        return -1;
    }

    /* ============================
     * Function: retrieve(int pos)
     * ============================
    */
    @Override
    public int retrieve(int pos) {
        if(this.isEmpty() || !(inRange(pos))) return -1;
        else return listArray[pos - listBegin];
    }
    
    /* ===================================
     * Function: concat(AbstractList list)
     * ===================================
    */
    @Override
    public void concat(AbstractList list) {
        if( laenge() + list.laenge() > listArray.length) expandArray(laenge()+list.laenge());
        int listLength = list.laenge();
        for (int i = listBegin; i <=  listLength; i++) insert(list.retrieve(i), laenge() + listBegin);
    }
	
    /* ============================
     * Function: inRange(int position)
     * ============================
    */
    private boolean inRange(int position){
        return ( listBegin <= position && position <= (laenge() + 1) );
    }

    /* ============================
     * Function: expandArray(int next)
     * ============================
    */
    private void expandArray(int next) {
        listArray = Arrays.copyOf(listArray, (int) (next * 2.0));
    }

    /* ============================
     * Function: print()
     * ============================
    */
    public void print(){
         System.out.println(Arrays.toString(listArray));
    }
}