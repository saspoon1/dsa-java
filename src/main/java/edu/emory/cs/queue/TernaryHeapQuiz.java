package edu.emory.cs.queue;
import java.util.*;

public class TernaryHeapQuiz<T extends Comparable<T>> extends AbstractPriorityQueue<T> {
    public final List<T> keys;

    public TernaryHeapQuiz() {
        this(Comparator.naturalOrder());
    }

    public TernaryHeapQuiz(Comparator<T> priority) {
        super(priority);
        keys = new ArrayList<>();
        keys.add(null);
    }

    private int compare(int i1, int i2) {
        return priority.compare(keys.get(i1), keys.get(i2));
    }

    /**
     * Adds a key to the heap
     */
    @Override
    public void add(T key) {
        keys.add(key);
        swim(size());
    }

    private void swim(int k) {
        for (; 1 < k && compare( getParentIndex(k), k) < 0; k = getParentIndex(k))
            Collections.swap(keys, getParentIndex(k) , k);
    }

    /**
     * Removes the key with the highest/lowest priority if exists.
     * Returns key with the highest/lowest priority or null (if DNE)
     */
    @Override
    public T remove() {
        if (isEmpty()) return null;
        Collections.swap(keys, 1, size());
        T max = keys.remove(size());
        sink();
        return max;
    }

    private void sink() {

        for (int k = 1, i = 3; (k*3) - 1 <= size(); k = i) {
            i = getLargestChild(k);
            if (compare(k, i) >= 0) break;
            Collections.swap(keys, k, i);
        }
    }

    @Override
     public int size(){
         return keys.size() - 1;
     }

    /**
     * returns index of parent or -1 if there is no parent.
     */

     private int getParentIndex(int childIndex){
         int parentIndex;
         if (childIndex == 0 || childIndex == 1){
             parentIndex = -1;
         }
         else {
             if (childIndex % 3 == 2) { parentIndex = (childIndex/3) + 1;}
             else parentIndex = childIndex/3;
         }
         return parentIndex;
     }

     private int getLargestChild(int parentIndex){
         int middleChild = parentIndex * 3;
         int leftChild = middleChild - 1;
         int rightChild = middleChild + 1;
         if ( rightChild < size()) {
             if (compare(middleChild, leftChild) >= 0 && compare(middleChild, rightChild) >= 0) {
                 return middleChild;
             }
             //already determined that middle child is not largest.
             else if (compare(leftChild, rightChild) >= 0) {
                 return leftChild;
             }
             //since middle child and left child are not largest, then right child is.
             else return rightChild;
         }
         //no right child exists.
         else if (middleChild < size()){
             if (compare(middleChild, leftChild) >= 0 ) return middleChild;
             else return leftChild;
         }
         //no middle child or right child exist.
         else return leftChild;
     }



}
