package edu.emory.cs.sort.comparison;
import edu.emory.cs.sort.AbstractSort;
import java.util.Comparator;

public class SelectionSort<T extends Comparable<T>> extends AbstractSort<T> {
    /**
     * Stupid Stupid algorithm; always worst case.
     *
     *
     * For each key, Ai where |A| = n and i is inside [n, 0):
     * search for the maximum key, Am where m is inside [1, i)
     * swap Ai and Am
     *
     * Selection sort: search O(n), compare O(n^2), swap O(n)
     * heap sort: search O(logn), compare O(nlogn), swap O(nlogn)
     */



    public SelectionSort(){
        this(Comparator.naturalOrder());
    }

    public SelectionSort(Comparator<T> comparator){
        super(comparator);
    }

    @Override
    public void sort(T[] array, final int beginIndex, final int endIndex){
        for (int i = endIndex; i > beginIndex; i--){ //O(n)
            int max = beginIndex;

            for (int j = beginIndex + 1; j < i; j++){ //O(n)
                if (compareTo(array, j, max) > 0) max = j;
            }
            swap (array, max, i-1);               //O(1)
        }
    }
}
