/**
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */

package implementations;

class ADTStack {
    /* ============================
     * CONFIG                     
     * ============================
    */
    private ADTList list = ADTList.create();

    /* ============================
     * PRIVATE KONSTRUKTOR                     
     * ============================
    */
    private ADTStack(){}
    
    /* ============================
     * PUBLIC KONSTRUKTOR
     * ============================
    */
    public static ADTStack createS(){
        return new ADTStack();
    }

    /* ============================
     * Function: isEmptyS()
     * ============================
    */
    
    public boolean isEmptyS() {
        return list.isEmpty();
    }

    /* ============================
     * Function: push(int elem)
     * ============================
    */
    
    public ADTStack push(int elem) {
        ADTStack temp = createS();
        
        for (int i = 1; i <= list.laenge(); i++) {
            temp.list = temp.list.insert(list.retrieve(i), i);
        }
            
        temp.list = temp.list.insert(elem, 1);
        return temp;
    }
    
    /* ============================
     * Function: pop()
     * ============================
    */
    
    public ADTStack pop() {
        ADTStack temp = createS();
        
        for (int i = 1; i <= list.laenge(); i++)
            temp.list = temp.list.insert(list.retrieve(i), i);
        
        temp.list = temp.list.delete(1);
        return temp;
    }
    
    /* ============================
     * Function: top()
     * ============================
    */
    
    public int top() {
        return list.retrieve(1);
    }
       
    public void print(){
         list.print();
    }

    /* ============================
    * Function: equals(Object o)
    * ============================
    */
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ADTStack)) return false;

        ADTStack object = (ADTStack) o;
        ADTStack temp = createS();
        
        for (int i = 1; i <= list.laenge(); i++) {
            temp.list = temp.list.insert(list.retrieve(i), i);
        }
        
        return temp.list.equals(object.list);
    }
}