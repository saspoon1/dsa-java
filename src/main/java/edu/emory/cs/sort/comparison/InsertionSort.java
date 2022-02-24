package edu.emory.cs.sort.comparison;
import edu.emory.cs.sort.AbstractSort;
import java.util.Comparator;

public class InsertionSort <T extends Comparable<T>> extends AbstractSort<T>{
    /**
     * for each key Ai where |A| = n and i in [1, n)
     * keep swapping Aj and Ai until Aj <= Ai
     *
     * Insertion sort: compare O(n^2), swap O(n^2)
     * Shell Sort: compare O(n^1.5), swap O(n^1.5)
     */

    public InsertionSort() {
        this(Comparator.naturalOrder());
    }

    public InsertionSort(Comparator <T> comparator) {
        super (comparator);
    }

    protected void sort(T[] array, int beginIndex, int endIndex, final int h){
        int begin_h = beginIndex + h;
        for(int i = begin_h; i < endIndex; i++){ //Iterates keys in the input array O(n)
            //compares keys in the sublist of the input array O(n/h)
            for (int j = i; begin_h <= j && compareTo(array, j, j-h) < 0; j -= h)
                swap(array, j, j-h); //swaps the two keys
        }
    }

    @Override
    public void sort(T[] array, int beginIndex, int endIndex){
        sort (array, beginIndex, endIndex, 1);
    }


}
