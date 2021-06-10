import java.awt.*;

public class Alien implements Paintable {
    private int x;
    private int y;
    private int width;
    private int height;

    public Alien(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }



    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(Color.green);
        graphics.fillRect(this.x,this.y,this.width,this.height);


    }

    @Override
    public void move(int direction) {

    }
}
