package edu.emory.cs.sort;
import java.util.*;

public abstract class AbstractSort<T extends Comparable<T>> {
    // comparator specifies the precedence of comparable keys
    private final Comparator<T> comparator;
    // comparisons counts the number of comparisons performed during sort (microbenchmarking)
    protected long comparisons;
    // assignments counts the number of assignments performed during sort (microbenchmarking)
    protected long assignments;

    public AbstractSort(Comparator<T> comparator){
        this.comparator = comparator;
        resetCounts();
    }


    public long getComparisonCount(){
        return comparisons;
    }

    public long getAssignmentCount(){
        return assignments;
    }

    public void resetCounts() {
        comparisons = assignments = 0;
    }

    /**
     * compares two keys in the array and increments the count
     * @param array an array of comparable keys
     * @param i     the index of the first key
     * @param j     the index of the second key.
     * @return array[i].compareTo(array[j])
     */
    protected int compareTo(T[] array, int i, int j){
        comparisons++;
        return comparator.compare(array[i], array[j]);
    }

    /**
     * assigns the value to the specific position in the array and increments the count
     * array[index] = value
     * @param array an array of comparable keys
     * @param index the index of the array to assign
     * @param value the value to be assigned
     */
    protected void assign(T[] array, int index, T value){
        assignments++;
        array[index] = value;
    }

    /**
     * Swaps the values of the specific positions in the array by calling the assign() method
     * Swaps array[i] and array[j]
     * @param array an array of comparable keys
     * @param i     the index of the first key
     * @param j     the index of the second key
     */
    protected void swap(T[] array, int i, int j){
        T t = array[i];
        assign(array, i, array[j]);
        assign(array, j, t);
    }

    /**
     * Calls the abstract method sort() that overwrites it.
     * Sorts the array in ascending order
     * @param array an array of comparable keys.
     */
    public void sort(T[] array){
        sort(array, 0, array.length);
    }

    /**
     * Sorts the array in the range of (beginIndex, endIndex) as specified in comparator
     * @param array      an array of comparable keys.
     * @param beginIndex the index of the first key to be sorted (inclusive)
     * @param endIndex   the index of the last key to be sorted (exclusive)
     */
    abstract public void sort(T[] array, int beginIndex, int endIndex);
}