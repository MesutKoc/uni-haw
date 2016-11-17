/**
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */

package ADT;

public class NewMain {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int array1[] = { 1, 2, 3, 4, 5 };
        AVLTree tree = AVLTree.create();

        for( int i = 0; i < 5; i++) tree.insert(i);
    }
}
