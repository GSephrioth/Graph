package WeightedGraph;

import java.io.File;
import java.util.*;

/**
 *
 * Created by cxz on 2016/10/3.
 */

public class MinHeap {
    List<Edge> heap;

    MinHeap() {
        this.heap = new ArrayList<>();
    }
    /**
     * 删除堆中位置是index处的值
     * 操作原理是：当删除节点的数值时，原来的位置就会出现一个孔
     * 填充这个孔的方法就是，把最后的叶子的值赋给该孔，最后把该叶子删除
     *
     */
    public void delete(int index) {
        //把最后的一个叶子的数值赋值给index位置
        heap.set(index, heap.get(heap.size() - 1));
        //下沉操作
        heapDown(index); //节点下沉
        //把最后一个位置的数字删除
        heap.remove(heap.size() - 1);
    }

    /**
     * 节点下沉递归实现
     * 删除一个堆中一个数据的时候，根据堆的性质，应该把相应的位置下移，才能保持住堆性质不变
     *
     * @param index 被删除的那个节点的位置
     */
    public void heapDown(int index) {
        //因为第一个位置存储的是空值，不在考虑之内
        int n = heap.size() - 2;

        //记录最小的那个节点的位置
        int child = -1;

        // 2 * index > n 说明该节点没有左右儿子节点了，那么就返回
        if (2 * index > n) {
            return;
        } //如果左右儿子都存在
        else if (2 * index < n) {

            //定义左儿子节点
            child = 2 * index;
            //如果左儿子大于右儿子的数值，取右儿子的下标
            if (heap.get(child).largerThan(heap.get(child + 1))) {
                child++;
            }

        }//如果只有一个儿子（左儿子节点）
        else if (2 * index == n) {
            child = 2 * index;
        }

        if (heap.get(child).smallerThan(heap.get(index))) {
            //交换堆中的child，和index位置的值
            swap(child, index);

            //完成交换后递归调用，继续下降
            heapDown(child);
        }
    }

    //打印链表
    public void print() {
        for (int i = 1; i < heap.size(); i++) {
            System.out.print(heap.get(i) + " ");
        }
        System.out.println();
    }

    //把堆中的a,b位置的值互换
    public void swap(int a, int b) {
        //临时存储child位置的值
        Edge temp = heap.get(a);

        //把index的值赋给child的位置
        heap.set(a, heap.get(b));

        //把原来的child位置的数值赋值给index位置
        heap.set(b, temp);
    }

    //向最小堆中插入元素
    public void insert(Edge element) {
        //在数组的尾部添加要插入的元素
        if (heap.size() == 0)
            heap.add(new Edge("", "", 0));//数组下标为0的位置不放元素
        heap.add(element);
        //开始上升操作
        heapUp(heap.size() - 1);

    }

    //节点上浮，让插入的数和父节点的数值比较，当小于父节点的时候就和节点的值相交换
    public void heapUp(int index) {

        //注意由于数值是从小标为一开始，当index = 1的时候，已经是根节点了
        if (index > 1) {
            //保存父亲的节点
            int parent = index / 2;

            //获取相应位置的数值

            //如果父亲节点比index的数值大，就交换二者的数值
            if (heap.get(parent).largerThan(heap.get(index))) {
                //交换数值
                swap(parent, index);
                //递归调用
                heapUp(parent);
            }

        }
    }

    /*根据树的性质建堆，树节点前一半一定是分支节点，即有孩子的，所以我们从这里开始调整出初始堆*/
    public void adjust() {
        for (int i = heap.size() / 2; i > 0; i--)
            adjust(i, heap.size() - 1);
        System.out.println("调整后的初始堆：");
        print();
    }
    /**
     * 调整堆，使其满足堆得定义
     * @param i
     * @param n
     */
    public void adjust(int i, int n) {

        int child;
        for (; i <= n / 2; ) {
            child = i * 2;
            if (child + 1 <= n && heap.get(child).largerThan(heap.get(child + 1)))
                child += 1;/*使child指向值较小的孩子*/
            if (heap.get(i).largerThan(heap.get(child))) {
                swap(i, child);
                /*交换后，以child为根的子树不一定满足堆定义，所以从child处开始调整*/
                i = child;

            } else break;
        }
    }

    public Edge popMin() {
        Edge temp = heap.get(1);
        delete(1);
        return temp;
    }

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
}