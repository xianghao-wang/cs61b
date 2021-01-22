import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void testEmptySize() {
        ArrayDeque<Integer> D = new ArrayDeque<>();

        assertEquals(0, D.size());
        assertEquals(true, D.isEmpty());
    }

    @Test
    public void testSize() {
        ArrayDeque<Integer> D = new ArrayDeque<>();
        D.addFirst(15);
        D.addLast(20);

        assertEquals(2, D.size());
    }

    @Test
    public void testAddFirstAndGet() {
        ArrayDeque<Integer> D = new ArrayDeque<>();
        D.addFirst(1);
        D.addFirst(2);
        D.addFirst(3);
        D.addFirst(4);
        D.addFirst(5);
        D.addFirst(6);
        D.addFirst(7);
        D.addFirst(8);

        assertEquals(Integer.valueOf(8), D.get(0));
        assertEquals(Integer.valueOf(7), D.get(1));
        assertEquals(Integer.valueOf(1), D.get(7));


        /* Invalid access */
        assertNull(D.get(-1));
        assertNull(D.get(8));
        assertNull(D.get(15));
    }

    @Test
    public void testAddLastAndGet() {
        ArrayDeque<Integer> D = new ArrayDeque<>();
        D.addLast(0);
        D.addLast(1);
        D.addLast(2);
        D.addLast(3);
        D.addLast(4);
        D.addLast(5);
        D.addLast(6);
        D.addLast(7);

        assertEquals(Integer.valueOf(0), D.get(0));
        assertEquals(Integer.valueOf(1), D.get(1));
        assertEquals(Integer.valueOf(2), D.get(2));
        assertEquals(Integer.valueOf(7), D.get(7));
    }

    @Test
    public void testRemoveFirst() {
        ArrayDeque<Integer> D = new ArrayDeque<>();
        D.addLast(0);
        D.addLast(1);
        D.addLast(2);

        D.removeFirst();
        assertEquals(Integer.valueOf(1), D.get(0));
        assertEquals(Integer.valueOf(2), D.get(1));

        assertEquals(Integer.valueOf(1), D.removeFirst());
        assertEquals(Integer.valueOf(2), D.get(0));
        assertEquals(Integer.valueOf(2), D.removeFirst());
        assertNull(D.removeFirst());

        D.removeFirst();

    }

    @Test
    public void testRemoveLast() {
        ArrayDeque<Integer> D = new ArrayDeque<>();
        D.addLast(0);
        D.addLast(1);
        D.addLast(2);

        D.removeLast();
        assertEquals(Integer.valueOf(1), D.get(1));
        assertNull(D.get(2));

        assertEquals(Integer.valueOf(1), D.removeLast());
        assertEquals(Integer.valueOf(0), D.removeLast());
        assertNull(D.removeLast());
    }

    @Test
    public void testIntegration() {
        ArrayDeque<Integer> D = new ArrayDeque<>();

        /* addFirst with addLast
        * 6 5 1 2 8
        *  */
        D.addFirst(1);
        D.addLast(2);
        D.addFirst(5);
        D.addFirst(6);
        D.addLast(8);
        D.printDeque();

        /* Some deletions
        * 1 2
        * */
        D.removeFirst();
        D.removeFirst();
        D.removeLast();
        D.printDeque();

        /* Removes all items */
        D.removeLast();
        D.removeLast();
        D.printDeque();
        assertEquals(0, D.size());

        D.addLast(0);
        D.addLast(1);
        D.addLast(2);

        D.removeLast();
        assertEquals(Integer.valueOf(1), D.get(1));
        assertNull(D.get(2));
    }

    @Test
    public void testDynamicDeque() {
        ArrayDeque<Integer> D = new ArrayDeque<>();

        for (int i = 0; i < 100; i ++) {
            D.addLast(i);
        }

        D.printDeque();
        assertEquals(100, D.size());
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests("all", ArrayDequeTest.class);
    }
}
