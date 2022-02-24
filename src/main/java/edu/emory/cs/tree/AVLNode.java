package edu.emory.cs.tree;

public class AVLNode<T extends Comparable<T>> extends AbstractBinaryNode<T, AVLNode<T>> {
    // keeps track of the height of this node.
    //based purely on height; compare height of left and right trees for balancing.
    private int height;

    public AVLNode(T key) {
        super(key);
        //a node with no children has the height of 1.
        height = 1;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setLeftChild(AVLNode<T> node) {
        super.setLeftChild(node);
        resetHeights();
    }

    @Override
    public void setRightChild(AVLNode<T> node) {
        super.setRightChild(node);
        resetHeights();
    }

    /** Resets the heights of this node and its ancestor, recursively. */
    public void resetHeights() {
        //adjusts the height of this node and its ancestors, recursively.
        resetHeightsAux(this);
    }

    private void resetHeightsAux(AVLNode<T> node) {
        if (node != null) {
            // retrieve the height of this node.
            int lh = node.hasLeftChild() ? node.getLeftChild().getHeight() : 0;
            int rh = node.hasRightChild() ? node.getRightChild().getHeight() : 0;
            int height = Math.max(lh, rh) + 1;

            // resets the height of this node if different and
            // makes the recursive call to its parent.
            if (height != node.getHeight()) {
                node.setHeight(height);
                resetHeightsAux(node.getParent());  // recursively update parent height
            }
        }
    }

    /** @return height(left-subtree) - height(right-subtree). */
    public int getBalanceFactor() {
        if (hasBothChildren())
            return left_child.getHeight() - right_child.getHeight();
        else if (hasLeftChild())
            return left_child.getHeight();
        else if (hasRightChild())
            return -right_child.getHeight();
        else
            return 0;
    }

    /**
     * How can we tell if the node is unbalanced by using the balance factor?
     * Tree is unbalanced if the getBalanceFactor is 2+ or (-2)-;
     * +2-> unbalanced right
     * -2 -> unbalanced left.
     *
     */


}
