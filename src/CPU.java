import static java.lang.Integer.parseInt;

/**
 * Created by Michael on 11/25/2016.
 */
public class CPU
{
    public static CPU cpu = new CPU();
    static int clock = 0;
    static int cycle = 0;
    static int cycleMax = 30;

        public void advanceClock()
        {
            clock++;
        }

    public void detectInterrupt()
    {
//            if (interrupt)
//                do something
    }

    public Boolean detectPreemption(ExecutionQueue exec)
    {
        if (exec.first.pcb.state == "Wait")
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public static void run()
    {
        PCB cpuPCB = new PCB();
        cpuPCB = Scheduler.exec.first.pcb;
        String command = cpuPCB.instructions.get(cpuPCB.pointer);
        while (cycle < cycleMax)
        {

            if (command == "Calculate")
            {
                cpuPCB.counter++;
                cpuPCB.cpuNeeded--;
                cpuPCB.cpuUsed++;
                Scheduler.exec.increment(Scheduler.exec.first);
                cycle++;
                if (cpuPCB.counter == parseInt(cpuPCB.instructions.get(cpuPCB.pointer + 1)))
                {
                    if (cpuPCB.instructions.get(cpuPCB.pointer + 1) != null)
                    {
                        cpuPCB.pointer += 2;
                        Scheduler.exec.deQueue();
                        Scheduler.exec.enQueue(cpuPCB);
                    }
                    else
                    {
                        Scheduler.removePCB();
                        cycle = cycleMax;
                    }


                }
            }
            if (command == "Out")
            {
                cpuPCB.printPCB();
            }

        }
    }
}
