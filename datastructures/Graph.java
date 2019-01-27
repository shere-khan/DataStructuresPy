java.util.*;
java.lang.*;
java.util.function.*;

public Graph {
    private class Node {
	private Map<String, Node> children;
	private List<Integer> positions;
	private String value;

	public Node() {
	}

	public void setChildren(Map<String, Node> children) {
	    this.children = children;
	}

	public Map<String, Node> getChildren() {
	    return children;
	}
	
	public void setPositions(List<Integer> positions) {
	    this.positions = positions;
	}

	public List<Integer> getPositions() {
	    return positions;
	}

	public void setValue(String value) {
	    this.value = value;
	}

	public String getValue() {
	    return value;
	}
    }
}
