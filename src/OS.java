/**
 * Created by Andrew on 11/29/2016.
 */
import java.util.Random;

public class OS {
    CPU cpu;
    CommandInterface comm;
    Scheduler scheduler;
    Clock clock;
    CacheMemory memory;
    Gui gui = new Gui();
    private int stopTime;

    public OS()
    {
        memory = new CacheMemory();
        clock = new Clock();
        scheduler = new Scheduler(clock);
        comm = new CommandInterface(scheduler);
        cpu = new CPU(clock, scheduler, comm);

        stopTime = -1;
    }

//    public OS(int cycles)
//    {
//        clock = new Clock();
//        scheduler = new Scheduler(clock);
//        comm = new CommandInterface(scheduler);
//        cpu = new CPU(clock, scheduler, comm);
//
//        stopTime = clock.getClock() + cycles;
//    }

    public void run() {
        while(true) {
            int select = -1;
//            select = Input from Called Comm

            if (select != 0) {
                stopTime = clock.getClock() + select;
                exe(stopTime);
            } else {
                return;
            }
        }
    }

    public void exe(int stopTime) {
        while(true) {
            clock.execute();

            Random random = new Random();
//            if (random.nextInt(500) == 499) {
//                ECB ecb = new ECB();
//                scheduler.insertECB(ecb, "System",
//                                    "Random Process",
//                                    random.nextInt(10) + 1,
//                                    random.nextInt(26) + 25);
//            }

            cpu.detectInterrupt();

            cpu.detectPreemption();

            cpu.run();
            gui.newtable.editPCBTable();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }

            if (clock.getClock() == stopTime || scheduler.getExec().getSize() == 0) {
                break;
            }

        }
        System.out.println("\n\nOS is Finished\nClock: " + clock.getClock());
    }

}
