/**
 * Created by Michael on 11/25/2016.
 */
public class CommandInterface
{
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

    public void load(String job)
    {
//      getProgram(job);
//      insertPCB(int id, int priority);
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
