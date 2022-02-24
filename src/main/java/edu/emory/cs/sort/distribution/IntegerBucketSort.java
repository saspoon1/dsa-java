package edu.emory.cs.sort.distribution;

public class IntegerBucketSort extends BucketSort<Integer>{
    //takes the smallest integer in the range
    private final int MIN;

    /**
     *
     * @param min the minimum integer (inclusive).
     * @param max the maximum integer (exclusive).
     */
    public IntegerBucketSort(int min, int max){
        //passes the number of buckets, max - min,
        //to be initialized to the super constructor.
        super(max - min);
        MIN = min;
    }
    @Override
    public void sort(Integer[] array, int beginIndex, int endIndex){
        //passes a lambda expression that takes
        //key and returns key-MIN indicating the index of the
        //bucket that key should be assigned to
        sort(array, beginIndex, endIndex, key -> key - MIN);
    }
}
