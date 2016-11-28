import java.util.ArrayList;

/**
 * Created by Michael on 11/27/2016.
 */
public class ECB {

    String name;
    String handler;
    PCB pcb;
    int priority;
    int time;


    public ECB() {
        this.name = "Name";
        this.handler = "Handler";
        this.priority = 0;
        this.pcb = null;
        this.time = 0;
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

    public void setTime(int time)
    {
        this.time = time;
    }
}
