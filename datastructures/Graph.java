java.util.*;
java.lang.*;
java.util.function.*;

public Graph {
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


}
