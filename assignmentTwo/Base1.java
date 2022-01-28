package assignmentTwo;

import javax.swing.*;
import java.awt.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import static assignmentTwo.Health.BAR_LENGTH;
import static assignmentTwo.Movement.velX;
import static assignmentTwo.Movement.velY;
import static assignmentTwo.GameObject.flag;
import static assignmentTwo.Score.FILENAME;

public class Base1 extends JFrame {
    //instance variables
    private int c;//x-coord for circle
    private int d;//y coord for circle
    private Circle circle;// 'circle' corresponds to food in the game
    private Health h;//'h' corresponds to the health bar
    public int score;//corresponds to scores
    public int life;//extra life
    public ArrayList<Snake> snakes;
    public ArrayList<Enemy> e;
    private int numberOfEnemies;//number of enemies increases after a certain score
    public boolean collided;//To check whether Player and Enemy/Obstacle intersects
    public boolean gameOver;//determines whether the game is over
    private boolean eaten;//Checks whether Player has eaten
    public Score s;//Score
    String[] topFive = new String[18];//Displays the information in the dialog box which appears at the end of a round
    JPanel scoreBoard;//Displays top 5 scores of all time
    private double enemySpeed; // Enemy speed
    private double currentY;//Current x position of Player
    private double currentX;//Current y position of Player
    public SnakeBody body;
    public Movement m;
    public int[] tempReadArray = new int [5];//retrieves int array from readScores()
    public int numberOfCollisions;

    //Constructor
    public Base1() {
        //Menu
        Menu mnu = new Menu(this, s);
        //movement
        m = new Movement(this, h);
        addKeyListener(m);
        //main program
        body = new SnakeBody();
        add(body, BorderLayout.CENTER);//add components to frame
        c = 200;
        d = 400;
        collided = false;
        gameOver = false;
        eaten = false;
        numberOfEnemies = 0;
        enemySpeed = 0.5;
        life = 3;
        score = 0;
        numberOfCollisions = 0;
    }

    class SnakeBody extends JPanel {

        Feedback one, two, three, four, five;//Feedback objects


        SnakeBody() {
            //square corresponds to snake

            snakes = new ArrayList<>();
            e = new ArrayList<>();
            //snake.draw(g);

        }

        int k, temp;
        int count = 1;

