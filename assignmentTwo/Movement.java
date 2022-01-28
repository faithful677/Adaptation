package assignmentTwo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement extends JPanel implements KeyListener, ActionListener {
    //instance var
    private Base1 app;
    public int score;
    public Health healthBar;
    public static int x = 110,y = 110, velX = 0, velY=0;//x-coord, y-coord, Velocity-x, Velocity-y
    private Timer t = new Timer(5,this);//Timer determines the speed of Player and Enemy

    //constructor
    Movement(Base1 app, Health healthBar){
        this.healthBar = healthBar;
        this.app = app;
        this.score = app.score;
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);
        t.start();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP){
            velY = - 1;// y-cord
            velX = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            velY = 1;//y cord
            velX = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            velX = 1;//x cord
            velY = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            velX = - 1;
             velY = 0;

        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE){
            if (app.life > 0) {
                app.newLife();
                app.pause();
                if (app.life == 1)
                {
                    JOptionPane.showMessageDialog(null,"Your health has increased!\n\nYou now " +
                            "have "+app.life+" more chance.\n\nClick 'OK' to continue");
                }
                else
                    {
                        JOptionPane.showMessageDialog(null,"Your health has increased!\n\nYou now " +
                        "have "+app.life+" more chances.\n\nClick 'OK' to continue");
                    }
                app.life--;
                app.gameOver = false;
            }
            else
            {
                app.pause();
                JOptionPane.showMessageDialog(null,"Your health has finished!\n\nYou now " +
                        "have no more chances.\n\nClick 'OK' to continue");
                app.gameOver = false;
            }



        }
        else if (e.getKeyCode() == KeyEvent.VK_K){
            velX = 0;
            velY = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_SHIFT){
            app.pause();
            System.out.println("Pause");
            int r = JOptionPane.showConfirmDialog(null, "Game Paused\n\n\nClick 'Yes' to continue\n\n\nOr 'No' to end game:", "Pause",JOptionPane.YES_NO_OPTION);
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
        else{
            app.pause();
            JOptionPane.showMessageDialog(null,e.getKeyChar()+" is not a command in this game!");
            app.gameOver = false;
        }


    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!app.gameOver) {
            app.repaint();
            x += velX;
            y += velY;
        }
    }
}