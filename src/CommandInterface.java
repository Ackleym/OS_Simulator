import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Created by Michael on 11/25/2016.
 */
public class CommandInterface
{
    ReadIn read;
    ExecutionQueue execqueue;
    Scheduler scheduler;

    public CommandInterface(Scheduler newScheduler)
    {
        scheduler = newScheduler;
        execqueue = scheduler.getExec();
    }

    public void proc()
    {
//        for (int i; i = unfinished processes; i++)
//        getState();
//        getCPUTime();
//        getPriority();
//        getIORequests();
    }

    public void mem()
    {
        System.out.println("\nMemory Remaining: " + CacheMemory.memoryRemaining);
        System.out.println("Memory Usage:");
        if(scheduler.getExec().getSize() < 1)
        {
            System.out.println("No Processes in Memory\n");
            return;
        }

        for (int i = 0; i < scheduler.getExec().getSize(); i++)
        {
            System.out.println(scheduler.getExec().get(i).getName() + ": " +
                                scheduler.getExec().get(i).getMemory());
        }
        System.out.println();
    }

    public void exe()
    {
        
    }

    public void exe(int cycle)
    {
//      run simulation for given amount of cycles
    }

    public void load(String job)
    {
        read = new ReadIn();
        read.openFile(job);
        read.readFile(job);
        read.closeFile();
        PCB pcb = new PCB();
        pcb.setName(read.testArray.get(0));
        scheduler.insertPCB(pcb);


    }

    public void reset()
    {
//        Clear everything
    }

    public void promptUser()
    {
//        get command from user
    }

}
