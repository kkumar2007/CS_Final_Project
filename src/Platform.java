//Kavan Kumar
//CS2
//Mr. Blick
public class Platform {
    private int x; // The x-coordinate of the platform
    private int y; // The y-coordinate of the platform
    private int width; // The width of the platform
    private int height; // The height of the platform
    //constructs the platform
    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    //returns x-coordinate
    public int getX() {
        return x;
    }
    //returns y coordinate
    public int getY() {
        return y;
    }
    //return width
    public int getWidth() {
        return width;
    }
    //return height
    public int getHeight() {
        return height;
    }
}
