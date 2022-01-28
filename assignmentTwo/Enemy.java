package assignmentTwo;

import java.awt.*;

public class Enemy extends GameObject {
    public Enemy(double posX, double posY) {
        super(posX, posY);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)posX,(int)posY,DIMENSION,DIMENSION);

    }

    @Override
    public Rectangle setBounds() {
        return new Rectangle((int)posX,(int)posY,DIMENSION,DIMENSION);
    }
}
