import java.util.LinkedList;
import java.util.Queue;
import java.lang.*;

public class CPUQueue {
    private Queue<Task> queue = new LinkedList<>();
    private int capacity;

    public CPUQueue(int capacity) {
        this.capacity = capacity;
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

