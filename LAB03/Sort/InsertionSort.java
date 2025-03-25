package Lab3.Sort;

public class InsertionSort implements Sort {
    //zaczyanmy od drugiego, porównujemy z poprzednim i tak co jeden
    @Override
    public void sort(int[] tab) {
        int n = tab.length;
        for (int i = 1; i < n; ++i) {
            int key = tab[i];
            int j = i - 1;

            while (j >= 0 && tab[j] > key) {
                tab[j + 1] = tab[j];
                j = j - 1;
            }
            tab[j + 1] = key;
        }
    }
}
