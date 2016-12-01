/**
 * Created by Andrew on 11/29/2016.
 */
import java.util.Random;

public class OS{
    CPU cpu;
    public static CommandInterface comm;
    Scheduler scheduler;
    Clock clock;
    CacheMemory memory;
    Gui gui;
    int stopTime;
    boolean execute;

    public OS()
    {
        memory = new CacheMemory();
        clock = new Clock();
        scheduler = new Scheduler(clock);
        comm = new CommandInterface(scheduler);
        gui = new Gui(this);
        cpu = new CPU(clock, scheduler, comm, gui);

        stopTime = -1;
        execute = false;
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
//        System.out.println("Hi");
        while(true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            if(execute){
                exe(stopTime + clock.getClock());
            }
        }
    }

    public void exe(int stopTime) {
        while(true) {
            clock.execute();

            Random random = new Random();
            if (random.nextInt(500) == 499) {
                ECB ecb = new ECB();
                scheduler.insertECB(ecb, "System",
                                    "Random Process",
                                    random.nextInt(10) + 1);
            }

            cpu.detectInterrupt();

            cpu.detectPreemption();

            cpu.run();
            gui.newtable.editPCBTable();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }

//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException ie)
//            {
//            }


            if (clock.getClock() == stopTime || scheduler.getExec().getSize() == 0) {
                break;
            }

        }
        execute = false;
        stopTime = -1;
        System.out.println("\n\nOS is Finished\nClock: " + clock.getClock());
    }

}
