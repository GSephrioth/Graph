package WeightedGraph;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Minimum Heap
 * Contains Edges of the Graph
 * Used to find the Minimum Spanning Tree
 * Created by cxz on 2016/10/2.
 */
public class MinHeap {
    // 堆的存储结构 - 数组
    private Edge[] data;

    /**
     * 初始化堆中的数据,以int的最小值作为初始值
     *
     * @param size
     */
    public MinHeap(int size) {
        if (size <= 0) {
            System.out.println("Heap initialize error: Heap size must be bigger than 0!");
            return;
        }
        this.data = new Edge[size];
        for (int i = 0; i < size; i++) {
            data[i] = new Edge();
        }
    }

    private void adjustHeap(int i) {
        //获取左右结点的数组下标
        int l = left(i);
        int r = right(i);

        // 这是一个临时变量，表示 跟结点、左结点、右结点中最小的值的结点的下标
        int min = i;

        // 存在左结点，且左结点的值小于根结点的值
        if (l < data.length && data[l].smallerThan(data[i])) {
            min = l;
        }
        // 存在右结点，且右结点的值小于以上比较的较小值
        if (r < data.length && data[r].smallerThan(data[min])) {
            min = r;
        }

        // 左右结点的值都大于根节点，直接return，不做任何操作
        if (i == min)
            return;

        // 交换根节点和左右结点中最小的那个值，把根节点的值替换下去
        swap(i, min);

        // 由于替换后左右子树会被影响，所以要对受影响的子树再进行adjustHeap
        adjustHeap(min);
    }

    /**
     * 获取右结点的数组下标
     *
     * @param i
     * @return int
     */
    private int right(int i) {
        return (i + 1) << 1;
    }

    /**
     * 获取左结点的数组下标
     *
     * @param i
     * @return int
     */
    private int left(int i) {
        return ((i + 1) << 1) - 1;
    }

    /**
     * 交换元素位置
     *
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
        Edge tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    /**
     * 将数值加入到堆中
     *
     * @param element
     */
    public void add(Edge element) {
        if (data[0].smallerThan(element)) {
            data[0] = element;
            adjustHeap(0);
        }
    }

    public Edge[] getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (int i = 0; i < data.length; i++) {
            builder.append(data[i]);
            if (i != data.length - 1) {
                builder.append(",");
            }
        }
        builder.append("}");

        return builder.toString();
    }
}