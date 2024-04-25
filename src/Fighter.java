import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Fighter {
        private int health;
        final int MAX_HEALTH = 100;
        private int strength;
        private int x;              // Center x
        private int y;              // Center y
        private int dx;             // delta x in one time unit
        private int dy;
        public int diameter = 100;
        private static final int JUMP_HEIGHT = 400;
        private static final int JUMP_SPEED = 20;
        private  FighterGameView view;
        private Color color;
        private String name;

        private Image walk;
    private Image walkLeft;
    public boolean facingRight = true; // Default direction




    public Fighter(String name, int health, int strength, int x, int y, int dx, int dy, FighterGameView view) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
            this.health = health;
            this.strength = strength;
            this.view = view;
            getColor();
            walk = new ImageIcon("Resources/fighter.png").getImage();
            walkLeft = new ImageIcon("Resources/fighter_left.png").getImage();

        }
        public String getName()
        {
            return name;
        }
    public int getMaxHealth() {
        return MAX_HEALTH;
    }
    public void switchDirection() {
        facingRight = !facingRight;
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
    public void attack(Fighter opponent) {
        if (Math.abs(x - opponent.x) <= diameter) {
            // Check if the fighters are touching vertically
            if (Math.abs(y - opponent.y) <= diameter) {
                int damage = strength; // For simplicity, let's say the damage is equal to the attacker's strength
                opponent.defend(damage);
            }
        }
    }
        public int getX()
        {
            return x;
        }
        public int getY()
        {
            return y;
        }
    public void jump() {
            if(y>=450)
            {
                dy = -50;
            }
        }
    public void move() {
        y+= dy;
        dy+=10;
        if(y>=450)
        {
            y = 450;
            dy=0;
        }
        if (facingRight) {
            x += dx;
        } else {
            x -= dx;
        }

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
        public void getColor()
        {
            Random random = new Random();
            int red = random.nextInt(256); // Generates a random value between 0 and 255 for red
            int green = random.nextInt(256); // Generates a random value between 0 and 255 for green
            int blue = random.nextInt(256); // Generates a random value between 0 and 255 for blue

            color = new Color(red, green, blue);
        }
        public void draw(Graphics g) {
            g.setColor(color);
            if (getHealth() <= 0)
            {
                // If any fighter's health is less than or equal to 0, paint the screen white
                g.setColor(Color.WHITE);
                g.drawString(getName() +" lost", 400, 300);
            }
            else
            {
                if (facingRight) {
                    g.drawImage(walk, x - diameter, y - diameter, diameter, diameter, null);
                } else {
                    // Flip the image horizontally
                    g.drawImage(walkLeft, x, y - diameter, -diameter, diameter, null);
                }
            }
        }
    }
