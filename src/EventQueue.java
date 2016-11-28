import java.util.PriorityQueue;

public class EventQueue
{
    public static PriorityQueue<ECB> queue = new PriorityQueue<>();

    public static void enQueue(ECB event) {
        queue.add(event);
    }

    public static ECB peek() {
        return queue.peek();
    }

    public static ECB deQueue() {
        return queue.poll();
    }
}

