/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Toschka
 */
public interface AbstractArray {
    
    /*
    parameter array: Position und Element
    Insert an Element in a position
    Return Value is an new Array with the Element
    */
    public void setA(int position, int element);

    /*
    Returns an array with an Element which position have been asked for.
    parameter: position, array
    Return value is an Element
    */
    public int getA(int position);
    
    /*
    Returns the length of an Array
    parameter: array
    Return value: integer!
    */
    public int lengthA();
    
}
