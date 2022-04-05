package edu.emory.cs.set;

public class DisjointSetQuiz {
    static public void main(String[] args) {
        DisjointSet ds = new DisjointSet(5);
        ds.union(ds.find(0), ds.find(1));
        ds.union(ds.find(2), ds.find(3));
        ds.union(ds.find(4), ds.find(3));
        ds.union(ds.find(1), ds.find(3));
    }
}