import javax.swing.*;
import java.awt.*;

public class FighterGameView extends JFrame {
    public static int HEIGHT = 600, WIDTH = 800;
    private FighterGame game;


    private Image background;
    private Image start;
    public Fighter f;
    public Fighter f2;


    public FighterGameView(Fighter fighter, Fighter f2, FighterGame game) {

        f = fighter;
        this.f2 = f2;
        this.game = game;
        background = new ImageIcon("Resources/back.png").getImage();
        start = new ImageIcon("Resources/2- Player.png").getImage();

        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Fighter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public void paint(Graphics g) {

        if(game.getState() == 0)
        {
            g.drawImage(start, 0, 0, WIDTH, HEIGHT, this);
        }

        else if(game.getState() == 1)
        {
            g.drawImage(background, 0, 0, WIDTH, HEIGHT, this);
            f.draw(g);
            f2.draw(g);

            drawHealthBar(g, f, false); // Left health bar for fighter 1
            drawHealthBar(g, f2, true);
            for (Platform platform : game.getPlatforms()) {
                g.setColor(Color.WHITE);
                g.fillRect(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight());
            }
            if (f.getHealth() <= 0) {
                displayWinScreen(g, f2.getName()); // Display win screen for player 2
            } else if (f2.getHealth() <= 0) {
                displayWinScreen(g, f.getName()); // Display win screen for player 1
            }
        }
    }
    private void displayWinScreen(Graphics g, String winnerName) {
        // Set background color
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Set text color and font
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));

        // Display win message
        String winMessage = winnerName + " wins!";
        int messageWidth = g.getFontMetrics().stringWidth(winMessage);
        int x = (WIDTH - messageWidth) / 2;
        int y = HEIGHT / 2;
        g.drawString(winMessage, x, y);
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

