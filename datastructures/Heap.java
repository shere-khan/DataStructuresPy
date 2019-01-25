import java.util.function.BiFunction;
import java.util.Arrays;
import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

public class Heap {
    private int size;
    private int[] array;
    private BiFunction<Integer, Integer, Boolean> heapOrder;

    public Heap(BiFunction<Integer, Integer, Boolean> heapOrder) {
	size = 0;
	array = new int [0];
	this.heapOrder = heapOrder;
    }
    
    private int lChild(int i) {
	return 2 * i + 1;
    }

    private int rChild(int i) {
	return 2 * i + 2;
    }

    private boolean hasLeft(i) {
	return 2 * i + 1 <= (size - 1)
    }

    private boolean hasRight(i) {
	return 2 * i + 2 <= (size - 1)
    }

    private int parent(int i) {
	if (i == 0)
	    throw new IllegalArgumentException();
	if (i == 1 || i == 2)
	    return 0;
	return (i - 1) / 2;
    }

    private int getProperChild(int i) {
	int left = lChild(i);
	int right = rChild(i);
	int properChild = heapOrder.apply(left, right) ? left : right;

	return properChild;
    }

    private void heapify() {

    }

    private void heapUp(int i) {
	if (2 * i > size)
	    throw new IndexOutOfBoundsException("Index out of bounds");
	do {
	    int properChild = getProperChild(i);
	    int temp = array[i];
	    array[i] = array[properChild];
	    array[properChild] = temp;
	} while (parent(i) != 0);
    }

    private void heapDown() {
	if (size > 1) {
	    i = 0;
	    while (hasLeft(i)) {
		int left = lChild(i);
		if (hasRight(i)) {
		    int right = rChild(i);
		    properChild = heapOrder.apply(left, right) ? left : right;
		} else
		    properChild = left;
		int temp = array[i];
		array[i] = array[left];
		array[left] = temp;
	    }
	}
    }
    
    private int[] resize() {
	return Arrays.copyOf(array, array.length * 2);
    }

    public void insert(int val) {
	if (size == 0) {
	    array = new int[1];
	    array[0] = val;
	} else {
	    if (size == array.length - 1)
		resize();
	    System.out.println(array.length);
	    array[size] = val;
	    size++;
	    heapUp(size / 2);
	}
    }

    public int remove() {
	if (size == 0)
	    throw new IllegalArgumentException();
	if (size == 1) {
	    int val = array[0];
	    array[0] = null;
	    size --;

	    return val;
	}

	int val = array[0];
	array[0] = array[size - 1];
	array[size - 1] = null;
	size--;
	heapDown();

	return val;
    }

    public static void main(String [] args) {
	BiFunction<Integer, Integer, Boolean> heapOrder = (a, b) -> a < b;
	Heap heap = new Heap(heapOrder);
	heap.insert(7);
	heap.insert(3);
	heap.insert(9);
	heap.insert(2);
	heap.insert(8);
	heap.insert(3);
	heap.insert(2);
    }
}
