package edu.emory.cs.sort.hybrid;

import edu.emory.cs.sort.AbstractSort;
import edu.emory.cs.sort.comparison.ShellSortKnuth;
import edu.emory.cs.sort.divide_conquer.IntroSort;
import edu.emory.cs.sort.divide_conquer.QuickSort;
import java.lang.reflect.Array;

public class HybridSortHW<T extends Comparable<T>> implements HybridSort<T> {

    //private final AbstractSort<T> BaseEngine;
    private final AbstractSort<T> ShellEngine;
    private final AbstractSort<T> QuicksortEngine;
    public HybridSortHW(){
        QuicksortEngine = new QuickSort<T>();
        ShellEngine = new ShellSortKnuth<T>();
        //BaseEngine = new IntroSort<T>(new ShellSortKnuth<T>());
    }





    @Override
    public T[] sort(T[][] input){
        int rowCase;
        T[][] output;
        for (T[] row : input){
            rowCase = determineWhichCase(row);
            switch (rowCase) {
                case 1:
                    row = sortRandom(row);
                    break;
                case 2:
                    //Do nothing since is already sorted.
                    break;
                case 3:
                    row = sortMostlyAscending(row);
                    break;
                case 4:
                    row = sortDescending(row);
                    break;
                case 5:
                    row = sortMostlyDescending(row);
                    break;
                default:
                    break;
            }
        }
        output = mergeSortedRows(input);

        while (output.length != 1){
            output = mergeSortedRows(output);
            //System.out.println(output.length);
        }
        return output[0];
    }

    private int determineWhichCase(T[] input){
        int counter = 0;
        int output;
        //randomly distributed: return 1
        //ascending order: return 2
        //mostly ascending order: return 3
        //descending order: return 4
        //mostly descending order: return 5

        for (int i = 0; i < input.length-1; i++){
            if (input[i].compareTo(input[i+1]) < 0) counter = counter + 1; //1, 2
            else if (input[i].compareTo(input[i+1]) > 0) counter = counter - 1; //2, 1
            else counter = counter; //1, 1
        }
        if (counter == input.length - 1)                output = 2; //ascending order: return 2
        else if (counter * (-1) == input.length - 1)    output = 4; //descending order: return 4
        else if (counter > input.length / 2)            output = 3; //mostly ascending order: return 3
        else if (counter * (-1) > input.length / 2)     output = 5; //mostly descending order: return 5
        else                                            output = 1; //randomly distributed: return 1

        return output;

        /**
         * Idea: go through the array, comparing the adjacent numbers
         * use a counter
         * if the left < right, add 1;
         * if left > right, subtract 1;
         * At end, if all were +1's then is in ascending
         * if all were -1's then descending
         * if close to 0, then random.
         * if mediumly positive -> mostly ascending
         * if mediumly negative -> mostly descending.
         *
         */
    }

    private T[][] mergeSortedRows(T[][] input){
            T[][] output = (T[][]) Array.newInstance(input[0].getClass(), input.length / 2 + (input.length % 2));
            int outputRow = 0;
            if (input.length % 2 == 0) {
                for (int i = 0; i < input.length; i += 2) {
                    output[outputRow++] = mergeTwoRows(input[i], input[i + 1]);
                }
            } else { //odd length
                for (int i = 0; i < input.length - 1; i += 2) {
                    output[outputRow++] = mergeTwoRows(input[i], input[i + 1]);
                }
                output[output.length - 1] = input[input.length - 1];
            }
        return output;
    }

    protected T[] mergeTwoRows(T[] first, T[] second){
        int fst = 0, snd = 0, n = first.length + second.length;
        T[] output = (T[]) Array.newInstance(first[0].getClass(), n);
        for (int outputIndex = 0; outputIndex < n; outputIndex++){
            if (fst >= first.length) //no key left in first array
                assign(output, outputIndex, second[snd++]);
            else if (snd >= second.length) //no key left in second array
                assign(output, outputIndex, first[fst++]);
            else if (first[fst].compareTo(second[snd]) < 0) //the 2nd key is greater than the 1st key.
                assign(output, outputIndex, first[fst++]);
            else assign(output, outputIndex, second[snd++]); //the 1st key is greater than or equal to the 2nd key.
        }

        return output;
    }

    protected void assign(T[] array, int index, T value){
        array[index] = value;
    }
















    private T[] sortRandom(T[] input){
        //Quicksort
        QuicksortEngine.sort(input);
        return input;
    }
    private T[] sortMostlyAscending(T[] input){
        // Shellsort
        ShellEngine.sort(input);
        return input;
    }
    private T[] sortDescending(T[] input){
        //Shellsort
        ShellEngine.sort(input);
        return input;
    }
    private T[] sortMostlyDescending(T[] input){
        //Shellsort
        ShellEngine.sort(input);
        return input;
    }

}
