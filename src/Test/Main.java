package Test;

import BinaryTree.AVLTree;


import java.lang.*;
import java.util.Scanner;

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
        Scanner scan = new Scanner(System.in);
        String NL = System.getProperty("line.separator");
        /* Creating object of AVLTree */
        AVLTree<Integer> avlt = new AVLTree<>();

        int[] array = {3, 10, 9, 5, 2, 10, 20, 22, 33, 10, 12, 8, 50, 4, 32, 34, 54, 1, 2, 7};
        for (int i : array)
            avlt.insert(i);
        System.out.println("Current Tree: " + NL + avlt.toString());
        for (int i : array) {
            avlt.remove(i);
            System.out.println("Current Tree: " + NL + avlt.toString());
        }
        System.out.println("AVLTree Tree Test" + NL);
        char ch;
        /*  Perform tree operations  */
        do {
            System.out.println(NL + "AVLTree Operations" + NL);
            System.out.println("1. insert ");
            System.out.println("2. search");
            System.out.println("3. delete");
            System.out.println("4. height of tree");
            System.out.println("5. check empty");
            System.out.println("6. clear tree");

            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter integer element to insert");
                    avlt.insert(scan.nextInt());
                    break;
                case 2:
                    System.out.println("Enter integer element to search");
                    System.out.println("Search result : " + avlt.contains(scan.nextInt()));
                    break;
                case 3:
                    System.out.println("Enter integer element to delete");
                    avlt.remove(scan.nextInt());
                    break;
                case 4:
                    System.out.println("Nodes = " + avlt.getHeight());
                    break;
                case 5:
                    System.out.println("Empty status = " + avlt.isEmpty());
                    break;
                case 6:
                    System.out.println(NL + "Tree Cleared");
                    avlt.makeEmpty();
                    break;
                default:
                    System.out.println("Wrong Entry " + NL);
                    break;
            }
            System.out.println("Current Tree: " + NL + avlt.toString());

            System.out.println(NL + "Do you want to continue (Type y or n) " + NL);
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');

    }
}
