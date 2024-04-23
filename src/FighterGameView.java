import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FighterGameView extends JFrame
{
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
    }


}
