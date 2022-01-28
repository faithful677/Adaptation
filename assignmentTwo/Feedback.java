package assignmentTwo;

import java.awt.*;

public class Feedback extends GameObject {
    public Color col = Color.WHITE;//inital color
    public Feedback(double posX, double posY) {
        super(posX, posY);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(col);
        g.fillOval((int)posX,(int)posY,10,10);

    }

    @Override
    public Rectangle setBounds() {
        return null;
    }
}
