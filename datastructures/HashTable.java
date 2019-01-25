import java.lang.*;
import java.util.*;

public class HashTable {

    private int[][] table;

    public HashTable() {
	table = new int[1][1];
    }

    public HashTable(int m, int n) {
	table = new int[m][n];
    }

    private void resizeKeyArray() {
	table = Arrays.copyOf(table, table.length + 1);
    }

    private int[][] getTable() {
	return table;
    }

    private int hash(int key) {

	return 0;
    }

    public void put(int key, int val) {
	
    }

    public void get(int key) {
	
    }

    public static void main(String[] args) {
	HashTable hashTable = new HashTable(2, 2);
	int[][] table = hashTable.getTable();
	table[0][0] = 3;
	table[0][1] = 5;
	table[1][0] = 8;
	table[1][1] = 2;
	System.out.println(Arrays.deepToString(table));
    }
}
