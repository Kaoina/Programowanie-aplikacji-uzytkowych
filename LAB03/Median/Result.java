package Lab3.Median;

import java.util.Arrays;

public class Result {
    int[] num1 = {1, 2, 3, 4, 5 , 5};
    int[] num2 = {6, 7, 8, 9, 10, 11};

    @Override
    public String toString() {
        return "Arrays: " +
                "num1=" + Arrays.toString(num1) +
                ", num2=" + Arrays.toString(num2);
    }

    public void show () {
        System.out.println("\nBefore: " + this);
        Median median = new Median();
        System.out.print("After: ");
        double result = median.mergeArrays(num1, num2);
        System.out.println("Median: " + result);
    }
}
