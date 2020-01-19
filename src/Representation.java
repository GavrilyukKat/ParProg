import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Representation {

    static void printToFile (double[][] array, String path){
        DecimalFormat df = new DecimalFormat("#.########", new DecimalFormatSymbols(Locale.US));
        StringBuilder writeToFile = new StringBuilder("");
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[i].length; j++)
            {
               writeToFile.append(df.format(Constants.stepX*j)).append(" ").append((df.format(Constants.stepT*i))).append(" ").append(df.format(array[i][j])).append(" ");
               writeToFile.append("\n");
            }
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
                bufferedWriter.write(writeToFile.toString());
                bufferedWriter.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /*static void printOnScreen (double[][] array, String name) {
        System.out.print(name + "={");
        for (int i = 0; i < Constants.numberOfStepsT; i++)
        {
            System.out.print("{");
            for (int j = 0; j < Constants.numberOfStepsX; j++)
            {
                //System.out.print(directAnswerMatrix[i][j] + "\t");
                if (j < Constants.numberOfStepsX - 1) {
                    System.out.print(array[i][j]);
                }
            }
            System.out.println();
            if (i < Constants.numberOfStepsT -1) {
                System.out.print("},");
            }
            else {
                System.out.print("}");
            }
        }
        System.out.print("};\n");
    }*/
}
