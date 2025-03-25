package Lab3.Sort;

public class SelectionSort implements Sort {
    //znajduje najmniejszy, zamienia z pierwszym itd
    @Override
    public void sort(int[] tab) {
        int n = tab.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (tab[j] < tab[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = tab[min_idx];
            tab[min_idx] = tab[i];
            tab[i] = temp;
        }
    }
}
