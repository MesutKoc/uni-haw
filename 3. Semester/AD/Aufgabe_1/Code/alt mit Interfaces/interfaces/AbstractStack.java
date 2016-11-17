/*
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */
package interfaces;

public interface AbstractStack {

    /**
     * Prüft, ob gegebener Stack leer oder nicht leer ist.
     * @param stack: ein Stack, der übergeben wird
     * @return true, wenn der Stack empty ist, andernfalls false.
     */
    public boolean isEmptyS();

    /** 
     * Das Element, welches auf den obersten Stack soll
     * @param stack, elem: das Element, welches gepusht werden soll
     * @return void
     */
    public void push(int elem);
    
    /** 
     * Das Element, welches auf den obersten Stack gelöscht werden soll
     * @param stack
     * @return void
     */
    public void pop();
    
    /** 
     * Gibt den Top Element eines Stacks zurück
     * @param stack
     * @return ein reduzierter Stack ohne das Topelement
     */
    public int top();
}
