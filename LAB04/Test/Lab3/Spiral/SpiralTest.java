package Lab3.Spiral;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class SpiralTest {
    ByteArrayOutputStream outputStream;
    Spiral spiral;

    @BeforeEach
    public void setUp(){
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        spiral = new Spiral();
    }

    @Test
    public void testPrintCounterclockwiseWith2x2Matrix() {
        int[][] matrix = {
                {1, 2},
                {3, 4}};
        spiral.printCounterclockwise(matrix);
        assertEquals("1, 3, 4, 2, ", outputStream.toString());
    }

    @Test
    public void testPrintCounterclockwiseWith3x3Matrix() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        spiral.printCounterclockwise(matrix);
        assertEquals("1, 4, 7, 8, 9, 6, 3, 2, 5, ", outputStream.toString());
    }

    @Test
    public void testPrintCounterclockwiseWithEmptyMatrix() {
        int[][] matrix = {{},{}};
        spiral.printCounterclockwise(matrix);
        assertEquals("Empty matrix", outputStream.toString());
    }
}