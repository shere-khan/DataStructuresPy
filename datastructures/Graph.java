import java.util.*;
import java.lang.*;
import java.util.function.*;

public class Graph {
    private class Node {

	public String value;

	public Node(String value) {
	    this.value = value;
	}

	/* Duplicate "value" fields not allowed */
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 17;
	    result = prime * result + value.hashCode();
	    System.out.println("hashcode val: " + value);
	    System.out.println("hashcode result: " + result);

	    return result;
	}

	/* Duplicate "value" fields not allowed */
	@Override
	public boolean equals(Object o) {
	    return value.equals(((Node) o).value) ? true : false;
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
    
    private Node addNodeToTable(String v) {
	Node n = new Node(v);
	for (Map<Node, Edge> map: table.values())
	    map.put(n, null);
	table.put(n, null);

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
	Node n1 = null;
	Node n2 = null;
	if (!table.containsKey(v1))
	    n1 = addNodeToTable(v1);
	if (!table.containsKey(v2))
	    n2 = addNodeToTable(v2);
	if (n1 == null || n2 == null)
	    throw new IllegalArgumentException("n1 or n2 null");
	Edge e = new Edge(n1, n2, w);
	addEdge(n1, n2, e);
	if (!isDirected)
	    addEdge(n2, n1, e);
    }

    public static void main(String[] args) {
	Graph g = new Graph(false);
	g.testAddNode("A");
	g.testAddNode("B");
	for (Node k : g.table.keySet()) {
	    System.out.println(k.value);
	}
	g.testAddNode("A");
	for (Node k : g.table.keySet()) {
	    System.out.println(k.value);
	}
    }
}
