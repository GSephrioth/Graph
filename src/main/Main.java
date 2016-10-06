package main;

import WeightedGraph.*;

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

        Vertex v = new Vertex("1");
        System.out.println(graph.PrimMST(v));
        System.out.println(graph.Dijkstra(v));

	}
}
