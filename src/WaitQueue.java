/**
 * Created by Michael on 11/25/2016.
 */

import java.util.ArrayList;

public class WaitQueue
{
    ArrayList<PCB> queue;

    public WaitQueue() {
        queue = new ArrayList<>();
    }

    public void enQueue(PCB pcb) {
        queue.add(pcb);
    }

    public void printPCB() {
        if(queue.isEmpty()) {
            System.out.println("Wait Queue is Empty");
            return;
        }

        System.out.println("Current Wait Queue Contents");
        for (int i=0; i<queue.size(); i++) {
            String name = queue.get(i).getName();
            int memory = queue.get(i).getMemory();
            int arrival = queue.get(i).getArrival();
            int timeElapsed = queue.get(i).getTimeElapsed();
            int counter = queue.get(i).getCounter();
            String state = queue.get(i).getState();
            int priority = queue.get(i).getPriority();
            int cpuNeeded = queue.get(i).getCpuBurst();

            System.out.println("Name: " + name +
                    "\nMemory: " + memory +
                    "\nArrival: " + arrival +
                    "\nTime Elapsed: " + timeElapsed +
                    "\nCounter: " + counter +
                    "\nState: " + state +
                    "\nPriority: " + priority +
                    "\nCPU Burst: " + cpuNeeded + "\n");
        }
    }

    public PCB deQueue() {
        return queue.remove(0);
    }

    public PCB getFirst() {
        return queue.get(0);
    }

    public PCB get(int pos) {
        return queue.get(pos);
    }

    public int getSize() {
        return queue.size();
    }
}
