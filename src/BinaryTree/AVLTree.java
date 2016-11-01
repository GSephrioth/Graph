
package BinaryTree;

import java.util.Comparator;

/**
 * Balanced Binary Search Tree designed for generics
 * leftChild < currentNode < rightChild
 * Created by cxz on 2016/10/9.
 */
public class AVLTree<T> {
    private class BinaryNode<T> {
        T element;      // Value of this Node
        int height;     // Height of this Node
        BinaryNode<T> parent;       // Parent Node of this Node
        BinaryNode<T> leftChild;    // Left Child Node of this Node
        BinaryNode<T> rightChild;   // Right Child Node of this Node

        BinaryNode(T element) {
            this.element = element;
            this.leftChild = null;
            this.rightChild = null;
            this.height = 1;
        }

        BinaryNode(T element, BinaryNode<T> leftChild, BinaryNode<T> rightChild) {
            this.element = element;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.height = 1;
        }
    }

    private BinaryNode<T> root;
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
     * Use Comparator to compare a and b
     *
     * @param a,b are generics to compare
     * @return result of Comparator
     */
    private int compareTo(T a, T b) {
        if (comparator != null) return comparator.compare(a, b);
        return ((Comparable) a).compareTo(b);
    }

    /**
     * return the Max height among two Nodes
     **/
    private int maxHeight(BinaryNode<T> node1, BinaryNode<T> node2) {
        int h1, h2;
        if (node1 == null) h1 = 0;
        else h1 = node1.height;
        if (node2 == null) h2 = 0;
        else h2 = node2.height;
        return h1 > h2 ? h1 : h2;
    }

    private void updateHeight(BinaryNode<T> root) {
        if (root != null) {
            if (root.leftChild != null) updateHeight(root.leftChild);
            if (root.rightChild != null) updateHeight(root.rightChild);
            root.height = maxHeight(root.leftChild, root.rightChild);
        }
    }

    /**
     * Four different Rotation of the AVL Tree:
     * L, LR, R, RL
     *
     * @param root is the root Node of subtree, which Lost Balance
     * @return root node of subtree after Rotating.
     */
    private BinaryNode<T> singleRotateLeft(BinaryNode<T> root) {
        BinaryNode<T> lChild;
        // Do a Simple Left Rotation
        lChild = root.leftChild;
        root.leftChild = lChild.rightChild;
        lChild.rightChild = root;
        // Recalculate the height of two Nodes
        root.height = maxHeight(root.leftChild, root.rightChild) + 1;
        lChild.height = maxHeight(lChild.leftChild, root) + 1;
        return lChild;
    }

    private BinaryNode<T> singleRotateRight(BinaryNode<T> root) {
        BinaryNode<T> rChild;
        // Do a Simple Right Rotation
        rChild = root.rightChild;
        root.rightChild = rChild.leftChild;
        rChild.leftChild = root;
        // Recalculate the height of two Nodes
        root.height = maxHeight(root.leftChild, root.rightChild) + 1;
        rChild.height = maxHeight(rChild.rightChild, root) + 1;
        return rChild;
    }

    private BinaryNode<T> doubleRotateRL(BinaryNode<T> root) {
        // First do a Left Rotation
        root.rightChild = singleRotateLeft(root.rightChild);
        // Then do a Right Rotation
        return singleRotateRight(root);
    }

    private BinaryNode<T> doubleRotateLR(BinaryNode<T> root) {
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
    private boolean contains(T x, BinaryNode<T> t) {
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
    private BinaryNode<T> findMin(BinaryNode<T> t) {
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
    private BinaryNode<T> findMax(BinaryNode<T> t) {
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
    private BinaryNode<T> insert(T x, BinaryNode<T> root) {
        // create a new subtree with only one Node
        if (root == null) return new BinaryNode<T>(x);

        int cmp = compareTo(x, root.element);
        int balanceFactor = 0;

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
                    root = doubleRotateLR(root);
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
                    root = doubleRotateRL(root);
                else
                    root = singleRotateRight(root);
            }
        } else { // comparator = 0
            System.err.println("Cannot insert same elements into a AVL Tree!");
        }
        return root;
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        // element not found print error
        if (t == null) {
            System.err.println("Sorry but you're mistaken, " + t + " doesn't exist in this tree :)\n");
            return null;
        }
        System.out.println("Remove starts... " + t.element + " and " + x);

        int cmp = compareTo(x, t.element);
        if (cmp < 0) {
            t.leftChild = remove(x, t.leftChild);
            // Check if the Balance is broken
            if (root.rightChild.height - root.leftChild.height >= 2) {
                // Find out the x have been inserted into which subtree
                if (compareTo(x, root.rightChild.element) < 0)
                    root = doubleRotateRL(root);
                else
                    root = singleRotateRight(root);
            }
        } else if (cmp > 0) {
            t.rightChild = remove(x, t.rightChild);
            // Check if the Balance is broken
            if (root.rightChild.height - root.leftChild.height >= 2) {
                // Find out the x have been inserted into which subtree
                if (compareTo(x, root.rightChild.element) < 0)
                    root = doubleRotateRL(root);
                else
                    root = singleRotateRight(root);
            }
        }
        // cmp == 0 which means current node is the Element
        else if (t.leftChild != null && t.rightChild != null) { // Two children
            t.element = removeMin(t.rightChild).element;
        } else { // One children or leaf;
            t = (t.leftChild != null) ? t.leftChild : t.rightChild;
        }
        return t;
    }

    /**
     * Internal method to remove the smallest node from a subtree.
     *
     * @param t the node that roots the subtree.
     * @return the smallest node of the subtree.
     */
    private BinaryNode<T> removeMin(BinaryNode<T> t) {
        if (t == null) return null;
        BinaryNode<T> n = t;
        BinaryNode<T> p = n;
        while (n.leftChild != null) {
            p = n;
            n = n.leftChild;
        }
        p.leftChild = n.rightChild;
        return n;
    }

    private void printTree(BinaryNode<T> t, StringBuilder stringBuilder) {
        if (t != null) {
            printTree(t.leftChild, stringBuilder);
            stringBuilder.append(t.element.toString());
            stringBuilder.append(" , ");
            printTree(t.rightChild, stringBuilder);
        }
    }

    /* Function for inorder traversal */
    public void inorder() {
        inorder(root);
    }

    private void inorder(BinaryNode<T> r) {
        if (r != null) {
            inorder(r.leftChild);
            System.out.println("node: " + r.element + " height: " + r.height);
            inorder(r.rightChild);
        }
    }

    /* Function for preorder traversal */
    public void preorder() {
        preorder(root);
    }

    private void preorder(BinaryNode<T> r) {
        if (r != null) {
            System.out.println("node: " + r.element + " height: " + r.height);
            preorder(r.leftChild);
            preorder(r.rightChild);
        }
    }

    /* Function for postorder traversal */
    public void postorder() {
        postorder(root);
    }

    private void postorder(BinaryNode<T> r) {
        if (r != null) {
            postorder(r.leftChild);
            postorder(r.rightChild);
            System.out.println("node: " + r.element + " height: " + r.height);
        }
    }
}