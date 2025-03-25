package Lab3.Median;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MedianTest {
    Median median;
    @BeforeEach
    public void setUp(){
        median = new Median();
    }

    @Test
    public void testMergeArrays_oddLength() {
        int[] num1 = {1, 3, 5};
        int[] num2 = {2, 4, 6, 8, 10};
        assertEquals(4.5, median.mergeArrays(num1, num2));
    }

    @Test
    public void testMergeArrays_evenLength() {
        int[] num1 = {1, 2, 3, 4};
        int[] num2 = {5, 6, 7, 8};
        assertEquals(4.5, median.mergeArrays(num1, num2));
    }

    @Test
    public void testMergeArrays_EmptyInputArrays() {
        int[] num1 = {};
        int[] num2 = {};
        assertEquals(-1, median.mergeArrays(num1, num2));
    }

    @Test
    public void testMergeArrays_oneEmptyArray() {
        int[] num1 = {1, 2, 3};
        int[] num2 = {};
        assertEquals(2.0, median.mergeArrays(num1, num2));
    }

    @Test
    public void testMergeArrays_singleElementArrays() {
        int[] num1 = {5};
        int[] num2 = {7};
        assertEquals(6.0, median.mergeArrays(num1, num2));
    }

    @Test
    public void testMergeArrays_negativeNumbers() {
        int[] num1 = {-5, -3, -1};
        int[] num2 = {-8, -6, -4, -2};
        assertEquals(-4.0, median.mergeArrays(num1, num2));
    }

    @Test
    public void testMergeArrays_duplicateNumbers() {
        int[] num1 = {1, 3, 3, 5};
        int[] num2 = {2, 4, 4, 6, 8};
        assertEquals(4.0, median.mergeArrays(num1, num2));
    }

    @Test
    public void testMergeArrays_EvenLength_SameNumbers() {
        int[] num1 = {1, 3, 5};
        int[] num2 = {1, 3, 5};
        assertEquals(3, median.mergeArrays(num1, num2));
    }

    @Test
    public void testMergeArrays_OddLength_SameNumbers() {
        int[] num1 = {1, 3, 5};
        int[] num2 = {1, 3, 5, 7};
        assertEquals(3, median.mergeArrays(num1, num2));
    }
}
