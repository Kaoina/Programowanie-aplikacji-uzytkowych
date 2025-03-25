package Lab3.Threes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThreesTest {
    Threes threes;

    @BeforeEach
    void setUp() {
        threes = new Threes();
    }

    @Test
    void testSumOfThreesEmptyArray() {

        int[] nums = {};
        List<List<Integer>> result = threes.sumOfThrees(nums);

        assertEquals(0, result.size());
    }

    @Test
    void testSumOfThreesNoTriplets() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = threes.sumOfThrees(nums);

        assertEquals(0, result.size());
    }

    @Test
    void testSumOfThreesOneTriplet() {
        int[] nums = {-1, 0, 1};
        List<List<Integer>> result = threes.sumOfThrees(nums);

        assertEquals(1, result.size());
        assertTrue(result.contains(List.of(-1, 0, 1)));
    }

    @Test
    void testSumOfThreesIncrementLeftPointer() {
        int[] nums = {-3, -2, 1, 2, 3};
        List<List<Integer>> result = threes.sumOfThrees(nums);

        assertEquals(1, result.size());
        assertTrue(result.contains(List.of(-3, 1, 2)));
    }

}