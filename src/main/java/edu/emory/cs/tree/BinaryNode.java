package edu.emory.cs.tree;

public class BinaryNode<T extends Comparable<T>> extends AbstractBinaryNode<T, BinaryNode<T>> {
    // defines only 1 generic type T for the comparable key
    // and passes itself for the generic type N to theAbstractBinaryNode class.
    public BinaryNode(T key) {
        super(key);
    }
    // Is there any abstract method from AbstractBinaryNode to be defined in BinaryNode?
    // No; we make it an abstract class because it is a concept; we don't want to instantiate it.
    //
}
