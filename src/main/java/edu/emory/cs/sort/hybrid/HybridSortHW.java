package edu.emory.cs.sort.hybrid;

public class HybridSortHW<T extends Comparable<T>> implements HybridSort<T> {
    @Override
    public T[] sort(T[][] input){
        T[] output = null;
        for (int i = 0; i < input.length; i++){ //goes through each row
            //sort(input[i]);
        }
        //output = merge(input);
        return output;
    }
}

/**
 *
 * See 1 row
 * Check if sorted, reverse sorted, random, slightly sorted, or slightly reverse sorted
 * Choose sorting algorithm
 * Sort
 * Do for all rows
 *
 * Merge sorted rows into a row array
 *
 */