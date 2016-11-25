/**
 * Created by Michael on 11/25/2016.
 */
public class PCB {

    String state;
    String name;
    int memory;
    int arrival;
    int timeElapsed;
    int counter;
    int priority;


    public PCB() {
        this.name = "Name";
        this.priority = 0;
        this.state = "New";
        this.arrival = 0;
        this.timeElapsed = 0;
        this.counter = 0;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getState(String state)
    {
        return state;
    }

    public void setMemory(int memory)
    {
        this.memory = memory;
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
}



