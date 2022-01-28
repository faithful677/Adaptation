package assignmentTwo;

import com.sun.codemodel.internal.JOp;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static assignmentTwo.GameObject.flag;

public class Menu implements ActionListener {
    Base1 app;
    JMenu control, instruction, about;//JMenu Objects
    JMenuItem allTime, pause, instr, story, restart;//JMenuItems


    public Menu(Base1 app, Score s){
        this.app = app;
        control = new JMenu("Control");
        instruction = new JMenu("Guidelines");
        about = new JMenu("About");
        //Menu Items
        allTime = new JMenuItem("All-time Ranking");
        control.add(allTime);
        pause = new JMenuItem("Pause (SHIFT)");
        control.add(pause);
        restart = new JMenuItem("Restart");
        control.add(restart);
        instr = new JMenuItem("View Guidelines");
        instruction.add(instr);
        story = new JMenuItem("Description");
        about.add(story);

        //Action listener and adding menu to frame
        instruction.addActionListener(this);
        story.addActionListener(this);
        instr.addActionListener(this);
        allTime.addActionListener(this);
        pause.addActionListener(this);
        restart.addActionListener(this);
        //add items to menu bar
        JMenuBar bar = new JMenuBar();
        app.setJMenuBar(bar);
        bar.add(control);
        bar.add(instruction);
        bar.add(about);
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == allTime) {
            app.pause();
            app.scoreBoard = new JPanel() {
                public void paintComponent(Graphics g) {

                    g.setColor(Color.BLACK);
                    g.setFont(new Font("arial", Font.BOLD, 10));
                    g.drawString("Top five all-time scores:", 0, 15);
                    for (int i = 0; i < app.tempReadArray.length; i++) {
                        g.drawString(" - ", 5, 30 + (10 * i));
                        g.drawString("" + (app.tempReadArray[i]), 25, 30 + (10 * i));
                    }

                }
            };
            JOptionPane.showMessageDialog(null, app.scoreBoard, "Score Board", JOptionPane.INFORMATION_MESSAGE);
            app.gameOver = false;
        }
        else if (a.getSource() == pause){
            //display content of this object if IF-Statement is true
            app.pause();
            System.out.println("Pause (SHIFT)");
            int r = JOptionPane.showConfirmDialog(null, "Game Paused\n\n\nClick 'Yes' to continue\n\nOr 'No' to end game:", "Pause",JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION)
            {
                System.out.println("Continue");
                app.gameOver = false;
            }
            else if (r == JOptionPane.NO_OPTION)
            {
                int v = JOptionPane.showConfirmDialog(null, "Are you sure you want to end the game?", "Confirm",JOptionPane.YES_NO_OPTION);
                if (v == JOptionPane.YES_OPTION){
                    app.gameOver = true;
                    System.exit(0);
                }
                else
                {
                    app.gameOver = false;
                }
            }
        }
        else if (a.getSource() == instr){
            //display content of this object if IF-Statement is true
            app.pause();
            JTextArea textArea = new JTextArea(10,10);
            JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            textArea.setEditable(false);
            textArea.setWrapStyleWord(true);
            String [] commands = {"The instructions for this game are stated as follows:","","","","","1. Use arrow keys to move Player;","","2. Score is increased by 10 points every time Player eats Food;",
                    "","3. Health decreases every time Player comes in contact with Enemy or Obstacle;","",
                    "4. Press SPACEBAR to increase health; you only have three chances to do so;", "","5. A new enemy is created after a certain score is reached;","6. Press 'k' to stop."};
            JList list = new JList(commands);
            sp.getViewport().add(list);
            JOptionPane.showMessageDialog(null,sp,"Guidelines",JOptionPane.INFORMATION_MESSAGE);
            app.gameOver = false;
        }
        else if (a.getSource() == story){
            String[] description = {"Adaptation © describes a player who goes out" +
                    " to find food in order to survive,","","but is attacked by various enemies of his kind. So, he has to find a way to remain alive in spite of enemy attacks."
                    ,"","This game serves as an illustration of how living things live in adverse conditions" +
                    " and in constant danger of predators.","","","","Their Creator, foreseeing that these things would take place have " +
                    "given them the ability to adapt to their environment.","","This is illustrated in the game " +
                    "when the player has the ability to increase their health.","The Owner reserves every right to bring charges against anyone who attempts to plagiarise this game.","","","Have Fun!"};
            app.pause();
            JTextArea textArea = new JTextArea(10,10);
            JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            textArea.setEditable(false);
            textArea.setWrapStyleWord(true);
            JList list = new JList(description);
            sp.getViewport().add(list);
            JOptionPane.showMessageDialog(null,sp,"Description",JOptionPane.INFORMATION_MESSAGE);
            app.gameOver = false;
        }
        else if (a.getSource() == restart){
            app.pause();
            int q = JOptionPane.showConfirmDialog(null,"Are you sure?","Restart",JOptionPane.YES_NO_OPTION);
            if (q == JOptionPane.YES_OPTION){
                app.gameOver = false;
                app.collided = false;
                flag = true;
                app.numberOfCollisions = 0;
                app.life = 3;
                Movement.x = 110;
                Movement.y = 110;
                app.score = 0;
                app.snakes.clear();
                app.e.clear();
                }
            else
                app.gameOver = false;

        }
    }
}
