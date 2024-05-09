import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

// Class definition for the FighterGame
public class FighterGame implements KeyListener, ActionListener {
    // Instance variables
    private FighterGameView window; // Game window
    private boolean isGameOver; // Flag indicating whether the game is over
    public int state = 0; // Game state
    private Fighter one; // First fighter
    private Fighter two; // Second fighter
    private static final int STEP_SIZE = 30; // Step size for movement
    private ArrayList<Platform> platforms; // List of platforms
    private Random random; // Random number generator

    // Constructor for FighterGame
    public FighterGame(boolean generatePlatforms) {
        // Initialize instance variables
        isGameOver = false;
        one = new Fighter("Player two", 200, 20, 550, 450, 0, 0, null, this); // Pass null temporarily
        two = new Fighter("Player one", 200, 20, 250, 450, 0, 0, null, this); // Pass null temporarily
        window = new FighterGameView(one, two, this); // Initialize window before creating fighters

        one.setView(window); // Set the view for fighter one
        two.setView(window); // Set the view for fighter two
        random = new Random();

        // Example health and strength for fighter two
        window.addKeyListener(this);
        platforms = new ArrayList<>();
        random = new Random();
        if (generatePlatforms) {
            generatePlatforms();
        }
    }

    // Method to generate platforms
    private void generatePlatforms() {
        int numPlatforms = 5; // Adjust this to generate more or fewer platforms
        int platformWidth = 100; // Width of each platform
        int spacing = 150; // Increased spacing between platforms
        int y = 330; // Adjust this value to change the vertical position of the platforms
        boolean generateVertical = random.nextBoolean(); // Randomly decide whether to generate vertical platforms
        for (int i = 0; i < numPlatforms; i++) {
            int x = i * (platformWidth + spacing) + spacing; // Adjusting the x-coordinate calculation
            int height = 20; // Adjust the height as needed
            Platform platform = new Platform(x, y, platformWidth, height);
            platforms.add(platform);
        }
    }

    // Method to get the list of platforms
    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    // Method to get the game state
    public int getState() {
        return state;
    }

    // Action performed method for ActionListener
    public void actionPerformed(ActionEvent e) {
        one.move();
        two.move();
        window.repaint();
    }

    // Method to handle key press events
    public void keyPressed(KeyEvent e) {
        // The keyCode lets you know which key was pressed
        switch(e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                state = 1;
                break;
            case KeyEvent.VK_LEFT:
                one.setDx(-STEP_SIZE);
                break;
            case KeyEvent.VK_RIGHT:
                one.setDx(STEP_SIZE);
                break;
            case KeyEvent.VK_UP:
                one.jump();
                break;
            case KeyEvent.VK_DOWN:
                one.defend();
                break;
            case KeyEvent.VK_W:
                two.jump();
                break;
            case KeyEvent.VK_A:
                two.setDx(-STEP_SIZE);
                break;
            case KeyEvent.VK_D:
                two.setDx(STEP_SIZE);
                break;
            case KeyEvent.VK_S:
                two.attack(one);
                break;
            case KeyEvent.VK_X:
                two.defend();
                break;
            case KeyEvent.VK_ENTER:
                one.attack(two);
                break;
            case KeyEvent.VK_9:
                boolean generatePlatforms = new Random().nextBoolean();
                FighterGame game = new FighterGame(generatePlatforms);
                Timer clock = new Timer(100, game);
                clock.start();
                break;
        }
        window.repaint();
    }

    // Method to handle key release events
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                one.stopDefend();
                break;
            case KeyEvent.VK_X:
                two.stopDefend();
                break;
            case KeyEvent.VK_ENTER:
                one.stopAttack();
                break;
            case KeyEvent.VK_S:
                two.stopAttack();
                break;
            case KeyEvent.VK_A:
                two.setDx(0);
                break;
            case KeyEvent.VK_D:
                two.setDx(0);
                break;
            case KeyEvent.VK_LEFT:
                one.setDx(0);
                break;
            case KeyEvent.VK_RIGHT:
                one.setDx(0);
                break;
        }
    }

    // Method to handle key typed events
    @Override
    public void keyTyped(KeyEvent e) {}

    // Main method to start the game
    public static void main(String[] args) {
        boolean generatePlatforms = new Random().nextBoolean();
        FighterGame game = new FighterGame(generatePlatforms);
        Timer clock = new Timer(100, game);
        clock.start();
    }
}
