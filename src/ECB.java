import java.util.ArrayList;

/**
 * Created by Michael on 11/27/2016.
 */

import java.util.Random;

public class ECB {

    String name;
    String handler;
    int priority;
    int ioBurst;
    int counter;
    Random random;

    public ECB() {
        random = new Random();
        this.name = "Name";
        this.handler = "Handler";
        this.priority = 0;
        counter = 0;
        this.ioBurst = random.nextInt(26) + 25;
    }

    public ECB(int ioBurst) {
        this.name = "Name";
        this.handler = "Handler";
        this.priority = 0;
        counter = 0;
        this.ioBurst = ioBurst;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setHandler(String handler)
    {
        this.handler = handler;
    }

    public String getHandler()
    {
        return handler;
    }

    public void setIoBurst(int ioBurst)
    {
        this.ioBurst = ioBurst;
    }

    public int getIoBurst() {
        return ioBurst;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }
}
