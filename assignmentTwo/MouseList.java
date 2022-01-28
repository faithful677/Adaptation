package assignmentTwo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class MouseList extends JPanel implements MouseListener {
    private FeedbackJPanel other;//receive object from FeedbackJpanel
    public MouseList(FeedbackJPanel other) {
        this.other = other;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        //determine the coordinates of the dialog box on the frame
        Point location = e.getComponent().getLocationOnScreen();
        double x = location.getX();
        double y = location.getY();
        //determine the postion of the mouse
        double mouseX = MouseInfo.getPointerInfo().getLocation().x;
        double mouseY = MouseInfo.getPointerInfo().getLocation().y;
        //find the exact point where the mouse is to be clicked
        double exactX = mouseX-x;
        double exactY = mouseY -y;
        //Array list contains 'Feedback' Objects
        ArrayList<Feedback> map = new ArrayList<>();
        map.add (other.one);
        map.add (other.two);
        map.add (other.three);
        map.add (other.four);
        map.add (other.five);
        // the following conditional statements check whether the position of the mouse lies between the diameter of each circle
        if ((other.one.posX <= exactX && exactX <= other.one.posX+10) && (other.one.posY <= exactY && exactY <= other.one.posY+10)){
            for (int i = 0; i<map.size();i++) {
                if (i < 1){
                    map.get(i).col = Color.GREEN;
                }
                else {
                    map.get(i).col = Color.WHITE;
                }

            }
        }
        else if ((other.two.posX <= exactX && exactX <= other.two.posX+15) && (other.two.posY <= exactY && exactY <= other.two.posY+10)){
            for (int i = 0; i<map.size();i++) {
                if (i < 2){
                    map.get(i).col = Color.GREEN;
                }
                else {
                    map.get(i).col = Color.WHITE;
                }
            }
        }
        else if ((other.three.posX <= exactX && exactX <= other.three.posX+15) && (other.three.posY <= exactY && exactY <= other.three.posY+10)){
            for (int i = 0; i<map.size();i++) {
                if (i < 3){
                    map.get(i).col = Color.GREEN;
                }
                else {
                    map.get(i).col = Color.WHITE;
                }
            }
        }
        else if ((other.four.posX <= exactX && exactX <= other.four.posX+15) && (other.four.posY <= exactY && exactY <= other.four.posY+10)){

            for (int i = 0; i<map.size();i++) {
                if (i < 4){
                    map.get(i).col = Color.GREEN;
                }
                else {
                    map.get(i).col = Color.WHITE;
                }
            }
        }
        else if ((other.five.posX <= exactX && exactX <= other.five.posX+15) && (other.five.posY <= exactY && exactY <= other.five.posY+10)){
            for (int i = 0; i<map.size();i++) {
                if (i < 5){
                    map.get(i).col = Color.GREEN;
                }
                else {
                    map.get(i).col = Color.WHITE;
                }
            }
        }
        other.repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
