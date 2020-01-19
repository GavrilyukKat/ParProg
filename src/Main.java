import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {
    static int sum = 0;
    public static void main(String[] args) {
        int array[] = new int[50000000];
        Arrays.fill(array,2);

        //кількість елементів масиву
        AtomicInteger atomicCount = new AtomicInteger();
        IntStream.of(array).parallel().forEach(arrayElement -> {
            atomicCount.incrementAndGet();
            sum++;
        });

        System.out.println("Atomic Count result: " + atomicCount + " Non-concurrent sum result: " + sum);
        sum = 0;

        //мінімальний та максимальний елемент масиву типу лонг
        AtomicLong atomicLongMax = new AtomicLong(0);
        AtomicLong atomicLongMin = new AtomicLong(Long.MAX_VALUE);

        Random random = new Random();
        long randArray[] = random.longs(50000000).toArray();
        LongStream.of(randArray).parallel().forEach(arrayElement -> {
            atomicLongMax.updateAndGet(value ->
               arrayElement > value ? arrayElement : value
            );
            atomicLongMin.updateAndGet(value ->
                    arrayElement < value ? arrayElement : value
            );
        });

        long maxValue = atomicLongMax.get();
        long minValue = atomicLongMin.get();
        AtomicInteger indexMax = new AtomicInteger(-1);
        AtomicInteger indexMin = new AtomicInteger(-1);

        IntStream.range(0, randArray.length).parallel().forEach(indexElement -> {
            if (maxValue == randArray[indexElement]) indexMax.set(indexElement);
            if (minValue == randArray[indexElement]) indexMin.set(indexElement);
        });

        System.out.println("Max: " + atomicLongMax.get() + " index: " + indexMax.get());
        System.out.println("Min: " + atomicLongMin.get() + " index: " + indexMin.get());

        //verification
        long maxVerifValue = 0L;
        long minVerifValue = Long.MAX_VALUE;
        int maxVerifIndex = 0;
        int minVerifIndex = 0;
        for (int i = 0; i < randArray.length; i++) {
            if (maxVerifValue < randArray[i]) {
                maxVerifValue = randArray[i];
                maxVerifIndex = i;
            }
            if (minVerifValue > randArray[i]) {
                minVerifValue = randArray[i];
                minVerifIndex = i;
            }
        }

        System.out.println("Real Max: " + maxVerifValue + " index: " + maxVerifIndex);
        System.out.println("Real Min: " + minVerifValue + " index: " + minVerifIndex);

        AtomicInteger atomicSum = new AtomicInteger();
        Arrays.fill(array,2);
        int serialSum = IntStream.of(array).sum();
        IntStream.of(array).parallel().forEach(arrayElement -> {
            sum += arrayElement;
            int oldValue;
            int newValue;
            do {
                oldValue = atomicSum.get();
                newValue = oldValue + arrayElement;
            } while (!atomicSum.compareAndSet(oldValue, newValue));
        });
        System.out.println("Serial sum: " + serialSum);
        System.out.println("Blocking sum: " + sum);
        System.out.println("Atomic sum: " + atomicSum.get());
    }


}
