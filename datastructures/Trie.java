import java.util.*;
import java.util.function.*;
import java.lang.*;
import java.io.*;

public class Trie {

    // TODO: Make generic
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

    private Node root;

    public Trie() {
	Node o = new Node();
	o.setChildren(new HashMap<String, Node>());
	// o.setPositions(new ArrayList<Integer>());
	o.setValue("@");
	root = o;
    }

    public Trie(List<String> words) {
	root = new Node();
	for (String word : words)
	    insert(word);
    }

    private Node traverse(Node o) {

	return null;
    }

    private void insert(String str) {
	List<String> letters = new ArrayList<String>(Arrays.asList(str));
	Node o = root;
	while (!o.getValue().equals("*")) {
	    if (letters.size() > 1) {
		String s = letters.remove(0);
		Node child = o.getChildren().get(s);
		if (child == null) {
		    Node newChild = new Node();
		    newChild.setChildren(new HashMap<String, Node>());
		    newChild.setPositions(new ArrayList<Integer>());
		    newChild.setValue(s);
		    o = newChild;
		} else {
		    o = child;
		}
	    } else {
		String s = letters.remove(0);
		Node child = o.getChildren().get(s);
		if (child == null) {
		    child.getPositions().add(0);
		} else {
		    Node grandKid = new Node();
		    grandKid.setChildren(new HashMap<String, Node>());
		    grandKid.setPositions(new ArrayList<Integer>());
		    grandKid.setValue("*");

		    Node newChild = new Node();
		    Map<String, Node> children = new HashMap<String, Node>();
		    children.put("*", grandKid);
		    newChild.setChildren(children);
		    newChild.setPositions(new ArrayList<Integer>());
		    newChild.setValue(s);

		    o.getChildren().put(s, newChild);
		}
	    }
	}
    }

    public static void main(String[] args) {
	File f = new File("/home/justin/git/DataStructuresPy" +
		"/datastructures/trietext.txt");
	Scanner sc;
	try {
	    sc = new Scanner(f);
	    List<String> words = new ArrayList<String>();
	    while (sc.hasNext()) {
		String word = sc.next();
		words.add(word);
	    }
	    Trie t = new Trie(words);
	    int i = 0;
	    for (String word : words) {
		t.insert(word);
		if (i == 0)
		    break;
	    }
	} catch (FileNotFoundException e) {
	    System.out.println(e.getMessage());
	}
    }
}
