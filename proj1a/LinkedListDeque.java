/* Deque implemented by linked list */
public class LinkedListDeque<T> {

    /* Node in doubly linked list */
    private class DoubleNode {
        public DoubleNode prev;
        public T item;
        public DoubleNode next;

        public DoubleNode(DoubleNode prev, T item, DoubleNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

        public DoubleNode(DoubleNode sentinel, int i, DoubleNode sentinel1) {
        }
    }

    /** Sentinel node
     * The first node of this list is always sentinel.next
     * The last node of this list is always sentinel.prev
     * When list is empty, both of sentinel.next and sentinel.prev are sentinel
     * */
    private DoubleNode sentinel;

    /* Caching size */
    private int size;

    /*  Creates an empty linked list deque */
    public LinkedListDeque() {
        sentinel = new DoubleNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Size of this deque
     * @return the number of items
     * */
    public int size() {
        return size;
    }

    /** Checks whether the deque is empty
     * @return true if deque is empty, false otherwise
     * */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Add x to the front of this deque
     * @param x item to be added
     */
    public void addFirst(T x) {
        size += 1;

        sentinel.next = new DoubleNode(sentinel, x, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }

    /** Add x to the back of this deque
     * @param x item to be added
     */
    public void addLast(T x) {
        size += 1;

        sentinel.prev = new DoubleNode(sentinel.prev, x, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }

    /** Print all items in this deque
     * */
    public void printDeque() {
        DoubleNode node = sentinel.next;

        for (int i = 0; i < size; i ++) {
            if (i != 0) {
                System.out.print(' ');
            }
            System.out.print(node.item.toString());

            node = node.next;
        }
    }

    /** Removes and returns the item at the front of deque
     * @return item to be removed
     * */
    public T removeFirst() {
        size -= 1;

        // Get item to be removed
        T item = sentinel.next.item;

        // Remove the node
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;

        return item;
    }

    /** Removes and returns the item at the back of deque
     * @return item to be removed
     * */
    public T removeLast() {
        size -= 1;

        // Get item to be removed
        T item = sentinel.prev.item;

        // Remove the node
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;

        return item;
    }

    /** Gets the item at the given index,
     * @param index index of item to be fetched
     * @return item to be fetched. If not found, null.
     * */
    public T get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }

        DoubleNode node = sentinel.next;
        while (index > 0) {
            node = node.next;
            index -= 1;
        }

        return node.item;
    }

    /** Gets the item at the given index by recursion
     * @param index index-th item in the list starting from node
     * @return item to be fetched. If not found, null.
     *  */
    public T getRecursive(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }

        return getRecursive(index, sentinel.next);
    }

    /** Helper function for getRecursive(int index)
     * @param index position of item in the list starting from node
     * @param node start of the list
     * */
    private T getRecursive(int index, DoubleNode node) {
        // Base case
        if (index == 0) {
            return node.item;
        }

        return getRecursive(index - 1, node.next);
    }


    public static void main(String[] args) {
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
        list.addLast(10);
        list.addLast(19);
        list.printDeque();
    }
}