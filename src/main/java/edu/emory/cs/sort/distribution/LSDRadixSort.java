package edu.emory.cs.sort.distribution;

public class LSDRadixSort extends RadixSort{
    @Override
    public void sort(Integer[] array, int beginIndex, int endIndex){
        int maxBit = getMaxBit(array, beginIndex, endIndex);
        //iterates from LSD to MSD
        for(int bit = 0; bit < maxBit; bit++){
            int div = (int)Math.pow(10, bit);
            //lambda expression; returns the bucket index
            // (the value in the n'th significant digit).
            sort(array, beginIndex, endIndex, key -> (key/div) % 10);
        }
    }

}
