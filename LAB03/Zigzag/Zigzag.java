package Lab3.Zigzag;

public class Zigzag {
    public String zigzag_it(String s, int numOfRows) {
        if (numOfRows == 1) return s;

        //zygzakowa tablica superstringow
        StringBuilder[] zigzag = new StringBuilder[numOfRows];
        for (int i = 0; i < numOfRows; i++) {
            zigzag[i] = new StringBuilder();
        }

        int index = 0;
        int direction = 1;

        //zygzakowanie
        for (char letter : s.toCharArray()) {
            zigzag[index].append(letter);
            index += direction;
            if (index == numOfRows - 1 || index == 0) {
                direction = -direction;
            }
        }

        //slowo koncowe
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : zigzag) {
            result.append(row);
        }

        return result.toString();
    }

}
