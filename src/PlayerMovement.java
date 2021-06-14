import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerMovement implements KeyListener {
    private Player player;

    public PlayerMovement(Player player){
        this.player=player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_RIGHT:
                if (this.player.getX() < Constants.WINDOW_WIDTH - Constants.PLAYER_WIDTH) {
                    this.player.move(Constants.MOVE_RIGHT);
                }
                break;
            case KeyEvent.VK_LEFT:
                if (this.player.getX() >= Constants.END_FRAME) {
                    this.player.move(Constants.MOVE_LEFT);
                }

                break;
            case KeyEvent.VK_UP:
                if (this.player.getY() - Constants.PLAYER_HEIGHT+22 >= Constants.END_FRAME)
                    this.player.move(Constants.MOVE_UP);


                break;

            case KeyEvent.VK_DOWN:
                if (this.player.getY() <= Constants.WINDOW_HEIGHT - 70) {
                    this.player.move(Constants.MOVE_DOWN);
                    break;
                }



                // if( this.player.getY() - Constants.WINDOW_HEIGHT  => Constants.END_FRAME)
               // if (this.player.getY() Constants.PLAYER_WIDTH <= Constants.END_FRAME){
                    //   if (this.player.getY() <= Constants.WINDOW_WIDTH-Constants.PLAYER_WIDTH) {



        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
