package Lab3.Sort;

public class BogoSort implements Sort {
    @Override
    public void sort(int[] tab) {  // jeśli tablica nie jest posortowana to mieszkamy
        while (!isSorted(tab))
            shuffle(tab);
    }

    void shuffle(int[] tab)
    {
        for (int i = 0; i < tab.length; i++)
            swap(tab, i, (int)(Math.random() * i)); //wymieszanie indeksów tablicy
    }

    void swap(int[] tab, int i, int j) // zamiana dwóch elementów
    {
        int temp = tab[i];
        tab[i] = tab[j];
        tab[j] = temp;
    }

    boolean isSorted(int[] tab) // sprawdza czy posortowana
    {
        for (int i = 1; i < tab.length; i++)
            if (tab[i] < tab[i - 1])
                return false;
        return true;
    }

}
