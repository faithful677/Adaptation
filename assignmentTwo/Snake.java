package assignmentTwo;

import java.awt.*;

public class Snake extends GameObject {
    public Snake(double posX, double posY) {
        super(posX, posY);
    }
    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect((int)posX,(int)posY,DIMENSION,DIMENSION);

    }
    public Rectangle setBounds(){
        return new Rectangle((int)posX,(int)posY,DIMENSION,DIMENSION);
    }

    public void extendSnake(int x, int y, int dir, Graphics g){
        //dir=-1 snake moving to the left
        //dir = 0 snake moving to the right
        g.setColor(Color.CYAN);
        g.fillRect(x+150, y, DIMENSION, DIMENSION);
    }
}
