package BinaryTree;

import java.util.Comparator;

/**
 * Binary BinarySearchTree designed for generics
 * leftChild < currentNode < rightChild
 * Created by cxz on 2016/10/9.
 */
public class BinarySearchTree<T> {
    private class BinaryNode<T> {
        T element;
        BinaryNode<T> leftChild;
        BinaryNode<T> rightChild;

        BinaryNode(T element) {
            this.element = element;
        }

        BinaryNode(T element, BinaryNode<T> leftChild, BinaryNode<T> rightChild) {
            this.element = element;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

    private BinaryNode<T> root;
    private Comparator<T> cmp;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchTree(Comparator<T> cmp) {
        this.cmp = cmp;
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

    public boolean sameAs(BinaryNode<T> t) {
        return sameAs(root, t);
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

    private int compareT(T a, T b) {
        if (cmp != null) return cmp.compare(a, b);
        return ((Comparable) a).compareTo(b);
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
        int result = compareT(x, t.element);
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
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null) return new BinaryNode<T>(x);
        int result = compareT(x, t.element);
        if (result < 0) t.leftChild = insert(x, t.leftChild);
        else if (result > 0) t.rightChild = insert(x, t.rightChild);
        else ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if (t == null) return t; // Item not found; do nothing
        int result = compareT(x, t.element);
        if (result < 0) t.leftChild = remove(x, t.leftChild);
        else if (result > 0) t.rightChild = remove(x, t.rightChild);
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

    private boolean sameAs(BinaryNode<T> t1, BinaryNode<T> t2) {
        if (t1 == null || t2 == null) return false;
        else if ((t1.leftChild == null && t2.leftChild != null) ||
                (t1.rightChild == null && t2.rightChild != null) ||
                (t1.leftChild != null && t2.leftChild == null) ||
                (t1.rightChild != null && t2.rightChild == null))
            return false;
        else return sameAs(t1.leftChild, t2.leftChild) && sameAs(t1.rightChild, t2.rightChild);
    }
}
