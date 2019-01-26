import java.lang.*;
import java.util.*;
import java.util.stream.IntStream;

/*
 * Implementation of a hash table
*/
public class HashTable {

    private String[] keys;
    private String[] values;
    private int keysSize;

    public HashTable() {
	keys = new String[20];
	values = new String[100];
	keysSize = 0;
    }

    private int getHashCode(String key) {
	int ascii = stringToAscii(key);
	int hc;
	int i = -1;
	do {
	    i++;
	    int offset = ascii % values.length + (int) Math.pow(i, 2);
	    hc = offset % values.length;
	} while (keys[hc] != null);

	return hc;
    }

    private int hashFind(String key) {
	int ascii = stringToAscii(key);
	int hc;
	int i = -1;
	do {
	    i++;
	    int offset = ascii % values.length + (int) Math.pow(i, 2);
	    hc = offset % values.length;
	} while (keys[hc] != null);

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
	int hc = getHashCode(key);
	keys[hc] = key;
	values[hc] = val;
    }

    public String get(String key) {
	if (Arrays.stream(keys).anyMatch(key::equals)) {
	    // return values[hashFind(
	}
	return null;
    }

    public static void main(String[] args) {
	HashTable hashTable = new HashTable();
	Random r = new Random();
	r.setSeed(34);
	int[] array = new int[100];
	for (int i = 0; i < array.length; i++)
	    array[i] = r.nextInt(10);
	// System.out.println(Arrays.toString(array));
	for (int i = 0; i < array.length; i++) {
	    if (IntStream.of(array).anyMatch(x -> x == 3))
		break;
	}
	System.out.println("contains");
    }
}
