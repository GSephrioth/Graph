package main;
/* GraphNode 
 * Each node contains it`s name and the edges linked to it
 * */

import java.util.*;

public class GraphNode {
	private String name; //name of the node
	private List<String> edges; //edges linked to the node
	private boolean mark; //whether the node have been explored
	private String parent; //the previous Node during the search

	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void mark(){
		mark = true;
	}
	public void unMark(){
		mark = false;
	}
	public boolean isMarked(){
		return mark;
	}
	
	public List<String> getEdges(){
		return edges;
	}
	
	/* Construction Method 
	 * Create an empty Node without edges linked to it.
	 * */
	public GraphNode(){
		this.name = null;
		this.edges = new ArrayList<String>();
		this.mark = false;
		this.parent = null;
	}
	
	/* toString
	 * Override the 'toString' in class 'Object'
	 * return a String containing the detail of the Node
	 * */
	public String toString(){
		String str = "node Name: "+name+" edges Number: "+edges.size()+" Linked nodes: ";
		if(!edges.isEmpty()){
			Iterator i = edges.iterator();
			while(i.hasNext()){
				str=str+i.next()+",";
			}
		}
		else{
			str+="Null";
		}
		return str;
	}
	
	/* String2Node (String to Node)
	 * get the node from a String, which must follow the listed pattern:
	 * 1. The String represents a node and the edges linked to it.
	 * 2. Words are separated by 'space'.
	 * 3. First word of each line represents the name of the Node.
	 * 4. Following words represents the edges.
	 * eg. a b c d e
	 * It means 'a' is the current node and it is linked to four other nodes:'b' 'c' 'd' 'e'.
	 * */
	public void String2Node(String str) throws Exception{
		List<String> temp = new LinkedList<String>();
		for (String part : str.split("\\s+")){
			temp.add(part);
		}
		if(!temp.isEmpty()){
			Iterator i = temp.iterator();
			if(i.hasNext()){
				this.name = i.next().toString();
			}
			while(i.hasNext()){
				this.edges.add(i.next().toString());
			}
		}
		temp.clear();
	}
	
	
}
