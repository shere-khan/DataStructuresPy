import java.util.*;
import java.lang.*;
import java.util.function.*;

public class Graph {
    private class Node {

	public String value;
	public Node prev;
	public boolean isVisited;
	public Integer weight;
	public List<Node> adj;

	public Node(String value) {
	    this.value = value;
	    this.weight = 1000;
	    this.isVisited = false;
	    this.adj = new ArrayList<Node>();
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 17;
	    result = prime * result + value.hashCode();

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
    public Map<String, Node> nodeMap;
    private boolean isDirected;

    public Graph(boolean isDirected) {
	this.isDirected = isDirected;
	this.table = new HashMap<Node, Map<Node, Edge>>();
	nodeMap = new HashMap<String, Node>();
    }

    private Node getNode(String n) {
	return nodeMap.get(n);
    }

    private void printTable() {
	for (Map.Entry<Node, Map<Node, Edge>> entry : table.entrySet()) {
	    System.out.println("values in " + entry.getKey().toString());
	    Map<Node, Edge> tab2 = entry.getValue();
	    for (Node entry2: tab2.keySet()) {
		System.out.println(entry2.toString());
	    }
	    System.out.println();
	}
    }
    
    private Node addNodeToTable(String v) {
	// Insert node into table.
	Node n = new Node(v);
	table.put(n, new HashMap<Node, Edge>());
	// put node into separate map for futre reference
	nodeMap.put(n.value, n);
	// Associate node with each existing node.
	for (Map<Node, Edge> map: table.values())
	    map.put(n, null);
	// Associate each existing node with new node.
	for (Node o : table.keySet())
	    table.get(n).put(o, null);

	return n;
    }

    private void addEdge(Node n1, Node n2, Edge e) {
	table.get(n1).put(n2, e);
    }

    private Integer stringToAscii(String s) {
	StringBuilder sb = new StringBuilder();
	char[] letters = s.toCharArray();
	for (char ch : letters)
	    sb.append((byte) ch);

	return Integer.parseInt(sb.toString());
    }

    private void relax(Node n1, Node n2, Queue<Node> q) {
	Edge e = table.get(n1).get(n2);
	Integer dist = n1.weight + e.weight;
	if (dist < n2.weight) {
	    n2.weight = dist;
	    n2.prev = n1;
	    q.add(n2);
	}
    }

    public void testAddNode(String v) {
	table.put(new Node(v), null);
    }

    public String getEdgeWeightAsString(String from, String to) {
	return table.get(new Node(from)).get(new Node(to)).weight.toString();
    }

    public void insertEdge(String v1, String v2, Integer w) {
	System.out.println(String.format("inserting edge %s -> %s", v1, v2));
	if (!nodeMap.containsKey(v1))
	    addNodeToTable(v1);
	if (!nodeMap.containsKey(v2))
	    addNodeToTable(v2);
	Node n1 = nodeMap.get(v1);
	Node n2 = nodeMap.get(v2);
	if (n1 == null || n2 == null)
	    throw new IllegalArgumentException("n1 or n2 null");
	Edge e = new Edge(n1, n2, w);
	addEdge(n1, n2, e);
	n1.adj.add(n2);
	if (!isDirected) {
	    addEdge(n2, n1, e);
	    n2.adj.add(n1);
	}
    }

    public void dijkstra(String source) {
	Comparator<Node> nodeComparator = new Comparator<Node>() {

	    @Override
	    public int compare(Node n1, Node n2) {
		return Integer.compare(n1.weight.intValue(), n2.weight.intValue());
	    }
	};
	Queue<Node> q = new PriorityQueue<Node>(10, nodeComparator);
	Node s = getNode(source);
	System.out.println("start node " + s.value);
	System.out.println("print nodes in nmap");
	for (Node m : nodeMap.values()) {
	    System.out.println("node val " + m.value);
	}
	s.weight = 0;
	q.add(s);
	while (!q.isEmpty()) {
	    Node o = q.remove();
	    System.out.println("current node val: " + o.value);
	    for (Node a : o.adj) {
		System.out.println("adjacent: " + a.value);
		relax(o, a, q);
	    }
	    System.out.println();
	}
    }

    public void printShortestPath(String dest) {
	Node cur = getNode(dest);
	System.out.println(cur.value);
	while(cur.prev != null) {
	    cur = cur.prev;
	    System.out.println(cur.value);
	}
    }

    public void printAdjListAllNodes() {
	for (Node n : nodeMap.values()) {
	    System.out.println(String.format("%s adj list inside printadj", n.value));
	    for (Node adj : n.adj) {
		System.out.println("adj " + adj.value);
	    }
	}
    }

    public static void main(String[] args) {
	Graph g = new Graph(true);
	g.insertEdge("A", "B", 2);

	g.insertEdge("E", "A", 3);
	g.insertEdge("E", "B", 1);
	g.insertEdge("E", "F", 3);

	g.insertEdge("B", "C", 8);
	g.insertEdge("B", "G", 1);

	g.insertEdge("F", "B", 6);
	g.insertEdge("F", "C", 1);

	g.insertEdge("C", "D", 6);
	g.insertEdge("C", "H", 5);

	g.insertEdge("G", "D", 2);

	g.printAdjListAllNodes();
	System.out.println();

	// Dijkstra
	g.dijkstra("E");
	g.printShortestPath("H");
    }
}
