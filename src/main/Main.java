package main;
import java.lang.*;
import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String args[]){
		Graph graph = new Graph();
		String filePath = "src/DefaultGraph.txt";
		
		graph.readMap(filePath);
		System.out.println(graph);
		
		System.out.println(graph.DFS("4"));
		System.out.println(graph.BFS("4"));
		System.out.println(graph.ShortestPath_BFS("4", "6"));
	}
}
