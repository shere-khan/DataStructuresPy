import java.util.*;
import java.lang.*;
import java.io.*;

public class Trie {

    private class Node {
	private HashMap<String, Node> children;
	private ArrayList<Integer> postitions;
	private String value;
    }

    private Node root;

    public Trie() {
	root = new Node();
    }

    public Trie(List<String> words) {
	root = new Node();
	// TODO: for word : words - insert into trie
    }

    public static void main(String[] args) {
	System.out.println("hello");
    }
}
