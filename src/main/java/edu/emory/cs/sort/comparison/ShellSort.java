package edu.emory.cs.sort.comparison;
import java.util.*;

public abstract class ShellSort<T extends Comparable<T>> extends InsertionSort<T> {
    /**
     * gap sequences:
     * Knuth: (3^k - 1) /2; 1, 4, 13, 40, 121 ...
     * Hibbard: 2^k - 1; 1, 3, 7, 15, 31, 63 ...
     */

    protected List<Integer> sequence; //Stores a particular gap sequence

    public ShellSort(Comparator<T> comparator){
        super (comparator);
        sequence = new ArrayList<>();
        populateSequence (10000); //prepopulate the gap sequence that can handle the input size up to 10000
    }

    /**
     * Populates the gap sequence with respect to the size of the list (input size n)
     * @param n the size of the list to be sorted.
     */
    protected abstract void populateSequence(int n);

    /**
     * Returns the index of the first gap to be used given the input size n
     * @param n the size of the list to be sorted
     * @return the starting index of the sequence with respect to the size of the list
     */
    protected abstract int getSequenceStartIndex(int n);

    @Override
    public void sort(T[] array, int beginIndex, int endIndex){
        int n = endIndex - beginIndex;
        populateSequence(n); //should not re-populate sequence unless it has to

        for(int i = getSequenceStartIndex(n); i >=0; i--) //iterates the sequence O(s) where s is the number of gaps in the sequence
            sort(array, beginIndex, endIndex, sequence.get(i)); //sorts the gap group by using the auxiliary method
    }
}
