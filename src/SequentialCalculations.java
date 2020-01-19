
public class SequentialCalculations {

    public static double calcDirectAnswers(double x, double tau) {
        double a = (- Math.sqrt(-Constants.b / Constants.a) +
                Constants.C * Math.exp( (-5 * Constants.a * tau/ 6) +
                        Math.sqrt((Constants.a + 1) * x / 6)));

        return Math.pow(a,Constants.pow);
    }

    static double calcApproxAnswers (double tau,double x, double wCentre,
                                    double wLeft, double wRight) {
        return wCentre + tau*((wLeft - 2 * wCentre + wRight + Constants.a * wCentre * Math.pow(x,2) + Constants.b * Math.pow(wCentre,2) * Math.pow(x,2)/Math.pow(x,2)));
    }


    static void CalcError (double[][] matrixDir, double[][] matrixApprox) {
        double compError = 0;

        for (int i = 0; i < Constants.numberOfStepsT; i++)
        {
            for (int j = 0; j < Constants.numberOfStepsX; j++)
            {
                double error = Math.abs(matrixDir[i][j]-matrixApprox[i][j])/matrixDir[i][j];
                if (error > compError) {
                    compError = error;
                }
            }
        }
        System.out.println("Error is " + compError*100);
    }
}
