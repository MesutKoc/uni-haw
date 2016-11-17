/**
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */

package Sorter;

import ADT.ADTArray;
import static Sorter.NumberBuilder.*;
import static Sorter.Sorter.*;

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

    // Wahl des Pivots
    private enum PIVOT {
        Left(1),
        Right(2),
        Random(3),
        Median(4);

        private int value;

        private PIVOT(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Die Methode führt unterschiedliche Messungen in
     * Bezug auf Insertionsort und Quicksort Algorithmen aus
     * und zählt die Zugriffe auf das unterliegende Array und
     * die gesamte Laufzeit.
     */
    public static void main(String args[]) {
        StringBuilder tempResult = new StringBuilder();
        tempResult.append("Zahlen" + DELIMETER +
                "Laufzeit Insertionsort fuer zufaellige Zahlen" + DELIMETER +
                "Laufzeit Insertionsort fuer linkssortierte Zahlen" + DELIMETER +
                "Laufzeit Insertionsort fuer rechtssortierte Zahlen" + DELIMETER +
                "Laufzeit Quicksort (Pivot links) fuer zufaellige Zahlen" + DELIMETER +
                "Laufzeit Insertionsort in Quicksort (Pivot links) fuer zufaellige Zahlen" + DELIMETER +
                "Laufzeit Quicksort (Pivot links) fuer linkssortierte Zahlen" + DELIMETER +
                "Laufzeit Insertionsort in Quicksort (Pivot links) fuer linkssortierte Zahlen" + DELIMETER +
                "Laufzeit Quicksort (Pivot links) fuer rechtssortierte Zahlen" + DELIMETER +
                "Laufzeit Insertionsort in Quicksort (Pivot links) fuer rechtssortierte Zahlen" + DELIMETER +
                "Laufzeit Quicksort (Pivot rechts) fuer zufaellige Zahlen" + DELIMETER +
                "Laufzeit Insertionsort in Quicksort (Pivot rechts) fuer zufaellige Zahlen" + DELIMETER +
                "Laufzeit Quicksort (Pivot rechts) fuer linkssortierte Zahlen" + DELIMETER +
                "Laufzeit Insertionsort in Quicksort (Pivot rechts) fuer linkssortierte Zahlen" + DELIMETER +
                "Laufzeit Quicksort (Pivot rechts) fuer rechtssortierte Zahlen" + DELIMETER +
                "Laufzeit Insertionsort in Quicksort (Pivot rechts) fuer rechtssortierte Zahlen" + DELIMETER +
                "Laufzeit Quicksort (Pivot random) fuer zufaellige Zahlen" + DELIMETER +
                "Laufzeit Insertionsort in Quicksort (Pivot random) fuer zufaellige Zahlen" + DELIMETER +
                "Laufzeit Quicksort (Pivot random) fuer linkssortierte Zahlen" + DELIMETER +
                "Laufzeit Insertionsort in Quicksort (Pivot random) fuer linkssortierte Zahlen" + DELIMETER +
                "Laufzeit Quicksort (Pivot random) fuer rechtssortierte Zahlen" + DELIMETER +
                "Laufzeit Insertionsort in Quicksort (Pivot random) fuer rechtssortierte Zahlen" + DELIMETER +
                "Laufzeit Quicksort (Pivot median) fuer zufaellige Zahlen" + DELIMETER +
                "Laufzeit Insertionsort in Quicksort (Pivot median) fuer zufaellige Zahlen" + DELIMETER +
                "Laufzeit Quicksort (Pivot median) fuer linkssortierte Zahlen" + DELIMETER +
                "Laufzeit Insertionsort in Quicksort (Pivot median) fuer linkssortierte Zahlen" + DELIMETER +
                "Laufzeit Quicksort (Pivot median) fuer rechtssortierte Zahlen" + DELIMETER +
                "Laufzeit Insertionsort in Quicksort (Pivot median) fuer rechtssortierte Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Insertionsort fuer zufaellige Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Insertionsort fuer linkssortierte Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Insertionsort fuer rechtssortierte Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Quicksort (Pivot links fuer zufaellige Zahlen)" + DELIMETER +
                "Anzahl der Zugriffe Insertionsort in Quicksort (Pivot links) fuer zufaellige Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Quicksort (Pivot links) fuer linkssortierte Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Insertionsort in Quicksort (Pivot links) fuer linkssortierte Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Quicksort (Pivot links) fuer rechtssortierte Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Insertionsort in Quicksort (Pivot links) fuer rechtssortierte Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Quicksort (Pivot rechts) fuer zufaellige Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Insertionsort in Quicksort (Pivot rechts) fuer zufaellige Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Quicksort (Pivot rechts) fuer linkssortierte Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Insertionsort in Quicksort (Pivot rechts) fuer linkssortierte Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Quicksort (Pivot rechts) fuer rechtssortierte Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Insertionsort in Quicksort (Pivot rechts) fuer rechtssortierte Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Quicksort (Pivot random) fuer zufaellige Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Insertionsort in Quicksort (Pivot random) fuer zufaellige Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Quicksort (Pivot random) fuer linkssortierte Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Insertionsort in Quicksort (Pivot random) fuer linkssortierte Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Quicksort (Pivot random) fuer rechtssortierte Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Insertionsort in Quicksort (Pivot random) fuer rechtssortierte Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Quicksort (Pivot median) fuer zufaellige Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Insertionsort in Quicksort (Pivot median) fuer zufaellige Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Quicksort (Pivot median) fuer linkssortierte Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Insertionsort in Quicksort (Pivot median) fuer linkssortierte Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Quicksort (Pivot median) fuer rechtssortierte Zahlen" + DELIMETER +
                "Anzahl der Zugriffe Insertionsort in Quicksort (Pivot median) fuer rechtssortierte Zahlen" + "\n");

        for (int e : COUNT) {
            tempResult.append(e + DELIMETER);

            // Messungen der Laufzeit der Algorithmen
            measureInsertionTime(e, tempResult);
            measureQuickTime(e, PIVOT.Left, tempResult);
            measureQuickTime(e, PIVOT.Right, tempResult);
            measureQuickTime(e, PIVOT.Random, tempResult);
            measureQuickTime(e, PIVOT.Median, tempResult);

            // Messungen der Zugriffe auf das unterliegende Array während des jeweiligen Algorithmus
            measureInsertionAcc(e, tempResult);
            measureQuickAcc(e, PIVOT.Left, tempResult);
            measureQuickAcc(e, PIVOT.Right, tempResult);
            measureQuickAcc(e, PIVOT.Random, tempResult);
            measureQuickAcc(e, PIVOT.Median, tempResult);

            tempResult.append("\n");
        }

        try {
            Files.write(Paths.get(BENCHMARK_RESULT), tempResult.toString().getBytes());
        } catch (IOException e) { e.printStackTrace(); }
    }

    private static void measureInsertionTime(int e, StringBuilder tempResult) {
        generateSortNum(e);
        ADTArray arr = importNumFile(DATEINAME);
        tempResult.append(insertionSortWTime(arr, 0, arr.lengthA()-1) + DELIMETER);

        sortNumLeft(e);
        arr = importNumFile(DATEINAME);
        tempResult.append(insertionSortWTime(arr, 0, arr.lengthA()-1) + DELIMETER);

        sortNumRight(e);
        arr = importNumFile(DATEINAME);
        tempResult.append(insertionSortWTime(arr, 0, arr.lengthA()-1) + DELIMETER);
    }

    private static void measureQuickTime(int e, PIVOT method, StringBuilder tempResult) {

            generateSortNum(e);
            ADTArray arr = importNumFile(DATEINAME);
            long results[] = quickSortWTime(arr, method.getValue());
            String quickSortTime = results[0] + "";
            String insertionSortTime = results[1] + "";

            tempResult.append(quickSortTime + DELIMETER + insertionSortTime + DELIMETER);

            sortNumLeft(e);
            arr = importNumFile(DATEINAME);
            results = quickSortWTime(arr, method.getValue());
            quickSortTime = results[0] + "";
            insertionSortTime = results[1] + "";

            tempResult.append(quickSortTime + DELIMETER + insertionSortTime + DELIMETER);

            sortNumRight(e);
            arr = importNumFile(DATEINAME);
            results = quickSortWTime(arr, method.getValue());
            quickSortTime = results[0] + "";
            insertionSortTime = results[1] + "";

            tempResult.append(quickSortTime + DELIMETER + insertionSortTime + DELIMETER);
    }

    private static void measureInsertionAcc(int e, StringBuilder tempResult) {

        generateSortNum(e);
        ADTArray arr = importNumFile(DATEINAME);

        tempResult.append(insertionSortWAcc(arr, 0, arr.lengthA()-1) + DELIMETER);

        sortNumLeft(e);
        arr = importNumFile(DATEINAME);

        tempResult.append(insertionSortWAcc(arr, 0, arr.lengthA()-1) + DELIMETER);

        sortNumRight(e);
        arr = importNumFile(DATEINAME);

        tempResult.append(insertionSortWAcc(arr, 0, arr.lengthA()-1) + DELIMETER);
    }

    private static void measureQuickAcc(int e, PIVOT method, StringBuilder tempResult) {

            generateSortNum(e);
            ADTArray arr = importNumFile(DATEINAME);
            long results[] = quickSortWAcc(arr, method.getValue());
            String quickSortAcc = results[0] + "";
            String insertionSortAcc = results[1] + "";

            tempResult.append(quickSortAcc + DELIMETER + insertionSortAcc + DELIMETER);

            sortNumLeft(e);
            arr = importNumFile(DATEINAME);
            results = quickSortWAcc(arr, method.getValue());
            quickSortAcc = results[0] + "";
            insertionSortAcc = results[1] + "";

            tempResult.append(quickSortAcc + DELIMETER + insertionSortAcc + DELIMETER);

            sortNumRight(e);
            arr = importNumFile(DATEINAME);
            results = quickSortWAcc(arr, method.getValue());
            quickSortAcc = results[0] + "";
            insertionSortAcc = results[1] + "";

            tempResult.append(quickSortAcc + DELIMETER + insertionSortAcc + DELIMETER);
    }
}