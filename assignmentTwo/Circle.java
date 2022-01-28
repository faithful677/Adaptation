package assignmentTwo;

import java.awt.*;

public class Circle extends GameObject {
    public Circle(double posX, double posY){
        super(posX, posY);
    }

    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillOval((int)posX,(int)posY,DIMENSION,DIMENSION);
    }
    public Rectangle setBounds(){
        return new Rectangle((int)posX,(int)posY,DIMENSION,DIMENSION);
    }

}
