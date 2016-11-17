/*
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */
package interfaces;

public interface AbstractQueue {

    /**
     * Prüft, ob Queue leer ist oder nicht
     * @param queue: eine Queue, die übergeben wird
     * @return true, wenn Queue leer ist, andernfalls false.
     */
    public boolean isEmptyQ();
    
    /**
     * Fügt ein Element in die Queue ein.
     * @param queue, elem
     * @return gibt eine neue Queue mit dem eingefügten Element zurück
     */
    public void enQueue(int elem);

    /**
     * deQueue Beschreibung hier
     * @param queue
     * @return gibt eine neue Queue ohne vorderste Element zurück
     */
    public void deQueue();

    /**
     * Gibt das vorderste Element zurück
     * @param queue
     * @return Element
     */
    public int front();
}