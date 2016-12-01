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
            if (random.nextInt(100) == 1) {
                cpu.getInterruptProcessor().addEvent("Random Process", "System", random.nextInt(10));
            }

            cpu.detectInterrupt();

            cpu.detectPreemption();

            cpu.run();
            gui.newtable.editPCBTable();
            gui.new_mem.editMemTable(this);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }

            if (clock.getClock() == stopTime || (scheduler.getExec().getSize() == 0) &&
                    cpu.getInterruptProcessor().getIoScheduler().getEventQueue().getSize() == 0 &&
                    scheduler.getNewQueue().getSize() == 0) {
                break;
            }

        }
        execute = false;
        System.out.println("\n\nOS is Finished\nClock: " + clock.getClock());
    }

}
