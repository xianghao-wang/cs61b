/** Deque implemented by Array
 * @author Xianghao Wang
 * */

public class ArrayDeque<T> {

    /** Invariants
     * 1. The first item is items[(nextFirst + 1) % items.length]
     * 2. The last item is items[(nextLast - 1 + items.length) % items.length]
     * */

    /* Underlying array */
    private T[] items;
    
    /* Caching size */
    private int size;

    /* Sentinels */
    private int nextFirst;
    private int nextLast;

    /* Empty deque */
    public ArrayDeque() {
	    items = (T[]) new Object[8];
	    size = 0;
	    nextFirst = 0;
	    nextLast = 1;
    }

    /** Get size
     * @return number of items in this deque
     * */
    public int size() {
        return size;
    }

    /** Check whether the deque is empty
     * @return true if there is no items in this deque; false otherwise
     * */
    public boolean isEmpty() {
	    return size == 0;
    }

    /** Returns the usage of this array
     * @return ratio of spaces being used
     * */
    private double usage() {
        return (double) size / items.length;
    }

    /** Add item to the front of this deque
     * @param x to be added
     * */
    public void addFirst(T x) {
        if (items.length == size) {
            expand();
        }

        items[nextFirst] = x;

        nextFirst -= 1;
        nextFirst = (nextFirst + items.length) % items.length;

        size += 1;
    }

    /** Add item to the back of this deque
     * @param x to be added
     * */
    public void addLast(T x) {
        if (items.length == size) {
            expand();
        }

        items[nextLast] = x;

        nextLast += 1;
        nextLast %= items.length;

        size += 1;
    }

    /** Get the item at the given index
     * @param index to locate the item
     * @return item; if not such an item, return null
     * */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(front() + index) % items.length];
    }

    /** Helps locate the front
     * */
    private int front() {
        return(nextFirst + 1) % items.length;
    }

    /** Helps locate the back
     * */
    private int back() {
        return (nextLast - 1 + items.length) % items.length;
    }

    /** Remove the item at the front of the deque
     * @return item removed
     * */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        T x = items[front()];

        /* Releases the memory */
        items[front()] = null;

        nextFirst += 1;
        nextFirst %= items.length;

        size -= 1;

        if (items.length >= 16 && usage() < 0.25) {
            shrink();
        }

        return x;
    }

    /** Remove the item at the back of the deque
     * @return item removed
     * */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        T x = items[back()];

        /* Release memory */
        items[back()] = null;

        nextLast -= 1;
        nextLast = (nextLast + items.length) % items.length;

        size -= 1;

        if (items.length >= 16 && usage() < 0.25) {
            shrink();
        }

        return x;
    }

    /** Prints the items in the deque from first to last appending with a new line
     * */
    public void printDeque() {
        int count = 0;
        int p = front();

        while (count < size) {
            System.out.print(items[p]);
            if (count != size - 1) {
                System.out.print(" ");
            } else {
                System.out.println("");
            }

            p ++;
            p %= items.length;
            count ++;
        }
    }

    /** Shrink the array when the usage is too low
     * */
    private void shrink() {
        T[] arr = (T[]) new Object[items.length / 2];
        int count = 0;
        int p = front();

        while (count < size) {
            arr[count] = items[p];

            p ++;
            p %= items.length;
            count ++;
        }

        items = arr;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    /** Expands the array when the array is full
     * */
    private void expand() {
        T[] arr = (T[]) new Object[items.length * 2];
        int count = 0;
        int p = front();

        while (count < size) {
            arr[count] = items[p];

            p ++;
            p %= items.length;
            count ++;
        }

        items = arr;
        nextFirst = items.length - 1;
        nextLast = size;
    }
}
