/**
 * Created by robert on 6/24/17.
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Enemy {
    private double x;
    private double y;
    private int r;

    private double dx;
    private double dy;
    private double rad;
    private double speed;

    private int health;
    private int type;
    private int rank;

    private Color color1;

    private boolean ready;
    private boolean dead;

    private BufferedImage img = null;

    public Enemy(int type, int rank){
        this.rank = rank;
        this.type = type;

        if(type ==1){
            color1 = Color.BLUE;
            if(rank == 1){
                speed = 5;
                r = 15;
                health = 1;
                try {
                    img = ImageIO.read(new File("/home/robert/Documents/wasteland/src/res/e1.png"));
                } catch (IOException e) {System.out.println("IO Error");}
            }
            if(rank == 2){
                color1 = Color.PINK.darker();
                speed = 7;
                r = 15;
                health = 2;
                try {
                    img = ImageIO.read(new File("/home/robert/Documents/wasteland/src/res/e2.png"));
                } catch (IOException e) {System.out.println("IO Error");}
            }
            if(rank == 3){
                color1 = Color.ORANGE;
                speed = 3;
                r = 20;
                health = 4;
                try {
                    img = ImageIO.read(new File("/home/robert/Documents/wasteland/src/res/e3.png"));
                } catch (IOException e) {System.out.println("IO Error");}
            }
            if(rank == 4){
                color1 = Color.GREEN.darker();
                speed = 14;
                r = 7;
                health = 2;
                try {
                    img = ImageIO.read(new File("/home/robert/Documents/wasteland/src/res/e4.png"));
                } catch (IOException e) {System.out.println("IO Error");}
            }
        }
        x = Math.random() * GamePanel.WIDTH / 2 + GamePanel.WIDTH / 4;
        y = -r;

        double angle = Math.random() * 140 + 20;
        rad = Math.toRadians(angle);

        dx = Math.cos(rad) * speed;
        dy = Math.sin(rad) * speed;

        ready = false;
        dead = false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public int getType() {
        return type;
    }

    public int getRank() {
        return rank;
    }

    public boolean isDead() {
        return dead;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
        this.dx = Math.cos(rad) * speed;
        this.dy = Math.sin(rad) * speed;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void hit(double d){
        health -= d;
        if(health <= 0){
            dead = true;
        }
    }

    public void update(){
        x += dx;
        y += dy;

        if(!ready){
            if(x > r && x < GamePanel.WIDTH - r && y > r && y < GamePanel.HEIGHT - r ){
                ready = true;
            }
        }

        if(x > GamePanel.WIDTH - r && dx > 0) dx = -dx;
        if(y > GamePanel.HEIGHT - r && dy > 0) dy = -dy;
        if(x < r && dx < 0) dx = -dx;
        if(y < r && dy < 0) dy = -dy;
    }

    public void draw(Graphics2D g){
        //g.setColor(color1);
        //g.fillOval((int) (x-r), (int) (y-r), 2*r, 2*r);
        //g.setStroke(new BasicStroke(3));
        //g.setColor(color1.darker());
        //g.drawOval((int) (x-r), (int) (y-r), 2*r, 2*r);
        //g.setStroke(new BasicStroke(1));
        g.drawImage(img, (int)x-r, (int)y-r, null);
    }
}
