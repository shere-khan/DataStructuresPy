import java.util.*;
import java.lang.*;
import java.util.function.*;

public class Graph {
    private class Node {

	public String value;

	public Node(String value) {
	    this.value = value;
	}
    }

    private class Edge {

	public Integer weight;
	public Node from;
	public Node to;

	public Edge(Node from, Node to) {
	    this.from = from;
	    this.to = to;
	}
    }

    private Map<Node, Map<Node, Edge>> table;
    private boolean isDirected;

    public Graph(boolean isDirected) {
	this.isDirected = isDirected;
	table = new HashMap<Node, Map<Node, Edge>>();
    }
    
    private void addNodeToTable(Node n) {
	for (Map<Node, Edge> map: table.values())
	    map.put(n, null);
    }

    private void addEdge(Node n1, Node n2, Edge e) {
	table.get(n1).put(n2, e);
    }

    public static void main(String[] args) {
	System.out.println("hello");
    }
}
