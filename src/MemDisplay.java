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




    public MemDisplay(){
        super(new GridLayout(1,0));
        String[] mem_col = {"Memory"};
        Object[][] memory_table = {{"Memory Left", "insert here"}};


        final JTable mem_Jtable = new JTable(memory_table, mem_col);
        mem_Jtable.setPreferredScrollableViewportSize(new Dimension(200, 200));



        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(mem_Jtable);

        //Add the scroll pane to this panel.
        add(scrollPane);





    }







}