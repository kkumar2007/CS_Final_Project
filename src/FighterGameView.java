import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class FighterGameView extends JFrame
{
    public static int HEIGHT = 600, WIDTH = 800;
    private FighterGameView game;

    private Image background;
    public Fighter f;

    public FighterGameView(Fighter fighter) {

        f = fighter;
        this.game = game;
        background = new ImageIcon("Resources/Cards/amiga bisonj8.png").getImage();



        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Fighter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, this);
        f.draw(g);
    }

    public static void main(String[] args)
    {
        Fighter one = new Fighter(100, 20, 200, 200, 0, 0);
        FighterGameView view = new FighterGameView(one);
    }


}
