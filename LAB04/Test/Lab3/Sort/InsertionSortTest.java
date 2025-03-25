package Lab3.Sort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InsertionSortTest {
    InsertionSort sorter;

    @BeforeEach
    public void setUp(){
        sorter = new InsertionSort();
    }

    @Test
    public void testSortEmptyArray() {
        int[] array = {};
        int[] expected = {};
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testSortSingleElementArray() {
        int[] array = {5};
        int[] expected = {5};
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testSortAlreadySortedArray() {
        int[] array = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testSortReverseSortedArray() {
        int[] array = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testSortRandomArray() {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5};
        int[] expected = {1, 1, 2, 3, 4, 5, 5, 6, 9};
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testSortNullArray() {
        try {
            sorter.sort(null);
            fail("Expected a NullPointerException to be thrown");
        } catch (NullPointerException e) {
            assertEquals("Cannot read the array length because \"tab\" is null", e.getMessage());
        }
    }

    @Test
    public void testPerformance() {

        int[] largeArray = new int[10000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = largeArray.length - i;
        }

        long startTime = System.nanoTime();
        sorter.sort(largeArray);
        long endTime = System.nanoTime();
        long durationInMilliseconds = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        //System.out.println("InsertionSort: " + durationInMilliseconds);
        // Check if the sorting took less than 500 milliseconds
        assertTrue(durationInMilliseconds < 500);
    }
}
