package edu.emory.cs.tree.balanced;
import edu.emory.cs.tree.BinaryNode;

public class BalancedBinarySearchTreeQuiz<T extends Comparable<T>>
        extends AbstractBalancedBinarySearchTree<T, BinaryNode<T>> {
    @Override
    public BinaryNode<T> createNode(T key) {
        return new BinaryNode<>(key);
    }
    @Override
    protected void balance(BinaryNode<T> node) {
        if (node == null) return;
        BinaryNode<T> cousin;
        BinaryNode<T> uncle = node.getUncle();
        BinaryNode<T> parent = node.getParent();
        BinaryNode<T> grandparent = node.getGrandParent();
        if(parent == null) return;
        if(uncle == null) return;
        if(grandparent == null) return;
        /*
         * Override the balance() method that first checks the following conditions:
         *      node is the only child &
         *      node’s parent is the right child of node's grand parent &
         *      node’s uncle has only one child.
         */
        boolean cousinIsLeftChild = true;
        boolean nodeIsLeftChild = node.getParent().isLeftChild(node);
        boolean onlyChild = (node.getSibling() == null) ;
        boolean parentIsARightChild = (node.getGrandParent().isRightChild(node.getParent())) ;
        boolean uncleHasOnlyOneChild = (node.getUncle().hasLeftChild() ^ node.getUncle().hasRightChild()) ;
        /*
         * If all of the above conditions are satisfied, balance() makes multiple rotations
         * such that the left subtree is always full before any node gets added to the right subtree.
         * For the example below, after adding the keys (in red) to the trees 1, 2, 3, and 4,
         * they all need to be transformed into the tree 5 by the balance() method.
         */
        if(onlyChild && parentIsARightChild && uncleHasOnlyOneChild){
            cousin = node.getUncle().getLeftChild();
            if (cousin == null){
                cousin = node.getUncle().getRightChild();
                cousinIsLeftChild = false;
            }
            if (cousinIsLeftChild && nodeIsLeftChild){ //Case 1: cousin and node are both left children
                rotateRight(parent);
                rotateLeft(grandparent);
                rotateRight(grandparent);
            }
            else if (cousinIsLeftChild && !nodeIsLeftChild){ //Case 2: cousin is left child and node is right child
                rotateLeft(grandparent);
                rotateRight(grandparent);
            }
            else if (!cousinIsLeftChild && nodeIsLeftChild){ //Case 3: cousin is right child and node is left child
                rotateRight(parent);
                rotateLeft(grandparent);
                rotateLeft(uncle);
                rotateRight(grandparent);
            }
            else { //Case 4: cousin and node are both right children
                rotateLeft(grandparent);
                rotateLeft(uncle);
                rotateRight(grandparent);
            }
        }
    }
}