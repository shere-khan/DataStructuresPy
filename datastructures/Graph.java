import java.util.*;
import java.lang.*;
import java.util.function.*;

public class Graph {
    private class Node {

	public String value;

	public Node(String value) {
	    this.value = value;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 17;
	    result = prime * result + value.hashCode();
	    // System.out.println("hashcode: " + result);

	    return result;
	}

	@Override
	public boolean equals(Object o) {
	    return value.equals(((Node) o).value) ? true : false;
	}

	@Override
	public String toString() {
	    return value;
	}
    }

    private class Edge {

	public Integer weight;
	public Node from;
	public Node to;

	public Edge(Node from, Node to, Integer weight) {
	    this.from = from;
	    this.to = to;
	    this.weight = weight;
	}
    }

    public Map<Node, Map<Node, Edge>> table;
    private boolean isDirected;

    public Graph(boolean isDirected) {
	this.isDirected = isDirected;
	this.table = new HashMap<Node, Map<Node, Edge>>();
    }

    private void printTable() {
	System.out.println("printing table");
	for (Map.Entry<Node, Map<Node, Edge>> entry : table.entrySet()) {
	    System.out.println("values in " + entry.getKey().toString());
	    Map<Node, Edge> tab2 = entry.getValue();
	    System.out.println("assoc nodes");
	    for (Node entry2: tab2.keySet()) {
		System.out.println(entry2.toString());
	    }
	    System.out.println();
	}
    }
    
    private Node addNodeToTable(Node n) {
	table.put(n, new HashMap<Node, Edge>());
	for (Map<Node, Edge> map: table.values()) {
	    // System.out.println("value: " + map.toString());
	    map.put(n, null);
	}

	return n;
    }

    private void addEdge(Node n1, Node n2, Edge e) {
	table.get(n1).put(n2, e);
    }

    public void testAddNode(String v) {
	table.put(new Node(v), null);
    }

    private Integer stringToAscii(String s) {
	StringBuilder sb = new StringBuilder();
	char[] letters = s.toCharArray();
	for (char ch : letters)
	    sb.append((byte) ch);

	return Integer.parseInt(sb.toString());
    }

    public void insertEdge(String v1, String v2, Integer w) {
	Node n1 = new Node(v1);
	Node n2 = new Node(v2);
	if (!table.containsKey(n1)) {
	    n1 = addNodeToTable(n1);
	} 
	if (!table.containsKey(n2)) {
	    n2 = addNodeToTable(n2);
	}
	if (n1 == null || n2 == null)
	    throw new IllegalArgumentException("n1 or n2 null");
	Edge e = new Edge(n1, n2, w);
	addEdge(n1, n2, e);
	if (!isDirected)
	    addEdge(n2, n1, e);
    }

    public static void main(String[] args) {
	Graph g = new Graph(false);
	g.insertEdge("A", "B", 2);
	g.printTable();
	System.out.println();
	g.insertEdge("F", "A", 3);
	g.printTable();
    }
}
