import java.util.LinkedList;
import java.util.Queue;
import java.lang.*;

public class CPUQueue {
    private Queue<Task> queue = new LinkedList<>();
    private int capacity;

    //private int coutnterQ1;
    //public int processToGenerate;

    public CPUQueue(int capacity) {//конструктор
        this.capacity = capacity;
        //this.processToGenerate = processToGenerate;
    }

    public synchronized Task get() throws InterruptedException {

        while (queue.isEmpty()) {
            System.out.println("Queue is EMPTY, waiting..");
            wait();
        }
            Task task = queue.remove();
            notify();

            return task;
        }

    public synchronized void put (Task task) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("Queue is FULL, waiting..");
            wait();
        }
            queue.add(task);
            notify();
        }

    public synchronized int getQueueSize(){

        return queue.size();
    }


}

