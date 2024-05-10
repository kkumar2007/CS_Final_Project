//Kavan Kumar
//CS2
//Mr. Blick
import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

// Class definition for a Fighter in the game
public class Fighter {
    // Instance variables
    private int health; // Fighter's health
    final int MAX_HEALTH = 200; // Maximum health value
    private int strength; // Fighter's strength
    private int x; // Center x-coordinate
    private int y; // Center y-coordinate
    private int dx; // Change in x-coordinate in one time unit
    private int dy; // Change in y-coordinate in one time unit
    public int diameter = 100; // Diameter of the fighter
    public FighterGameView view; // Reference to the game view
    public FighterGame game; // Reference to the game logic
    private Color defendColor; // Color for defending
    private int defendRadius; // Radius for defending
    private boolean isDefending; // Flag indicating whether the fighter is defending
    private Timer defendTimer; // Timer for defending
    private String name; // Name of the fighter
    private Image walk; // Image for walking (facing right)
    private Image walkLeft; // Image for walking (facing left)
    public boolean facingRight = true; // Default direction (facing right)
    private boolean isAttacking; // Flag indicating whether the fighter is attacking
    private boolean isJumping; // Flag indicating whether the fighter is jumping
    private Image attackImage; // Image for attacking (facing right)
    private Image attackImageLeft; // Image for attacking (facing left)
    private ImageIcon walkIcon; // Icon for walking animation
    private long lastAttackTime; // Timestamp of the last attack
    private static final long ATTACK_COOLDOWN = 1000; // Cooldown period for attacks (in milliseconds)

    // Constructor to initialize a Fighter object
    public Fighter(String name, int health, int strength, int x, int y, int dx, int dy, FighterGameView view, FighterGame game) {
        // Initialize instance variables
        this.name = name;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.health = health;
        this.strength = strength;
        this.view = view;
        walk = new ImageIcon("Resources/fighter.png").getImage(); // Load walk image (facing right)
        walkLeft = new ImageIcon("Resources/fighter_left.png").getImage(); // Load walk image (facing left)
        defendColor = new Color(173, 216, 230); // Light blue color for defending
        defendRadius = diameter + 10; // Adjusted radius for defending
        isAttacking = false;
        isJumping = false;
        attackImage = new ImageIcon("Resources/attack.png").getImage(); // Load attack image (facing right)
        attackImageLeft = new ImageIcon("Resources/attack_left.png").getImage(); // Load attack image (facing left)
        this.game = game;
        lastAttackTime = 0; // Initialize last attack time

        isDefending = false;
        defendTimer = new Timer();

        // Load specific resources for Player two fighter
        if (name.equals("Player two")) {
            facingRight = false;
            walk = new ImageIcon("Resources/walk2right.png").getImage();
            walkLeft = new ImageIcon("Resources/walk2left2.png").getImage();
            attackImage = new ImageIcon("Resources/attack2right.png").getImage();
            attackImageLeft = new ImageIcon("Resources/walk2attack2.png").getImage();
        }
        walkIcon = new ImageIcon("Resources/fighter_walk.gif");
    }

    // Method to initiate defending
    public void defend() {
        if (!isDefending) {
            isDefending = true;

            // Start the defend timer for 3 seconds
            //learnt how to implement and do it through the CS2 tutors and through Java manual on Timers to know how to set it up
            defendTimer.schedule(new TimerTask() {
                public void run() {
                    stopDefend();
                }
            }, 3000);
        }
    }

    // Method to stop attacking
    public void stopAttack() {
        isAttacking = false;
    }

    // Method to stop defending
    public void stopDefend() {
        isDefending = false;
    }


    // Getter for fighter's name
    public String getName() {
        return name;
    }

    // Getter for maximum health
    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    // Setter for FighterGameView
    public void setView(FighterGameView view) {
        this.view = view;
    }

    // Method to handle defending with damage
    public void defend(int damage) {
        // Reduce health by the damage received
        health -= damage;
        if (health <= 0) {
            System.out.println("I'm defeated!");
        }
    }

    // Method to handle attacking
    public void attack(Fighter opponent) {
        if (!isDefending && System.currentTimeMillis() - lastAttackTime >= ATTACK_COOLDOWN) {
            // Allow attack only if not defending and cooldown period has elapsed
            if (Math.abs(x - opponent.x) <= diameter) {
                if (Math.abs(y - opponent.y) <= diameter) {
                    int damage = strength;
                    if (opponent.isDefending) {
                        damage /= 3;
                    }
                    opponent.defend(damage);
                    isAttacking = true;
                    //learnt this code from CS2 tutors
                    lastAttackTime = System.currentTimeMillis(); // Update last attack time
                }
            }
        }
    }

    // Getter for x-coordinate
    public int getX() {
        return x;
    }

    // Getter for y-coordinate
    public int getY() {
        return y;
    }

    // Setter for dx (change in x-coordinate)
    public void setDx(int num) {
        dx = num;
    }

    // Method to handle jumping
    public void jump() {
        if (y >= 450) {
            dy = -50;
        }
    }

    // Method to handle movement
    public void move() {
        y += dy;
        dy += 15;
        for (Platform platform : game.getPlatforms()) {
            if (checkCollision(platform)) {
                // Adjust the fighter's position to be on top of the platform
                y = platform.getY() + 10;
                dy = 0;
                break;
            }
        }
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
    }

    // Method to check collision with a platform
    private boolean checkCollision(Platform platform) {
        if (x + diameter + 10 > platform.getX() && x < platform.getX() + platform.getWidth()) {
            if (y + diameter > platform.getY() && y < platform.getY() + platform.getHeight()) {
                isJumping = false;
                return true;
            }
        }
        return false;
    }

    // Getter for health
    public int getHealth() {
        return health;
    }


    // Method to draw the fighter
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
                g.drawOval(x, y - diameter, defendRadius, defendRadius);
            } else {
                g.setColor(defendColor);
                g.drawOval(x, y - diameter, defendRadius, defendRadius);
            }
        }
        // Draw appropriate image based on actions
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
