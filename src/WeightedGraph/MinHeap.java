package WeightedGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Minimum Heap
 * specially designed for Edge
 * Created by cxz on 2016/10/3.
 */

public class MinHeap {
    private List<Edge> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    /**
     * swap heap[a] and heap[b]
     * Complexity: constant
     */
    public void swap(int a, int b) {
        Edge temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    /**
     * Recursively move down the heap[index]
     * Complexity: log(n)
     */
    public void heapDown(int index) {
        // because first element is regard as empty
        int n = heap.size() - 2;
        // store the index of min element
        int child = -1;
        // have no child
        if (2 * index > n) {
            return;
        } // have both child
        else if (2 * index < n) {
            child = 2 * index;
            if (heap.get(child).largerThan(heap.get(child + 1))) {
                child++;
            }
        }// have one child ( must be left child)
        else if (2 * index == n) {
            child = 2 * index;
        }
        if (heap.get(child).smallerThan(heap.get(index))) {
            swap(child, index);
            heapDown(child);
        }
    }

    /**
     * Recursively move up the heap[index]
     * Complexity: log(n)
     */
    public void heapUp(int index) {

        // root is heap[1]
        if (index > 1) {
            int parent = index / 2;
            // if heap[parent] > heap[index] than swap and continue heapUp
            if (heap.get(parent).largerThan(heap.get(index))) {
                swap(parent, index);
                heapUp(parent);
            }
        }
    }

    /**
     * insert an element to heap
     * Complexity: log(n)
     */
    public void insert(Edge element) {
        // heap[0] is regard as empty
        if (heap.size() == 0)
            heap.add(new Edge("", "", 0));
        // add the element at the end of the heap
        heap.add(element);
        heapUp(heap.size() - 1);
    }

    /**
     * delete heap[index]
     * Complexity: log(n)
     */
    public void delete(int index) {
        // assign the last element to heap[index]
        heap.set(index, heap.get(heap.size() - 1));
        heapDown(index);
        // remove the last element
        heap.remove(heap.size() - 1);
    }

    /**
     * Read and delete heap[1]
     * Complexity: log(n)
     */
    public Edge popMin() {
        if (heap.size() <= 1) return null;
        Edge temp = heap.get(1);
        delete(1);
        return temp;
    }

    /**
     * find element in the heap and update
     * Complexity: log(n)
     */
    public void update(Edge element) {
        boolean flag = false;
        if (heap.isEmpty()) {
            insert(element);
        } else {
            for (Edge e : heap) {
                if (e.getEndVertex().equals(element.getEndVertex())) {
                    flag = true;
                    if (e.getWeight() > element.getWeight()) {
                        delete(heap.indexOf(e));
                        insert(element);
                    }
                    break;
                }
            }
            if (!flag) {
                insert(element);
            }
        }

    }

    /**
     * find and return the Edges in the heap
     * which has same Start Vertex Name and End Vertex Name as the param
     * return null, if not found
     */
    public List<Edge> find(Vertex startVertex, Vertex endVertex) {
        List<Edge> result = new LinkedList<>();
        for (Edge e : heap) {
            if (e.getStartVertex().equals(startVertex) && e.getEndVertex().equals(endVertex))
                result.add(e);
        }
        if (result.isEmpty()) return null;
        else return result;
    }
}