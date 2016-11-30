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
        cycle = 0;

        switch (command) {
            case "Out":

                cpuPCB.printPCB();
                cpuPCB.pointer++;
                Scheduler.roundRobin(cpuPCB);
                cycle = cycleMax;
                break;

            case "Calculate":

                while (cycle < cycleMax) {
                    cpuPCB.counter++;
                    cpuPCB.cpuNeeded--;
                    cpuPCB.cpuUsed++;
                    cycle++;
                    advanceClock();
                    System.out.println(cpuPCB.counter);
                    //.02 to IO
                    //If IO

                    if (cpuPCB.counter == parseInt(cpuPCB.instructions.get(cpuPCB.pointer + 1))) {
                        cpuPCB.pointer += 2;
                        cpuPCB.counter = 0;
                        cycle = cycleMax;

                    }

                }
                Scheduler.roundRobin(cpuPCB);
                break;

            case "Yield":
                cpuPCB.pointer++;
                Scheduler.roundRobin(cpuPCB);
                break;

            case "Stop":
                cpuPCB.state = "Exit";
                Scheduler.removePCB();
                break;

        }
            if (Scheduler.exec.first != null)
                run();
            }




//            if (command.equalsIgnoreCase("Out")) {
//                cpuPCB.printPCB();
//                cpuPCB.pointer++;
//                Scheduler.roundRobin(cpuPCB);
//
//            }

//            if (command.equalsIgnoreCase("Calculate")) {
//                cycle = 0;
//
//                while (cycle < cycleMax) {
//                    cpuPCB.counter++;
//                    cpuPCB.cpuNeeded--;
//                    cpuPCB.cpuUsed++;
//                    cycle++;
//                    advanceClock();
//                    System.out.println(cpuPCB.counter);
//                    //.02 to IO
//                    //If IO
//
//                    if (cpuPCB.counter == parseInt(cpuPCB.instructions.get(cpuPCB.pointer + 1))) {
//                        cpuPCB.pointer += 2;
//                        cpuPCB.counter = 0;
//                        Scheduler.roundRobin(cpuPCB);
//                        cycle = cycleMax;
//
//                    }
//
//                }
//                Scheduler.roundRobin(cpuPCB);

//            }
//            if (command.equalsIgnoreCase("Yield")) {
//                cpuPCB.pointer++;
//                Scheduler.roundRobin(cpuPCB);
//            }
//
//
//            if (command.equalsIgnoreCase("Stop")) {
//                cpuPCB.state = "Exit";
//                Scheduler.removePCB();
//            }
//            if (Scheduler.exec.first != null) {
//                run();
//            }
        }
