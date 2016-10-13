I use java 8 as my programming language.
"main" folder contains all the codes.
"test.jar" is a runnable jar file, and all results are echo to the Terminal.
"DefaultGraph.txt" is used to store the graph, which have to follow these rules:

1. Each line in the file represents a node and the edges linked to it.
2. Words are separated by 'space'.
3. First word of each line represents the name of the Node.
4. Following words represents the edges.

Two packages: "main" for test
              "WeightedGraph" for all the important classes

Four important classes: "Vertex" represents a Vertex in the Graph
                        "Edge" represents an Edge in the Graph
                        "Graph" represents the Graph
                        "MinHeap" represents a MinHeap of Edges

Function Complexity: Dijkstra`s Algorithm O( E * log(V) )
                     Kruskal`s Algorithm O( E * (V+E) )

As there is dozens of functions, to short the README file, I`ve add comments to all the important parts.

Make sure "DefaultGraph.txt" and "test.jar" are in the same folder
Run in Terminal: $ java -jar test.jar

DefaultGraph.txt:
1 2,10 3,20 4,15
2 1,10 4,35 5,30
3 1,20 5,25
4 1,15 2,35
5 2,30 3,25

Expected Output:
Vertex: 1 [ (Vertex: 1 ,Vertex: 2 ,10) ,  (Vertex: 1 ,Vertex: 3 ,20) ,  (Vertex: 1 ,Vertex: 4 ,15) ]
Vertex: 2 [ (Vertex: 2 ,Vertex: 1 ,10) ,  (Vertex: 2 ,Vertex: 4 ,35) ,  (Vertex: 2 ,Vertex: 5 ,30) ]
Vertex: 3 [ (Vertex: 3 ,Vertex: 1 ,20) ,  (Vertex: 3 ,Vertex: 5 ,25) ]
Vertex: 4 [ (Vertex: 4 ,Vertex: 1 ,15) ,  (Vertex: 4 ,Vertex: 2 ,35) ]
Vertex: 5 [ (Vertex: 5 ,Vertex: 2 ,30) ,  (Vertex: 5 ,Vertex: 3 ,25) ]

{Vertex: 1 =0, Vertex: 2 =10, Vertex: 3 =20, Vertex: 4 =15, Vertex: 5 =40}

Vertex: 1 [ (Vertex: 1 ,Vertex: 2 ,10) ,  (Vertex: 1 ,Vertex: 4 ,15) ,  (Vertex: 1 ,Vertex: 3 ,20) ]
Vertex: 2 [ (Vertex: 2 ,Vertex: 1 ,10) ]
Vertex: 3 [ (Vertex: 3 ,Vertex: 1 ,20) ,  (Vertex: 3 ,Vertex: 5 ,25) ]
Vertex: 4 [ (Vertex: 4 ,Vertex: 1 ,15) ]
Vertex: 5 [ (Vertex: 5 ,Vertex: 3 ,25) ]