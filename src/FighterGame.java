import java.awt.event.KeyEvent;
import java.util.Random;
import java.awt.event.KeyListener;

public class FighterGame implements KeyListener
{
    private FighterGameView window;
    private boolean isGameOver;
    private int state;
    private Fighter one;
    private Fighter two;
    private static final int STEP_SIZE = 5;

    public FighterGame() {
        window = new FighterGameView(one);
        isGameOver = false;
        state = 0;
        one = new Fighter(100, 20, 200, 200, 0, 0);
        // Example health and strength for fighter one
        two = new Fighter(100, 20, 200, 200, 0, 0);
        // Example health and strength for fighter two
        window.addKeyListener(this);
    }
    public void keyPressed(KeyEvent e) {
        // The keyCode lets you know which key was pressed
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                one.shiftX(-STEP_SIZE, 0, FighterGameView.WIDTH);
                break;
            case KeyEvent.VK_RIGHT:
                one.shiftX(STEP_SIZE, 0, FighterGameView.WIDTH);
                break;
            case KeyEvent.VK_UP:
                int topOfPane = window.getInsets().top;
                one.jump();
                break;
            case KeyEvent.VK_W: // Cause the ball to jump to a random new location
                two.jump();
                break;
            case KeyEvent.VK_A: // Make the ball smaller - multiply its diameter by 1/2
                two.shiftX(-STEP_SIZE, 0, FighterGameView.WIDTH);
                break;
            case KeyEvent.VK_D: // Change the color
                two.shiftX(STEP_SIZE, 0, FighterGameView.WIDTH);
                break;
        }
        window.repaint();
    }
    public void keyReleased(KeyEvent e) {
        // This method is required by KeyListener but we don't need to do anything here
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // This method is required by KeyListener but we don't need to do anything here
    }

    public static void main(String[] args) {
        FighterGame game = new FighterGame();
    }
}
