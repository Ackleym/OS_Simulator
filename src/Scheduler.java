import static java.lang.Integer.parseInt;

/**
 * Created by Michael on 11/25/2016.
 */
public class Scheduler {

    private static ExecutionQueue exec;
    private static WaitQueue wait;
    private static EventQueue event;
    private Clock clock;

    public Scheduler(Clock nclock)
    {
        exec = new ExecutionQueue();
        wait = new WaitQueue();
        event = new EventQueue();
        clock = nclock;
    }

    //Check to see if top priority event is load command

    //Insert PCB into the proper queue
    public void insertPCB(PCB pcb)
    {
        ReadIn read = new ReadIn();
        read.openFile(pcb.name + "Proc");
        read.readFile(pcb.name + "Proc");
        read.closeFile();
        pcb.setMemory(parseInt(read.testArray.get(0)));
        pcb.setCpuBurst(parseInt(read.testArray.get(1)));

        read.testArray.remove(0);
        read.testArray.remove(0);

        pcb.setInstructions(read.testArray);
        if (pcb.memory > CacheMemory.memoryRemaining)
        {
            pcb.setState("Ready");
            wait.enQueue(pcb);
        }

        else
        {
            pcb.setState("Ready");
            exec.enQueue(pcb);
            CacheMemory.memoryRemaining = CacheMemory.memoryRemaining - pcb.getMemory();
            pcb.setArrival(clock.getClock());
        }
    }

    //Remove the PCB from queue
    public void removePCB()
    {
        if(exec.getSize() == 0) {
            return;
        }

        PCB pcb = exec.getFirst();
        if (pcb.state.equalsIgnoreCase("Exit"))
        {
            CacheMemory.memoryRemaining = CacheMemory.memoryRemaining + pcb.memory;
            exec.deQueue();

            if(wait.getSize() > 0)
            {
                if (wait.getFirst().getMemory() <= CacheMemory.memoryRemaining) {
                    PCB temp = wait.deQueue();
                    temp.setState("Ready");
                    temp.arrival = clock.getClock();
                    exec.enQueue(temp);
                    CacheMemory.memoryRemaining = CacheMemory.memoryRemaining - temp.memory;
                }
            }

        }
    }

    public void cycle(){
        exec.cycle();
    }

    public void insertECB(ECB ecb, String name, String handler, int priority, int ioBurst) {
        ecb.setName(name);
        ecb.setHandler(handler);
        ecb.setPriority(priority);
        ecb.setIoBurst(ioBurst);
        event.enQueue(ecb);
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
        return clock.getClock();
    }

    public ExecutionQueue getExec(){
        return exec;
    }

    public WaitQueue getWait() {
        return wait;
    }

    public EventQueue getEvent() {
        return event;
    }
}
