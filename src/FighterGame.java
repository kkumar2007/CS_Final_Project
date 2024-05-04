import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;



public class FighterGame implements KeyListener, ActionListener
{
    private FighterGameView window;
    private boolean isGameOver;
    public int state = 0;
    private Fighter one;
    private Fighter two;
    private static final int STEP_SIZE = 30;
    private ArrayList<Platform> platforms;
    private Random random;

    public FighterGame() {
        isGameOver = false;
        one = new Fighter("Player two", 200, 20, 550, 450, 0, 0, null, this); // pass null temporarily
        two = new Fighter("Player one", 200, 20, 250, 450, 0, 0, null, this); // pass null temporarily
        window = new FighterGameView(one, two, this); // Initialize window before creating fighters

        one.setView(window); // Set the view for fighter one
        two.setView(window); // Set the view for fighter two



        // Example health and strength for fighter two
        window.addKeyListener(this);
        platforms = new ArrayList<>();
        random = new Random();
        generatePlatforms();

    }
    private void generatePlatforms() {
        int numPlatforms = 5; // adjust this to generate more or fewer platforms
        for (int i = 0; i < numPlatforms; i++) {
            int x = random.nextInt(FighterGameView.WIDTH - 100); // adjust the width to avoid platforms going off-screen
            int y = random.nextInt(FighterGameView.HEIGHT - 200) + 200; // adjust the height to ensure platforms are below the top of the screen
            int width = random.nextInt(100) + 50; // adjust the width to make platforms varying sizes
            int height = 20; // adjust the height to make platforms thicker or thinner
            Platform platform = new Platform(x, y, width, height);
            platforms.add(platform);
        }
    }
    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public int getState()
    {
        return state;
    }
    public void actionPerformed(ActionEvent e)
    {
        one.move();
        two.move();

        window.repaint();
    }

    public void keyPressed(KeyEvent e) {
        // The keyCode lets you know which key was pressed
        switch(e.getKeyCode())
        {
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
            case KeyEvent.VK_DOWN: // Change the color
                one.defend();
                break;
            case KeyEvent.VK_W: // Cause the ball to jump to a random new location
                two.jump();
                break;
            case KeyEvent.VK_A: //
                two.setDx(-STEP_SIZE);
                break;
            case KeyEvent.VK_D: //move
                two.setDx(STEP_SIZE);
                break;
            case KeyEvent.VK_S: // Change the color
                two.attack(one);
                break;
            case KeyEvent.VK_X: // Change the color
                two.defend();
                break;
            case KeyEvent.VK_ENTER: // Change the color
                one.attack(two);
                break;

        }
        window.repaint();
    }
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_DOWN: // Change the color
                one.stopDefend();
                break;
            case KeyEvent.VK_X: // Change the color
                two.stopDefend();
                break;
            case KeyEvent.VK_ENTER: // Change the color
                one.stopAttack();
                break;
            case KeyEvent.VK_S: // Change the color
                two.stopAttack();
                break;
            case KeyEvent.VK_A: //
                two.setDx(0);
                break;
            case KeyEvent.VK_D: //move
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

    @Override
    public void keyTyped(KeyEvent e)
    {

    }
    public static void main(String[] args) {
        FighterGame game = new FighterGame();
        Timer clock = new Timer(100, game);
        clock.start();
    }
}
