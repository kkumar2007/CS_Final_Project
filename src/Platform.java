import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Platform {
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;

    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = new ImageIcon("Resources/Platform.png").getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
