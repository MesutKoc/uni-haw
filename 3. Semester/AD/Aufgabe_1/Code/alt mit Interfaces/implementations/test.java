/**
 *   Copyright (C) <2015>  @author Mesut, Igor and Anton
 */

package implementations;
import interfaces.AbstractList;

public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        AbstractList instance = ADTList.create();
        AbstractList instance2 = ADTList.create();
        //test
        
        // [ ].isEmpty() -> true
        //System.out.println(instance.isEmpty());
        //instance.insert(1, 6); //ungültige Position
        instance2.insert(5, 1);
        instance2.insert(6, 2);
        
        //System.out.println(instance.retrieve(1));
        //instance.delete(4);
        //instance.print();
        // [].laenge()
        
        System.out.println(instance2.retrieve(7));
     
        //instance.concat(instance2);
        System.out.println("Länge: "+instance.laenge());
        //System.out.println(instance.find(5));
        
        //[1,2].isEmpty() -> false
        //instance.insert(1, 1);
        //instance.insert(2, 2);
        //System.out.println(instance.isEmpty());
        
        //[1,2].laenge() -> 2
        //System.out.println(instance.laenge());
    }
}
