
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
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;









    public class Gui extends JPanel {





        public JFrame GuiWindow;
        public JTextPane comLine;
        public JTextField user;
        public JScrollPane scroll;

        public StyledDocument styledoc;

        public static String jobs[] = {"MediaPlayer", "PhotoEditing", "Test", "VideoGame", "VirusScan", "WebBrowser",
        "WordProcessor"};
        public static PCBtable newtable;
        int stopTime;
        OS os;

        boolean t = false;

        public Gui(OS os){

            try {

                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            }
            catch (Exception exception)  {}
            this.os = os;
            stopTime = 0;
            GuiWindow = new JFrame();
            GuiWindow.setTitle("Gui");
            GuiWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            newtable = new PCBtable();

//            newtable.setOpaque(true);
//            GuiWindow.setContentPane(newtable);
//            GuiWindow.pack();
//            GuiWindow.setVisible(true);


            MemDisplay new_mem = new MemDisplay();


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



            GuiWindow.setSize(1500, 600);
            GuiWindow.setLocationRelativeTo(null);
            GuiWindow.setResizable(false);
            GuiWindow.setVisible(true);

            GuiWindow.add(new_mem, BorderLayout.EAST);
            GuiWindow.add(newtable, BorderLayout.NORTH);



        }



        //////♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥
        public void scrollUp(){
            comLine.setCaretPosition(0);


        }
        public void scrollDown(){
            comLine.setCaretPosition(comLine.getDocument().getLength());



        }


        public void print(String s, boolean trace, Color col){
            Style addStyle = comLine.addStyle("Style", null);
            StyleConstants.setForeground(addStyle, col);

            if (trace ){
                Throwable w = new Throwable();
                StackTraceElement[] e = w.getStackTrace();
                String c_var = e[0].getClassName();

                s = c_var  + s;



            }


            try{
                styledoc.insertString(styledoc.getLength(), s, addStyle);

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
                    for (int i = 0; i < Scheduler.exec.getSize(); i++)
                    Scheduler.exec.deQueue();
                    for (int i = 0; i < Scheduler.wait.getSize(); i++)
                    Scheduler.wait.deQueue();

                    newtable.editPCBTable();
                }


                else if (commands[0].equalsIgnoreCase("proc")){
                    print_type_two("  -- Execution Queue Contents --", t, Color.WHITE);
                    for (int i = 0; i < Scheduler.exec.getSize(); i++) {
                        Scheduler.exec.printProc(i);
                        String string = Scheduler.exec.proc;
                        print_type_two(string, t, new Color(255, 255, 255));
                    }

                    print_type_two("  --  Wait Queue Contents --", t, Color.WHITE);
                    for (int i = 0; i < Scheduler.wait.getSize(); i++) {
                        Scheduler.wait.printProc(i);
                        String string = Scheduler.wait.proc;
                        print_type_two(string, t, new Color(255, 255, 255));
                    }

                }
                else if(commands[0].equalsIgnoreCase("mem")){
                    String mem = "Show current usage of memory space";
                    print_type_two(mem, t, new Color(255,255,255));

                }

                else if (commands[0].equalsIgnoreCase("load") && commands.length == 1){

                    Random random = new Random();
                    int rand = random.nextInt(6);
                    OS.comm.load(jobs[rand]);
                    newtable.editPCBTable();

                }

                else if (commands[0].equalsIgnoreCase("load") && commands.length == 2){
                    OS.comm.load(commands[1]);

                    newtable.editPCBTable();

                }

                else if (commands[0].equalsIgnoreCase("exe") && commands.length == 2) {
                    os.stopTime = Integer.parseInt(commands[1]);
                    os.execute = true;
                }

                else if (commands[0].equalsIgnoreCase("exe") ){
                    os.stopTime = -1;
                    os.execute = true;
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

    }

