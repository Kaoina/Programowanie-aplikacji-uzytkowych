package Lab3.Sort;

public class BubbleSort implements Sort { //porównywanie z nastepnym i zamienianie
    @Override
    public void sort(int[] tab) {
        boolean swapped;
        int n = tab.length;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (tab[j] > tab[j + 1]) {

                    // Swap arr[j] and arr[j+1]
                    int temp = tab[j];
                    tab[j] = tab[j + 1];
                    tab[j + 1] = temp;
                    swapped = true;
                }
            }
            //Jeśli nie zamienia już elementów to koniec
            if (!swapped)
                break;
        }
    }
}
