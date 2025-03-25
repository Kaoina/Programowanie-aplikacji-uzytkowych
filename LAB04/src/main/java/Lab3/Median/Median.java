package Lab3.Median;
import java.util.Arrays;

public class Median {
    public double mergeArrays(int[] num1, int[] num2) {
        if (num1.length == 0 && num2.length == 0) {
            return -1;
        }

            int[] num3 = new int[num1.length + num2.length];
            System.arraycopy(num1, 0, num3, 0, num1.length);
            System.arraycopy(num2, 0, num3, num1.length, num2.length);
            Arrays.sort(num3);

            System.out.println(Arrays.toString(num3));
            double median;

            if (num3.length % 2 == 0) {
                double a = num3[num3.length / 2];
                double b = num3[num3.length / 2 - 1];
                median = (a + b) / 2;
            }
            else {
                median = num3[num3.length / 2];
            }
            return median;

    }
}
