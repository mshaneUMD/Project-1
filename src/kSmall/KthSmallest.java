package kSmall;

public class KthSmallest {

    private  static void swap(int[] theArray, int i, int j){
        int temp = theArray[i];
        theArray[i] = theArray[j];
        theArray[j] = temp;
    }

    private static int partition(int[] theArray, int first, int last){
    // Returns the index of the pivot element after partitioning
    // theArray[first..last]

        int p = theArray[first];    // use the first item of the array as the pivot (p)
        int lastS1 = first;         // set S1 and S2 to empty

        // ToDo: Determine the regions S1 and S2
	    // Refernce the page 172 on the textbook for finding the kth smallest item of an array
        
        // Loop through every index after the first index until the last index.
        for(int i = first + 1; i <= last; i++) {
            // Check if the current index is less than the partition value.
            if(theArray[i] < p) {
                // Increment the size of S1 by incrementing the value of lastS1.
                lastS1++;

                // Swap the value at the current index with the value of index lastS1.
                swap(theArray, i, lastS1);
            }
        }

        // Swap the pivot value (index = first) and the last value in S1 (index = lastS1) to place the pivot in the center of S1 and S2.
        swap(theArray, first, lastS1);

        // Return the index of the pivot, which is now at the index of lastS1.
        return lastS1;              // the index of the pivot element
    }

    public static int kSmall(int k, int[] anArray, int first, int last){
        int pivotIndex = partition(anArray, first, last);
        int p = anArray[pivotIndex]; // p is the pivot

        // ToDo: Return the kth smallest value in anArray[first..last].
	    // Reference partition algorithm in QuickSort algorith, which is on page 533 of the textbook
        
        // Check to see if the Kth smallest value is within S1.
        if(k < pivotIndex - first + 1) {
            // Return the value of kSmall solving the problem within S1.
            return kSmall(k, anArray, first, pivotIndex - 1);
        }

        // Check to see if the Kth smallest value is equal to the pivot.
        else if(k == pivotIndex - first + 1) {
            // Return the pivot value.
            return p;
        }

        // Otherwise, the Kth smallest value is within S2.
        else {
            // Return the value of kSmall solving the problem within S2.
            return kSmall(k - (pivotIndex - first + 1), anArray, pivotIndex + 1, last);
        }
    }
}
