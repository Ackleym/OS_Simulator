import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
/**
 * Created by Michael on 11/30/2016.
 */

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


    /**
     *
     * @author Najia13
     */

    public class PCBtable extends JPanel {

        class Table {


            String name;
            int memory;
            int arrival;
            int timeElapsed;
            int counter;
            String state;
            int priority;
            int cycle;
            int clock;
            OS os;

            public Table(OS os) {
                this.name = "Web";
                this.priority = 0;
                this.state = "New";
                this.arrival = 0;
                this.timeElapsed = 0;
                this.counter = 0;
                this.cycle = 0;
                this.clock = 0;
                this.os = os;
            }

            public void editTable(int i) {
                this.name = os.scheduler.getExec().get(i).getName();
                this.memory = os.scheduler.getExec().get(i).getMemory();
                this.state = os.scheduler.getExec().get(i).getState();
                this.arrival = os.scheduler.getExec().get(i).getArrival();
                this.timeElapsed = os.scheduler.getExec().get(i).getTimeElapsed();
                this.counter = os.scheduler.getExec().get(i).getCounter();
                this.cycle = os.cpu.cycle;
                this.clock = os.clock.getClock();
            }


        }

        public static Table prac;
        public static Object[][] processes = new Object[13][8];
        public static String[] col = {"Name", "Memory", "State", "Arrival", "Counter", "Time Waiting"};
        public static JTable table;

        public void editPCBTable()
        {

            for (int i = 0; i < Scheduler.exec.getSize(); i++)
            {
                prac.editTable(i);
                processes[i][0] = prac.name;
                processes[i][1] = prac.memory;
                processes[i][2] = prac.state;
                processes[i][3] = prac.arrival;
                processes[i][4] = prac.counter;
                processes[i][5] = prac.timeElapsed;
                table.repaint();
            }
            for (int j = Scheduler.exec.getSize(); j < 12; j++)
            {
                processes[j][0] = null;
                processes[j][1] = null;
                processes[j][2] = null;
                processes[j][3] = null;
                processes[j][4] = null;
                processes[j][5] = null;
                table.repaint();
            }
        }

        public PCBtable(OS os) {

            super(new GridLayout(1, 1));


            prac = new Table(os);
            processes = new Object[13][8];


            table = new JTable(processes, col);
            table.setPreferredScrollableViewportSize(new Dimension(800,225));
            JScrollPane scrollPane = new JScrollPane(table);

            //Add the scroll pane to this panel.
            add(scrollPane);


        }

        public static void main(String[] args)
        {

        }

    }







