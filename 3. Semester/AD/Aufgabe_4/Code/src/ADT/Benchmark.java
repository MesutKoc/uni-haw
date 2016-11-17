/**
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */

package ADT;

import static ADT.ADTHashmap.*;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Benchmark {
    /*===================================
      CONFIG
     ====================================*/
    private static final String DATEINAME[] = {"texta.txt", "textb.txt"};
    private static final String BENCHMARK_RESULT = "benchmark.csv";
    private static final String DELIMETER = ";";

    private static final int PRIMES[] = {40, 41, 103};

    /**
     * Die Methode führt unterschiedliche Messungen in
     * Bezug auf find- und insert-Methode in dem ADT Hashmap Baum aus
     * und zählt die gesamte Laufzeit.
     */
    public static void main(String args[]) {
        StringBuilder tempResult = new StringBuilder();
        tempResult.append("Importierter Text" + DELIMETER +
                "gewaehlte Groesse der Hash Tabelle" + DELIMETER +
                "Durchschnittliche Laufzeit fuer insert mit Hilfe lineares Sondieren" + DELIMETER +
                "Gesamte Laufzeit fuer insert mit Hilfe lineares Sondieren" + DELIMETER +
                "Durchschnittliche Laufzeit fuer insert mit Hilfe quadratisches Sondieren" + DELIMETER +
                "Gesamte Laufzeit fuer insert mit Hilfe quadratisches Sondieren" + DELIMETER +
                "Durchschnittliche Laufzeit fuer insert mit Hilfe Double-Hashing" + DELIMETER +
                "Gesamte Laufzeit fuer insert mit Hilfe Double-Hashing" + DELIMETER +
                "Durchschnittliche Laufzeit fuer find mit Hilfe lineares Sondieren" + DELIMETER +
                "Gesamte Laufzeit fuer find mit Hilfe lineares Sondieren" + DELIMETER +
                "Durchschnittliche Laufzeit fuer find mit Hilfe quadratisches Sondieren" + DELIMETER +
                "Gesamte Laufzeit fuer find mit Hilfe quadratisches Sondieren" + DELIMETER +
                "Durchschnittliche Laufzeit fuer find mit Hilfe Double-Hashing" + DELIMETER +
                "Gesamte Laufzeit fuer find mit Hilfe Double-Hashing" + DELIMETER + "\n");

        for (String file : DATEINAME) {
            ArrayList<String> parsedWords = TextParser.readSourceFile(file);

            for (int hs : PRIMES) {
                tempResult.append(file + DELIMETER + hs + DELIMETER);

                // Messungen der Laufzeit der insert-Methode
                ADTHashmap linearHash = measureInsertionTime(parsedWords, hs, ADTHashmap.Strategy.L, tempResult);
                ADTHashmap quadrHash = measureInsertionTime(parsedWords, hs, ADTHashmap.Strategy.Q, tempResult);
                ADTHashmap doubleHash = measureInsertionTime(parsedWords, hs, ADTHashmap.Strategy.B, tempResult);

                // Messungen der Laufzeit der find-Methode
                measureFindTime(parsedWords, linearHash, tempResult);
                measureFindTime(parsedWords, quadrHash, tempResult);
                measureFindTime(parsedWords, doubleHash, tempResult);

                tempResult.append("\n");
            }
        }

        try {
            Files.write(Paths.get(BENCHMARK_RESULT), tempResult.toString().getBytes());
        } catch (IOException e) { e.printStackTrace(); }
    }

    private static ADTHashmap measureInsertionTime(ArrayList<String> parsedWords, int hashTableSize, ADTHashmap.Strategy strategy, StringBuilder tempResult) {
        long resultTime = 0;
        ADTHashmap words = create(hashTableSize, strategy);

        for (String word : parsedWords) {
            long startTime = System.nanoTime();
            words.insert(word);
            long endTime = System.nanoTime();
            resultTime += endTime - startTime;
        }

        long averageTime = 0;
        if (parsedWords.size() != 0)
            averageTime = resultTime / parsedWords.size();

        tempResult.append(averageTime + DELIMETER + resultTime + DELIMETER);

        return words;
    }

    private static void measureFindTime(ArrayList<String> parsedWords, ADTHashmap words, StringBuilder tempResult) {
        long resultTime = 0;

        for (String word : parsedWords) {
            long startTime = System.nanoTime();
            words.find(word);
            long endTime = System.nanoTime();
            resultTime += endTime - startTime;
        }

        long averageTime = 0;
        if (parsedWords.size() != 0)
            averageTime = resultTime / parsedWords.size();

        tempResult.append(averageTime + DELIMETER + resultTime + DELIMETER);
    }
}