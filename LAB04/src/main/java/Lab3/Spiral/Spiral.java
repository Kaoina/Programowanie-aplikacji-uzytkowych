package Lab3.Spiral;

import java.util.List;

public class Spiral {
    public void printCounterclockwise(int[][] tab) {
        if (tab.length == 0 || tab[0].length == 0) {
            System.out.print("Empty matrix");
        }

        int top = 0, bottom = tab.length - 1;
        int left = 0, right = tab[0].length - 1;

        while (top <= bottom && left <= right) {

            for (int i = top; i <= bottom; i++) {
                System.out.print(tab[i][left]+ ", ");
            }
            left++;

            if (top <= bottom) {
                for (int i = left; i <= right; i++) {
                    System.out.print(tab[bottom][i]+ ", ");
                }
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    System.out.print(tab[i][right]+ ", ");
                }
                right--;
            }

            if(top <= bottom) {
                for (int i = right; i >= left; i--) {
                    System.out.print(tab[top][i]+ ", ");
                }
                top++;
            }
        }
    }
}