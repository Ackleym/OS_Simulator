import static java.lang.Integer.parseInt;

/**
 * Created by Michael on 11/25/2016.
 */
public class Scheduler {

    public static ExecutionQueue exec = new ExecutionQueue();
    public static WaitQueue wait = new WaitQueue();
    public static ReadIn read;

    //Check to see if top priority event is load command

    //Insert PCB into the proper queue
    public static void insertPCB(PCB pcb)
    {
        read.openFile(pcb.name + "Proc");
        read.readFile(pcb.name + "Proc");
        read.closeFile();
        pcb.memory = parseInt(read.testArray.get(0));
        pcb.cpuNeeded = parseInt(read.testArray.get(1));
        read.testArray.remove(0);
        read.testArray.remove(0);
        pcb.instructions = read.testArray;
            if (pcb.memory > CacheMemory.memoryRemaining)
            {
                wait.enQueue(pcb);
            }

            else
            {
                pcb.setState("Ready");
                exec.enQueue(pcb);
                CacheMemory.memoryRemaining = CacheMemory.memoryRemaining - pcb.memory;
                ExecutionQueue.numProcesses++;
                pcb.arrival = CPU.clock;

            }
        }

    //Remove the PCB from queue
    public static void removePCB()
    {
        PCB pcb = exec.first.pcb;
        if (pcb.state == "Exit")
        {
            CacheMemory.memoryRemaining = CacheMemory.memoryRemaining + pcb.memory;
            exec.deQueue();
            pcb = null;
        }
    }

    public int getWait(PCB pcb)
    {
        return pcb.timeElapsed;
    }

    //Set wait(?) for a given process
    public void setWait(PCB pcb, int wait)
    {
        pcb.timeElapsed = wait;
    }

    public int getArrival(PCB pcb)
    {
        return pcb.arrival;
    }

    public void setArrival(PCB pcb, int arrival)
    {
        pcb.arrival = arrival;
    }

    public int getCPUTime()
    {
        return CPU.clock;
    }

    public void setCPUTime(int time)
    {
        CPU.clock = time;
    }
}
