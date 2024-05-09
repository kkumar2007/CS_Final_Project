import javax.swing.*;
import java.awt.*;

// Class definition for the main game window
public class FighterGameView extends JFrame {

    // Constants for window dimensions
    public static int HEIGHT = 600, WIDTH = 800;

    // Instance variables
    private FighterGame game; // Reference to the game logic
    private Image background; // Background image
    private Image start; // Start screen image
    public Fighter f; // First fighter
    public Fighter f2; // Second fighter
    private Image platformImage; // Image for platforms

    // Constructor for initializing the game view
    public FighterGameView(Fighter fighter, Fighter f2, FighterGame game) {
        // Assigning parameters to instance variables
        f = fighter;
        this.f2 = f2;
        this.game = game;

        // Loading images for background, start screen, and platforms
        background = new ImageIcon("Resources/back.png").getImage();
        start = new ImageIcon("Resources/2- Player.png").getImage();
        platformImage = new ImageIcon("Platform.png").getImage();

        // Setting up JFrame properties
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Fighter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    // Custom painting method to render game components
    public void paint(Graphics g) {
        // Check game state and render appropriate components
        if (game.getState() == 0) {
            // Display start screen
            g.drawImage(start, 0, 0, WIDTH, HEIGHT, this);
        } else if (game.getState() == 1) {
            // Display gameplay screen
            g.drawImage(background, 0, 0, WIDTH, HEIGHT, this); // Background
            f.draw(g); // Draw first fighter
            f2.draw(g); // Draw second fighter

            // Draw health bars for both fighters
            drawHealthBar(g, f, false); // Left health bar for fighter 1
            drawHealthBar(g, f2, true); // Right health bar for fighter 2

            // Draw platforms
            for (Platform platform : game.getPlatforms()) {
                g.setColor(Color.WHITE);
                g.fillRect(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight());
            }

            // Check for game over conditions and display win screen
            if (f.getHealth() <= 0) {
                displayWinScreen(g, f2.getName()); // Display win screen for player 2
            } else if (f2.getHealth() <= 0) {
                displayWinScreen(g, f.getName()); // Display win screen for player 1
            }
        }
    }

    // Method to display win screen
    private void displayWinScreen(Graphics g, String winnerName) {
        // Set background color
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Set text color and font
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));

        // Display win message
        String winMessage = winnerName + " wins! (9 to play again)";
        int messageWidth = g.getFontMetrics().stringWidth(winMessage);
        int x = (WIDTH - messageWidth) / 2;
        int y = HEIGHT / 2;
        g.drawString(winMessage, x, y);
    }

    // Method to draw health bars for fighters
    private void drawHealthBar(Graphics g, Fighter fighter, boolean isLeft) {
        //new way of using code that I learnt from the Java manual
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
