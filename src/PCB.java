import java.util.ArrayList;

/**
 * Created by Michael on 11/25/2016.
 */
public class PCB {

    String state;
    String name;
    ArrayList<String> instructions = new ArrayList<>();
    int memory;
    int arrival;
    int timeElapsed;
    int counter;
    int priority;
    int cpuNeeded;
    int cpuUsed;
    int ioRequests;
    int pointer;


    public PCB() {
        this.name = "Name";
        this.priority = 0;
        this.state = "New";
        this.arrival = 0;
        this.timeElapsed = 0;
        this.counter = 0;
        this.instructions = null;
        this.cpuNeeded = 0;
        this.cpuUsed = 0;
        this.ioRequests = 0;
        this.pointer = 0;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }

    public void setMemory(int memory)
    {
        this.memory = memory;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void printMemory()
    {
        memory = this.memory;
        System.out.println(memory);
    }

    public void timeElapsed(int time)
    {
        this.timeElapsed = time;
    }

    public void setCounter(int counter)
    {
        this.counter = counter;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    public void printPCB()
    {
        String name = this.name;
        int memory = this.memory;
        int arrival = this.arrival;
        int timeElapsed = this.timeElapsed;
        int counter = this.counter;
        String state = this.state;
        int priority = this.priority;
        int cpuNeeded = this.cpuNeeded;

        System.out.println("Name: " + name + "\n" + "Memory: " + memory + "\n" + "Arrival: " + arrival + "\n" + "Time Elapsed: " + timeElapsed +
                "\n" + "Counter: " + counter + "\n" + "State: " + state + "\n" + "Priority: " + priority + "\n" + "CPU Needed: " + cpuNeeded);

    }
}



