package Lab3.Sort;

public class HeapSort implements Sort {
    //tworzymy kopiec (najwiekszy element na górze), dodajemy do listy i znowu kopcuejmy
    void heapify(int[] tab, int N, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < N && tab[l] > tab[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < N && tab[r] > tab[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = tab[i];
            tab[i] = tab[largest];
            tab[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(tab, N, largest);
        }
    }

    @Override
    public void sort(int[] tab) {
        int N = tab.length;

        // Zbudowanie sterty (uporządkuj tablicę)
        for (int i = N / 2 - 1; i >= 0; i--)
            heapify(tab, N, i);

        // pokolei ściągamy ze sterty
        for (int i = N - 1; i > 0; i--) {
            int temp = tab[0];
            tab[0] = tab[i];
            tab[i] = temp;

            // wywołanie maxheapify dla zredukowanej sterty
            heapify(tab, i, 0);
        }
    }
}
