import java.util.function.BiFunction;
import java.util.Arrays;
import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

public class BinaryHeap {
    private int size;
    private int[] array;
    private BiFunction<Integer, Integer, Boolean> heapOrder;

    public BinaryHeap(BiFunction<Integer, Integer, Boolean> heapOrder) {
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

    private boolean hasLeft(int i) {
	return (2 * i + 1) <= (size - 1);
    }

    private boolean hasRight(int i) {
	return 2 * i + 2 <= (size - 1);
    }

    private int getParent(int i) {
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
	if (size > 1) {
	    do {
		int properChild = getProperChild(i);
		// System.out.println("properchild: " + properChild);
		int temp = array[i];
		array[i] = array[properChild];
		array[properChild] = temp;
		// System.out.println("63 i: " + i);
		i = getParent(i);
	    } while (i > 0);
	}
	// } while (hasParent(i) && parent(i) != 0);
    }

    private void heapDown() {
	if (size > 1) {
	    int i = 0;
	    while (hasLeft(i)) {
		int left = lChild(i);
		if (hasRight(i)) {
		    int right = rChild(i);
		    int properChild = heapOrder.apply(left, right) ? left : right;
		} else {
		    int properChild = left;
		}
		int temp = array[i];
		array[i] = array[left];
		array[left] = temp;
	    }
	}
    }
    
    private void resize() {
	array = Arrays.copyOf(array, array.length + 1);
    }

    public void insert(int val) {
	// System.out.println("inserting: " + val);
	if (size == 0) {
	    array = new int[1];
	    array[0] = val;
	} else {
	    // System.out.println("93: " + array.length);
	    if (size >= array.length - 1)
		resize();
	    System.out.println("96 arraylength: " + array.length);
	    System.out.println("96 size: " + size);
	    System.out.println("96 array[size - 1]: " + array[size - 1]);
	    array[size] = val;
	    System.out.println("96 array[size - 1]: " + array[size - 1]);
	    heapUp(size / 2);
	}
	System.out.println(Arrays.toString(array));
	System.out.println();
	size++;
    }

    public int remove() {
	if (size == 0)
	    throw new IllegalArgumentException();
	if (size == 1) {
	    int val = array[0];
	    array = new int[1];
	    size --;

	    return val;
	}

	int val = array[0];
	array[0] = array[size - 1];
	// array[size - 1] = null;
	size--;
	heapDown();

	return val;
    }

    public static void main(String [] args) {
	BiFunction<Integer, Integer, Boolean> heapOrder = (a, b) -> a < b;
	BinaryHeap heap = new BinaryHeap(heapOrder);
	heap.insert(7);
	heap.insert(3);
	heap.insert(9);
	heap.insert(2);
	heap.insert(8);
	heap.insert(3);
	heap.insert(2);

	System.out.println("remove: " + heap.remove());
    }
}
