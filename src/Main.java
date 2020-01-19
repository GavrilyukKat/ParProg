public class Main {

    public static void main(String[] args) {
        int queueCapacity = 6;
        int processToGenerate = 15;
        System.out.println("\nQueue capacity = [" +queueCapacity + "], will be generated " + processToGenerate + "processes\n");
        CPUQueue q1 = new CPUQueue(queueCapacity);
        CPUQueue q2 = new CPUQueue(queueCapacity);
        CPUProcess Cp = new CPUProcess(q1,q2,processToGenerate);
        CPU C = new CPU(q1,q2);
        new Thread(Cp).start();
        new Thread(C).start();
    }
}
