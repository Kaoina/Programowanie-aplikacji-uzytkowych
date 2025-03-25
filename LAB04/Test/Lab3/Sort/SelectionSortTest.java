package Lab3.Sort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SelectionSortTest {
    SelectionSort selectionSort;

    @BeforeEach
    public void setUp(){
        selectionSort = new SelectionSort();
    }

        @Test
    void testEmptyArray() {
        int[] arr = {};
        selectionSort.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void testSingleElementArray() {
        int[] arr = {5};
        selectionSort.sort(arr);
        assertArrayEquals(new int[]{5}, arr);
    }

    @Test
    void testMultipleElements() {
        int[] arr = {64, 25, 12, 22, 11};
        selectionSort.sort(arr);
        assertArrayEquals(new int[]{11, 12, 22, 25, 64}, arr);
    }

    @Test
    void testDuplicateElements() {
        int[] arr = {5, 5, 3, 3, 1, 1};
        selectionSort.sort(arr);
        assertArrayEquals(new int[]{1, 1, 3, 3, 5, 5}, arr);
    }

    @Test
    void testAlreadySortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        selectionSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testReverseSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        selectionSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testSortNullArray() {
        try {
            selectionSort.sort(null);
            fail("Expected a NullPointerException to be thrown");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testPerformance() {

        int[] largeArray = new int[10000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = largeArray.length - i;
        }

        long startTime = System.nanoTime();
        selectionSort.sort(largeArray);
        long endTime = System.nanoTime();
        long durationInMilliseconds = (endTime - startTime) / 1_000_000; // Convert to milliseconds
       // System.out.println("SelectionSort: " + durationInMilliseconds);
        // Check if the sorting took less than 500 milliseconds
        assertTrue(durationInMilliseconds < 500);
    }
}
