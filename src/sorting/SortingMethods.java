/* 
 * File:   SortingMethods.java
 * Author: Nenad Erceg
 *
 * Created on January 21, 2014, 2:39 AM
 */
package sorting;

import java.util.Random;
import java.util.Stack;

/**
 *
 * @author nenaderceg
 */
public class SortingMethods {
    
    private static final int HIGH = 934;
    private static final int LOW = 1;
    private static final int VALUE = 28;
    
    public static void selectionSort(int[] a) {
        int min, len;
        len = a.length;
        for (int i = 0; i < len - 1; i++) {
            min = i;

            for (int j = i + 1; j < len; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(i, min, a);
            }
        }
    }

    public static void insertionSort(int[] a) {
        int len = a.length;
        int key, j;
        for (int i = 1; i < len; i++) {
            key = a[i];
            j = i;
            while (j > 0 && a[j - 1] > key) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = key;
        }
    }

    public static void insertionSort(int[] a, int left, int right) {
        int key, j;
        for (int i = left + 1; i <= right; i++) {
            key = a[i];
            j = i;
            while (j > 0 && a[j - 1] > key) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = key;
        }
    }
     //Math.log(n) / Math.log(2)
    public static void shellSort(int[] a) {

        int len = a.length;
        int key, j;
        int t = (int) (Math.log(len/2.0 + 1) / Math.log(2.0));
        
        for (int gap = (int) Math.pow(2, t) - 1; gap >= 1; gap = (int)Math.pow(2, t) - 1) {
            for (int i = gap; i < len; i+= gap) {
                key = a[i];
                j = i;
                while (j >= gap && a[j - gap] > key) {
                    a[j] = a[j - gap];
                    j-= gap;
                }
                a[j] = key;
            }
            t--;
        }
    }

    public static int choosePivot(int a[], int left, int right) {

        int mid = randInt(left, right);

        if (a[left] > a[mid]) {
            swap(left, mid, a);
        }
        if (a[left] > a[right]) {
            swap(left, right, a);
        }
        if (a[mid] < a[right]) {
            swap(mid, right, a);
        }

        swap(mid, right - 1, a);

        assert (a[left] <= a[right]);
        assert (a[right - 1] >= a[right]);

        return a[right];
    }

    /* left  --> Starting index,  right  --> Ending index */
    public static void quickSort(int[] a, int left, int right) {

        // make sure array has at least three elements
        if (left + 2 <= right) {

            // pick three values, sort them, and put pivot into a[right]
            // the smallest of three into a[left] and the largest into A[right-1]
            int pivot = choosePivot(a, left, right);
            // A[left] <= pivot <= A[right] and the pivot = a[right]

            // now partition
            int i = left;
            int j = right - 1;

            while (true) {
                while (a[++i] < pivot) {
                }
                while (pivot < a[--j]) {
                }

                // a[i] >= pivot and a[j] <= pivot
                if (i < j) {
                    swap(i, j, a);
                } else {
                    break;
                }
            }

            // we can swap the pivot with a[i]
            swap(i, right, a);
            // Now the pivot is between the two sets and in a[i]
            // quicksort the left set:
            quickSort(a, left, i - 1);
            // quicksort the right set:
            quickSort(a, i + 1, right);
        } else {

            // or we can do insertionSort(a, left, right);
            // a has just two elements
            if (a[left] > a[right]) {
                swap(left, right, a);
            }
        }
    }

    public static int choosePivot1(int[] a, int left, int right) {
        int mid = randInt(left, right);
        swap(mid, right, a);
        return a[right];
    }

