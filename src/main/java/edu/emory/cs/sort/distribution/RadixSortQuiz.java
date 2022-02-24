package edu.emory.cs.sort.distribution;
import java.util.*;
import java.util.function.*;
import java.lang.reflect.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RadixSortQuiz extends RadixSort {

    @Override
    public void sort(Integer[] array, int beginIndex, int endIndex) {
        int maxBit = getMaxBit(array, beginIndex, endIndex);
        sort(array, beginIndex, endIndex, maxBit);
        int debug = 0;
    }

    private void sort(Integer[] array, int beginIndex, int endIndex, int maxBit) {
        int index00 = beginIndex;
        if (maxBit <= 0) {
            return;
        }
        int div = (int)Math.pow(10, maxBit - 1);
        //sort array into buckets
        for (int j = beginIndex; j < endIndex; j++) {
            int num = array[j];
            buckets.get( (num/div) % 10 ).add(num);
        }
        int index0 =index00 + buckets.get(0).size();
        int index1 = index0 + buckets.get(1).size();
        int index2 = index1 + buckets.get(2).size();
        int index3 = index2 + buckets.get(3).size();
        int index4 = index3 + buckets.get(4).size();
        int index5 = index4 + buckets.get(5).size();
        int index6 = index5 + buckets.get(6).size();
        int index7 = index6 + buckets.get(7).size();
        int index8 = index7 + buckets.get(8).size();
        int index9 = index8 + buckets.get(9).size();
        int mn = 0;
        for (Deque<Integer> bucket : buckets){ // go through each bucket
            while(!bucket.isEmpty() && beginIndex < array.length) {
                array[beginIndex++] = bucket.remove();
            }
        }
        if (maxBit > 0) {
            maxBit = maxBit - 1;
            sort(array, index00, index0, maxBit);
            sort(array, index0, index1, maxBit);
            sort(array, index1, index2, maxBit);
            sort(array, index2, index3, maxBit);
            sort(array, index3, index4, maxBit);
            sort(array, index4, index5, maxBit);
            sort(array, index5, index6, maxBit);
            sort(array, index6, index7, maxBit);
            sort(array, index7, index8, maxBit);
            sort(array, index8, index9, maxBit);
        }
    }

    /**
     sort(array, 0, index0, maxBit);
     sort(array, index0, index1, maxBit);
     sort(array, index1, index2, maxBit);
     sort(array, index2, index3, maxBit);
     sort(array, index3, index4, maxBit);
     sort(array, index4, index5, maxBit);
     sort(array, index5, index6, maxBit);
     sort(array, index6, index7, maxBit);
     sort(array, index7, index8, maxBit);
     sort(array, index8, index9, maxBit);
     */
    public static void main(String[] args) {
        Integer[] array = {100, 10, 234, 481, 84, 851, 2, 28, 43, 3, 48};
        RadixSortQuiz me = new RadixSortQuiz();
        me.sort(array, 0, array.length);
        for (int element : array){
            System.out.println(element);
        }

    }
}

