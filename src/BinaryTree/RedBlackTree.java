package BinaryTree;

import java.util.Comparator;

/**
 * Red Black Tree designed for generics
 * leftChild < currentNode < rightChild
 * Created by cxz on 2016/10/24.
 */
public class RedBlackTree<T> {
    private BinaryNode<T> root;
    private Comparator<T> comparator;

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

}
