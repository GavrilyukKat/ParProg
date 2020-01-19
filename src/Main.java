import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) {
        //создаем массивы класса лист
        final List<Integer> firstArray = new ArrayList<Integer>();
        final List<Integer> secondArray = new ArrayList<Integer>();
        fillListWithRandomValues(firstArray, 10);
        fillListWithRandomValues(secondArray, 10);
        System.out.println("First array: " + firstArray);
        System.out.println("Second array: " + secondArray);
        CompletableFuture<List<Integer>> firstCFuture, secondCFuture, resultFuture;
        firstCFuture = CompletableFuture.supplyAsync(() -> firstArray).thenApplyAsync(first -> {
            double sortValue = Collections.max(first) * 0.6;
            List<Integer> firstResult = new ArrayList<>();
            for (int i = 0; i < first.size(); i++) {
                if (first.get(i) > sortValue) firstResult.add(first.get(i));
            }
            Collections.sort(firstResult);
            System.out.println("First result: " + firstResult);
            return firstResult;
        });
        secondCFuture = CompletableFuture.supplyAsync(() -> secondArray).thenApplyAsync(second -> {
            List<Integer> secondResult = new ArrayList<>();
            for (int i = 0; i < second.size(); i++) {
                if (second.get(i) % 2  == 0) secondResult.add(second.get(i));
            }
            Collections.sort(secondResult);
            System.out.println("Second result: " + secondResult);
            return secondResult;
        });
        resultFuture = firstCFuture.thenCombine(secondCFuture, (firstResult, secondResult) -> {
            firstResult.retainAll(secondResult);

            return firstResult;
        });

        try {
            System.out.println("Result: " + resultFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //этот метод берет лист и заполняет его случайными числами от 0 до 10, всего вставляет newLength значений
    private static void fillListWithRandomValues(List<Integer> list, int newLength) {
        Random random = new Random();
        for (int i = 0; i < newLength; i++) {
            list.add( random.nextInt(11));
        }
    }
}
