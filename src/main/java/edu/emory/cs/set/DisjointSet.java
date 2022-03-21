package edu.emory.cs.set;
import java.util.Arrays;

public class DisjointSet {
    //The size of subset(-1 implies 1 ID, -2 implies 2 IDs, and so on).
    private final int[] subsets;

    //Constructor
    public DisjointSet(int size) {
        //every set is initialized with the size of 1
        subsets = new int[size];
        Arrays.fill(subsets, -1);
    }

    //Copy constructor
    public DisjointSet(DisjointSet set) {
        subsets = Arrays.copyOf(set.subsets, set.subsets.length);
    }

    public int baselineFind(int id) { // O(n)
        //recursive call
        //if subsets[id] is a negative number, then it is the root of the set -> return id.
        //                  not negative -> is a child of something
        //                               -> calls subsets for the id of the parent
        return (subsets[id] < 0) ? id : baselineFind(subsets[id]);
    }

    public int find(int id) { //efficient; still O(n) but gets faster after it is called
        return (subsets[id] < 0) ? id : (subsets[id] = find(subsets[id]));
        // subsets[id] = find(subsets[id])
        // find(subsets[id]) will always return the root value.
        //
    }

    public boolean inSameSet(int key1, int key2){
        return find(key1) == find(key2);
    }
    public int union(int key1, int key2) {
        int r1 = find(key1);
        int r2 = find(key2);
        if (r1 == r2) return r1;
        if (subsets[r1] < subsets[r2]) {
            subsets[r1] += subsets[r2];
            subsets[r2] = r1;
            return r1;
        }
        else {
            subsets[r2] += subsets[r1];
            subsets[r1] = r2;
            return r2;
        }
    }
}
