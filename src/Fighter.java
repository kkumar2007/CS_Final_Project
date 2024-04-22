import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Fighter {
        private int health;
        private int strength;
        private int x;              // Center x
        private int y;              // Center y
        private int dx;             // delta x in one time unit
        private int dy;
        public int diameter = 25;
        private static final int JUMP_HEIGHT = 400;
        private static final int JUMP_SPEED = 20;
        private  FighterGameView view;


        public Fighter(int health, int strength, int x, int y, int dx, int dy, FighterGameView view) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
            this.health = health;
            this.strength = strength;
            this.view = view;

        }
        public void setView(FighterGameView view) {
            this.view = view;
        }
        public void defend(int damage) {
            // Reduce health by the damage received
            health -= damage;
            if (health <= 0) {
                System.out.println("I'm defeated!");
            }
        }
        public void setX(int where)
        {
            x = where;
        }
        public void setY(int where)
        {
            y = where;
        }
    public void jump() {
        dy = 10;
        }
    public void move() {
        y+= dy;
        dy++;
    }

        // Getter for health
        public int getHealth() {
            return health;
        }

        // Getter for strength
        public int getStrength() {
            return strength;
        }
        public void shiftX(int shift, int xLow, int xHigh) {
            if (x + shift <= xLow && shift < 0) {
                x = xLow;
            }
            else if (x + shift >= xHigh && shift > 0) {
                x = xHigh;
            }
            else {
                x += shift;
            }
        }
        public void draw(Graphics g) {
            Random random = new Random();
            int red = random.nextInt(256); // Generates a random value between 0 and 255 for red
            int green = random.nextInt(256); // Generates a random value between 0 and 255 for green
            int blue = random.nextInt(256); // Generates a random value between 0 and 255 for blue

            Color randomColor = new Color(red, green, blue);
            g.setColor(randomColor);

            g.fillOval(x - diameter, y - diameter, diameter, diameter);
        }
    }
