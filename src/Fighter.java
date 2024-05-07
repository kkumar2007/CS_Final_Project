import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Fighter {
    private int health;
    final int MAX_HEALTH = 200;
    private int strength;
    private int x;              // Center x
    private int y;              // Center y
    private int dx;             // delta x in one time unit
    private int dy;
    public int diameter = 100;
    public FighterGameView view;
    public FighterGame game;
    private Color defendColor;
    private int defendRadius;
    private boolean isDefending;
    private Timer defendTimer;
    private String name;
    private Image walk;
    private Image walkLeft;
    public boolean facingRight = true; // Default direction
    private boolean isAttacking;
    private Image attackImage;
    private Image attackImageLeft;
    private ImageIcon walkIcon;

    public Fighter(String name, int health, int strength, int x, int y, int dx, int dy, FighterGameView view, FighterGame game) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.health = health;
        this.strength = strength;
        this.view = view;
        walk = new ImageIcon("Resources/fighter.png").getImage();
        walkLeft = new ImageIcon("Resources/fighter_left.png").getImage();
        defendColor = new Color(173, 216, 230); // Light blue color
        defendRadius = diameter + 10; // Adjust the radius as needed
        isAttacking = false;
        attackImage = new ImageIcon("Resources/attack.png").getImage();
        attackImageLeft = new ImageIcon("Resources/attack_left.png").getImage();
        this.game= game;

        isDefending = false;
        defendTimer = new Timer();
        if (name.equals("Player two")) {
            facingRight = false;
        }
        walkIcon = new ImageIcon("Resources/fighter_walk.gif");

    }


    public void defend() {
        if (!isDefending) {
            isDefending = true;

            // Start the defend timer for 3 seconds
            defendTimer.schedule(new TimerTask() {
                public void run() {
                    stopDefend();
                }
            }, 3000);
        }
    }
    public void stopAttack()
    {
        isAttacking = false;
    }

    public void stopDefend() {
        isDefending = false;
    }

    public boolean getIsDefending() {
        return isDefending;
    }
    public boolean getIsAttacking() {
        return isAttacking;
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    public void switchDirection() {
        facingRight = !facingRight;
    }

    public boolean getFacingRight() {
        return facingRight;
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
        if (!isDefending) { // Only attack if not defending
            if (Math.abs(x - opponent.x) <= diameter) {
                if (Math.abs(y - opponent.y) <= diameter) {
                    int damage = strength;
                    if (opponent.isDefending) {
                        damage /= 3;
                    }
                    opponent.defend(damage);
                    isAttacking = true;
                }
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void setDx(int num)
    {dx = num;}

    public void jump() {
        if (y >= 450) {
            dy = -50;
        }
    }

    public void move() {
        y += dy;
        dy += 15;
        if (y >= 450) {
            y = 450;
            dy = 0;
        }
        if (dx != 0 && !isDefending) {
            if (x + dx < 0 && dx < 0) {
                x = 10;
            } else if (x + dx > 800 && dx >= 0) {
                x = 790;
            } else {
                x += dx;
            }

            // Update facingRight only when actively moving left or right
            if (dx < 0) {
                facingRight = false;
            } else if (dx > 0) {
                facingRight = true;
            }
        }
        for (Platform platform : game.getPlatforms()) {
            if (checkCollision(platform)) {
                // adjust the fighter's position to be on top of the platform
                y = platform.getY()+ 10;
                dy = 0;
                break;
            }
        }
    }
    private boolean checkCollision(Platform platform) {
        if (x + diameter > platform.getX() && x < platform.getX() + platform.getWidth()) {
            if (y + diameter > platform.getY() && y < platform.getY() + platform.getHeight()) {
                return true;
            }
        }
        return false;
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
        if (!isDefending) { // Only shift if not defending
            int copy = x;
            if (x + shift <= xLow && shift < 0) {
                x = xLow;
            } else if (x + shift >= xHigh && shift > 0) {
                x = xHigh;
            } else {
                x += shift;
            }
            facingRight = (copy <= x);
        }
    }

    public void draw(Graphics g) {
        if (getHealth() <= 0) {
            // If any fighter's health is less than or equal to 0, paint the screen white
            g.setColor(Color.WHITE);
            g.drawString(getName() + " lost", 400, 300);

        }
        if (isDefending) {
            // Draw defend image if defending
            if (facingRight) {
                g.setColor(defendColor);
                g.drawOval(x , y - diameter, defendRadius, defendRadius);
            } else {
                g.setColor(defendColor);
                g.drawOval(x, y - diameter, defendRadius, defendRadius);
            }
        }
        // Draw regular image if not defending
        if (isAttacking) {
            // Draw appropriate attack image based on direction
            if (facingRight) {
                g.drawImage(attackImage, x, y - diameter, diameter, diameter, null);
            } else {
                g.drawImage(attackImageLeft, x, y - diameter, diameter, diameter, null);
            }
        } else if (!isAttacking) {
            if (facingRight) {
                g.drawImage(walk, x, y - diameter, diameter, diameter, null);
            } else {
                // Flip the image horizontally
                g.drawImage(walkLeft, x, y - diameter, diameter, diameter, null);
            }
        }
    }
}