        //grid
        public void paintComponent(Graphics g) {
            g.fillRect(100, 100, 500, 500);
            for (int i = 100; i < 600; i = i + 50) {
                if (count % 2 != 0)
                    k = 100;
                else
                    k = (100 + (i - temp));
                for (; k < 600; k = k + 100) {

                    g.clearRect(k, i, 50, 50);
                }
                count++;
                temp = i;//store prev value of 'i'
            }
            //obstacle
            Obstacle[] arrayObstacle = new Obstacle[3];
            for (int i = 0; i < arrayObstacle.length; i++) {
                arrayObstacle[i] = new Obstacle((150 * (i + 1)), 150 * (i + 1));
                arrayObstacle[i].draw(g);
            }
            //initialise first objects of Player and Enemy
            if (flag) {
                Snake player = new Snake(Movement.x, Movement.y);
                snakes.add(player);

                circle = new Circle(c, d);
                Enemy e1 = new Enemy(Movement.x + 450, Movement.y);
                if (!e.isEmpty())
                    e.clear();
                else {
                    e.add(e1);
                    numberOfEnemies++;
                }
                flag = false;
            }
            //draw player
            snakes.get(0).draw(g);
            snakes.get(0).posX = Movement.x;
            snakes.get(0).posY = Movement.y;
            //circle corresponds to food
            circle.draw(g);
            // draw health
            h = new Health(220, 30, numberOfCollisions);
            h.draw(g);
            //Eating happens when cirlc
            if (snakes.get(0).setBounds().intersects(circle.setBounds())) {
                eaten = true;
                int[] temp = rnd();
                circle.posX = temp[0];
                circle.posY = temp[1];
                score = score + 10;//score increase by 10
                System.out.println();
            }
            //Walls: Player passes through walls
            if (snakes.get(0).posX > 550) {
                Movement.x = 100;
            } else if (snakes.get(0).posY > 550) {
                Movement.y = 100;
            } else if (snakes.get(0).posX < 100) {
                Movement.x = 550;
            } else if (snakes.get(0).posY < 100) {
                Movement.y = 550;
            }

            //Obstacles
            if (arrayObstacle[0].setBounds().intersects(snakes.get(0).setBounds())) {
                collided = true;
                numberOfCollisions++;
            }
            if (arrayObstacle[1].setBounds().intersects(snakes.get(0).setBounds())) {
                numberOfCollisions++;
                collided = true;
            }

            if (arrayObstacle[2].setBounds().intersects(snakes.get(0).setBounds())) {
                numberOfCollisions++;
                collided = true;
            }
            if (numberOfEnemies > 0) {
                for (int i = 0; i < e.size(); i++) {
                    if (e.get(i).setBounds().intersects(snakes.get(0).setBounds())) {
                        numberOfCollisions++;
                        collided = true;
                    }
                }
            }
            //Health decreases when Player collides with Obstacle
            if (collided) {
                h.numberOfCollisions = numberOfCollisions;
                h.greenValue = h.greenValue - 80;
                h.redValue = h.redValue + 20;
                h.draw(g);
                //Game Over
                if (h.bar <= 0) {
                    System.out.println("Game over!!");
                    gameOver = true;
                    velX = 0;
                    velY = 0;
                    //write score to file
                    s = new Score(score);
                    s.writeScores();
                    System.out.println();
                    System.out.println();
                    System.out.println("Print scores:");
                    //read array from file
                    tempReadArray = s.readScores().clone();
                    for (int i = 8; i < topFive.length - 5; i++) {
                        topFive[i] = "  -   " + (tempReadArray[i - 8]);
                        //System.out.println("TopFive "+ i + " : "+topFive[i]);
                    }
                    topFive[0] = "Game Over!";
                    topFive[2] = "";
                    topFive[3] = "";
                    topFive[4] = "Total Score: " + score;
                    topFive[5] = "All-time ranking: " + s.tempScores.get(0);
                    topFive[6] = "";
                    topFive[7] = "Top five all-time scores:";
                    topFive[13] = "";
                    topFive[14] = "";
                    topFive[15] = "";
                    topFive[16] = "";
                    topFive[17] = "Do you want to restart?";

                    JTextArea textArea = new JTextArea(50, 30);
                    textArea.setBackground(Color.BLACK);
                    JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    textArea.setEditable(false);
                    textArea.setWrapStyleWord(true);
                    JList list = new JList(topFive);
                    list.setPreferredSize(new Dimension(200, 200));
                    Color rgb = new Color(240, 240, 240);
                    list.setBackground(rgb);
                    sp.getViewport().add(list);
                    //Top-five scores
                    scoreBoard = new JPanel() {
                        public void paintComponent(Graphics g) {
                            g.setColor(Color.BLACK);
                            g.setFont(new Font("arial", Font.BOLD, 10));
                            g.drawString("All-time ranking: " + s.tempScores.get(0), 0, 10);
                            g.drawString("Total Score: " + score + "", 0, 20);
                            g.drawString("Top five all-time scores:", 0, 30);
                            for (int i = 0; i < topFive.length; i++) {
                                g.drawString(" - ", 5, 35 + (10 * i));
                                g.drawString("" + (topFive[i]), 25, 35 + (10 * i));
                            }

                        }
                    };
                    int reply = JOptionPane.showConfirmDialog(null, list, "GAME OVER", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.NO_OPTION) {
                        //Create objects of Feedback to obtain feedback from the user
                        one = new Feedback(15, 30);
                        two = new Feedback(30, 30);
                        three = new Feedback(45, 30);
                        four = new Feedback(60, 30);
                        five = new Feedback(75, 30);
                        FeedbackJPanel fb = new FeedbackJPanel(one, two, three, four, five);
                        MouseList mouse = new MouseList(fb);
                        fb.addMouseListener(mouse);
                        JOptionPane.showMessageDialog(null, fb, "Feedback", JOptionPane.INFORMATION_MESSAGE);
                        JOptionPane.showMessageDialog(null, "Thank you for your feedback!", "Feedback", JOptionPane.INFORMATION_MESSAGE);
                        //delete file after the game is exited
                        try {
                            Files.delete(Paths.get(FILENAME));
                            System.exit(0);
                        } catch (IOException x) {
                            System.out.println(x);
                        }

                    } else if (reply == JOptionPane.YES_OPTION) {
                        //restart
                        gameOver = false;
                        collided = false;
                        flag = true;
                        numberOfCollisions = 0;
                        Movement.x = 110;
                        Movement.y = 110;
                        score = 0;
                        life = 3;
                        snakes.clear();
                        e.clear();
                    } else
                        System.exit(0);
                }
            }
            //Score
            g.setColor(Color.BLACK);
            g.setFont(new Font("arial", Font.BOLD, 25));
            g.drawString("Score: " + score, 300, 630);
            //Enemy attack
            for (int i = 0; i < e.size(); i++) {
                e.get(i).draw(g);
            }
            if (!snakes.isEmpty()) {
                currentY = snakes.get(0).posY;
                currentX = snakes.get(0).posX;
            }
            //if the player is not dead keep attacking
            if (score % 3 == 0 && score != 0 && eaten) {
                e.add(new Enemy(Movement.x, 100));
                eaten = false;

            }
            //Enemy Movement
            if (!gameOver) {
                for (int p = 0; p < e.size(); p++) {
                    if (e.get(p).posY < currentY) {
                        if (p > 0 && p % 2 != 0) {
                            if (e.get(p).posY < circle.posY + 20) {
                                e.get(p).posY = e.get(p).posY + enemySpeed;
                            }
                        } else {
                            e.get(p).posY = e.get(p).posY + enemySpeed;
                        }
                    } else if (e.get(p).posY > currentY) {
                        e.get(p).posY = e.get(p).posY - enemySpeed;
                    } else if (e.get(p).posX < currentX) {
                        e.get(p).posX = e.get(p).posX + enemySpeed;
                    } else if (e.get(p).posX > currentX) {
                        e.get(p).posX = e.get(p).posX - enemySpeed;
                    }
                }
            }

        }
    }


    //Instance methods
    //food placed at random locations on the grid
    private int[] rnd() {
        int[] myArray = new int[2];
        Random rnd = new Random();
        int x = rnd.nextInt(400) + 100;
        int y = rnd.nextInt(400) + 100;
        myArray[0] = x;
        myArray[1] = y;
        return myArray;
    }

    //Pause game
    public void pause() {
        velX = 0;
        velY = 0;
        gameOver = true;
    }

    //Method to increment health
    public void newLife() {
        System.out.println("Bar: "+h.bar);
        System.out.println("BAR_LENGTH "+BAR_LENGTH);
        int grace = numberOfCollisions - 30;
        System.out.println("Grace "+grace);
        if ((BAR_LENGTH-grace) > (BAR_LENGTH) ){
            numberOfCollisions = 0;
            System.out.println("It is");
        }
        else
            numberOfCollisions = grace;
    }
}