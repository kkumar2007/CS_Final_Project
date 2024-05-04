import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
public class Projectile {
    private int x;
    private int y;
    private int velocityX;
    private int damage;
    private int diameter; // Diameter of the projectile

    public Projectile(int x, int y, int velocityX, int damage, int diameter) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.damage = damage;
        this.diameter = diameter;
    }

    public void updatePosition() {
        // Update position based on velocity
        x += velocityX;
    }

    public void draw(Graphics g) {
        // Set color to red
        g.setColor(Color.RED);
        // Draw oval representing the projectile
        g.fillOval(x - diameter / 2, y - diameter / 2, diameter, diameter);
    }

    // Add getters and setters as needed
}
