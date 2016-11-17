/**
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */

package ADT;

import ADT.ADTArray;
import static ADT.NumberBuilder.*;
import static ADT.AVLTree.*;

import java.io.*;
import java.nio.file.*;

public class Benchmark {
    /*===================================
      CONFIG
     ====================================*/
    private static final String DATEINAME = "zahlen.dat";
    private static final String BENCHMARK_RESULT = "benchmark.csv";
    private static final String DELIMETER = ";";

    // Anzahl der Zahlen (Eingabewerte) für Messungen
    private static final int COUNT[] = { 12, 100, 500, 1000, 5000, 8000};

    /**
     * Die Methode führt unterschiedliche Messungen in
     * Bezug auf insert-Methode in dem AVL Baum aus
     * und zählt die Zugriffe auf die unterliegende Knoten und Blätter und
     * die gesamte Laufzeit. Dazu noch entstehende Rotationen, um den Baum balanciert zu halten.
     */
    public static void main(String args[]) {
        StringBuilder tempResult = new StringBuilder();
        tempResult.append("Zahlen" + DELIMETER +
                "Laufzeit insert fuer zufaellige Zahlen" + DELIMETER +
                "Laufzeit insert fuer linkssortierte Zahlen" + DELIMETER +
                "Laufzeit insert fuer rechtssortierte Zahlen" + DELIMETER +
                "Anzahl der lesenden Zugriffe fuer zufaellige Zahlen" + DELIMETER +
                "Anzahl der schreibenden Zugriffe fuer zufaellige Zahlen" + DELIMETER +
                "Anzahl der linken Rotationen fuer zufaellige Zahlen" + DELIMETER +
                "Anzahl der rechten Rotationen fuer zufaellige Zahlen" + DELIMETER +
                "Anzahl der lesenden Zugriffe fuer linkssortierte Zahlen" + DELIMETER +
                "Anzahl der schreibenden Zugriffe fuer linkssortierte Zahlen" + DELIMETER +
                "Anzahl der linken Rotationen fuer linkssortierte Zahlen" + DELIMETER +
                "Anzahl der rechten Rotationen fuer linkssortierte Zahlen" + DELIMETER +
                "Anzahl der lesenden Zugriffe fuer rechtssortierte Zahlen" + DELIMETER +
                "Anzahl der schreibenden Zugriffe fuer rechtssortierte Zahlen" + DELIMETER +
                "Anzahl der linken Rotationen fuer rechtssortierte Zahlen" + DELIMETER +
                "Anzahl der rechten Rotationen fuer rechtssortierte Zahlen" + "\n");

        for (int e : COUNT) {
            tempResult.append(e + DELIMETER);

            // Messungen der Laufzeit der insert-Methode
            measureInsertionTime(e, tempResult);

            // Messungen der Zugriffe und Rotationen
            measureInsertionAcc(e, tempResult);

            tempResult.append("\n");
        }

        try {
            Files.write(Paths.get(BENCHMARK_RESULT), tempResult.toString().getBytes());
        } catch (IOException e) { e.printStackTrace(); }
    }

    private static void measureInsertionTime(int e, StringBuilder tempResult) {
        generateSortNum(e, false);
        ADTArray arr = importNumFile(DATEINAME);
        tempResult.append(insertWTime(arr) + DELIMETER);

        sortNumLeft(e, false);
        arr = importNumFile(DATEINAME);
        tempResult.append(insertWTime(arr) + DELIMETER);

        sortNumRight(e, false);
        arr = importNumFile(DATEINAME);
        tempResult.append(insertWTime(arr) + DELIMETER);
    }

    private static void measureInsertionAcc(int e, StringBuilder tempResult) {

        generateSortNum(e, false);
        ADTArray arr = importNumFile(DATEINAME);
        long results[] = insertWAcc(arr);
        String readAccCount = results[0] + "";
        String writeAccCount = results[1] + "";
        String leftRotCount = results[2] + "";
        String rightRotCount = results[3] + "";

        tempResult.append(readAccCount + DELIMETER + writeAccCount + DELIMETER + leftRotCount + DELIMETER + rightRotCount + DELIMETER);

        sortNumLeft(e, false);
        arr = importNumFile(DATEINAME);
        results = insertWAcc(arr);
        readAccCount = results[0] + "";
        writeAccCount = results[1] + "";
        leftRotCount = results[2] + "";
        rightRotCount = results[3] + "";

        tempResult.append(readAccCount + DELIMETER + writeAccCount + DELIMETER + leftRotCount + DELIMETER + rightRotCount + DELIMETER);

        sortNumRight(e, false);
        arr = importNumFile(DATEINAME);
        results = insertWAcc(arr);
        readAccCount = results[0] + "";
        writeAccCount = results[1] + "";
        leftRotCount = results[2] + "";
        rightRotCount = results[3] + "";

        tempResult.append(readAccCount + DELIMETER + writeAccCount + DELIMETER + leftRotCount + DELIMETER + rightRotCount + DELIMETER);
    }
}