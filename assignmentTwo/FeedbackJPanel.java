package assignmentTwo;

import javax.swing.*;
import java.awt.*;

public class FeedbackJPanel extends JPanel {
    //This class is used to draw the small circles that are used to obtain feedback from the user
    Feedback one, two, three, four, five;//receive Feedback objects
    public FeedbackJPanel(Feedback one, Feedback two, Feedback three, Feedback four, Feedback five){
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
    }
    //Draw Feedback
    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("arial",Font.BOLD,11));
        g.drawString("Tell us what you think: ",0,20);
        //draw objects
        System.out.println("Num");
        one.draw(g);
        two.draw(g);
        three.draw(g);
        four.draw(g);
        five.draw(g);
    }
}
