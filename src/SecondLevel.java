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
    private Alien alien;
    private Alien alien2;
    private JButton resetButton;
    //private GameMain gameMain;
    //private JButton resetButton;
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
        this.alien = new Alien(Constants.ALIEN_X, Constants.ALIEN_Y);
        this.alien2 = new Alien(0, 500);
        //mainGameLoop();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Image background = icon.getImage();
        g.drawImage(background, 0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, null);
        this.player.paint(g);
        this.enemy1.paint(g);
        this.enemy2.paint(g);
        this.enemy3.paint(g);
        this.enemy4.paint(g);
        alien.paint(g, this);
        alien2.paint(g, this);

    }


    public void mainGameLoop() {
        new Thread(() -> {
            while (true) {
                try {

                    this.enemy1.move(random());
                    this.enemy2.move(random());
                    this.enemy3.move(random());
                    this.enemy4.move(random());
                    if (this.player.getX() != Constants.PLAYER_X && this.player.getY() != Constants.PLAYER_Y) {
                        startOverAfterCollision2();
                        isWinner();
                      /*  if (playerMeetAlien(player, alien)) {
                            this.alien.setX(1000);
                            this.alien.setY(700);
                            if (playerMeetAlien(player, alien2)) {

                            }

                       */



                        }

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

    private void isWinner() {
        if (playerMeetAlien(player, alien)|| playerMeetAlien(player,alien2)) {
            Object[] options = {"Yes", "No", "ask later"};
            int n = JOptionPane.showOptionDialog(this, "You Won Do You want to start over", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
            if (n == 1) {
                System.exit(0);
            } else if (n == 0) {

            }

            }

        }
         /*   JButton resetButton = new JButton();
            resetButton.setText("Reset");
            resetButton.setSize(100, 50);
            resetButton.setLocation(500, 200);
            resetButton.addActionListener(e -> {

              JFrame jFrame=new JFrame("you are winner");
              jFrame.setVisible(true);
              jFrame.setSize(1000,700);

            });
            this.add(resetButton);
            //this.add(gameMain);


        }
    }



          */

          /* Object[] options = {"Yes", "No", "ask later"};
            int n = JOptionPane.showOptionDialog(this, "You Won Do You want to start over", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
            if (n == 1) {
           */    // System.exit(0);
        //} else if (n == 0) {

/*
        @Override
        public void actionPerformed (ActionEvent e){
            resetButton = (JButton) e.getSource();

            System.out.println("Frame Closed.");
        }
    }
/*
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            this.remove(this.gameMain);
            gameMain = new GameMain();
            this.add(gameMain);
            SwingUtilities.updateComponentTreeUI(this);
    }
              /*
        }

               */
        //}
        //}


     /*   @Override
        public void actionPerformed (ActionEvent e){
           JButton resetButton = new JButton();
            resetButton.setText("reset");
            resetButton.setSize(100, 50);
            resetButton.setLocation(0, 200);
            resetButton.addActionListener(this);
            this.add(resetButton);
            if (e.getSource() == resetButton) {
                this.remove(this.gameMain);
                GameMain game = new GameMain();
                this.add(game);
                SwingUtilities.updateComponentTreeUI(this);
            }

        }

      */


    }