package edu.emory.cs.sort.divide_conquer;
import edu.emory.cs.sort.AbstractSort;
import java.util.*;

public class QuickSort<T extends Comparable<T>> extends AbstractSort<T> {

    /**
     * Pick a pivot key in the input array
     * Exchange keys between the left and right partitions such that all keys
     * in the left and right partitions are smaller or bigger than the pivot key, respectively
     * Repeat in each partition recursively
     */

    public QuickSort() {
        this(Comparator.naturalOrder());
    }

    public QuickSort(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(T[] array, int beginIndex, int endIndex) {
        if (beginIndex >= endIndex) return; //Stops when the pointers are crossed

        int pivotIndex = partition(array, beginIndex, endIndex);
        sort(array, beginIndex, pivotIndex); //sort left partition
        sort(array, pivotIndex + 1, endIndex); //sort right partition
    }

    protected int partition(T[] array, int beginIndex, int endIndex) {
        int fst = beginIndex, snd = endIndex;

        while (true){
            //Finds the left pointer where endIndex > fst > pivot
            while (++fst < endIndex && compareTo(array, beginIndex, fst) >= 0);
            //Find the right pointer where beginIndex < snd < pivot
            while (--snd > beginIndex && compareTo(array, beginIndex, snd) <= 0);
            if (fst >= snd) break; //left and right pointers are crossed
            swap (array, fst, snd); //swaps between keys in the left and right partitions
        }

        swap(array, beginIndex, snd); //swaps the keys in the beginIndex and pivot
        return snd;
    }

}
