import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Created by Michael on 11/25/2016.
 */
public class CommandInterface
{
    public static ReadIn read;


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
        System.out.println("Memory Remaining: " + CacheMemory.memoryRemaining);
        System.out.println("Memory Usage:");
        Scheduler.exec.current = Scheduler.exec.first;
        System.out.println(Scheduler.exec.current.pcb.name + ": " + Scheduler.exec.current.pcb.memory);
        for (int i = 1; i < ExecutionQueue.numProcesses; i++)
        {
            Scheduler.exec.current = Scheduler.exec.current.next;
            System.out.println(Scheduler.exec.current.pcb.name + ": " + Scheduler.exec.current.pcb.memory);
        }
    }

    public void exe()
    {
//        run simulation until finished
    }

    public void exe(int cycle)
    {
//      run simulation for given amount of cycles
    }

    public static void load(String job)
    {
        read.openFile(job);
        read.readFile(job);
        read.closeFile();
        ECB ecb = new ECB();
        PCB pcb = new PCB();
        pcb.setName(read.testArray.get(4));
        ecb.setName(pcb.name);
        ecb.setHandler("Scheduler");
        ecb.setTime(parseInt(read.testArray.get(2)));
        ecb.pcb = pcb;
        EventQueue.queue.add(ecb);

    }

    public void reset()
    {
//        Clear everything
    }

    public void promptUser()
    {
//        get command from user
    }

    public static void main(String[] args)
    {
        load("WordProcessor");

    }
}
