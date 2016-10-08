package main;

import WeightedGraph.*;
import com.sun.corba.se.impl.orb.ParserTable;

import java.lang.*;
import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String args[]){
		String filePath = "src/DefaultGraph.txt";
		File f = new File(filePath);

        Graph graph = new Graph(f);
        System.out.println(graph);

//        graph.addEdge("6", "5", 5);
//        System.out.println(graph);
//        System.out.println(graph.isConnected());
//        System.out.println(graph.hasCircle());


        try {
            System.out.println(graph.PrimMST("1"));
            System.out.println(graph.Dijkstra("1"));
            System.out.println(graph.Kruskal());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
