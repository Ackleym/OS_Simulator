/**
 * Created by Michael on 11/25/2016.
 */
public class Scheduler {

    public static ExecutionQueue exec = new ExecutionQueue();
    public static WaitQueue wait = new WaitQueue();

    //Insert PCB into the proper queue
    public static void insertPCB(PCB pcb)
    {
        if (pcb.state == "New");
        {
            if (pcb.memory > CacheMemory.memoryRemaining)
            {
                wait.enQueue(pcb);
            }

            else
            {
                pcb.setState("Ready");
                exec.enQueue(pcb);
                CacheMemory.memoryRemaining = CacheMemory.memoryRemaining - pcb.memory;
                ExecutionQueue.numProcesses = ExecutionQueue.numProcesses + 1;

            }
        }

    }

    //Remove the PCB from queue
    public void removePCB(PCB pcb)
    {
        if (pcb.state == "Exit")
        {
            CacheMemory.memoryRemaining = CacheMemory.memoryRemaining + pcb.memory;
            pcb = null;
        }
    }

    //Get the wait(?) for a given process
    public void getWait(Process process)
    {
//        PCB = process.PCB;
//        return PCB.timeElapsed;
    }

    //Set wait(?) for a given process
    public void setWait(Process process, int wait)
    {
//        PCB = process.PCB;
//        process.timeElapsed = wait;
    }

    public void getArrival(Process process)
    {
//        PCB = process.PCB;
//        return PCB.timeElapsed;

    }

    public void setArrival(Process process, int arrival)
    {
//        PCB = process.PCB;
//        process.arrival = arrival;
    }

    public void getCPUTime()
    {
//      time = clock;
//        return time;
    }

    public void setCPUTime(int time)
    {
//        clock = time;
    }
}
