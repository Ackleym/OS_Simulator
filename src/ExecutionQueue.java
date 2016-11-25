/**
 * Created by Michael on 11/25/2016.
 */
public class ExecutionQueue {


    PCBNode current;
    PCBNode first;
    PCBNode last;
    static int numProcesses;

    public void executionQueue()
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

    public void deQueue()
    {
        if (first.next != null)
            first.next.prev = null;
        first = first.next;

    }

    public void increment(PCBNode node)
    {
        if (node.next != null)
        {
            node = node.next;
            node.pcb.timeElapsed = node.pcb.timeElapsed + 1;
            increment(node);
        }
    }

    public void run(ExecutionQueue exec)
    {
        PCBNode node = new PCBNode(null);
        PCB pcb = new PCB();
        node = exec.first;
        pcb = node.pcb;
    }

    public void run(int time, ExecutionQueue exec)
    {
        PCBNode node = new PCBNode(null);
        PCB pcb = new PCB();
        node = exec.first;
        pcb = node.pcb;

        for (int i = 0; i < time; i++)
        {
            if ((i % 11) == 0 && exec.first.next != null)
            {
                exec.deQueue();
                exec.enQueue(pcb);
                pcb = exec.first.pcb;
            }
            else
            {
                pcb.timeElapsed = 0;
                pcb.counter = (pcb.counter + 1);
                increment(first);
            }
        }

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


    public static void main(String[] args)
    {

//    	BufferedReader br = null;
//        try {
//            br = new BufferedReader(new FileReader("test.txt"));
//            String line;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (br != null) {
//                    br.close();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }

        ExecutionQueue exec = new ExecutionQueue();
        PCB pcb = new PCB();
        PCB pcb2 = new PCB();
        PCB pcb3 = new PCB();
        exec.enQueue(pcb);
        exec.enQueue(pcb2);
        exec.enQueue(pcb3);

        exec.printPCB(exec.first);

    }
}