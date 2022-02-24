package edu.emory.cs.sort.comparison;

import java.util.Collections;
import java.util.Comparator;

/**
 * create the shellsortquiz class that inherits shellsort
 * override the populateSequence() and getSequenceStartIndex()
 * methods such that it performs Shell Sort by using the Hibbard sequence:
 * 2^k - 1 -> {1, 3, 7, 15, ...}
 * Feel free to use the code in ShellSortKnuth.
 */


public class ShellSortQuiz <T extends Comparable<T>> extends ShellSort<T>{

    public ShellSortQuiz(){
        this(Comparator.naturalOrder());
    }

    public ShellSortQuiz(Comparator<T> comparator){
        super(comparator);
    }

    @Override
    protected void populateSequence(int n){
        n /= 3; //populates the Knuth sequence up to the gap <= n/3

        for(int t = sequence.size() + 1; ;t++) {
            int h = (int) (Math.pow(2, t) - 1);
            if (h <= n) sequence.add(h);
            else break;
        }
    }

    @Override
    //Returns the index of the first key <= n/3
    protected int getSequenceStartIndex(int n){
        int index = Collections.binarySearch(sequence, n/3);
        if(index < 0) index = -(index+1);
        if (index == sequence.size()) index--;
        return index;
    }
}

