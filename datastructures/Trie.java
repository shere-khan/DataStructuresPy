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

    public void find(String word) {
	Node o = root;
	List<String> letters = convertStringToListofCharacterStrings(word);
	System.out.println("letters: " + letters);
	while (!o.getValue().equals("*")) {
	    if (letters.size() > 1) {
		String s = letters.remove(0);
		System.out.println("letter to match value: " + s);
		// System.out.println("Node value: " + o.getValue());
		Node child = o.getChildren().get(s);
		System.out.println("keyset: " + o.getChildren().keySet());
		if (child != null) {
		    System.out.println("child value: " + child.getValue());
		    o = child;
		} else {
		    System.out.println("child null");
		    System.out.println();
		}
	    } else {
		String s = letters.remove(0);
		Node child = o.getChildren().get(s);
		if (child != null) {
		    System.out.println("child letter: " + child.getValue());
		    System.out.println("# times appear: "
			    + child.getPositions().size());
		    break;
		}
	    }
	}
    }

    private List<String> convertStringToListofCharacterStrings(String word) {
	List<String> wordList = new ArrayList<String>();
	for (char c : word.toCharArray())
	    wordList.add(String.valueOf(c));
	return wordList;
    }

    public void insert(String word) {
	List<String> letters = convertStringToListofCharacterStrings(word);
	Node o = root;
	while (!o.getValue().equals("*")) {
	    if (letters.size() > 1) {
		String s = letters.remove(0);
		System.out.println("letter: " + s);
		Node child = o.getChildren().get(s);
		if (child == null) {
		    Node newChild = new Node();
		    newChild.setChildren(new HashMap<String, Node>());
		    newChild.setPositions(new ArrayList<Integer>());
		    newChild.setValue(s);
		    o.getChildren().put(s, newChild);
		    o = newChild;
		} else {
		    o = child;
		}
	    } else {
		System.out.println("letters size: " + letters.size());
		String s = letters.remove(0);
		Node child = o.getChildren().get(s);
		if (child != null) {
		    System.out.println("child exists");
		    child.getPositions().add(0);
		} else {
		    System.out.println("child null");
		    Node grandKid = new Node();
		    grandKid.setChildren(new HashMap<String, Node>());
		    grandKid.setPositions(new ArrayList<Integer>());
		    grandKid.setValue("*");

		    Node newChild = new Node();
		    Map<String, Node> children = new HashMap<String, Node>();
		    children.put("*", grandKid);
		    newChild.setChildren(children);
		    newChild.setPositions(new ArrayList<Integer>());
		    newChild.getPositions().add(0);
		    newChild.setValue(s);

		    o.getChildren().put(s, newChild);
		}
		break;
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
	    Trie t = new Trie();
	    int i = 0;
	    for (String word : words) {
		System.out.println("inserting: " + word);
		if (word.equals("schizophrenia"))
		    i++;
		t.insert(word);
		System.out.println();
	    }
	    System.out.println("schiz count: " + i);
	    System.out.println();
	    t.find("schizophrenia");
	} catch (FileNotFoundException e) {
	    System.out.println(e.getMessage());
	}
    }
}
