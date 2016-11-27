/**
 * Created by Michael on 11/25/2016.
 */
public class Test {

    public static void main(String[] args)
    {
        Scheduler scheduler = new Scheduler();
        ExecutionQueue exec = new ExecutionQueue();
        WaitQueue wait = new WaitQueue();
        CommandInterface comm = new CommandInterface();

        PCB pcb = new PCB();
        pcb.setMemory(34);
        PCB pcb2 = new PCB();
        pcb2.setMemory(55);
        PCB pcb3 = new PCB();
        pcb3.setMemory(21);
        scheduler.insertPCB(pcb);
        scheduler.insertPCB(pcb2);
        scheduler.insertPCB(pcb3);
        exec.run(35, exec);
        exec.printPCB(exec.first);
        comm.mem(exec);



//        wait.enQueue(pcb);
//        wait.enQueue(pcb2);
//        wait.enQueue(pcb3);
//        wait.printPCB(wait.first);
//        wait.deQueue(exec);
//        System.out.println("--------------------");
//        wait.printPCB(wait.first);
//        System.out.println("--------------------");
//        exec.printPCB(exec.first);
//        exec.run(9, exec);
//        System.out.println("--------------------");
//        exec.printPCB(exec.first);


    }
}
