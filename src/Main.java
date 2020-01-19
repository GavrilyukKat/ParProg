import javafx.concurrent.Task;
import java.sql.Array;
import java.util.Random;

public class Main {
    public static int threadCount = 5;//number of threads

    public static void main(String[] args) {
        double[] vectN = new double[1000];
        int vectLong = 1000;
        int startIndex;
        int endIndex;

        //create array for N for 1000 elements
        for (int i = 0; i < vectLong ; i++) {
            Random rand = new Random();
            vectN[i]= rand.nextDouble();
        }

        long timer = System.currentTimeMillis();
        //non-parallel timer
        ThreadPar threadNonPar = new ThreadPar(vectN, 0, vectLong - 1);
        threadNonPar.start();
        try {
            threadNonPar.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Non parallel took: " + (System.currentTimeMillis() - timer));

        //parallel timer
        timer = System.currentTimeMillis();
        ThreadPar threadArray[] = new ThreadPar[threadCount];
        int counter = 0;
        for (int i = -1; i<(vectLong-1); i+=vectLong/threadCount) {
            startIndex = i + 1;
            endIndex = i + vectLong / threadCount;
            ThreadPar threadPar = new ThreadPar(vectN, startIndex, endIndex);
            threadArray[counter] = threadPar;
            counter++;
            threadPar.start();
        }
        for (int i = 0; i <threadCount ; i++) {
            try {
                threadArray[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        double parallelRes = 0;
        for (int i = 0; i <threadCount ; i++) {
            parallelRes += threadArray[i].getNormaL();
        }
        System.out.println("Parallel took: " + (System.currentTimeMillis() - timer));
        System.out.println("Result is: " + parallelRes);
    }
}


