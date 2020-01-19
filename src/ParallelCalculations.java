import java.util.stream.IntStream;

public class ParallelCalculations {

    static void streamDirect(double[][] matrix) {

        long startTimeParDirect = System.currentTimeMillis();

        IntStream.range(0, Constants.numberOfStepsX).parallel().forEach(value -> { //value is from 0 to 10
                double currentValueX = value * Constants.stepX;
                for (int i = 0; i < Constants.numberOfStepsT; i++) {
                    double currentValueY = i * Constants.stepT;
                    matrix[i][value] = SequentialCalculations.calcDirectAnswers(currentValueX, currentValueY);
                }
        });
        long timeSpent = System.currentTimeMillis() - startTimeParDirect;
        System.out.println("Parallel calculation was " + timeSpent + " ms");
    }
}