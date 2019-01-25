import java.lang.*;
import java.util.*;

/*
 * Implementation of a hash table that can only accept positive integers.
 * Negative integers suggest a null index.
 */
public class HashTable {

    private String[] keys;
    private String[] values;
    private int size;

    public HashTable() {
	keys = new String[20];
	values = new String[20];
    }

    private int[] resize(int[] array) {
	return Arrays.copyOf(array, array.length + 1);
    }

    private int[] downsize(int[] array) {
	return Arrays.copyOf(array, array.length - 1);
    }

    private int[][] getTable() {
	return table;
    }

    private int hash(int key) {
	int hc = key % size;
	do {
	} while (table[hc] != null);

	return 0;
    }

    public void put(int key, int val) {
	
    }

    public void get(int key) {
	
    }

    public static void main(String[] args) {
	HashTable hashTable = new HashTable(2, 2);
	// int[][] table = hashTable.getTable();
	// table[0][0] = 3;
	// table[0][1] = 5;
	// table[1][0] = 8;
	// table[1][1] = 2;
	// System.out.println(Arrays.deepToString(table));
    }
}
