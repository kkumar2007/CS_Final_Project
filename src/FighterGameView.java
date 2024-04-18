import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class FighterGameView extends JFrame
{
    private final int HEIGHT = 600, WIDTH = 800;

    public FighterGameView() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Street Fighter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }

    public static void main(String[] args) {
        new FighterGameView();
    }

}
