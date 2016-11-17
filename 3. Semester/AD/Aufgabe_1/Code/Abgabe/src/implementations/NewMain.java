package implementations;

public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ADTList List2 = ADTList.create();
        ADTList new_List = List2.insert(5, 1).insert(6, 2).insert(7, 3);
        new_List.print();
    }
    
}
