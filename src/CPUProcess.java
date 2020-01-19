public class CPUProcess implements Runnable {
    CPUQueue queue1;
    CPUQueue queue2;
    int generateNumber;
    private int capacityQ1;
    public int percentQ1;


    CPUProcess(CPUQueue q1,CPUQueue q2, int gN){
        this.queue1 = q1;
        this.queue2 = q2;
        this.generateNumber = gN;//в мейне это количество процессов которые надо сгенерировать
    }


    public void run(){
        long generateDelay;
        Task task = new Task();
        for (int i = 0; i< generateNumber; i++){
            int randMix = 10;
            int randMax = 40;
            generateDelay = randMix + (int) (Math.random() * randMax);
            try {
                Thread.sleep(generateDelay);
            } catch (InterruptedException e){
                e.printStackTrace();

            } try {
                System.out.println(i + "   Process generated with delay " + generateDelay);

                if (queue1.getQueueSize() <= queue2.getQueueSize()) {
                    System.out.println("Q1 size is " + queue1.getQueueSize()+ " Q2 size is " + queue2.getQueueSize());
                    queue1.put(task);
                    capacityQ1++;
                }
                else {
                    queue2.put(task);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("No more processes.");
        percentQ1 =  (100 * capacityQ1) / generateNumber;
        System.out.println("\n Q1 percent is "+ percentQ1);
    }

}
