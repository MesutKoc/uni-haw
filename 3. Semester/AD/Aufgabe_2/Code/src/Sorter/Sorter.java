/**
 *   Copyright (C) <2015>  @author Anton, Igor und Mesut
 */
package Sorter;

import ADT.ADTArray;

public class Sorter {
    private static final int QUICKSORT_THRESHOLD = 11;
    
    /*===================================
      PRIVATE KONSTRUKTOR
      ===================================*/
    private Sorter(){}


    /*===================================
      Insertionsort Algorithmus
      pre_check: Array ist nicht leer, start ist >= 0 und end ist < Länge des Arrays
      ===================================*/
    public static void insertionSort(ADTArray arr, int start, int end) {
        if ( arr == null || !checkNat(start) || !checkNat(end) || end >= arr.lengthA()) return;
        
        for (int i = start + 1; i <= end; i++) {
            int j = i;
            int tmp = arr.getA(i);
        
            while ((arr.getA(j-1) > tmp) && (j > start)) {
                arr.setA(j, arr.getA(j-1));
                j--;
            }
            arr.setA(j, tmp);
        }
    }


    /*===================================
      Quicksort Algorithmus
      pre_check: Array ist nicht leer + Pivot ist bestimmt (von 1 bis 4)
      Bemerkung: wenn es die Anzahl der Elementen in einem Array <= 12 ist (THRESHOLD), dann Insertionsort.
      ===================================*/
    public static void quickSort(ADTArray arr, int pivotMethod) {
        if ((pivotMethod >= 1) && (pivotMethod <= 4)) quickSort(arr, pivotMethod, 0, arr.lengthA()-1);
    }

    private static void quickSort(ADTArray arr, int pivotMethod, int start, int end) {
        if (start < end) {
            int pivotIndex = calculatePivot(arr, pivotMethod, start, end);
            int i = quickSwap(arr, pivotIndex, start, end);
            if (diff(start, i-1) <= QUICKSORT_THRESHOLD) {
                insertionSort(arr, start, i-1);
            } else
                quickSort(arr, pivotIndex, start, i-1);

            if (diff(i+1, end) <= QUICKSORT_THRESHOLD){
                insertionSort(arr, i+1, end);
            } else
                quickSort(arr, pivotIndex, i+1, end);
        }
    }

    private static int quickSwap(ADTArray arr, int pivotIndex, int start, int end) {

        int i = start;
        int j = end - 1;
        swap(arr, pivotIndex, end);

        while (i <= j) {
            while (arr.getA(i) <= arr.getA(pivotIndex) && i < end)
                i++;
            while (arr.getA(j) > arr.getA(pivotIndex) && j >= start)
                j--;
            if (i < j)
                swap(arr, i,j);
        }
        swap(arr, i, end);
        return i;
    }


    /*===================================
      Messung der Laufzeit für den Insertionsort Algorithmus
      ===================================*/
    public static long insertionSortWTime(ADTArray arr, int start, int end) {
        long startTime = System.currentTimeMillis();
        insertionSort(arr, start, end);
        return System.currentTimeMillis() - startTime;
    }

    /*===================================
      Messung der Laufzeit für den Quicksort Algorithmus
      Bemerkung: als Ergebnis kommen die zwei Werte: die Zeit des Quicksort Algorithmus (insgesamt)
      und auch die Zeit nur für internen Insertionsort Aufruf (wenn es weniger als 12 Elemente sortiert werden müssen)
      ===================================*/
    public static long[] quickSortWTime(ADTArray arr, int pivotMethod) {
        if (pivotMethod >= 1 && pivotMethod <= 4) {
            long startTime = System.currentTimeMillis();
            long insertionSortTime = quickSortWTime(arr, pivotMethod, 0, arr.lengthA()-1);
            long quickSortTime = System.currentTimeMillis() - startTime;
            return new long[] { quickSortTime, insertionSortTime };
        }
        else return new long[] { 0, 0 };
    }

    private static long quickSortWTime(ADTArray arr, int pivotMethod, int start, int end) {
        long insertionSortTime = 0;

        if (start < end) {
            int pivotIndex = calculatePivot(arr, pivotMethod, start, end);
            int i = quickSwap(arr, pivotIndex, start, end);
            if (diff(start, i-1) <= QUICKSORT_THRESHOLD) {
                insertionSortTime += insertionSortWTime(arr, start, i-1);
            } else
                insertionSortTime += quickSortWTime(arr, pivotIndex, start, i-1);

            if (diff(i+1, end) <= QUICKSORT_THRESHOLD){
                insertionSortTime += insertionSortWTime(arr, i+1, end);
            } else
                insertionSortTime += quickSortWTime(arr, pivotIndex, i+1, end);
        }

        return insertionSortTime;
    }

