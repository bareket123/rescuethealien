import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class FirstLevel extends JPanel {
    private Player player;
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    private Enemy enemy4;
    private Alien alien;
    private ImageIcon alien2=new ImageIcon("images/alien.gif");
    private int sceneId;
    private Image backGround=new ImageIcon("images/space.jpg").getImage();

    public FirstLevel() {
         Image backGround=new ImageIcon("C:/Users/barek/IdeaProjects/final vertion of the game/images/space.jpg").getImage();
        this.setDoubleBuffered(true);
        this.player = new Player(Constants.PLAYER_X, Constants.PLAYER_Y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        this.enemy1 = new Enemy(Constants.ENEMY1_X, Constants.ENEMY1_Y, Constants.ENEMY1_SIZE, Constants.ENEMY1_SIZE);
        this.enemy2 = new Enemy(Constants.ENEMY2_X, Constants.ENEMY2_Y, Constants.ENEMY2_SIZE, Constants.ENEMY2_SIZE);
        this.enemy3 = new Enemy(Constants.ENEMY3_X, Constants.ENEMY3_Y, Constants.ENEMY3_SIZE, Constants.ENEMY3_SIZE);
        this.enemy4 = new Enemy(Constants.ENEMY4_X, Constants.ENEMY4_Y, Constants.ENEMY4_SIZE, Constants.ENEMY4_SIZE);
        //this.alien=new Alien(Constants. ALIEN_X,Constants. ALIEN_Y,Constants.ALIEN_SIZE,Constants.ALIEN_SIZE);
        this.sceneId = Constants.FIRST_LEVEL;
        mainGameLoop();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
       // this.sceneId=1;
        //  moveToNextLevel();
        try {
            switch (this.sceneId){
                case Constants.FIRST_LEVEL:
                    g.drawImage(backGround,0,0,Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT,this);
                    this.player.paint(g);
                    this.enemy1.paint(g);
                    this.enemy2.paint(g);
                    this.enemy3.paint(g);
                    this.enemy4.paint(g);
                    g.drawImage(alien2.getImage(),Constants.ALIEN_X,Constants.ALIEN_Y,Constants.ALIEN_SIZE_LEVEL1,Constants.ALIEN_SIZE_LEVEL1,null);
                    break;
                case Constants.SECOND_LEVEL:
                     SecondLevel secondLevel = new SecondLevel(this.player,this.enemy1,this.enemy2,this.enemy3,this.enemy4);
                     secondLevel.mainGameLoop();
                     this.add(secondLevel);
                     //nextLevel.addActionListener(this);
                    break;
            }
        }catch (Exception e){
           e.printStackTrace();
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
                    startOverAfterCollision();
                    if(moveToNextLevel()){
                        this.sceneId=Constants.SECOND_LEVEL;
                        break;
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

    public boolean collision(Player player, Enemy enemy){
        Rectangle playerRectangle=new Rectangle(player.getX(),player.getY(),player.getWidth(),player.getHeight());
        Rectangle enemyRectangle=new Rectangle(enemy.getX(),enemy.getY(),enemy.getWidth(),enemy.getHeight());
        boolean collision=playerRectangle.intersects(enemyRectangle);
        return collision;

    }public boolean playerMeetAlien(Player player, ImageIcon alien2){
        Rectangle playerRectangle=new Rectangle(player.getX(),player.getY(),player.getWidth(),player.getHeight());
        Rectangle alienRectangle = new Rectangle(Constants.ALIEN_X,Constants.ALIEN_Y,Constants.ALIEN_SIZE_LEVEL1,Constants.ALIEN_SIZE_LEVEL1);
        boolean meeting=playerRectangle.intersects(alienRectangle);
        return meeting;

    }

    public void startOverAfterCollision(){
        if ( collision(player,enemy1) || collision(player,enemy2) || collision(player,enemy3) || collision(player,enemy4) ) {
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


    }public boolean moveToNextLevel(){
        if (playerMeetAlien(this.player,this.alien2)) {
            this.player.setX(Constants.PLAYER_X);
           // this.sceneId=Constants.SECOND_LEVEL;
            return true;
        }else
            return false;


            //JFrame winButton = new JFrame();
            //winButton.setLocationRelativeTo(null);
            //JOptionPane.showMessageDialog(winButton, "You are winner!!, press 'OK' TO MOVE TO NEXT LEVEL " );
            //this.player.setX(Constants.ENEMY1_X);
            //this.player.setY(Constants.ENEMY1_Y);

           /* nextLevel=new JButton("Next Level");
            nextLevel.setVisible(true);
            nextLevel.setLayout(null);
            nextLevel.setBounds(400,300,200,50);
            nextLevel.setFont(new Font("Comic con",Font.BOLD,20));
            nextLevel.setForeground(Color.BLUE);
            nextLevel.setBackground(Color.YELLOW);
            nextLevel.addActionListener(e -> {
                System.out.println("checked if pressed");
                sceneId=2;
              //nextLevel.setEnabled(false);


           });
            this.add(nextLevel);


            */



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

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public Alien getAlien() {
        return alien;
    }

    public void setAlien(Alien alien) {
        this.alien = alien;
    }


    }


