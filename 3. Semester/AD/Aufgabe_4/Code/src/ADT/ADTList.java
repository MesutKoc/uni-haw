/**
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */

package ADT;

import java.util.Arrays;

class ADTList {
    /* ============================
     * CONFIG                     
     * ============================
    */
    private static final int listSize = 55000;     // Die Größe 32 an Integers
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
    public static ADTList create(){
        return new ADTList();
    }
    
    /* ============================
     * Function: isEmpty()
     * ============================
    */
    public boolean isEmpty() {
        return laenge() == 0;
    }

    /* ============================
     * Function: laenge()
     * ============================
    */
    public int laenge() {
        return listLength;
    }
    
    /* ============================
     * Function: insert(int elem, int pos)
     * ============================
    */
    public ADTList insert(int elem, int pos) {
        if ( listBegin > pos || pos > laenge() + 1 ) return this; // wenn eine ungültige Position
        
        ADTList temp = create();
        System.arraycopy(listArray, 0, temp.listArray, 0, laenge());
        temp.listLength = listLength;
        
        if ( pos + laenge() > temp.listArray.length) temp.expandArray(pos);
        
        temp.listLength++;
        if (pos == 1 && laenge() == 0) { temp.listArray[pos - 1] = elem; }
        else {
            for (int i = pos; i < laenge() + 1; i++) {
                temp.listArray[i]=listArray[i-1];
            }
            temp.listArray[pos - 1] = elem;
        }
        return temp;
    }

    /* ============================
     * Function: delete(int pos)
     * ============================
    */
    public ADTList delete(int pos){
        if( !inRange(pos) ) return this;
        
        ADTList temp = create();
        System.arraycopy(listArray, listBegin - 1, temp.listArray, 0, laenge());
        temp.listLength = listLength; 

        for (int i = pos; i <= laenge(); i++) {
            int tmp = temp.listArray[i];
            temp.listArray[i - 1] = tmp;
        }
        // System.arraycopy(listArray, pos, listArray, pos - 1, laenge() + 1 - pos);
        temp.listLength--;
        
        return temp;
    }
    
    /* ============================
     * Function: find(int elem)
     * ============================
    */
    public int find(int elem) {
        for (int idx = 0; idx <= laenge(); idx++)
            if( listArray[idx] == elem ) return idx + listBegin;
        
        return -1;
    }

    /* ============================
     * Function: retrieve(int pos)
     * ============================
    */
    public int retrieve(int pos) {
        if(this.isEmpty() || !(inRange(pos))) return -1;
        else return listArray[pos - listBegin];
    }
    
    /* ===================================
     * Function: concat(AbstractList list)
     * ===================================
    */
    public ADTList concat(ADTList list) {
        if (isEmpty() && list.isEmpty()) return this;
        else if (isEmpty()) return list.concat(this);
        else {
        
        ADTList temp = create();
        System.arraycopy(listArray, listBegin - 1, temp.listArray, 0, laenge());
        temp.listLength = listLength;
        
        if( temp.laenge() + list.laenge() > temp.listArray.length) temp.expandArray(temp.laenge()+list.laenge());
        int listLength = list.laenge();
        
        for (int i = listBegin; i <=  listLength; i++) {
            temp = temp.insert(list.retrieve(i), temp.laenge() + listBegin);
        }
        
           return temp;
        }
    }
	
    /* ============================
     * Function: inRange(int position)
     * ============================
    */
    private boolean inRange(int position){
        return ( listBegin <= position && position <= laenge() );
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

    /* ============================
     * Function: equals(Object o)
     * ============================
    */
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ADTList)) return false;

        ADTList object = (ADTList) o;
        if (laenge() != object.laenge()) return false;

        for (int i = 0; i < laenge(); i++)
            if (listArray[i] != object.listArray[i]) return false;

        return true;
    }
}