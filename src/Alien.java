import javax.swing.*;
import java.awt.*;

public class Alien {
    private int x;
    private int y;
    /*private int width;
    private int height;

     */
     private ImageIcon icon;
    public Alien(int x, int y) {
        this.x = x;
        this.y = y;
        icon=new ImageIcon("images/alienlevel2.jpg");


    }

  /*  public int getWidth() {
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

   */

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



    public void paint(Graphics graphics,SecondLevel secondLevel) {
       /* graphics.setColor(Color.green);
        graphics.fillRect(this.x,this.y,this.width,this.height);
*/
        this.icon.paintIcon(secondLevel,graphics,this.x,this.y);

    }



}
