import java.lang.*;
import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

/*
 * Implementation of a hash table
*/
public class HashTable {

    private String[] keys;
    private String[] values;
    private int keysSize;

    public HashTable() {
	keys = new String[1000];
	values = new String[1000];
	keysSize = 0;
    }

    private int getHashCodeInsert(String key) {
	int ascii = stringToAscii(key);
	int hc;
	int i = -1;
	do {
	    i++;
	    int m1 = i * i;
	    int m2 = (int) Math.pow(i, 2);
	    // int offset = ascii % values.length + m2;
	    // System.out.println("offset: " + offset);
	    // hc = offset % values.length;
	    hc = (ascii + m2) % values.length;
	    System.out.println("hc: " + hc);
	    // if (i == 50) {
	    //     break;
	    // }
	    if (keys[hc] != null) {
		System.out.println(keys[hc]);
	    }
	} while (keys[hc] != null);

	return hc;
    }

    private int getHashCodeLookup(String key) {
	int ascii = stringToAscii(key);
	int hc;
	int i = -1;
	do {
	    i++;
	    int offset = ascii % values.length + (int) Math.pow(i, 2);
	    hc = offset % values.length;
	} while (keys[hc] != key);

	return hc;
    }

    private int stringToAscii(String s) {
	StringBuilder sb = new StringBuilder();
	char[] letters = s.toCharArray();
	for (char ch : letters)
	    sb.append((byte) ch);

	return 0;
    }

    public void put(String key, String val) {
	int hc = getHashCodeInsert(key);
	keys[hc] = key;
	values[hc] = val;
    }

    public String get(String key) {
	if (keys.length == 0)
	    throw new IllegalArgumentException();
	int hc = getHashCodeLookup(key);

	return values[hc];
    }

    public static void main(String[] args) {
	HashTable hashTable = new HashTable();
	Random r = new Random();
	r.setSeed(34);
	File f = new File("/home/justin/git/" +
		"DataStructuresPy/datastructures/wordlist.txt");
	try {
	    Scanner sc = new Scanner(f);
	    int i = 1;
	    while (sc.hasNextLine()) {
		String line = sc.nextLine();
		System.out.println("word index: " + i);
		System.out.println("key: " + line);
		int num = r.nextInt(100);
		System.out.println("val: " + num);
		hashTable.put(line, Integer.toString(num));
		i++;
		System.out.println();
	    }
	} catch (FileNotFoundException e) {
	    System.out.println(e.getMessage());
	}
    }
}
