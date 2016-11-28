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


    public PCB() {
        this.name = "Name";
        this.priority = 0;
        this.state = "New";
        this.arrival = 0;
        this.timeElapsed = 0;
        this.counter = 0;
        this.instructions = null;
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
}



