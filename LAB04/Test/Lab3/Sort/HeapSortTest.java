package Lab3.Sort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class HeapSortTest {
    HeapSort heapSort;
    @BeforeEach
    public void setUp(){
        heapSort = new HeapSort();
    }

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        heapSort.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    public void testSingleElementArray() {
        int[] arr = {5};
        heapSort.sort(arr);
        assertArrayEquals(new int[]{5}, arr);
    }

    @Test
    public void testAlreadySortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        heapSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testReverseSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        heapSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testRandomArray() {
        int[] arr = {3, 7, 1, 8, 5, 2, 4, 6};
        HeapSort heapSort = new HeapSort();
        heapSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, arr);
    }

    @Test
    public void testSortNullArray() {
        try {
            heapSort.sort(null);
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
        heapSort.sort(largeArray);
        long endTime = System.nanoTime();
        long durationInMilliseconds = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        //System.out.println("HeapSort: " + durationInMilliseconds);
        // Check if the sorting took less than 500 milliseconds
        assertTrue(durationInMilliseconds < 500);
    }
}
