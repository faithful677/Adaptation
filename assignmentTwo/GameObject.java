package assignmentTwo;

import java.awt.*;

public abstract class GameObject {
    double posX;//x coord of graphics object
    double posY;//y coord of graphics object
    public static int DIMENSION = 30;//corresponds to height and width of Graphics object
    public static boolean flag = true;



    public GameObject(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public abstract void draw(Graphics g);//draw graphics
    public abstract Rectangle setBounds();//envelop graphics object in Rectangle object to enable collision detection


}
