import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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

    public void mem(ExecutionQueue exec)
    {
        System.out.println("Memory Remaining: " + CacheMemory.memoryRemaining);
        System.out.println("Memory Usage:");
        exec.current = exec.first;
        System.out.println(exec.current.pcb.name + ": " + exec.current.pcb.memory);
        for (int i = 1; i < ExecutionQueue.numProcesses; i++)
        {
            exec.current = exec.current.next;
            System.out.println(exec.current.pcb.name + ": " + exec.current.pcb.memory);
        }
    }

    public void exe()
    {
//        if (ready queue = null)
//        {
//            return;
//        }
//        else (int i = 0; i < cycle; i++)
//            Run the simulation
    }

    public void exe(int cycle)
    {

    }

    public static void load(String job)
    {
        read.openFile(job);
        read.readFile(job);
        read.closeFile();
        PCB pcb = new PCB();
        pcb.setName(read.testArray.get(4));
        System.out.println("--------" + pcb.name);
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
