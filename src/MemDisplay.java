/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Najia13
 */
public class MemDisplay extends JPanel {

    class Table {


        String ioInterrupt;
        int memory;
        int cycle;
        int clock;
        Integer counter;
        String ioBurst;
        OS os;

        public Table(OS os) {
            this.ioInterrupt = "";
            this.memory = CacheMemory.memoryRemaining;
            this.cycle = 0;
            this.clock = 0;
            this.counter = null;
            this.ioBurst = "";
            this.os = os;
        }

        public void editTable() {
            if(os.cpu.interrupt > 0){
                this.ioInterrupt = os.scheduler.getEvent().peek().getName();
                this.counter = os.scheduler.getEvent().peek().getCounter();
                this.ioBurst = Integer.toString(os.scheduler.getEvent().peek().getIoBurst());
            } else {
                this.ioInterrupt = null;
                this.counter = null;
                this.ioBurst = "";
            }
            this.memory = CacheMemory.memoryRemaining;
            this.cycle = os.cpu.cycle;
            this.clock = os.clock.getClock();
        }


    }

    Table prac;
    Object[][] memory_table = new Object[13][8];
    String[] mem_col = {"Additional Resources", ""};
    JTable mem_Jtable;

    public MemDisplay(OS os){
        super(new GridLayout(1,1));
        prac = new Table(os);
        memory_table = new Object[6][2];

        memory_table[0][0] = "Memory Left: ";
        memory_table[1][0] = "Clock: ";
        memory_table[2][0] = "Current CPU Cycle: ";
        memory_table[3][0] = null;
        memory_table[4][0] = "--IO Interrupt--";

        memory_table[0][1] = prac.memory;
        memory_table[1][1] = prac.clock;
        memory_table[2][1] = prac.cycle;
        memory_table[3][1] = null;
        memory_table[4][1] = "IO Burst Time: " + prac.ioBurst;
        memory_table[5][1] = prac.counter;
        memory_table[5][0] = prac.ioInterrupt;

        mem_Jtable = new JTable(memory_table, mem_col);
        mem_Jtable.setPreferredScrollableViewportSize(new Dimension(300, 200));

        JScrollPane scrollPane = new JScrollPane(mem_Jtable);

        add(scrollPane);
    }

    public void editMemTable() {

        memory_table[0][0] = "Memory Left: ";
        memory_table[1][0] = "Clock: ";
        memory_table[2][0] = "Current CPU Cycle: ";
        memory_table[3][0] = null;
        memory_table[4][0] = "--IO Interrupt--";

        prac.editTable();
        memory_table[0][1] = prac.memory;
        memory_table[1][1] = prac.clock;
        memory_table[2][1] = prac.cycle;
        memory_table[3][1] = null;
        memory_table[4][1] = "IO Burst Time: " + prac.ioBurst;
        memory_table[5][1] = prac.counter;
        memory_table[5][0] = prac.ioInterrupt;
        mem_Jtable.repaint();
    }







}