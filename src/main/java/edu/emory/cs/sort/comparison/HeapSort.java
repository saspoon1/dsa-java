package edu.emory.cs.sort.comparison;
import edu.emory.cs.sort.AbstractSort;
import java.util.*;


public class HeapSort<T extends Comparable<T>> extends AbstractSort<T> {
    public HeapSort() {
        this(Comparator.naturalOrder());
    }

    public HeapSort(Comparator<T> comparator) {
        super(comparator);
    }

    //Finds the right position of the k'th key by using the sink operation
    private void sink (T[] array, int k, int beginIndex, int endIndex){
        for (int i = getLeftChildIndex(beginIndex, k); i < endIndex; k = i, i = getLeftChildIndex(beginIndex, k)){
            if (i + 1 < endIndex && compareTo(array, i, i+1) < 0) i++;
            if (compareTo(array, k, i) >= 0) break;
            swap (array, k, i);
        }
    }

    //Finds the parent index of the k'th key given the beginning index
    private int getParentIndex (int beginIndex, int k){
        return beginIndex + (k- beginIndex - 1)/2;
    }

    //Finds the left child index of the k'th key given the beginning index
    private int getLeftChildIndex(int beginIndex, int k){
        return beginIndex + 2 * (k - beginIndex) + 1;
    }

    @Override
    public void sort(T[] array, int beginIndex, int endIndex) {
        //heapify all elements                                                            O(nlogn)
        for (int k = getParentIndex(beginIndex, endIndex - 1); k >= beginIndex; k--) //O(n)
            sink(array, k, beginIndex, endIndex);                                       //O(logn)

        //swap the endIndex element with the root element and sink it.                    O(nlogn)
        while (endIndex > beginIndex + 1){       // iterates all keys within the range O(n)
            swap(array, beginIndex, --endIndex); //swaps the max key with beginning key in the range O(1)
            sink(array, beginIndex, beginIndex, endIndex);             //sink to heapify: O(logn)
        }
    }
}
