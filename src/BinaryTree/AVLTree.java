
package BinaryTree;

import java.util.Comparator;

/**
 * Balanced Binary Search Tree designed for generics
 * leftChild < currentNode < rightChild
 * Created by cxz on 2016/10/9.
 */
public class AVLTree<T> {
    private AVLNode root;
    private Comparator<T> comparator;
    public AVLTree() {
        this.root = null;
    }

    public AVLTree(Comparator<T> comparator) {
        this.comparator = comparator;
        this.root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int getHeight() {
        return root.height;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public T findMin() {
        return isEmpty() ? null : findMin(root).element;
    }

    public T findMax() {
        return isEmpty() ? null : findMax(root).element;
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    public boolean sameAs(AVLTree<T> t) {
        return sameAs(root, t.root);
    }

    /**
     * Use Comparator to compare a and b
     *
     * @param a,b are generics to compare
     * @return result of Comparator
     */
    private int compareTo(T a, T b) {
        if (comparator != null) return comparator.compare(a, b);
        return ((Comparable<T>) a).compareTo(b);
    }

    /**
     * return the Max height among two Nodes
     *
     * @param node1,node2 are two nodes for comparation
     * @return result of Maximum Height among two nodes
     **/
    private int maxHeight(AVLNode node1, AVLNode node2) {
        int h1, h2;
        if (node1 == null) h1 = 0;
        else h1 = node1.height;
        if (node2 == null) h2 = 0;
        else h2 = node2.height;
        return h1 > h2 ? h1 : h2;
    }

    /**
     * Four different Rotation of the AVL Tree:
     * L, LR, R, RL
     *
     * @param root is the root Node of subtree, which Lost Balance
     * @return root node of subtree after Rotating.
     */
    private AVLNode singleRotateLeft(AVLNode root) {
//        System.out.println("L");
        AVLNode lChild;
        if (root == null) return null;
        // Do a Simple Left Rotation
        lChild = root.leftChild;
        root.leftChild = lChild.rightChild;
        lChild.rightChild = root;
        // Recalculate the height of two Nodes
        root.height = maxHeight(root.leftChild, root.rightChild) + 1;
        lChild.height = maxHeight(lChild.leftChild, root) + 1;
        return lChild;
    }

    private AVLNode singleRotateRight(AVLNode root) {
//        System.out.println("R");
        AVLNode rChild;
        if (root == null) return null;
        // Do a Simple Right Rotation
        rChild = root.rightChild;
        root.rightChild = rChild.leftChild;
        rChild.leftChild = root;
        // Recalculate the height of two Nodes
        root.height = maxHeight(root.leftChild, root.rightChild) + 1;
        rChild.height = maxHeight(rChild.rightChild, root) + 1;
        return rChild;
    }

    private AVLNode doubleRotateLR(AVLNode root) {
//        System.out.println("LR");
        // First do a Left Rotation
        root.rightChild = singleRotateLeft(root.rightChild);
        // Then do a Right Rotation
        return singleRotateRight(root);
    }

    private AVLNode doubleRotateRL(AVLNode root) {
//        System.out.println("RL");
        // First do a Right Rotation
        root.leftChild = singleRotateRight(root.leftChild);
        // Then do a Left Rotation
        return singleRotateLeft(root);
    }

    /**
     * Internal method to find an item in a subtree.
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains(T x, AVLNode t) {
        if (t == null) return false;
        int result = compareTo(x, t.element);
        if (result < 0) return contains(x, t.leftChild);
        else if (result > 0) return contains(x, t.rightChild);
        return true;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private AVLNode findMin(AVLNode t) {
        if (t == null) return null;
        if (t.leftChild == null) return t;
        return findMin(t.leftChild);
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private AVLNode findMax(AVLNode t) {
        if (t == null) return null;
        if (t.rightChild == null) return t;
        return findMax(t.rightChild);
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x    the item to insert.
     * @param root the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AVLNode insert(T x, AVLNode root) {
        // create a new subtree with only one Node
        if (root == null) return new AVLNode(x);

        int cmp = compareTo(x, root.element);
        int balanceFactor;

        if (cmp < 0) {
            root.leftChild = insert(x, root.leftChild);
//            root.leftChild.height++;
            root.height = maxHeight(root.leftChild, root.rightChild) + 1;
            // Check if the Balance is broken
            if (root.rightChild == null) balanceFactor = root.leftChild.height;
            else balanceFactor = root.leftChild.height - root.rightChild.height;
            if (balanceFactor >= 2) {
                // Find out the x have been inserted into which subtree
                if (compareTo(x, root.leftChild.element) < 0)
                    root = singleRotateLeft(root);
                else
                    root = doubleRotateRL(root);
            }
        } else if (cmp > 0) {
            root.rightChild = insert(x, root.rightChild);
//            root.rightChild.height++;
            root.height = maxHeight(root.leftChild, root.rightChild) + 1;
            // Check if the Balance is broken

            if (root.leftChild == null) balanceFactor = root.rightChild.height;
            else balanceFactor = root.rightChild.height - root.leftChild.height;
            if (balanceFactor >= 2) {
                // Find out the x have been inserted into which subtree
                if (compareTo(x, root.rightChild.element) < 0)
                    root = doubleRotateLR(root);
                else
                    root = singleRotateRight(root);
            }
        }
//        else  //comparator = 0
//            System.err.println("Cannot insert same elements into a AVL Tree!");
        return root;
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AVLNode remove(T x, AVLNode t) {
        // element not found print error
        if (t == null) {
            System.err.println("Sorry but you're mistaken, " + x + " doesn't exist in this tree :)" + System.getProperty("line.separator"));
            return null;
        }

        int cmp = compareTo(x, t.element);
        // x is smaller than t.element
        if (cmp < 0) {
            t.leftChild = remove(x, t.leftChild);
            int lh = t.leftChild != null ? t.leftChild.height : 0;
            if (t.rightChild != null && t.rightChild.height - lh >= 2) {
                int rrh = t.rightChild.rightChild != null ? t.rightChild.rightChild.height : 0;
                int rlh = t.rightChild.leftChild != null ? t.rightChild.leftChild.height : 0;

                if (rrh >= rlh) t = singleRotateRight(t);
                else t = doubleRotateLR(t);
            }
            // x is larger than t.element
        } else if (cmp > 0) {
            // cause a problem when remove 10
            t.rightChild = remove(x, t.rightChild);
            int rh = t.rightChild != null ? t.rightChild.height : 0;
            if (t.leftChild != null && t.leftChild.height - rh >= 2) {
                int llh = t.leftChild.leftChild != null ? t.leftChild.leftChild.height : 0;
                int lrh = t.leftChild.rightChild != null ? t.leftChild.rightChild.height : 0;

                if (llh >= lrh) t = singleRotateLeft(t);
                else t = doubleRotateRL(t);
            }
            // x is equal to t.element
        } else if (t.rightChild != null) {
            // find the min node of the right sub tree and replace t.element
            t.element = findMin(t.rightChild).element;
            t.rightChild = remove(t.element, t.rightChild);
            //balance t

            if (t.rightChild != null && t.leftChild != null && t.rightChild.height - t.leftChild.height >= 2) {
                int rrh = t.rightChild.rightChild != null ? t.rightChild.rightChild.height : 0;
                int rlh = t.rightChild.leftChild != null ? t.rightChild.leftChild.height : 0;
                if (rrh >= rlh) singleRotateLeft(t);
                else doubleRotateRL(t);
            }
        } else if (t.leftChild != null) {
            t = t.leftChild;
        } else // x equals to t.element and t is leaf node
            t = null;
        if (t != null)
            t.height = maxHeight(t.rightChild, t.leftChild) + 1;
        return t;
    }

    @Override
    public String toString() {
        StringBuilder strB = new StringBuilder();
        if (isEmpty()) return "Empty tree";
        else {
            strB.append("[");
            printTree(root, strB);
            strB.append("]");
        }
        return strB.toString();
    }

    /**
     * Print the tree into the stringBuilder with inorder traversal
     *
     * @param t             root of the tree
     * @param stringBuilder output String
     */
    private void printTree(AVLNode t, StringBuilder stringBuilder) {
        if (t != null) {
            printTree(t.leftChild, stringBuilder);
            stringBuilder.append("Node: ");
            stringBuilder.append(t.element.toString());
            stringBuilder.append(" Height: ");
            stringBuilder.append(t.height);
            stringBuilder.append(System.getProperty("line.separator"));
            printTree(t.rightChild, stringBuilder);
        }
    }

    /**
     * Check Two trees t1 and t2, if they have the same structure
     * !!Attention!! This function does not care about values in tree nodes
     *
     * @param t1,t2 root nodes of two trees
     * @return True if two trees have same structure, otherwise return False
     */
    private boolean sameAs(AVLNode t1, AVLNode t2) {
        if (t1 == null || t2 == null) return true;
        else if ((t1.leftChild == null && t2.leftChild != null) ||
                (t1.rightChild == null && t2.rightChild != null) ||
                (t1.leftChild != null && t2.leftChild == null) ||
                (t1.rightChild != null && t2.rightChild == null))
            return false;
        else return sameAs(t1.leftChild, t2.leftChild) && sameAs(t1.rightChild, t2.rightChild);
    }

    private class AVLNode {
        T element;      // Value of this Node
        int height;     // Height of this Node
        AVLNode leftChild;    // Left Child Node of this Node
        AVLNode rightChild;   // Right Child Node of this Node

        AVLNode(T element) {
            this.element = element;
            this.leftChild = null;
            this.rightChild = null;
            this.height = 1;
        }

        AVLNode(T element, AVLNode leftChild, AVLNode rightChild) {
            this.element = element;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.height = 1;
        }
    }
}