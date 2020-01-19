
public class Matrix {

    public static SequentialCalculations calculation = new SequentialCalculations();

    static void calcDirectMatrix (double[][] matrix) {
        double currentValueY = 0.0;
        for (int i = 0; i < Constants.numberOfStepsT; i++)
        {
            double currentValueX = 0.0;

            for (int j = 0; j < Constants.numberOfStepsX; j++)
            {
                matrix[i][j] = calculation.calcDirectAnswers(currentValueX, currentValueY);
                currentValueX += Constants.stepX;
            }
            currentValueY += Constants.stepT;
        }
    }

    static void fillFirstRow (double[][] matrix) {
        double currentValue_X = 0.0;
        for (int i = 0; i < Constants.numberOfStepsX; i++)
        {
            matrix[0][i] = calcFirstRow(currentValue_X);
            currentValue_X += Constants.stepX;
        }
    }

    static double calcFirstRow(double x) {
        double a = (- Math.sqrt(-Constants.b / Constants.a) +
                Constants.C * Math.exp(Math.sqrt((Constants.a + 1) * x / 6)));

        return Math.pow(a,Constants.pow);
    }

    static void fillEdgeCondition (double[][] matrix, double stepT) {
        double currentValueT = 0.0;
        for (int i = 0; i < Constants.numberOfStepsT; i++) {
            matrix[i][0] = LeftEdgeCondition(currentValueT);
            matrix[i][Constants.numberOfStepsX - 1] = RightEdgeCondition(currentValueT);
            currentValueT += stepT;
        }
    }

    static double LeftEdgeCondition (double tau) {
        double a = (- Math.sqrt(-Constants.b / Constants.a) +
                Constants.C * Math.exp( (-5 * Constants.a * tau/ 6)));

        return Math.pow(a,Constants.pow);
    }

    static double RightEdgeCondition (double tau) {
        double a = (- Math.sqrt(-Constants.b / Constants.a) +
                Constants.C * Math.exp( (-5 * Constants.a * tau/ 6) +
                        Math.sqrt((Constants.a + 1)/ 6)));

        return Math.pow(a,Constants.pow);
    }

    static void CalcApproxMatrix (double[][] matrix) {
        double currentValueT =0.00001;
        for (int i = 1; i < Constants.numberOfStepsT; i++)
        {
            double currentValueX = 0.1;
            for (int j = 1; j < Constants.numberOfStepsX - 1; j++)
            {
                matrix[i][j] = calculation.calcApproxAnswers(currentValueT,currentValueX, matrix[i-1][j],
                        matrix[i-1][j-1], matrix[i-1][j+1]);
                currentValueX += Constants.stepX;
            }
            currentValueT += Constants.stepT;
        }
    }
}
