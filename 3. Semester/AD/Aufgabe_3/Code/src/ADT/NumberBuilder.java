/**
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */

package ADT;

import ADT.ADTArray;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NumberBuilder {
    /*===================================
      CONFIG
     ====================================*/
    private static final String DATEINAME = "zahlen.dat";
    private static final String BREAK     = " ";
    
    /*===================================
      PRIVATE KONSTRUKTOR
      ===================================*/
    private NumberBuilder(){}
    
    /*===================================
      PUBLIC KONSTRUKTOR
      ===================================*/
    public static NumberBuilder getInstance(){
        return new NumberBuilder();
    }
    
    /**
     * Die Methode generiert eine angegebene Anzahl 
     * von Zufallszahlen und exportiert diese in eine Datei
     * Der Bereich geht von 0 bis anz.
     *
     * @param anz Die Anzahl der gewünschten Zufallszahen
     *            müssen größer oder gleich 0 sein.
     */
    public static void generateSortNum(int anz, boolean duplicateEnabled){
        if(anz <= 0) return; 
        
        ArrayList<Integer> numbers = new ArrayList<Integer>();   
    
        while (numbers.size() < anz) {
            int random = (int) (Math.random() * anz + 1);
            if(duplicateEnabled) numbers.add(random);
            else if(!numbers.contains(random)) numbers.add(random);
        }

        int[] arr = numbers.stream().mapToInt(i -> i).toArray();
        createFile(stringBuilderToString(arr));
    }
    
    /**
     * Die Methode generiert eine angegebene Anzahl 
     * von Zufallszahlen und exportiert diese von links nach rechts
     * in eine Datei
     *
     * @param anz Die Anzahl der gewünschten Zufallszahen
     *            müssen größer oder gleich 0 sein.
     */
    public static void sortNumLeft(int anz, boolean duplicateEnabled){
        generateSortNum(anz, duplicateEnabled);   // Erstelle Zufallszahlen + Writing
        int[] arr = readFile(anz); //Lies die Datei, die erstellt wurde
        Arrays.sort(arr);  // Sortiere das Array
        createFile(stringBuilderToString(arr));
    }
    
    /**
     * Die Methode generiert eine angegebene Anzahl 
     * von Zufallszahlen und exportiert diese von rechts nach links
     * in eine Datei
     *
     * @param anz Die Anzahl der gewünschten Zufallszahen
     *            müssen größer oder gleich 0 sein.
     */
    public static void sortNumRight(int anz, boolean duplicateEnabled){
        sortNumLeft(anz, duplicateEnabled);
        int[] arr = readFile(anz);
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int intValue : arr) list.add(intValue);
        Collections.reverse(list);
        
        arr = list.stream().mapToInt((Integer i) -> i).toArray();
        createFile(stringBuilderToString(arr));
    }
    
    //importNumFile
    public static ADTArray importNumFile(String datname) {
        try {
            ADTArray arr = ADTArray.initA();
            Scanner input = new Scanner(new File(datname));

            for (int i = 0; input.hasNext(); i++)
                arr.setA(i, input.nextInt());

            return arr;
        } catch (FileNotFoundException e) {}
        return null;
    }
    /*===================================
      CREATEFILE HELPER
      ===================================*/
    private static void createFile(String op) {
        try {
            Files.write(Paths.get(DATEINAME), op.getBytes());
        } catch (IOException e) {}
    }
    
    /*===================================
      stringBuilderToString HELPER
      ===================================*/
    private static String stringBuilderToString(int array[]){
        StringBuilder op = new StringBuilder(array.length * 3);
        for(int e : array) op.append(e).append(BREAK);
        return op.toString();
    }
    
    /*===================================
      READFILE HELPER
      ===================================*/
    private static int[] readFile(int anz){
        try {
            int arr[] = new int[anz];
            Scanner input = new Scanner(new File(DATEINAME));
            
            for (int i = 0; input.hasNext(); i++) arr[i] = input.nextInt();
            return arr;
        } catch (FileNotFoundException e) {}
        return null;
    }
}