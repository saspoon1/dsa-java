package edu.emory.cs.sort.divide_conquer;
import edu.emory.cs.sort.AbstractSort;
import java.util.*;
import java.lang.reflect.Array;
import edu.emory.cs.utils.Utils;

public class MergeSort<T extends Comparable<T>> extends AbstractSort<T> {

    /**
     * Divide the problem into sub-problems recursively
     * Conquer sub-problems, which effectively solves the super problem
     *
     * MergeSort: Best O(nlogn)  Worst O(nlogn)  Average O(nlogn)
     * TimSort:   Best O(nlogn)  Worst O(nlogn)  Average O(nlogn)
     * QuickSort: Best O(nlogn)  Worst O(n^2)    Average O(nlogn)
     * IntroSort: Best O(nlogn)  Worst O(nlogn)  Average O(nlogn)
     */

    private T[] temp; //holds copy of input array

    public MergeSort() {
        this(Comparator.naturalOrder());
    }

    public MergeSort(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sort(T[] array, int beginIndex, int endIndex){
        if (temp == null || temp.length < array.length){ //increases size of temp if necessary
            temp = (T[])Array.newInstance(array[0].getClass(), array.length);
            //unchecked type Object to T[] warning
        }
        sort(array, temp, beginIndex, endIndex);
    }

    /**
     *
     * @param input the input array
     * @param copy the array to hold the copy of the input array
     * @param beginIndex the beginning index of the 1st half (inclusive)
     * @param endIndex the ending index of the 2nd half (exclusive)
     */
    protected void sort(T[] input, T[] copy, int beginIndex, int endIndex){
        if (beginIndex + 1 >= endIndex) return;
        int middleIndex = Utils.getMiddleIndex(beginIndex, endIndex);

        sort(input, copy, beginIndex, middleIndex); //Sorts the left subarray
        sort(input, copy, middleIndex, endIndex); //Sorts the right subarray
        merge(input, copy, beginIndex, middleIndex, endIndex); //merges left and right subarrays

    }

    protected void merge(T[] input, T[] copy, int beginIndex, int middleIndex, int endIndex){
        int fst = beginIndex, snd = middleIndex, n = endIndex - beginIndex;
        //copies the input array to the temporary array and counts the assignments.
        System.arraycopy(input, beginIndex, copy, beginIndex, n);
        assignments += n;

        for (int k = beginIndex; k < endIndex; k++){
            if (fst >= middleIndex) //no key left in the 1st half.
                assign(input, k, copy[snd++]);
            else if (snd >= endIndex) //no key left in the 2nd half.
                assign(input, k, copy[fst++]);
            else if (compareTo(copy, fst, snd) < 0) //the 2nd key is greater than the 1st key.
                assign(input, k, copy[fst++]);
            else assign(input, k, copy[snd++]); //the 1st key is greater than or equal to the 2nd key.
        }
    }
}
