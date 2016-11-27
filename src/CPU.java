/**
 * Created by Michael on 11/25/2016.
 */
public class CPU
{
    static int clock = 0;

        public void advanceClock()
        {
            clock++;
        }

    public void detectInterrupt()
    {
//            if (interrupt)
//                do something
    }

    public Boolean detectPreemption(ExecutionQueue exec)
    {
        if (exec.first.pcb.state == "Wait")
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
