package edu.emory.cs.tree;

/**
 *      search          insert      delete
 *      unbalanced      O(n)        O(n)                O(n) + beta
 *      balanced        O(logn)     O(logn) + alpha     O(logn) + beta
 * To ensure the O(n) complexity, it needs to be balanced at all time (or at most time).
 *
 *
 * 4 Cases of unbalanced trees to be considered
 * left linear -> 3, 2, 1
 * right linear -> 1, 2, 3
 * left zig-zag 3, 1, 2
 * right sigzag 1, 3, 2
 *
 * rotations of bst's to make balanced.
 *
 */


public abstract class AbstractBalancedBinarySearchTree<T extends Comparable<T>, N extends AbstractBinaryNode<T, N>> extends AbstractBinarySearchTree<T, N> {
    /**
     * Rotates the specific node to the left.
     * @param node the node to be rotated.
     */
    protected void rotateLeft(N node) {
        N child = node.getRightChild();
        node.setRightChild(child.getLeftChild());

        if (node.hasParent())
            node.getParent().replaceChild(node, child);
        else
            setRoot(child);

        child.setLeftChild(node);
    }

    /**
     * Rotates the specific node to the right.
     * @param node the node to be rotated.
     * very similar to rotate left but just the mirror image.
     */
    protected void rotateRight(N node) {
        N child = node.getLeftChild();
        node.setLeftChild(child.getRightChild());

        if (node.hasParent())
            node.getParent().replaceChild(node, child);
        else
            setRoot(child);

        child.setRightChild(node);
    }

    @Override
    public N add(T key) {
        //calls the add() method in the super class, AbstractBinarySearchTree
        N node = super.add(key);
        //performs balancing on node that is just added.
        balance(node);
        return node;
    }

    @Override
    public N remove(T key) {

        N node = findNode(root, key);

        if (node != null) {
            N lowest = node.hasBothChildren() ? removeHibbard(node) : removeSelf(node);
            //performs balancing on node that is the lowest node in the tree affected by this removal.
            if (lowest != null && lowest != node) balance(lowest);
        }

        return node;
    }

    /**
     * Preserves the balance of the specific node and its ancestors.
     * balances when needed
     * @param node the node to be balanced.
     */
    //defines a balancing algorithm for a specific type of balanced binary search trees.
    protected abstract void balance(N node);
    //need to check after add; need to check after remove
}
