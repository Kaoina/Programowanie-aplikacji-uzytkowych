package Lab3.Zigzag;
public class Result {
    public void show(){
        Zigzag zigzag = new Zigzag();
        // Przykład 1
        String word1 = "PAYPALISHIRING";
        int rows1 = 3;
        System.out.println("1: Before being Zigzaged = " + word1 + ", numRows = " + rows1);
        System.out.println("After: " + zigzag.zigzag_it(word1, rows1) +"\n");

        // Przykład 2
        String word2 = "PAYPALISHIRING";
        int rows2 = 4;
        System.out.println("2. Before being Zigzaged = " + word2 + ", numRows = " + rows2);
        System.out.println("Aftert: " + zigzag.zigzag_it(word2, rows2));
    }
}