    /*===================================
      Messung der Zugriff Anzahl für den Insertionsort Algorithmus
      pre_checks: wie bei insertionSort
      Bemerkung: als Ergebnis kommt die Anzahl der Zugriffe auf das internen Array
      ===================================*/
    public static long insertionSortWAcc(ADTArray arr, int start, int end) {
        if ( arr == null || !checkNat(start) || !checkNat(end) || end >= arr.lengthA()) return 0;

        long accCount = 0;
        for (int i = start + 1; i <= end; i++) {
            int j = i;
            int tmp = arr.getA(i);
            accCount++;

            accCount++;
            while ((arr.getA(j-1) > tmp) && (j > start)) {
                arr.setA(j, arr.getA(j-1));
                accCount += 2;
                j--;
            }
            arr.setA(j, tmp);
            accCount++;
        }

        return accCount;
    }

    /*===================================
      Messung der Zugriff Anzahl für den Quicksort Algorithmus
      pre_checks: wie bei Quicksort
      Bemerkung: als Ergebnis kommen die zwei Werte: die Zugriff Anzahl des Quicksort Algorithmus (insgesamt)
      und auch die Zugriff Anzahl nur für internen Insertionsort Aufruf (wenn es weniger als 12 Elemente sortiert werden müssen)
      ===================================*/
    public static long[] quickSortWAcc(ADTArray arr, int pivotMethod) {
        if (pivotMethod >= 1 && pivotMethod <= 4) {
            long quickSortAccCount[] = quickSortWAcc(arr, pivotMethod, 0, arr.lengthA()-1);
            return quickSortAccCount;
        }

        else return new long[] {0, 0};
    }

    private static long[] quickSortWAcc(ADTArray arr, int pivotMethod, int start, int end) {
        long quickSortAccCount = 0;
        long insertSortAccCount = 0;

        if (start < end) {
            int pivotIndex = calculatePivot(arr, pivotMethod, start, end);
            if (pivotMethod == 4) quickSortAccCount += 3;

            int swapCount[] = quickSwapWAcc(arr, pivotIndex, start, end);
            int i = swapCount[0];
            insertSortAccCount += swapCount[1];

            if (diff(start, i-1) <= QUICKSORT_THRESHOLD) {
                insertSortAccCount += insertionSortWAcc(arr, start, i-1);
            } else {
                long quickAccCount[] = quickSortWAcc(arr, pivotIndex, start, i-1);
                insertSortAccCount += quickAccCount[0];
                quickSortAccCount += quickAccCount[1];
            }

            if (diff(i+1, end) <= QUICKSORT_THRESHOLD){
                insertSortAccCount += insertionSortWAcc(arr, i+1, end);
            } else {
                long quickAccCount[] = quickSortWAcc(arr, pivotIndex, i+1, end);
                insertSortAccCount += quickAccCount[0];
                quickSortAccCount += quickAccCount[1];
            }
        }
        return new long[] {quickSortAccCount, insertSortAccCount};
    }

    private static int[] quickSwapWAcc(ADTArray arr, int pivotIndex, int start, int end) {

        int i = start;
        int j = end - 1;
        int swapCount = 0;

        swap(arr, pivotIndex, end);
        swapCount += 4;

        while (i <= j) {
            while (arr.getA(i) <= arr.getA(pivotIndex) && i < end) {
                i++;
                swapCount += 2;
            }
            while (arr.getA(j) > arr.getA(pivotIndex) && j >= start) {
                j--;
                swapCount += 2;
            }
            if (i < j) {
                swap(arr, i, j);
                swapCount += 4;
            }
        }
        swap(arr, i, end);
        swapCount += 4;
        return new int[] {i, swapCount};
    }

    /*===================================
      ALL HELPERS
      ===================================*/
    private static int randomInRange(int min, int max)
    {
        int range = Math.abs(max - min) + 1;
        return (int)(Math.random() * range) + (min <= max ? min : max);
    }

    private static boolean checkNat(int num) {
        return num >= 0;
    }
    
    //Hilfsmethode Swap
    private static void swap (ADTArray arr, int pos1, int pos2) {
        int temp = arr.getA(pos1);
        arr.setA(pos1, arr.getA(pos2));
        arr.setA(pos2, temp);
    }
    
    //Hilfsmethode für die Länge des Arrays.
    private static int diff(int a, int b) {
        return Math.abs(a-b);
    }

    private static int medianOf3(ADTArray arr, int start, int end) {

        int first = arr.getA(start);
        int mid = arr.getA((start+end) / 2);
        int last = arr.getA(end);
        return ((first-mid)*(mid-last) > -1 ? (start+end) / 2 : ((first-mid)*(first-last) < 1 ? 0 : arr.lengthA()-1));
    }

    private static int calculatePivot(ADTArray arr, int pivotMethod, int start, int end) {
        int tmp = 0;

        switch (pivotMethod) {
            case 1:
                tmp = start;
                break;
            case 2:
                tmp = end;
                break;
            case 3:
                tmp = randomInRange(start, end);
                break;
            case 4:
                tmp = medianOf3(arr, start, end);
                break;
        }

        return tmp;
    }
}