package edu.emory.cs.sort.distribution;
import java.util.*;
import java.util.stream.*;
import java.util.function.Function;

import edu.emory.cs.sort.AbstractSort;

public abstract class BucketSort<T extends Comparable<T>> extends AbstractSort<T> {
    //Declares a list of buckets where each bucket is represented by a Deque
    protected List<Deque<T>> buckets;
    /**
     * seems to be faster than radix sort.
     */



    /**
     *
     * @param numBuckets the total number of buckets.
     */
    public BucketSort(int numBuckets){
        //no comparator needed; no comparison sorting
        super(null);
        //creates a predefined number of buckets
        buckets = Stream.generate (ArrayDeque<T>::new).limit(numBuckets).collect(Collectors.toList());
    }

    /**
     * Takes a comparable key and returns the appropriate bucket index of the key
     * @param array       the input array
     * @param beginIndex  the index of the first key to be sorted (inclusive)
     * @param endIndex    the index of the last key to be sorted (exclusive)
     * @param bucketIndex takes a key and returns the index of the bucket that the key should be added.
     */
    protected void sort(T[] array, int beginIndex, int endIndex, Function<T, Integer> bucketIndex) {
        //Higher order function
        //bucketIndex is a function which takes 1 parameter and returns an Integer.
        //add each element ot bucket
        for (int i = beginIndex; i < endIndex; i++) {
            //adds the keys within the range in the input array to the buckets.
            buckets.get(bucketIndex.apply(array[i])).add(array[i]);
        }
        //merge elements in all buckets to the input array
        //puts all the keys in the buckets back to the input array while keeping the order
        for (Deque<T> bucket: buckets){ // go through each bucket
            while(!bucket.isEmpty()) {
                array[beginIndex++] = bucket.remove();
            }
        }
    }
}
