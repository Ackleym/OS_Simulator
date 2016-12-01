/**
 * Created by Michael on 11/25/2016.
 */
public class Test {

    public static void main(String[] args)
    {
        OS os = new OS();
//        Scheduler scheduler = new Scheduler();
//        CommandInterface comm = new CommandInterface();
//        CPU cpu = new CPU();

        os.comm.load("Test");
        os.comm.load("WebBrowser");
        os.comm.load("WordProcessor");
        os.comm.load("VideoGame");
//        os.comm.load("WordProcessor");
        os.comm.mem();
        os.scheduler.getExec().printPCB();
        os.scheduler.getWait().printPCB();
        os.exe(-1);

//        comm.load("Test");
////        os.scheduler.exec.printPCB(os.scheduler.exec.first);
//        comm.mem();
//        comm.exe();




//        PCB pcb = new PCB();
//        pcb.setMemory(34);
//        PCB pcb2 = new PCB();
//        pcb2.setMemory(55);
//        PCB pcb3 = new PCB();
//        pcb3.setMemory(21);
//        scheduler.insertPCB(pcb);
//        scheduler.insertPCB(pcb2);
//        scheduler.insertPCB(pcb3);
//        Scheduler.exec.run(35, Scheduler.exec);
//        Scheduler.exec.printPCB(Scheduler.exec.first);
//        comm.mem(Scheduler.exec);



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
