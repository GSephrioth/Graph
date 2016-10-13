package Test;

import BinaryTree.BinarySearchTree;
import WeightedGraph.*;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.lang.*;
import java.io.*;
import java.util.Random;

public class Main {
	public static void main(String args[]){
//		String filePath = "DefaultGraph.txt";
//		File f = new File(filePath);
//
//        Graph graph = new Graph(f);
//        System.out.println(graph);
//
//        graph.addEdge("6", "5", 5);
//        System.out.println(graph);
//        System.out.println(graph.isConnected());
//        System.out.println(graph.hasCircle());
//
//        try {
//            System.out.println(graph.PrimMST("1"));
//            System.out.println(graph.Dijkstra("1"));
//            System.out.println();
//            System.out.println(graph.Kruskal());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Random r = new Random();

        r.setSeed(3);
        for (int i = 0; i < 20; i++) {
            tree.insert(r.nextInt(200));
        }

        System.out.println(tree);
    }

    /**
     * Find out the longest
     */
    public static void LCS() {
        String s1 = null;
        String s2 = null;
        s1 = "abcdaf";
        s2 = "acbcf";
    }
}
