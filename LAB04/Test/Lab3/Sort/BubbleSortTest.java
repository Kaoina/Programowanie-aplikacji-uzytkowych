package Lab3.Sort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BubbleSortTest {
    BubbleSort bubbleSort;

    @BeforeEach
    public void setUp(){
        bubbleSort = new BubbleSort();
    }

    @Test
    public void testBubbleSortUnsortedArray() {
        int[] unsortedArray = {5, 3, 8, 1, 2};
        bubbleSort.sort(unsortedArray);
        int[] expectedSortedArray = {1, 2, 3, 5, 8};
        assertArrayEquals(expectedSortedArray, unsortedArray);
    }

    @Test
    public void testBubbleSortWithEmptyArray() {
        int[] emptyArray = {};
        bubbleSort.sort(emptyArray);
        int[] expectedSortedArray = {};
        assertArrayEquals(expectedSortedArray, emptyArray);
    }

    @Test
    public void testBubbleSortWithAlreadySortedArray() {
        int[] sortedArray = {1, 2, 3, 4, 5};
        bubbleSort.sort(sortedArray);
        int[] expectedSortedArray = {1, 2, 3, 4, 5};

        assertArrayEquals(expectedSortedArray, sortedArray);
    }

    @Test
    public void testBubbleSortWithDuplicateValues() {
        int[] arrayWithDuplicates = {4, 2, 1, 3, 2, 4};
        bubbleSort.sort(arrayWithDuplicates);
        int[] expectedSortedArray = {1, 2, 2, 3, 4, 4};

        assertArrayEquals(expectedSortedArray, arrayWithDuplicates);
    }

    @Test
    public void testSortNullArray() {
        try {
            bubbleSort.sort(null);
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
        bubbleSort.sort(largeArray);
        long endTime = System.nanoTime();
        long durationInMilliseconds = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        //System.out.println("BubbleSort: " + durationInMilliseconds);
        // Check if the sorting took less than 500 milliseconds
        assertTrue(durationInMilliseconds < 500);
    }
}
