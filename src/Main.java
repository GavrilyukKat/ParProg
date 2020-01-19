public class Main {
    public static void main (String[] args)
    {
        Matrix matrix = new Matrix();

        double[][] directAnswerMatrix = new double[Constants.numberOfStepsT][Constants.numberOfStepsX];
        double[][] approximateAnswersMatrix = new double[Constants.numberOfStepsT][Constants.numberOfStepsX];
        long startTime = System.currentTimeMillis();

        matrix.calcDirectMatrix (directAnswerMatrix);

        //calculate approximate matrix
        matrix.fillFirstRow (approximateAnswersMatrix);
        matrix.fillEdgeCondition (approximateAnswersMatrix, Constants.stepT);
        matrix.CalcApproxMatrix (approximateAnswersMatrix);

        long timeSpent = System.currentTimeMillis() - startTime;

        System.out.println("Sequential calculations was " + timeSpent + " ms");

        Representation.printToFile(directAnswerMatrix, "/Users/Katarina/Documents/Labs/directAnswersParCW.txt");
        Representation.printToFile(approximateAnswersMatrix, "/Users/Katarina/Documents/Labs/aproximateAnswersParCW.txt");

        double[][] directAnswersMatrixPar = new double[Constants.numberOfStepsT][Constants.numberOfStepsX];

        ParallelCalculations.streamDirect(directAnswersMatrixPar);

        Representation.printToFile(directAnswersMatrixPar, "/Users/Katarina/Documents/Labs/directAnswersParParCW.txt");

        //print on screen
        //String stringDir = "Direct calculation";
        //String stringApr = "Approximate calculation";
        //representation.printOnScreen (directAnswerMatrix,stringDir);
        //representation.printOnScreen (approximateAnswersMatrix, stringApr);

        //System.out.println("\n Sequential error: ");

        SequentialCalculations.CalcError (directAnswerMatrix, approximateAnswersMatrix);

    }
}

