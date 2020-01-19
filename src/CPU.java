public class CPU implements Runnable {
    CPUQueue queue1;
    CPUQueue queue2;


    CPU(CPUQueue q1, CPUQueue q2) {
        this.queue1 = q1;
        this.queue2 = q2;

    }

    public void run() {
        Task task;

        while (true) {

            try {
                if (queue1.getQueueSize() >= queue2.getQueueSize()) {
                    task = queue1.get();

                }
                else {
                    task = queue2.get();
                }

            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
            System.out.println("CPU: Processed in time +" + task.calculateCPUprocessingTime() + "\n");
            //try {
                Thread.sleep(task.calculateCPUprocessingTime());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
