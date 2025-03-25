package Lab3.Spiral;

public class Result {
    int[][]tab = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}
    };

    void printTab(int[][] tab) {
        for (int[] ints : tab) {
            for (int anInt : ints) {
                if (anInt<10){
                    System.out.print(anInt + "  ");
                }
                else{
                    System.out.print(anInt + " ");
                }
            }
            System.out.println(); //
        }
    }

    public void show () {
        System.out.println("Before being spiraled: ");
        printTab(tab);
        System.out.println(" ");
        Spiral spiral = new Spiral();
        System.out.print("After: ");
        spiral.printCounterclockwise(tab);
        System.out.println(" ");
    }

}

