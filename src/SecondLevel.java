import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SecondLevel extends JPanel {
   private Player player;
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    private Enemy enemy4;
    private Alien alien;

    public SecondLevel(Player player) {

        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);

        setBounds(0,0,Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
       // this.player = new Player(Constants.PLAYER_X-1, Constants.PLAYER_Y-2, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        this.player=player;
        this.player.setX(Constants.PLAYER_X);
        this.enemy1 = new Enemy(Constants.ENEMY1_X, Constants.ENEMY1_Y, Constants.ENEMY1_SIZE * 2, Constants.ENEMY1_SIZE * 2);
        this.enemy2 = new Enemy(Constants.ENEMY2_X, Constants.ENEMY2_Y, Constants.ENEMY2_SIZE + 2, Constants.ENEMY2_SIZE + 2);
        this.enemy3 = new Enemy(Constants.ENEMY3_X, Constants.ENEMY3_Y, Constants.ENEMY3_SIZE + 10, Constants.ENEMY3_SIZE + 10);
        this.enemy4 = new Enemy(Constants.ENEMY4_X, Constants.ENEMY4_Y, Constants.ENEMY4_SIZE - 10, Constants.ENEMY4_SIZE - 10);
        this.alien = new Alien(Constants.ALIEN_X, Constants.ALIEN_Y, Constants.ALIEN_SIZE, Constants.ALIEN_SIZE);
        //mainGameLoop();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.player.paint(g);
        this.enemy1.paint(g);
        this.enemy2.paint(g);
        this.enemy3.paint(g);
        this.enemy4.paint(g);
        this.alien.paint(g);


    }

    public void mainGameLoop() {
        new Thread(() -> {
            while (true){

                try {
                    this.enemy1.move(random());
                    this.enemy2.move(random());
                    this.enemy3.move(random());
                    this.enemy4.move(random());
                    startOverAfterCollision();
                    isWinner();
                    repaint();
                    Thread.sleep(Constants.FRAMES_SPEED);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public int random() {
        Random random = new Random();

        return random.nextInt(2);
    }

    public boolean collision(Player player, Enemy enemy) {
        Rectangle playerRectangle = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        Rectangle enemyRectangle = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
        boolean collision = playerRectangle.intersects(enemyRectangle);
        return collision;


    }

    public boolean playerMeetAlien(Player player, Alien alien) {
        Rectangle playerRectangle = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        Rectangle alienRectangle = new Rectangle(alien.getX(), alien.getY(), alien.getWidth(), alien.getHeight());
        boolean meeting = playerRectangle.intersects(alienRectangle);
        return meeting;

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Enemy getEnemy1() {
        return enemy1;
    }

    public void setEnemy1(Enemy enemy1) {
        this.enemy1 = enemy1;
    }

    public Enemy getEnemy2() {
        return enemy2;
    }

    public void setEnemy2(Enemy enemy2) {
        this.enemy2 = enemy2;
    }

    public Enemy getEnemy3() {
        return enemy3;
    }

    public void setEnemy3(Enemy enemy3) {
        this.enemy3 = enemy3;
    }

    public Enemy getEnemy4() {
        return enemy4;
    }

    public void setEnemy4(Enemy enemy4) {
        this.enemy4 = enemy4;
    }

    public Alien getAlien() {
        return alien;
    }

    public void setAlien(Alien alien) {
        this.alien = alien;
    }

    public void startOverAfterCollision() {
        if (collision(player, enemy1) || collision(player, enemy2) || collision(player, enemy3) || collision(player, enemy4)) {
            JFrame startOverButton = new JFrame();
            startOverButton.setLocationRelativeTo(null);
            JOptionPane.showMessageDialog(startOverButton, "You are died!!,Please press OK if you want to stars Over");
            this.player.setX(Constants.PLAYER_X);
            this.player.setY(Constants.PLAYER_Y);
            this.enemy1.setX(Constants.ENEMY1_X);
            this.enemy1.setY(Constants.ENEMY1_Y);
            this.enemy2.setX(Constants.ENEMY2_X);
            this.enemy2.setY(Constants.ENEMY1_Y);
            this.enemy3.setX(Constants.ENEMY3_X);
            this.enemy3.setY(Constants.ENEMY3_Y);
            this.enemy4.setX(Constants.ENEMY4_X);
            this.enemy4.setY(Constants.ENEMY4_Y);

        }



    }
    private boolean isWinner(){
        if (playerMeetAlien(player,alien)){
            JFrame victoryButton = new JFrame();
            victoryButton.setLocationRelativeTo(null);

            return true;


        }
        return false;
    }
}
