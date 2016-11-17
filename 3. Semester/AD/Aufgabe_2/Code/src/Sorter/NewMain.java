/**
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */

package Sorter;

import ADT.ADTArray;

public class NewMain {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ADTArray array1 = ADTArray.initA();
        NumberBuilder test1 = NumberBuilder.getInstance();
        array1 = test1.importNumFile("zahlen_easy.dat");

        System.out.println("Länge: "+array1.lengthA());
        
        Sorter insert = Sorter.getInstance();
        //insert.quicksort(array1, 1);
        insert.insertionSort(array1, 0, array1.lengthA());

       int counter;
       for(counter= 0; counter<=array1.lengthA(); counter++) System.out.print(" "+array1.getA(counter));
    }
}
