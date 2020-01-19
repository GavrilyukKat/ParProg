import java.lang.Math;

public class Task {

   // public long processingTime;
    private int randMin=20;
    private int randMax = 80;

    public long calculateCPUprocessingTime(){
    long cpuProcessingTime = 2*(randMin + (int) (Math.random() * randMax));
    return cpuProcessingTime;
}
}


/*
int randMix = 10;
        int randMax = 40;
        generateDelay = randMix + (int) (Math.random() * randMax);
 */