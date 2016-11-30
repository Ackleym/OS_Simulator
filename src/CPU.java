import static java.lang.Integer.parseInt;

/**
 * Created by Michael on 11/25/2016.
 */
public class CPU {
    public static CPU cpu = new CPU();
    static int clock = 0;
    static int cycle = 0;
    static int cycleMax = 30;

    public static void advanceClock() {
        clock++;
    }

    public void detectInterrupt() {
//            if (interrupt)
//                do something
    }

    public Boolean detectPreemption(ExecutionQueue exec) {
        if (exec.first.pcb.state == "Wait") {
            return false;
        } else {
            return true;
        }
    }

    public static void run() {
        PCB cpuPCB = new PCB();
        cpuPCB = Scheduler.exec.first.pcb;
        String command = cpuPCB.instructions.get(cpuPCB.pointer);
        System.out.println(command);

        if (command.equalsIgnoreCase("Calculate")) {
            cycle = 0;
            System.out.println("Madeit");

            while (cycle < cycleMax) {
                System.out.println("Madeit");
                cpuPCB.counter++;
                cpuPCB.cpuNeeded--;
                cpuPCB.cpuUsed++;
                cycle++;
                advanceClock();
                System.out.println(cpuPCB.counter);
                //.02 to IO
                //If IO

                if (cpuPCB.counter == parseInt(cpuPCB.instructions.get(cpuPCB.pointer + 1))) {
                    {
                        cpuPCB.pointer += 2;
                        cpuPCB.counter = 0;
                        Scheduler.exec.deQueue();
                        Scheduler.exec.enQueue(cpuPCB);
                        cycle = cycleMax;

                    }
                }
            }

        }

            if (command.equalsIgnoreCase("Out"))
            {
                cpuPCB.printPCB();
                cpuPCB.pointer++;

            }

            if (command.equalsIgnoreCase("Stop"))
            {
                Scheduler.removePCB();
                cycle = cycleMax;
            }
        if (Scheduler.exec.first != null)
        run();
        }
    }