    public static void quickSort1(int[] a, int left, int right) {

        if (left < right) {
            int p = choosePivot1(a, left, right); // get pivot
            //pivot <= A[right] and the pivot = a[right]

            // now scan from both ends
            int l = left;
            int r = right - 1; // but leave the pivot alone   

            while (true) {
                // now find an element larger than the pivot
                while (l <= r && a[l] <= p) { // 
                    l++;
                }
                // now find an element smaller than the pivot
                while (l <= r && a[r] >= p) { //
                    r--;
                }
                // we now check which condition caused the while loops to finish
                if (l < r) {
                    swap(l, r, a);
                } else {
                    break;
                }
            }
            // NB. We now must have l>r
            assert (l >= r);
            swap(l, right, a); // we put the pivot where it should be

            quickSort1(a, left, l - 1);
            quickSort1(a, l + 1, right);
        }
    }

    public static void quickSort2(int a[], int left, int right) {

        int i = left, j = right;

        int pivot = a[(left + right) / 2];

        /* partition */
        while (i <= j) {
            while (a[i] < pivot) {
                i++;
            }
            while (a[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swap(i, j, a);
                i++;
                j--;
            }
        }

        /* recursion */
        if (left < j) { // or i -1 instead of j 
            quickSort2(a, left, j);
        }
        if (i < right) {
            quickSort2(a, i, right);
        }
    }

    public static int partition(int arr[], int left, int right) {
        int i = left, j = right;

        int pivot = arr[(left + right) / 2];

        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }

            while (arr[j] > pivot) {
                j--;
            }

            if (i <= j) {
                swap(i, j, arr);
                i++;
                j--;
            }
        }
        return i;
    }

    public static void quickSort3(int arr[], int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1) {
            quickSort3(arr, left, index - 1);
        }
        if (index < right) {
            quickSort3(arr, index, right);
        }
    }

    public static void iterativeQuickSort(int a[]) {
        Stack<Integer> s = new Stack<>();
        int left, right;
        int size = a.length;
        //s.push(0, last);
        s.push(size - 1);
        s.push(0);

        while (!s.empty()) {

            //s.pop(left, right);
            left = s.peek();
            s.pop();
            right = s.peek();
            s.pop();

            while (left + 10 < right /* or right - MINSIZE */) {

                int pivot = choosePivot(a, left, right);
                // partition step 
                int i = left;
                int j = right - 1;
                while (true) {
                    while (a[++i] < pivot) {
                        // i++;
                    }
                    while (pivot < a[--j]) {
                        //  j--;
                    }
                    if (i < j) {
                        swap(i, j, a);
                        // i++;
                        // j--;
                    } else {
                        break;
                    }
                }
                // move pivot into position 
                swap(i, right, a);

                if ((right - i) > (j - left)) {
                    if (right > i) {
                        //s.push(i, right);
                        s.push(right);
                        s.push(i);
                        right = j;
                    }
                } else {
                    if (j > left) {
                        //s.push(left, j);
                        s.push(j);
                        s.push(left);
                        left = i;
                    }
                }
            }
            insertionSort(a, left, right);
            // could do the insertion sort here if right-left < MINSIZE
        }
    }

    public static void swap(int i, int j, int[] a) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void displayArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (i % 10 == 0) {
                System.out.println("");
            }
            System.out.format("%4d ", a[i]);
        }
    }

    public static void fillArray(int a[]) {

        for (int i = 0; i < a.length; i++) {
            a[i] = randInt(LOW, HIGH);
            assert (a[i] >= LOW && a[i] <= HIGH);
        }
    }

    public static void fillArray(int a[], int value) {

        for (int i = 0; i < a.length; i++) {
            a[i] = value;
        }
    }

    /**
     * Returns a psuedo-random number between min and max, exclusive. The
     * difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimim value
     * @param max Maximim value. Must be greater than min.
     * @return Integer between min and max, exclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) - 1) + min + 1;
        assert (randomNum > min && randomNum < max);
        return randomNum;
    }

    public static boolean isSorted(int a[]) {
        boolean sorted = true;
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                System.out.format("ERROR: %d\n", a[i - 1]);
                sorted = false;
            }
        }
        return sorted;
    }
}
