
/**
 *
 * @author Najia13
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;









    public class Gui extends JPanel {





        public JFrame GuiWindow;
        public JTextPane comLine;
        public JTextField user;
        public JScrollPane scroll;

        public StyledDocument styledoc;

        public static PCBtable newtable;

        boolean t = false;

        public Gui(){




            //Table set up










            try {

                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            }
            catch (Exception exception)  {}
            GuiWindow = new JFrame();
            GuiWindow.setTitle("Gui");
            GuiWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            newtable = new PCBtable();

            newtable.setOpaque(true);
            GuiWindow.setContentPane(newtable);
            GuiWindow.pack();
            GuiWindow.setVisible(true);


            comLine = new JTextPane();
            comLine.setEditable(false);
            comLine.setFont(new Font("Courier New" , Font.PLAIN, 12));
            comLine.setOpaque(false);

            styledoc = comLine.getStyledDocument();



            user = new JTextField();
            user.setEditable(true);
            user.setForeground(Color.WHITE);
            user.setCaretColor(Color.WHITE);
            user.setOpaque(false);


            user.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e){

                    String input_text = user.getText();
                    if (input_text.length()> 1){
                        performInput(input_text);
                        scrollDown();
                        user.selectAll();


                    }


                }


            });





            scroll = new JScrollPane(comLine);
            scroll.setOpaque(false);
            scroll.getViewport().setOpaque(false);


            GuiWindow.add(user, BorderLayout.SOUTH);

            GuiWindow.add(scroll, BorderLayout.CENTER);
            GuiWindow.getContentPane().setBackground(new Color(50, 50, 50));



            GuiWindow.setSize(1500, 500);
            GuiWindow.setLocationRelativeTo(null);
            GuiWindow.setResizable(false);
            GuiWindow.setVisible(true);




        }



        //////♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥
        public void scrollUp(){
            comLine.setCaretPosition(0);


        }
        public void scrollDown(){
            comLine.setCaretPosition(comLine.getDocument().getLength());



        }


        public void print(String s, boolean trace, Color col){
            Style style = comLine.addStyle("Style", null);
            StyleConstants.setForeground(style, col);

            if (trace ){
                Throwable w = new Throwable();
                StackTraceElement[] e = w.getStackTrace();
                String c_var = e[0].getClassName();

                s = c_var  + s;



            }


            try{
                styledoc.insertString(styledoc.getLength(), s, style);

            }
            catch(Exception ex){}




        }



        public void print_type_two(String s, boolean trace, Color color){
            print(s + "\n", trace, color);


        }





        public void performInput(String s){
            final String[] commands = s.split(" ");

            try {
                if(commands[0].equalsIgnoreCase("reset"))
                {
                    styledoc.remove(0,styledoc.getLength());
                }


                else if (commands[0].equalsIgnoreCase("proc")){
                    String help = "return unfinished processes and information, current process state, CPU time needed to finish,"
                            + "Priority, CPU time used ";
                    print_type_two(help, t, new Color(255,255,255));

                }
                else if(commands[0].equalsIgnoreCase("mem")){
                    String mem = "Show current usage of memory space";
                    print_type_two(mem, t, new Color(255,255,255));

                }

                else if (commands[0].equalsIgnoreCase("load") ){
                    String load = "loads prog/job files into simulator, includes allocation of programs PCB and memory space";

                    print_type_two(load, t, new Color(255,255,255));


                }

                else if (commands[0].equalsIgnoreCase("exe") ){
                    String exe = "Let's simulation run on it's own, specifiy number of cycles to run before pausing. If none in ready queue, go to command interface";

                    print_type_two(exe, t, new Color(255,255,255));
                }

                else if (commands[0].equalsIgnoreCase("exit") ){
                    String exe = "End and Exit simulation";

                    print_type_two(exe, t, new Color(255,255,255));
                    System.exit(0);
                }


                else {
                    print_type_two(s, t, new Color(255,255,255));

                }

            }

            catch(Exception ex){
                print_type_two("There's an Error " + ex.getMessage(), t, new Color(255,155,155));

            }


        }


        public static void main(String[] args){
            new Gui();
            //PCBgui table  = new PCBgui();

            //makeAndDisplay();



        }








    }

