package Lab3.Threes;
import java.util.Arrays;
import java.util.List;

public class Result {
    int[] nums1 = {-1, 0, 1, 2, -1, -4};
    int[] nums2 = {0, 1, 1};
    int[] nums3 = {0, 0, 0};

    public void printThrees (int[] tab, int i) {
        System.out.print("\n" + i + ". Table: ");
        System.out.print(Arrays.toString(tab));
        Threes threes = new Threes();
        List<List<Integer>> result1 = threes.sumOfThrees(tab);
        System.out.println("\nFound threes: " + result1);

    }
    public void show(){
        printThrees(nums1, 1);
        printThrees(nums2, 2);
        printThrees(nums3, 3);
    }
}
