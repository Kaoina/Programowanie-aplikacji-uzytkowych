package Lab3.Sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BogoSortTest {
    BogoSort bogoSort;

    @BeforeEach
    public void setUp(){
        bogoSort = new BogoSort();
    }

    @Test
    public void testSort() {
        int[] arr = {5, 3, 8, 2, 1};
        bogoSort.sort(arr);
        assertTrue(isSorted(arr));
    }

    @Test
    public void testShuffle() {
        int[] arr = {1, 2, 3, 4, 5};
        bogoSort.shuffle(arr);
        assertFalse(isSorted(arr));
    }

    @Test
    public void testSwap() {
        int[] arr = {1, 2, 3};
        bogoSort.swap(arr, 0, 2);
        assertArrayEquals(new int[]{3, 2, 1}, arr);
    }

    @Test
    public void testIsSorted() {
        int[] sortedArr = {1, 2, 3, 4, 5};
        int[] unsortedArr = {5, 3, 8, 2, 1};
        assertTrue(bogoSort.isSorted(sortedArr));
        assertFalse(bogoSort.isSorted(unsortedArr));
    }

    @Test
    public void testSortNullArray() {
        try {
            bogoSort.sort(null);
            fail("Expected a NullPointerException to be thrown");
        } catch (NullPointerException e) {
            assertEquals("Cannot read the array length because \"tab\" is null", e.getMessage());
        }
    }

    // Helper method to check if array is sorted
    private boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
