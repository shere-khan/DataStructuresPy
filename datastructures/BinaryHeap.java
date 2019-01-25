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

    private boolean hasParent(int i) {
	return i > 0 ? true : false;
    }

    private int getParent(int i) {
	if (i == 0)
	    throw new IllegalArgumentException();
	if (i == 1 || i == 2)
	    return 0;
	return (i - 1) / 2;
    }

    private void swap(int[] theArray, int i, int j) {
	int temp = theArray[i];
	theArray[i] = theArray[j];
	theArray[j] = temp;
    }

    private int getProperChild(int i) {
	System.out.println("getproperchild i: " + i);
	int left = lChild(i);
	System.out.println("left: " + left);
	if (hasRight(i)) {
	    int right = rChild(i);
	    System.out.println("right: " + right);
	    int properChild = heapOrder.apply(array[left], array[right])
		? left : right;
	    System.out.println("getproperchild properchild index: " + properChild);

	    return properChild;
	}
	return left;
    }

    private void heapify() {

    }

    private void heapUp(int i) {
	System.out.println("heapup start index: " + i);
	System.out.println("heapup size: " + size);
	System.out.println("heapup array length: " + array.length);
	if (2 * i > size)
	    throw new IndexOutOfBoundsException("Index out of bounds");
	if (size > 1) {
	    do {
		int properChild = getProperChild(i);
		// System.out.println("properchild index: " + properChild);
		System.out.println("before swap: " + Arrays.toString(array));
		if (heapOrder.apply(array[properChild], array[i]))
		    swap(array, i, properChild);
		System.out.println("after swap: " + Arrays.toString(array));
		if (!hasParent(i))
		    break;
		i = getParent(i);
		System.out.println("parent i: " + i);
	    } while (i >= 0);
	}
	// } while (hasParent(i) && parent(i) != 0);
    }

    private void heapDown() {
	if (size > 1) {
	    int i = 0;
	    while (hasLeft(i)) {
		int left = lChild(i);
		int properChild;
		if (hasRight(i)) {
		    int right = rChild(i);
		    System.out.println("right index: " + right);
		    properChild = heapOrder.apply(array[right], array[left])
			? left : right;
		} else {
		    properChild = left;
		}
		swap(array, i, left);
		i = properChild;
	    }
	}
    }
    
    private void resize() {
	array = Arrays.copyOf(array, array.length + 1);
    }

    private void downSize() {
	array = Arrays.copyOf(array, array.length - 1);
    }

    public void insert(int val) {
	System.out.println("inserting: " + val);
	if (size == 0) {
	    array = new int[1];
	    array[0] = val;
	    size++;
	} else {
	    if (size >= array.length - 1)
		resize();
	    array[size] = val;
	    size++;
	    System.out.println("insert size: " + size);
	    heapUp((size - 2) / 2);
	}
	System.out.println(Arrays.toString(array));
	System.out.println();
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
	System.out.println("remove size: " + size);
	array[0] = array[size - 1];
	System.out.println("remove array[0] = array[size - 1]: " + array[0]);
	downSize();
	System.out.println("array after remove: " + Arrays.toString(array));
	size--;
	// array[size - 1] = null;
	heapDown();
	System.out.println("array after heapDown: " + Arrays.toString(array));

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
