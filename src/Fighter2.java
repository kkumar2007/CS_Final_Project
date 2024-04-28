import javax.swing.*;
import java.awt.*;

public class Fighter2
{
    private int health;
    final int MAX_HEALTH = 200;
    private int strength;
    private int x;              // Center x
    private int y;              // Center y
    private int dx;             // delta x in one time unit
    private int dy;
    public int diameter = 100;
    private static final int JUMP_HEIGHT = 400;
    private static final int JUMP_SPEED = 20;
    private  FighterGameView view;
    private String name;

    private Image walk;
    private Image walkLeft;
    public boolean facingRight = true; // Default direction
    public Fighter2(String name, int health, int strength, int x, int y, int dx, int dy, FighterGameView view) {
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
    }
}
