public class ThreadPar extends Thread {
    double vectN[];
    double normaL;
    int startIndex;
    int endIndex;
//constructor
    public ThreadPar(double[] vectN, int startIndex, int endIndex){
        this.vectN = vectN;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public double getNormaL() {
        return normaL;
    }
    @Override
    public void run(){
        for (int i = startIndex; i <= endIndex ; i++) {
            normaL = normaL + Math.abs(vectN[i]);
        }

    }
}
