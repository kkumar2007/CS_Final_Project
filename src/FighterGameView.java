import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FighterGameView extends JFrame {
    public static int HEIGHT = 600, WIDTH = 800;
    private FighterGameView game;

    private Image background;
    public Fighter f;
    public Fighter f2;

    public FighterGameView(Fighter fighter, Fighter f2) {

        f = fighter;
        this.f2 = f2;
        this.game = game;
        background = new ImageIcon("Resources/back.png").getImage();

        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Fighter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, this);
        f.draw(g);
        f2.draw(g);

        drawHealthBar(g, f, false); // Left health bar for fighter 1
        drawHealthBar(g, f2, true);
    }
    private void drawHealthBar(Graphics g, Fighter fighter, boolean isLeft) {
        int barX = isLeft ? 20 : WIDTH - 220; // X-coordinate for left or right health bar
        int barY = 20; // Y-coordinate for both health bars
        int barWidth = 200; // Width of health bar
        int barHeight = 20; // Height of health bar

        // Draw health bar outline
        g.setColor(Color.BLACK);
        g.drawRect(barX, barY, barWidth, barHeight);

        // Calculate health bar fill width based on fighter's current health
        int fillWidth = (int) (((double) fighter.getHealth() / fighter.getMaxHealth()) * barWidth);

        // Draw health bar fill
        g.setColor(Color.GREEN);
        g.fillRect(barX, barY, fillWidth, barHeight);
    }
}

