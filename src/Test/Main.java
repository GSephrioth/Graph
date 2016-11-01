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
        /* Creating object of AVLTree */
        AVLTree<Integer> avlt = new AVLTree<>();

        int[] array = {3, 10, 9, 5, 2, 10, 20, 22, 33, 10, 12};
        for (int i : array) avlt.insert(i);

        System.out.println("AVLTree Tree Test\n");
        char ch;
        /*  Perform tree operations  */
        do {
            System.out.println("\nAVLTree Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. search");
            System.out.println("3. count nodes");
            System.out.println("4. check empty");
            System.out.println("5. clear tree");

            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter integer element to insert");
                    avlt.insert(scan.nextInt());
                    break;
                case 2:
                    System.out.println("Enter integer element to search");
//                    System.out.println("Search result : "+ avlt.search( scan.nextInt() ));
                    break;
                case 3:
//                    System.out.println("Nodes = "+ avlt.countNodes());
                    break;
                case 4:
                    System.out.println("Empty status = " + avlt.isEmpty());
                    break;
                case 5:
                    System.out.println("\nTree Cleared");
                    avlt.makeEmpty();
                    break;
                default:
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            /*  Display tree  */
            System.out.println("\nPost order : ");
            avlt.postorder();
            System.out.println("\nPre order : ");
            avlt.preorder();
            System.out.println("\nIn order : ");
            avlt.inorder();

            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }
}
