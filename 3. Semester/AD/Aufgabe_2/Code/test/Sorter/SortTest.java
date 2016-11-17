package Sorter;

import ADT.ADTArray;
import static Sorter.Sorter.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class SortTest {
    private ADTArray arr0,
                     arr0sorted,
                     arr1,
                     arr1sorted,
                     arr2leftunsorted,
                     arr2leftsorted,
                     arr3rightunsorted,
                     arr3rightsorted,
                     arr4with5kelements,
                     arr4with5kelements_Sorted,
                     arr5sorted,
                     arr6sorted,
                     arr7sorted,
                     arr8sorted,
                     arr5unsorted,
                     arr6unsorted,
                     arr7unsorted,
                     arr8unsorted;
                     
    
    private NumberBuilder leftsorted, 
                          rightsorted,
                          easysorted;

    @Before
    public void setUp() throws Exception {
        exTestInit();
    }
    
    private void initArray(){
        //arr0 = mapper.get("arr0");
        leftsorted = NumberBuilder.getInstance();
        //arr 0
        arr0       = ADTArray.initA();
        arr0sorted = ADTArray.initA();
       
        // arr1
        arr1       = ADTArray.initA();
        arr1sorted = ADTArray.initA();
        // arr2 left sorted
        arr2leftsorted   = ADTArray.initA();
        arr2leftunsorted = ADTArray.initA();
        // arr3 right sorted
        arr3rightunsorted = ADTArray.initA();
        arr3rightsorted   = ADTArray.initA();
        // arr4 easy sorted
        arr4with5kelements        = ADTArray.initA();
        arr4with5kelements_Sorted = ADTArray.initA();
        
        arr5sorted = ADTArray.initA();
        arr5unsorted = ADTArray.initA();
        
        arr6sorted = ADTArray.initA();
        arr6unsorted = ADTArray.initA();
        
        arr7sorted = ADTArray.initA();
        arr7unsorted = ADTArray.initA();
        
        arr8sorted = ADTArray.initA();
        arr8unsorted = ADTArray.initA();
        
        
    }
    
    private void exTestInit() throws Exception {
        // init Arrays and vars
        initArray();
        
        // arr0
        arr0       = importNumFile("zahlen_easy.dat");
        arr0sorted = importNumFile("zahlen_easy.dat");
        arr0sorted = insertionSort_(arr0sorted, 0, arr0sorted.lengthA());
        
        
        // arr1
        arr1.setA(0, 0);
        arr1sorted.setA(0, 0);
         
        // arr2 with left sorted
        leftsorted.sortNumLeft(100);
        arr2leftunsorted = importNumFile("zahlen_left.dat");
        arr2leftsorted = importNumFile("zahlen_left.dat");
        arr2leftsorted = insertionSort_(arr2leftsorted, 0, arr2leftsorted.lengthA());
        
        // arr3 with right sorted
        rightsorted.sortNumRight(100);
        arr3rightunsorted = importNumFile("zahlen_right.dat");
        arr3rightsorted   = importNumFile("zahlen_right.dat");
        arr3rightsorted   = insertionSort_(arr3rightsorted, 0, arr3rightsorted.lengthA());
        
        // arr4 with 5.000 Elements
        easysorted.generateSortNum(500);
        arr4with5kelements        = importNumFile("zahlen.dat");
        arr4with5kelements_Sorted = importNumFile("zahlen.dat");
        arr4with5kelements_Sorted = insertionSort_(arr4with5kelements_Sorted, 0, 
                                                    arr4with5kelements_Sorted.lengthA());
        
        arr5unsorted = importNumFile("zahlen.dat");
        arr5sorted = importNumFile("zahlen.dat");
        arr5sorted = quickSort_(arr5unsorted,1);
        
        
        arr2leftunsorted = importNumFile("zahlen_left.dat");
        arr2leftsorted = importNumFile("zahlen_left.dat");
        arr2leftsorted = quickSort_(arr2leftunsorted,2);
        
        arr6unsorted = importNumFile("zahlen.dat");
        arr6sorted = importNumFile("zahlen.dat");
        arr6sorted = quickSort_(arr6unsorted,4);
        
        arr7unsorted = importNumFile("zahlen.dat");
        arr7sorted = importNumFile("zahlen.dat");
        arr7sorted = quickSort_(arr7unsorted,3);
        
        easysorted.generateSortNum(1000);
        arr8unsorted = importNumFile("zahlen.dat");
        arr8sorted = importNumFile("zahlen.dat");
        arr8sorted = quickSort_(arr8unsorted,2);
                
    }
    
    @Test
    public void testInsertionSort() throws Exception {
        assertEquals(insertionSort_(arr0, 0, arr0.lengthA()), arr0sorted);
        assertEquals(insertionSort_(arr1, 0, 0), arr1sorted);
        assertEquals(insertionSort_(arr2leftunsorted, 0, arr2leftunsorted.lengthA()), arr2leftsorted);
        assertEquals(insertionSort_(arr3rightunsorted, 0, arr3rightunsorted.lengthA()), arr3rightsorted);
        assertEquals(insertionSort_(arr4with5kelements, 0, arr4with5kelements.lengthA()), arr4with5kelements_Sorted);
    }
    
    
    @Test
    public void testquicksort() throws Exception{
        
        assertEquals(quickSort_(arr5unsorted,1),arr5sorted);
        assertEquals(quickSort_(arr2leftunsorted,2),arr2leftsorted);
        assertEquals(quickSort_(arr5unsorted,3),arr5sorted);
        assertEquals(quickSort_(arr6unsorted,4),arr6sorted);
        assertEquals(quickSort_(arr8unsorted,2),arr8sorted);
        
    }
    
    /*===================================================================
        HELPER!
     ====================================================================
    */
    private ADTArray quickSort_(ADTArray array, int pivot){
        quickSort(array,pivot);
        return array;
    }
    
    private ADTArray insertionSort_(ADTArray array, int begin, int end) {
        insertionSort(array, begin, end);
        return array;
    }
    
    private ADTArray importNumFile(String datname){
        try {
            ADTArray arr = ADTArray.initA();
            Scanner input = new Scanner(new File(datname));

            for (int i = 0; input.hasNext(); i++) arr.setA(i, input.nextInt());
            
            return arr;
        } catch (FileNotFoundException e) {e.printStackTrace();}
        return null;
    } 
}
