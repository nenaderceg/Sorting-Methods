/* 
 * File:   Test.java
 * Author: Nenad Erceg
 *
 * Created on January 21, 2014, 2:39 AM
 */
package sorting;


/**
 *
 * @author Nenad Erceg
 */
public class Test {

    public static final int MAX = 10000;

    /**
     * @param args the command line arguments
     *
     * Driver program to test sorting algorithms
     */
    public static void main(String[] args) {

        int[] a = new int[MAX];

        SortingMethods.fillArray(a);
        SortingMethods.displayArray(a);

        System.out.print("\n\n***********-------SORTED ARRAY--------***********\n");
        
        // start to measure time
        long startTime = System.nanoTime();
        
        //insertionSort(a);
        //insertionSort(a, 0, 90);
        //selectionSort(a);
        //quickSort(a, 0, MAX - 1);

        //iterativeQuickSort(a);
        SortingMethods.shellSort(a);
        
        // end time measure
        long estimatedTime = System.nanoTime() - startTime;

        SortingMethods.displayArray(a);


        if (SortingMethods.isSorted(a)) {
            System.out.println("\n\n**************------SUCCESS--------**************\n\n");
        } else {
            System.out.println("****************------ERORR--------**************\n\n");
        }
        
        System.out.format("Execution time: %,d ns\n", estimatedTime);
    }

    
}
