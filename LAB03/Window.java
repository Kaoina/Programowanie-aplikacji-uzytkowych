package Lab3;
import java.util.Scanner;

public class Window {
    Scanner scanner = new Scanner(System.in);
    public void show(){
        boolean inf = true;
        while(inf) {
            System.out.println("\nChoose action: ");
            System.out.println("1 - Compare algorithms");
            System.out.println("2 - Zigzag a few words");
            System.out.println("3 - Median of two arrays");
            System.out.println("4 - Find Threes");
            System.out.println("5 - Spiral matrix");
            System.out.println("6 - Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Lab3.Sort.Result result_1 = new Lab3.Sort.Result();
                    result_1.show();
                    break;
                case 2:
                    Lab3.Zigzag.Result result_2 = new Lab3.Zigzag.Result();
                    result_2.show();
                    break;
                case 3:
                    Lab3.Median.Result result_3 = new Lab3.Median.Result();
                    result_3.show();
                    break;
                case 4:
                    Lab3.Threes.Result result_4 = new Lab3.Threes.Result();
                    result_4.show();
                    break;
                case 5:
                    Lab3.Spiral.Result result_5 = new Lab3.Spiral.Result();
                    result_5.show();
                    break;
                case 6:
                    inf = false;
                    break;
                default:
                    System.out.println("\nBłędna liczba");
                    break;
            }
        }
        scanner.close();
    }
}
