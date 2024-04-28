import java.awt.event.KeyEvent;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class FighterGame implements KeyListener, ActionListener
{
    private FighterGameView window;
    private boolean isGameOver;
    public int state = 0;
    private Fighter one;
    private Fighter two;
    private static final int STEP_SIZE = 30;

    public FighterGame() {
        isGameOver = false;
        one = new Fighter("Player two", 200, 20, 550, 450, 0, 0, null); // pass null temporarily
        two = new Fighter("Player one", 200, 20, 250, 450, 0, 0, null); // pass null temporarily
        window = new FighterGameView(one, two, this); // Initialize window before creating fighters

        one.setView(window); // Set the view for fighter one
        two.setView(window); // Set the view for fighter two


        // Example health and strength for fighter two
        window.addKeyListener(this);

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
                one.shiftX(-STEP_SIZE, 0, FighterGameView.WIDTH);
                break;
            case KeyEvent.VK_RIGHT:
                one.shiftX(STEP_SIZE, 0, FighterGameView.WIDTH);
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
                two.shiftX(-STEP_SIZE, 0, FighterGameView.WIDTH);
                break;
            case KeyEvent.VK_D: //move
                two.shiftX(STEP_SIZE, 0, FighterGameView.WIDTH);
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
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public static void main(String[] args) {
        FighterGame game = new FighterGame();
        Timer clock = new Timer(100, game);
        clock.start();
    }
}
