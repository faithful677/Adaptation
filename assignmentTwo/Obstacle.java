package assignmentTwo;

import java.awt.*;

public class Obstacle extends GameObject{
    public Obstacle(double posX, double posY) {
        super(posX, posY);
    }
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)posX,(int)posY,150,5);
    }
    public Rectangle setBounds(){
        return new Rectangle((int)posX,(int)posY,150,5);
    }

}
