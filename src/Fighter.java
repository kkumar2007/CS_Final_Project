import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.util.Random;

public class Fighter {
        private int health;
        private int strength;
        private int x;              // Center x
        private int y;              // Center y
        private int dx;             // delta x in one time unit
        private int dy;
    private static final int JUMP_HEIGHT = 50;


        public Fighter(int health, int strength, int x, int y, int dx, int dy) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
            this.health = health;
            this.strength = strength;
        }
        public void defend(int damage) {
            // Reduce health by the damage received
            health -= damage;
            if (health <= 0) {
                System.out.println("I'm defeated!");
                // You can handle defeat logic here
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
        // Adjust the vertical position (y-axis) to simulate a jump
        y -= JUMP_HEIGHT; // Decrease y-coordinate to move the fighter upwards
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

            g.fillOval(x, y, 25, 25);
        }
    }
