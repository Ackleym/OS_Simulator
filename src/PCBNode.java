/**
 * Created by Michael on 11/25/2016.
 */
public class PCBNode
{
    PCBNode next;
    PCBNode prev;
    PCB pcb;

    public PCBNode(PCB pcb)
    {
        this.pcb = pcb;
        this.next = null;
        this.prev = null;
    }


}
