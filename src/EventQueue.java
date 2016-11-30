import java.util.Comparator;
import java.util.PriorityQueue;

public class EventQueue
{
    private static PriorityQueue<ECB> queue;

    public EventQueue() {
        queue = new PriorityQueue<>(new Comparator<ECB>() {
            @Override
            public int compare(ECB first, ECB last) {
                Integer num1 = first.getPriority();
                Integer num2 = last.getPriority();
                return num1.compareTo(num2);
            }
        });
    }

    public void enQueue(ECB event) {
        queue.add(event);
    }

    public ECB peek() {
        return queue.peek();
    }

    public ECB deQueue() {
        return queue.poll();
    }

    public int getSize() {
        return queue.size();
    }

    public void printECB() {
        if(queue.isEmpty()) {
            System.out.println("Queue is Empty");
            return;
        }

        System.out.println("Current Head in Event Queue");
        String name = queue.peek().getName();
        String handler = queue.peek().getHandler();
        int priority = queue.peek().getPriority();
        int ioBurst = queue.peek().getIoBurst();

        System.out.println("Name: " + name +
                "\nHandler: " + handler +
                "\nPriority: " + priority +
                "\nIO Burst: " + ioBurst + "\n");
    }
}

