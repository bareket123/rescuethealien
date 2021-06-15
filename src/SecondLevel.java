import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SecondLevel extends JPanel  {
    private Player player;
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    private Enemy enemy4;
    private Enemy enemy5;
    private Enemy enemy6;
    private Enemy enemy7;
    private Alien alien;
    private int sceneId;
    //private FirstLevel firstLevel;
    //private GameMain gameMain;
    //private JButton resetButton;
    private ImageIcon winnerIcon=new ImageIcon("images/winner.gif");
    private ImageIcon icon = new ImageIcon("images/spacesecond.gif");

    public SecondLevel(Player player, Enemy enemy1, Enemy enemy2, Enemy enemy3, Enemy enemy4) {
        setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.setDoubleBuffered(true);
        // this.player = new Player(Constants.PLAYER_X-1, Constants.PLAYER_Y-2, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        this.player = player;
        this.player.setX(Constants.PLAYER_X);
        this.enemy1 = enemy1;
        this.enemy2 = enemy2;
        this.enemy3 = enemy3;
        this.enemy4 = enemy4;
        this.enemy5 = new Enemy(Constants.ENEMY5_X, Constants.ENEMY5_Y, Constants.ENEMY_SIZE,  Constants.ENEMY_SIZE );
        this.enemy6 = new Enemy(Constants.ENEMY6_X, Constants.ENEMY6_Y,  Constants.ENEMY_SIZE,  Constants.ENEMY_SIZE);
        this.enemy7 = new Enemy(Constants.ENEMY7_X, Constants.ENEMY7_Y,  Constants.ENEMY_SIZE,  Constants.ENEMY_SIZE);
        this.alien = new Alien(Constants.ALIEN_X, Constants.ALIEN_Y);
        sceneId=Constants.SECOND_LEVEL;
        //mainGameLoop();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        switch (this.sceneId){
            case Constants.SECOND_LEVEL:
        Image background = icon.getImage();
        g.drawImage(background, Constants.END_FRAME, Constants.END_FRAME, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, null);
        this.player.paint(g);
        this.enemy1.paint(g);
        this.enemy2.paint(g);
        this.enemy3.paint(g);
        this.enemy4.paint(g);
        this.enemy5.paint(g);
        this.enemy6.paint(g);
        this.enemy7.paint(g);

                alien.paint(g, this);
        break;
            case Constants.RESTART_THE_GAME:
                FirstLevel firstLevel=new FirstLevel();
                firstLevel.paint(g);
                //firstLevel.mainGameLoop();
                this.add(firstLevel);
                /*
                Image winnerBackground=winnerIcon.getImage();
                g.drawImage(winnerBackground,Constants.END_FRAME,Constants.END_FRAME, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, null);
                JButton restartButton=new JButton();
                restartButton.setVisible(true);
                restartButton.setText("START OVER");
                restartButton.setSize(1000,800);
                restartButton.setLocation(500,500);
                restartButton.addActionListener(e -> {

                    System.exit(0);

                });
                this.add(restartButton);


                 */
                break;

    }



    }


    public void mainGameLoop() {
        new Thread(() -> {
            while (true) {
                repaint();
                try {

                        this.enemy1.move(random());
                        this.enemy2.move(random());
                        this.enemy3.move(random());
                        this.enemy4.move(random());
                        this.enemy5.move(random());
                        this.enemy6.move(random());
                        this.enemy7.move(random());
                        if (this.player.getX() != Constants.PLAYER_X && this.player.getY() != Constants.PLAYER_Y) {
                            startOverAfterCollision2();
                           if (isWinner()){
                               this.sceneId=Constants.RESTART_THE_GAME;
                               this.setVisible(false);
                             //  break;

                              /* JButton restartButton=new JButton();
                               restartButton.setVisible(true);
                               restartButton.setText("START OVER");
                               restartButton.setSize(200,100);
                               restartButton.setLocation(500,500);
                               restartButton.addActionListener(e -> {

                                   this.sceneId=Constants.RESTART_THE_GAME;


                               });
                               this.add(restartButton);

                               */
                           }


                        }


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
        Rectangle alienRectangle = new Rectangle(alien.getX(), alien.getY(), Constants.ALIEN_SIZE_LEVEL2, Constants.ALIEN_SIZE_LEVEL2);
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

    public void startOverAfterCollision2() {
        if ( collision(player, enemy1) || collision(player, enemy2) || collision(player, enemy3) ||
             collision(player, enemy4) || collision(player,enemy5) ||
             collision(player,enemy6) || collision(player,enemy7) ) {
             JFrame startOverButton = new JFrame();
            startOverButton.setLocationRelativeTo(null);
            JOptionPane.showMessageDialog(startOverButton, "You are died!!,Please press OK if you want to stars Over");
            //if you dis
            resetPlayerAndEnemiesAfterCollision();



        }


    }

    private boolean isWinner() {

        if (playerMeetAlien(player, alien)) {
            return true;
        }


         return false;
        }


        private void resetPlayerAndEnemiesAfterCollision(){
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
        this.enemy5.setX(Constants.ENEMY4_X);
        this.enemy5.setY(Constants.ENEMY4_Y);
        this.enemy6.setX(Constants.ENEMY5_X);
        this.enemy6.setY(Constants.ENEMY5_Y);
        this.enemy7.setX(Constants.ENEMY6_X);
        this.enemy7.setY(Constants.ENEMY6_Y);



    }




/*
                Object[] options = {"Yes", "No", "ask later"};
                int n = JOptionPane.showOptionDialog(this, "You Won Do You want to start over", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
                if (n == 1) {
                    System.exit(0);
                } else if (n == 0) {


                }

 */
            }


          /* Object[] options = {"Yes", "No", "ask later"};
            int n = JOptionPane.showOptionDialog(this, "You Won Do You want to start over", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
            if (n == 1) {
           */    // System.exit(0);
        //} else if (n == 0) {



