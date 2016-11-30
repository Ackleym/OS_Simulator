import static java.lang.Integer.parseInt;

/**
 * Created by Michael on 11/25/2016.
 */
public class CPU {
    Clock clock;
    private int cycle;
    private static final int cycleMax = 30;
    Scheduler scheduler;
    CommandInterface comm;
    private boolean interrupt;

    public CPU(Clock nclock, Scheduler nscheduler, CommandInterface ncomm)
    {
        clock = nclock;
        scheduler = nscheduler;
        cycle = 0;
        comm = new CommandInterface(scheduler);
        interrupt = false;
    }

    public Scheduler getScheduler()
    {
        return scheduler;
    }

    public void detectInterrupt()
    {
        if(scheduler.getEvent().getSize() > 0) {
            interrupt = true;
        }



    }

    public void detectPreemption()
    {
        if(scheduler.getExec().getSize() == 0 || interrupt) {
            return;
        }

        if(scheduler.getExec().get(0).getState().equalsIgnoreCase("Ready")) {
            scheduler.getExec().get(0).setState("Run");
            cycle = 0;

            System.out.println("\nPreemption\n");
        }

        if(scheduler.getExec().get(0).getState().equalsIgnoreCase("Wait")) {
            scheduler.getExec().get(0).setState("Ready");
            scheduler.cycle();
            scheduler.getExec().get(0).setState("Run");
            cycle = 0;

            System.out.println("\nPreemption\n");
        }

        if(scheduler.getExec().get(0).getState().equalsIgnoreCase("Exit")) {
            comm.mem();
            scheduler.getExec().printPCB();
            scheduler.getWait().printPCB();
            scheduler.removePCB();

            System.out.println("\nProcess Removed Successfully\n");

            comm.mem();
            scheduler.getExec().printPCB();
            scheduler.getWait().printPCB();
            cycle = 0;

            System.out.println("\nPreemption\n");
        }

        if(cycle == cycleMax)
        {
            scheduler.getExec().get(0).setState("Ready");
            scheduler.cycle();
            scheduler.getExec().get(0).setState("Run");

            cycle = 0;
            System.out.println("\nPreemption\n");
        }
    }

    public void run()
    {
        if(scheduler.getExec().getSize() == 0) {
            return;
        }

        PCB cpuPCB = scheduler.getExec().getFirst();
        String command = cpuPCB.getInstructions().get(cpuPCB.getPointer());
        System.out.println("\n" + cpuPCB.getName() + "\n" + cpuPCB.getState());
        cpuPCB.setTimeElapsed(cpuPCB.getTimeElapsed() + 1);

        if (command.equalsIgnoreCase("Calculate")) {
            cpuPCB.setCounter(cpuPCB.getCounter() + 1);
            cycle++;
            System.out.println(command + ": " + cpuPCB.getCounter());

            if (cpuPCB.counter == parseInt(cpuPCB.getInstructions().get(cpuPCB.getPointer() + 1))) {
                if (cpuPCB.getInstructions().get(cpuPCB.getPointer() + 2) != null) {
                    cpuPCB.setPointer(cpuPCB.getPointer() + 2);
                    cpuPCB.setCounter(0);
                } else {
                    cpuPCB.setState("Exit");
                }
            }

        }

        if (command.equalsIgnoreCase("Yield"))
        {
            cpuPCB.setState("Wait");
            cpuPCB.setPointer(cpuPCB.getPointer() + 1);
            System.out.println(command);
        }

        if (command.equalsIgnoreCase("Out"))
        {
            cpuPCB.setState("Exit");
            System.out.println(command);
        }

        System.out.println("Clock: " + clock.getClock());
    }
}