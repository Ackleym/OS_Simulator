import java.awt.*;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * Created by Michael on 11/25/2016.
 */

import java.util.Random;

public class CPU {
    Clock clock;
    int cycle;
    private static final int cycleMax = 30;
    Scheduler scheduler;
    CommandInterface comm;
    Gui gui;
    int interrupt;

    public CPU(Clock nclock, Scheduler nscheduler, CommandInterface ncomm, Gui gui) {
        clock = nclock;
        scheduler = nscheduler;
        cycle = 0;
        comm = new CommandInterface(scheduler);
        this.gui = gui;
        interrupt = 0;
    }

    public Scheduler getScheduler()
    {
        return scheduler;
    }

    public void detectInterrupt() {
        if(scheduler.getEvent().getSize() > 0 && interrupt == 0) {
            interrupt = 1;

            if(scheduler.getEvent().peek().getName().equalsIgnoreCase("System")) {
                scheduler.getExec().get(0).setState("Ready");
            }else {
                scheduler.getExec().get(0).setState("Wait");
            }

            System.out.println("\nPreemption - IO Interrupt\n");
        }

        if(interrupt > 1){
            interrupt = 0;
            scheduler.removeECB();
        }

    }

    public void detectPreemption() {
        if(scheduler.getExec().getSize() == 0 || interrupt > 0) {
            return;
        }

        if(scheduler.getExec().get(0).getState().equalsIgnoreCase("Ready")) {
            scheduler.getExec().get(0).setState("Run");

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

            if(scheduler.getExec().getSize() > 0) {
                scheduler.getExec().get(0).setState("Run");
            }

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

    public void run() {
        if(scheduler.getExec().getSize() == 0) {
            return;
        }

        if(interrupt > 0){
            ECB cpuECB = scheduler.getEvent().peek();
            cpuECB.setCounter(cpuECB.getCounter() + 1);
            System.out.println("\nName: " + cpuECB.getName() +
                                "\nHandler: " + cpuECB.getHandler() +
                                "\nCounter: " + cpuECB.getCounter());

            if(cpuECB.getCounter() >= cpuECB.getIoBurst()){
                interrupt = 2;
                return;
            }

            return;
        }

        PCB cpuPCB = scheduler.getExec().getFirst();
        String command = cpuPCB.getInstructions().get(cpuPCB.getPointer());
        System.out.println("\n" + cpuPCB.getName() + "\n" + cpuPCB.getState());
        cpuPCB.setTimeElapsed(cpuPCB.getTimeElapsed() + 1);
        cycle++;

        if (command.equalsIgnoreCase("Calculate")) {
            cpuPCB.setCounter(cpuPCB.getCounter() + 1);
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

        if (command.equalsIgnoreCase("Yield")) {
            cpuPCB.setState("Wait");
            cpuPCB.setPointer(cpuPCB.getPointer() + 1);
            System.out.println(command);
        }

        if(command.equalsIgnoreCase("Out")){
            cpuPCB.setPointer(cpuPCB.getPointer() + 1);
            String output = scheduler.getExec().printProc(0);
            gui.print_type_two(output, true, Color.WHITE);
            System.out.println("Out");
        }

        if(command.equalsIgnoreCase("IO")) {
            String name = cpuPCB.getInstructions().get(cpuPCB.getPointer() + 1);
            int priority = Integer.parseInt(cpuPCB.getInstructions().get(cpuPCB.getPointer() + 2));
            Random random = new Random();
            ECB ecb = new ECB();
            scheduler.insertECB(ecb, name, cpuPCB.getName(), priority);

            cpuPCB.setPointer(cpuPCB.getPointer() + 3);
        }

        if (command.equalsIgnoreCase("Stop")) {
            cpuPCB.setState("Exit");
            System.out.println(command);
        }

        System.out.println("Clock: " + clock.getClock() + "\nCycle: " + cycle);
    }
}