import java.awt.*;

public class Enemy implements Paintable {
    private int x;
    private int y;
    private int width;
    private int height;
    private  int speedX = 3;
    private  int seedY=3;

    public Enemy(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
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

    public  int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
       speedX = speedX;
    }

    public  int getSeedY() {
        return seedY;
    }

    public void setSeedY(int seedY) {
        seedY = seedY;
    }


    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect(this.getX(),this.getY(),this.getWidth(),this.getHeight());
    }

    @Override
    public void move(int direction) {

        this.x+=speedX;
        this.y+=seedY;
        if (y>=Constants.WINDOW_HEIGHT-this.height || y <= Constants.END_FRAME+this.height){
            seedY=-seedY;
        }

        if (x>=Constants.WINDOW_WIDTH-this.width || x<=Constants.END_FRAME+this.width){
            speedX=-speedX;
        }

    }


    }
