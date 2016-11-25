/**
 * Created by Michael on 11/25/2016.
 */
public class WaitQueue
{

    PCBNode current;
    PCBNode first;
    PCBNode last;
    int numProc;

    public void highPriority()
    {
        current = null;
        this.first = null;
        this.last = null;
        int numProc = 0;
    }

    public PCBNode enQueue(PCB pcb)
    {
        PCBNode newNode = new PCBNode(pcb);
        newNode.pcb = pcb;

        if (current == null)
        {
            first = newNode;
            last = newNode;
            current = newNode;
            current.next = null;
        }

        else
        {
            current.next = newNode;
            current = newNode;
            last = newNode;
        }

        return newNode;
    }

    public void printPCB(PCBNode node)
    {

        int memory = node.pcb.memory;
        int arrival = node.pcb.arrival;
        int timeElapsed = node.pcb.timeElapsed;
        int counter = node.pcb.counter;
        String state = node.pcb.state;
        int priority = node.pcb.priority;

        System.out.println("Memory: " + memory + "\n" + "Arrival: " + arrival + "\n" + "Time Elapsed: " + timeElapsed +
                "\n" + "Counter: " + counter + "\n" + "State: " + state + "\n" + "Priority: " + priority + "\n");

        if (node.next != null)
        {
            node = node.next;
            printPCB(node);
        }
    }

    public void deQueue(ExecutionQueue exec)
    {
        PCB pcb = new PCB();
        pcb = first.pcb;
        exec.enQueue(pcb);
        if (first.next != null)
            first.next.prev = null;
        first = first.next;

    }

}
