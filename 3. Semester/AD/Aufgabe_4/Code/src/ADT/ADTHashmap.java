/**
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */

package ADT;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class ADTHashmap {
    /* ============================
     * CONFIG
     * ============================
     */
    private List<Integer> primes;
    private static final String EXPORT_RESULT = "words.csv",
                                DELIMETER = ";";

    private int N,   // number of entries in the symbol table
                M,  // size of hash table
                FACTOR = 100;
    
    private String[] keys;
    private int[] vals;

     /* ============================
      *  Option to choose strategy to 
      *  deal with collisions
      * ============================
      */
    public enum Strategy {
        L(1),
        Q(2),
        B(3);

        private int value;

        private Strategy(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    Strategy hashType;
    
    /* =====================================================
     * Function: ADTHashmap(int capacity, Strategy strategy)
     * =====================================================
     */ 
    private ADTHashmap(int capacity, Strategy strategy) {
        M = calculatePrime(capacity); 
        keys = new String[M];
        vals = new int[M];
        for (int i = 0; i < M; i++) {
            keys[i] = null;
            vals[i] = 0;
        }
        hashType = strategy;
    }
    /* =================================================
     * Function: create(int capacity, Strategy strategy)
     * =================================================
     */ 
    public static ADTHashmap create(int capacity, Strategy strategy) {
        return new ADTHashmap(capacity, strategy);
    }
    /* ============================
     * Function: size()
     * ============================
     */ 
    public int size() { return N; }
    /* ============================
     * Function: sizeOfTable()
     * ============================
     */ 
    public int sizeOfTable() { return M; }
    /* ============================
     * Function: isEmpty()
     * ============================
     */ 
    public boolean isEmpty() { return size() == 0; }
    /* ==============================
     * Function: contains(String key)
     * ==============================
     */ 
    public boolean contains(String key) {
        if (key == null) throw new NullPointerException("Argument can not be null!");
        return find(key) != 0;
    }
    /* =================================================
     * Function: hashHS()
     * Description: Hashing Function with Horner-Schema
     * =================================================
     */ 
    private int hashHS(String key) {
        // h(k) = k mod m = ( k_0+37*k_1+37^2*k_2+...+37^n*k_n ) mod m
        int hashVal = 0;

        for (int i = 0; i < key.length(); i++) 
            hashVal = 37 * hashVal + key.charAt(i);
        
        hashVal %= M;
        
        if (hashVal < 0) hashVal += M;
        return hashVal;
    }
    /* =================================================
     * Function: hashSecondHS()
     * Description: 2nd Hashing Function for Double Hashing
     * =================================================
     */ 
    private int hashSecondHS(String key) {
        // h'(k) = 1+(k mod m') mit m' = m-2
        int hashVal = 0;
        
        for (int i = 0; i < key.length(); i++) hashVal = 37 * hashVal + key.charAt(i);

        hashVal = 1 + (hashVal % (M-2));
        
        if (hashVal < 0) hashVal += M-2;
        return hashVal;
    }
    /* =================================================
     * Function: rezise()
     * Description: check if resize of hash table is needed
     * =================================================
     */ 
    private void resize() {
        Collection<Integer> newSizeCol = primes(M * FACTOR);
        int newSize = newSizeCol.size(); // Dummy to intialize
       
        for(int i : newSizeCol) {
            if (i > 2 * M && isPrime(i)) {
                newSize = i;
                break;    
            }
        }
        resize(newSize);
    }

    /* =================================================
     * Function: rezise(int capacity)
     * Description: resizes the hash table to the given 
     *              capacity by re-hashing all of the keys
     * =================================================
     */
    private void resize(int capacity) {
        ADTHashmap temp = new ADTHashmap(capacity, hashType);
        for (int i = 0; i < M; i++)
            if (keys[i] != null) temp.insert(keys[i], vals[i]);
           
        keys = temp.keys;
        vals = temp.vals;
        M    = temp.M;
    }
    /* =================================================
     * Function: getPos(String key)
     * =================================================
     */
    private int getPos(String key) {
        int i = hashHS(key);

        switch (hashType) {
            case L:
                while (keys[i] != null) {
                    if (keys[i].equals(key)) return i;
                    i = (i - 1) % M;
                    if (i < 0) i += M;
                }
                break;
            case Q:
                int qOffset = 1;
                int j = 1;
                while (keys[i] != null) {
                    if (keys[i].equals(key)) return i;
                    qOffset = (int) (Math.pow(Math.round(j / 2.0), 2.0) * Math.pow((-1.0), j));
                    i = (i - qOffset) % M;
                    if (i < 0) i += M;
                    j++;
                }
                break;
            case B:
                while (keys[i] != null) {
                    if (keys[i].equals(key)) return i;
                    int newIndex = (i - hashSecondHS(key)) % M;
                    if (newIndex < 0) newIndex += M;
                    int oldIndex = (i - hashSecondHS(keys[i])) % M;
                    if (oldIndex < 0) oldIndex += M;
                    if (keys[newIndex] == null || keys[oldIndex] != null)
                        i = newIndex;
                    else {
                        keys[oldIndex] = keys[i];
                        vals[oldIndex] = vals[i];
                        keys[i] = null;
                        vals[i] = 0;
                    }
                }
                break;
        }
        return i;
    }
    /* =================================================
     * Function: insert(String key)
     * =================================================
     */
    public void insert(String key) {
        if (key == null) throw new NullPointerException("Argument can not be null!");
        insert(key, 1);
    }
    /* =================================================
     * Function: insert(String key, int val)
     * =================================================
     */
    private void insert(String key, int val) {
        // double table size if 50% full
        if (N >= M / 2) resize();
       
        int i = getPos(key);

        if (keys[i] != null) {
            vals[i]++;
            return;
        }

        keys[i] = key;
        vals[i] = val;
        N++;
    }
    /* =================================================
     * Function: find(String key)
     * =================================================
     */
    public int find(String key) {
        if (key == null) throw new NullPointerException("Argument can not be null!");
        int i = getPos(key);
        return vals[i];
    }
    /* =================================================
     * Function: export()
     * =================================================
     */
    public void export() {
        StringBuilder tempResult = new StringBuilder();
        tempResult.append("List of Words" + DELIMETER + "count of appearance" + DELIMETER + "\n");

        for (int i=0; i < M; i++) 
            if (keys[i] != null)
                tempResult.append(keys[i]).append(DELIMETER).append(vals[i]).append(DELIMETER + "\n");
       
        try {
            Files.write(Paths.get(EXPORT_RESULT), tempResult.toString().getBytes());
        } catch (IOException e) {}
    }
    /* ======================================
     * Function: calculatePrime(int min)
     * ======================================
     */
    public static int calculatePrime(int min) {
        boolean foundingPrime = false;
        while (!foundingPrime) {
            if (isPrime(min)) foundingPrime = true;
            else              min++;
        }
        return min;
    }
    /* ======================================
     * Function: isPrime(int l)
     * ======================================
     */
    private static boolean isPrime(int l) {
        return l != 1 && getFactors(l).length == 2;
    }
    /* ======================================
     * Function: getFactors(int l)
     * ======================================
     */
    private static int[] getFactors(int l) {
        List list = new LinkedList<>();
        for (int i = 1; i <= l / 2; i++) if (l % i == 0) list.add(i);
        list.add(l);
        
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) result[i] = (int) list.get(i);
        return result;
    }
    /* ======================================
     * Function: primes(int numPrimes)
     * Description: to save all Primes in a List
     * ======================================
     */
    Collection<Integer> primes(int numPrimes) {
        primes = new ArrayList<>(numPrimes);
        IntStream.iterate(2, i -> i + 1).
                filter((int i) -> {
                    return primes.stream().noneMatch((prime) -> (i % prime == 0));
                }).limit(numPrimes).
                forEach(primes::add);
        return primes;
    }
    //======================================================
    // MAIN
    //======================================================
    public static void main(String[] args) {
        if (args.length < 2 || args.length > 2) {
            System.out.println("Please provide 2 following arguments: 1: Hash Strategy and 2: .txt filename");
            System.out.println("Hash Stretegy: L for linear hashing, Q for quadratic hashing, B for Double-Hashing");
            System.out.println("######");
        } else {
            Strategy argStrategy;
            switch (args[0]) {
                case ("L"):
                    argStrategy = Strategy.L;
                    break;
                case ("Q"):
                    argStrategy = Strategy.Q;
                    break;
                case ("B"):
                    argStrategy = Strategy.B;
                    break;
                default:
                    System.out.println("incorrect Hash Strategy, using L as default");
                    argStrategy = Strategy.L;
                    break;
            }
            ArrayList<String> parsedWords = TextParser.readSourceFile(args[1]);
            
            if (parsedWords.size() == 0) {
                System.out.println("no such .txt filename exist");
            }
            else {
                int hashTableSize = 227;
                ADTHashmap words = create(hashTableSize, argStrategy);
                //schreiben
                parsedWords.stream().forEach((word) -> words.insert(word));
                //export
                words.export();
            }
        }
    }
}