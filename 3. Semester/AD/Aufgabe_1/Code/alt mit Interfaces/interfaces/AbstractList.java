/**
 *   Copyright (C) <2015>  @author Mesut, Igor and Anton
 */
package interfaces;

public interface AbstractList {

    /**
     * Testet, ob die gegebene Liste leer ist oder nicht
     * @param list: eine Liste, die übergeben wird
     * @return true, wenn die Liste leer ist, andernfalls false.
     */
    public boolean isEmpty();
    
    /** 
     * Gibt die Länge einer Liste zurück
     * @param list: eine Liste, die übergeben wird
     * @return int
     */
    public int laenge();
    
    /** 
     * Fügt ein Element in die Liste ein.
     * @param list, pos, elem
     * @return gibt eine neue Liste mit dem eingefügten Element zurück
     */
    public void insert(int elem, int pos);
    
    /** 
     * Löscht ein Element von der gegebenen Position
     * @param list, pos
     * @return gibt eine neue Liste zurück
     */
    public void delete(int pos);
    
    /** 
     * Findet das gegebene Element
     * @param elem das gegebene Element
     * @return gibt die Position zurück
     */
    public int find(int elem);
    
    /** 
     * Gibt das Element auf der Position zurück
     * @param pos
     * @return Element
     */
    public int retrieve(int pos);
    
    /**
     * Fügt zwei Listen zusammen
     * @param list, list die Listen, die übergeben wird
     * @return gibt eine neue Liste zurück 
     */
    public void concat(AbstractList list);
}