package edu.emory.cs.tree;

public class AVLTree<T extends Comparable<T>> extends AbstractBalancedBinarySearchTree<T, AVLNode<T>> {
    @Override
    public AVLNode<T> createNode(T key) {
        return new AVLNode<>(key);
    }

    @Override
    protected void rotateLeft(AVLNode<T> node) {
        super.rotateLeft(node);
        //resets the height of node after rotation
        node.resetHeights();
    }

    @Override
    protected void rotateRight(AVLNode<T> node) {
        super.rotateRight(node);
        //resets the height of node after rotation
        node.resetHeights();
    }

    /**
     *  node.resetHeights() does not reset heights of nodes in its subtree.
     *  Would this cause an issue?
     *
     *
     */

    @Override
    protected void balance(AVLNode<T> node) {
        if (node == null) return;
        int bf = node.getBalanceFactor();

        //the left-subtree is longer.
        if (bf == 2) { //left heavy
            //at least one rotation; conditionally 2 rotations.
            AVLNode<T> child = node.getLeftChild();

            //checks if there is a zig-zag
            //the case of left zig-zag.
            if (child.getBalanceFactor() == -1)
                rotateLeft(child);

            //the case of left linear.
            rotateRight(node);
        }
        //the right-subtree is longer.
        else if (bf == -2) {
            AVLNode<T> child = node.getRightChild();

            if (child.getBalanceFactor() == 1)
                // the case of right zig-zag
                rotateRight(child);

            //case of right linear.
            rotateLeft(node);
        }
        else
            balance(node.getParent());
    }
    /**
     * will never reach to 3 or -3; will balance immediately -> only need to check for 2 or -2.
     */

}
