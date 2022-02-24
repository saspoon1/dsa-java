package edu.emory.cs.sort.distribution;
import java.util.Arrays;

public abstract class RadixSort extends BucketSort<Integer>{
    public RadixSort() {
        //creates 10 buckets to sort any range of integers
        super (10);
    }

    //returns the order of the most significant digit in the input array.
    protected int getMaxBit(Integer[] array, int beginIndex, int endIndex){
        Integer max = Arrays.stream(array, beginIndex, endIndex).reduce(Integer::max).orElse(null);
        return (max != null && max > 0) ? (int)Math.log10(max) + 1 : 0;
    }
}
