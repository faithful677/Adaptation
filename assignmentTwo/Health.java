package assignmentTwo;

import java.awt.*;

public class Health extends GameObject {
    public static final int BAR_LENGTH = 300;
    int greenValue;
    int redValue;
    double numberOfCollisions;//number of Collisions from Base1
    double bar;//Health bar
    public Health (double posX, double posY,double numberOfCollisions){
        super(posX, posY);
        redValue = 70;
        greenValue = 250;
        this.numberOfCollisions = numberOfCollisions;
        bar = (BAR_LENGTH-numberOfCollisions);

    }
    //Draw Health bar
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)posX,(int)posY, 300,50);
        g.setColor(new Color(redValue,greenValue,0));
        g.fillRect((int)posX,(int)posY, (int) bar,50);
        g.setColor(Color.BLACK);
        g.drawRect((int)posX,(int)posY, 300,50);
    }
    public Rectangle setBounds(){
        return new Rectangle((int)posX,(int)posY,DIMENSION,DIMENSION);
    }


}
