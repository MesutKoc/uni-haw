/**
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */

package ADT;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AVLTree {

    /* ============================
     * CLASS AVLNode
     * ============================
    */
    protected static class AVLNode {
        protected int value;
        protected AVLNode left;
        protected AVLNode right;
        protected int height;
        
        public AVLNode (int key) {
            this (key, null, null);
        }

        public AVLNode (int key, AVLNode lv, AVLNode rv) {
            value = key;
            left = lv;
            right = rv;
            
        }
    }

    /* ============================
     * CONFIG
     * ============================
    */
    private static final String EXPORT = "tree.dot";
    private static final String EXPORTPNG = "tree.png";
    private static final Integer ALLOWED_BALANCE = 1;

    public AVLNode root;

    /* ============================
     * PRIVATE KONSTRUKTOR
     * ============================
    */
    private AVLTree() { root = null; }

    /* ============================
     * PUBLIC KONSTRUKTOR
     * ============================
    */
    public static AVLTree create(){
        return new AVLTree();
    }

    /* ============================
     * Function: isEmpty()
     * ============================
    */
    public boolean isEmpty() { return root == null; }

    /* ============================
     * Function: high()
     * ============================
    */
    public int high() { return high(root); }

    /* ============================
    * Function: high(AVLNode node)
    * ============================
    */
    public int high(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    /* ============================
     * Function: insert(int key)
     * ============================
    */
    public void insert(int key) {
        root = insert(root, key);
    }

    /* ============================
     * Function: insert(AVLNode node, int key)
     * ============================
    */
    private AVLNode insert(AVLNode node, int key) {
        if (node == null) node = new AVLNode(key);
        
        if (key < node.value)     
            node.left = insert(node.left, key);
        else  if (key > node.value) 
                node.right = insert(node.right, key);
        else ;
        
        return balance(node);
    }
    
    private AVLNode balance(AVLNode node){
        
        if(node == null) return node;
        
        if(high(node.left) - high(node.right) > ALLOWED_BALANCE)
            if(high(node.left.left) >= high(node.left.right))
                node = rotateRight(node);
            else
                node = doubleRotateRight(node);
        else 
        if(high(node.right)- high(node.left) > ALLOWED_BALANCE)
            if(high(node.right.right) >= high(node.right.left))
                node = rotateLeft(node);
            else
                node = doubleRotateLeft(node);
                        
        node.height = Math.max(high(node.left), high(node.right))+1;
        
        return node;
    }

    /* ============================
     * Function: insertWTime(ADTArray input)
     * ============================
    */
    public static long insertWTime(ADTArray input) {
        long startTime = System.nanoTime();

        AVLTree tree = create();
        for (int i = 0; i < input.lengthA(); i++)
            tree.insert(input.getA(i));

        long endTime = System.nanoTime();
        long resultTime = endTime - startTime;

        return resultTime;
    }

    private static long countRead;
    private static long countWrite;
    private static long countLeftRotations;
    private static long countRightRotations;

    /* ============================
    * Function: insertWAcc(ADTArray input)
    * ============================
    */
    public static long[] insertWAcc(ADTArray input) {
        countRead = 0;
        countWrite = 0;
        countLeftRotations = 0;
        countRightRotations = 0;

        AVLTree tree = create();
        for (int i = 0; i < input.lengthA(); i++) {
            tree.root = tree.insertWAcc(tree.root, input.getA(i));
            countWrite++;
        }
        return new long[] { countRead, countWrite, countLeftRotations, countRightRotations };
    }

     /* ============================
     * Function: insertWAcc(AVLNode node, int key)
     * ============================
    */
    private AVLNode insertWAcc(AVLNode node, int key) {
        if (node == null) {
            node = new AVLNode(key);
            countWrite++;
        }

        if (key < node.value) {
            countRead++;
            node.left = insert(node.left, key);
            countWrite++;
        }

        else  if (key > node.value) {
            countRead++;
            node.right = insert(node.right, key);
            countWrite++;
        }
        else ;

        return balanceWAcc(node);
    }

    private AVLNode balanceWAcc(AVLNode node) {
        if(node == null) {
            countRead++;
            return node;
        }

        countRead += 2;
        if(high(node.left) - high(node.right) > ALLOWED_BALANCE)
            if (high(node.left.left) >= high(node.left.right)) {
                node = rotateRight(node);
                countRightRotations++;
                countWrite++;
            }
            else {
                node = doubleRotateRight(node);
                countLeftRotations++;
                countRightRotations++;
                countWrite++;
            }

        else {
            countRead += 2;
            if (high(node.right) - high(node.left) > ALLOWED_BALANCE)
                if (high(node.right.right) >= high(node.right.left)){
                    node = rotateLeft(node);
                    countLeftRotations++;
                    countWrite++;

                }
                else {
                    node = doubleRotateLeft(node);
                    countLeftRotations++;
                    countRightRotations++;
                    countWrite++;
                }
        }

        node.height = Math.max(high(node.left), high(node.right)) + 1;
        countWrite++;
        return node;
    }

    /* ============================
     * Function: delete(int key)
     * ============================
    */
    public void delete(int key) {
        root = delete(root, key);
    }

    /* ============================
     * Function: delete(AVLNode node, int key)
     * ============================
    */
    public AVLNode delete(AVLNode node, int key) {
        if (node == null) return null;

        if (key < node.value) node.left = delete(node.left, key);
            
        else 
            if (key > node.value) node.right = delete(node.right, key);
            
        else {
            if (node.left == null && node.right == null) node = null;
            else if (node.left == null)  node = node.right;
            else if (node.right == null) node = node.left;
            
            else {
                node.value = findMax(node.left).value;
                node.left = delete(node.left, node.value);
            }
        }

        /*if (node != null) {
            int leftHeight  = (node.left != null) ? node.left.height : 0;
            int rightHeight = (node.right!= null) ? node.right.height : 0;
            node.height = Math.max(leftHeight, rightHeight) + 1;
        }*/
        return balance(node);
    }

    /* ============================
     * Function: rotateRight(AVLNode node)
     * ============================
    */
    private AVLNode rotateRight(AVLNode node) {
        AVLNode newNode = node.left;

        node.left = newNode.right;
        newNode.right = node;

        node.height = Math.max(high(node.left), high(node.right)) + 1;
        newNode.height = Math.max(high(newNode.left), node.height) + 1;

        return newNode;
    }

    /* ============================
     * Function: doubleRotateRight(AVLNode node)
     * ============================
    */
    private AVLNode doubleRotateRight(AVLNode node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    /* ============================
     * Function: rotateLeft(AVLNode node)
     * ============================
    */
    private AVLNode rotateLeft(AVLNode node) {
        AVLNode newNode = node.right;

        node.right = newNode.left;
        newNode.left = node;

        node.height = Math.max(high(node.left), high(node.right)) + 1;
        newNode.height = Math.max(high(newNode.right), node.height) + 1;

        return newNode;
    }

    /* ============================
     * Function: doubleRotateLeft(AVLNode node)
     * ============================
    */
    private AVLNode doubleRotateLeft(AVLNode node) {
        node.right = rotateRight (node.right);
        return rotateLeft(node);
    }

    /* ============================
    * Function: rotateRightWAcc(AVLNode node)
    * ============================
    */
    private AVLNode rotateRightWAcc(AVLNode node) {
        AVLNode newNode = node.left;
        countRead++;
        countWrite++;

        node.left = newNode.right;
        countRead++;
        countWrite++;
        newNode.right = node;
        countWrite++;

        node.height = Math.max(high(node.left), high(node.right)) + 1;
        countRead+=2;
        countWrite++;
        newNode.height = Math.max(high(newNode.left), node.height) + 1;
        countRead+=2;
        countWrite++;

        return newNode;
    }

    /* ============================
     * Function: doubleRotateRightWAcc(AVLNode node)
     * ============================
    */
    private AVLNode doubleRotateRightWAcc(AVLNode node) {
        node.left = rotateLeft(node.left);
        countRead++;
        countWrite++;
        return rotateRight(node);
    }

    /* ============================
     * Function: rotateLeftWAcc(AVLNode node)
     * ============================
    */
    private AVLNode rotateLeftWAcc(AVLNode node) {
        AVLNode newNode = node.right;
        countRead++;
        countWrite++;

        node.right = newNode.left;
        countRead++;
        countWrite++;
        newNode.left = node;
        countWrite++;

        node.height = Math.max(high(node.left), high(node.right)) + 1;
        countRead+=2;
        countWrite++;
        newNode.height = Math.max(high(newNode.right), node.height) + 1;
        countRead+=2;
        countWrite++;

        return newNode;
    }

    /* ============================
     * Function: doubleRotateLeftWAcc(AVLNode node)
     * ============================
    */
    private AVLNode doubleRotateLeftWAcc(AVLNode node) {
        node.right = rotateRight (node.right);
        countRead++;
        countWrite++;
        return rotateLeft(node);
    }


    /* ============================
     * Function: treeToInfixList()
     * ============================
    */
    private ArrayList treeToInfixList() {
        ArrayList<AVLNode> export = new ArrayList();
        treeToInfixList(root, export);
        return export;
    }

    /* ============================
     * Function: treeToInfixList(AVLNode node, ArrayList export)
     * ============================
    */
    private void treeToInfixList(AVLNode node, ArrayList export) {
        if (node != null) {
            treeToInfixList(node.left, export);
            export.add(node);
            treeToInfixList(node.right, export);
        }
    }

    /* ============================
     * Function: printNode(AVLNode node, BufferedWriter writer)
     * ============================
    */
    private void printNode(AVLNode node, BufferedWriter writer) throws IOException {
        String arrow = "--";

        try {
            if (node.left != null) {
                writer.write(node.value + arrow + node.left.value + "[label=\"" + node.left.height + "\"];\n");
                printNode(node.left, writer);
            }

            if (node.right != null) {
                writer.write(node.value + arrow + node.right.value + "[label=\"" + node.right.height + "\"];\n");
                printNode(node.right, writer);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    /* ============================
     * Function: print()
     * ============================
    */
    public void print() throws IOException {
        Path outputFile = Paths.get(EXPORT);
        Path outputFilePng = Paths.get(EXPORTPNG);
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {

        //String arrow = "--";
            writer.write("graph G { \n");
        // Iterieren durch den ganzen Baum
            if (root == null)
                writer.write("\n");
            else if (root.right == null && root.left == null)
                writer.write(root.value + ";\n");
            else
                printNode(root, writer);
            writer.write("}\n");
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

       /* try {
            Runtime.getRuntime().exec("dot - Tpng " + outputFile + " > " + outputFilePng);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        */
    }

    /* ============================
     * Function: search(int key)
     * ============================
    */
    private AVLNode search(int key){

        while(root != null) {
            if (root.value == key)
                return root;
            else if (key > root.value)
                root = root.right;
            else
                root = root.left;
        }

        return null;
    }

    /* ============================
     * Function: findMax(AVLNode node)
     * ============================
    */
    private AVLNode findMax(AVLNode node) {
        if (node == null) return node;
        while (node.right != null) node = node.right;
        return node;
    }

    /* ============================
     * Function: equals(Object o)
     * ============================
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof AVLTree)) return false;

        AVLTree object = (AVLTree) o;
        if (high(root) != object.high(object.root)) return false;

        ArrayList<AVLNode> treeAsList = treeToInfixList();
        ArrayList<AVLNode> objectAsList = object.treeToInfixList();
        for (int i = 0; i < treeAsList.size(); i++)
            if (treeAsList.get(i) != objectAsList.get(i)) return false;

        return true;
    }
}
