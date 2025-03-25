package Lab3.Sort;
import java.util.Random;
import java.util.concurrent.*;
public class Result {
    Random random = new Random();
    int size = 10;
    int[] tab = new int[size];
    int[] sortedTab = new int[size];
    int[] reverseSortedTab = new int[size];
    public Result(){
        for(int i = 0, j = size; i < size; i++, j--){
            tab[i] = random.nextInt();
            sortedTab[i] = i;
            reverseSortedTab[i] = j;
        }
    }

    public void show(){
        Sort bubble = new BubbleSort();
        Sort heap = new HeapSort();
        Sort insertion = new InsertionSort();
        Sort selection = new SelectionSort();
        Sort bogo = new BogoSort();
        System.out.println("Size of the Table: " + size + "\n");
        sortingTime(bubble);
        sortingTime(heap);
        sortingTime(insertion);
        sortingTime(selection);
        sortingTime(bogo);
    }

    public void sortingTime(Sort sort){
        ExecutorService executor = Executors.newSingleThreadExecutor(); //interface do zarządzania wątkami(tu jednym)
        Future<?> future = executor.submit(() -> { //interface asynchronizacji operacji z typem generycznym
        //zapewnia metody do sprawdzania stanu operacji
        long tStart = System.currentTimeMillis(); //aktualny czas w milisekundach
        sort.sort(tab.clone());                   //klonujemy odpowiednia tablice
        long tEnd = System.currentTimeMillis();   //czas zakończenia
        long tDelta = tEnd - tStart;              //różnica czasu
        double elapsedSeconds = tDelta / 1000.0;  //z milisekud na sekundy
        System.out.println(sort.getClass().getSimpleName() + ": random array: " + elapsedSeconds);

        tStart = System.currentTimeMillis();
        sort.sort(sortedTab.clone());
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println(sort.getClass().getSimpleName() + ": sorted array: " + elapsedSeconds);

        tStart = System.currentTimeMillis();
        sort.sort(reverseSortedTab.clone());
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println(sort.getClass().getSimpleName() + ": reverse sorted array: " + elapsedSeconds + "\n");
    });

    try {
        future.get(3, TimeUnit.SECONDS); // Przerwanie sortowania po 3 sekundach
    } catch (InterruptedException | ExecutionException | TimeoutException e) {
        System.out.println("Function runs too long. Interruption.");
        future.cancel(true); // Przerwanie sortowania
    } finally {
        executor.shutdownNow(); // Zakończenie executora
    }

    }
}
