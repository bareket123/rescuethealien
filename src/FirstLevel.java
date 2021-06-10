import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;

public class FirstLevel extends JPanel implements ActionListener {
    private Player player;
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    private Enemy enemy4;
    private Alien alien;
    private ImageIcon alien2=new ImageIcon("C:/Users/barek/IdeaProjects/final vertion of the game/images/alien.gif");
    private int sceneId;
    private JButton nextLevel;
    private Image backGround=new ImageIcon("C:/Users/barek/IdeaProjects/final vertion of the game/images/space.jpg").getImage();

    public FirstLevel(){
        // Image backGround=new ImageIcon("C:/Users/barek/IdeaProjects/final vertion of the game/images/space.jpg").getImage();
        this.setDoubleBuffered(true);
        this.player = new Player(Constants.PLAYER_X,Constants.PLAYER_Y,Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT);
        this.enemy1= new Enemy(Constants.ENEMY1_X,Constants.ENEMY1_Y,Constants.ENEMY1_SIZE,Constants.ENEMY1_SIZE);
        this.enemy2=new Enemy(Constants.ENEMY2_X,Constants.ENEMY2_Y,Constants.ENEMY2_SIZE,Constants.ENEMY2_SIZE);
        this.enemy3=new Enemy(Constants.ENEMY3_X,Constants.ENEMY3_Y,Constants.ENEMY3_SIZE,Constants.ENEMY3_SIZE);
        this.enemy4=new Enemy(Constants.ENEMY4_X,Constants.ENEMY4_Y,Constants.ENEMY4_SIZE,Constants.ENEMY4_SIZE);
        //this.alien=new Alien(Constants. ALIEN_X,Constants. ALIEN_Y,Constants.ALIEN_SIZE,Constants.ALIEN_SIZE);
        this.sceneId=Constants.FIRST_LEVEL;
         mainGameLoop();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
       // g.drawImage(backGround,0,0,Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT,null);
      //  moveToNextLevel();
        try {
            switch (this.sceneId){
                case 1:
                    this.player.paint(g);
                    this.enemy1.paint(g);
                    this.enemy2.paint(g);
                    this.enemy3.paint(g);
                    this.enemy4.paint(g);
                    g.drawImage(alien2.getImage(),Constants.ALIEN_X,Constants.ALIEN_Y,Constants.ALIEN_SIZE,Constants.ALIEN_SIZE,null);
                    break;
                case 2:
                     nextLevel.addActionListener(this);
                     SecondLevel secondLevel = new SecondLevel();
                     this.add(secondLevel);
                    break;
            }
        }catch (Exception e){
           e.printStackTrace();
        }



        }



    public void mainGameLoop() {
        new Thread(() -> {
            while (true) {
                Timer timer = new Timer();
                timer.purge();
                try {

                    this.enemy1.move(random());
                    this.enemy2.move(random());
                    this.enemy3.move(random());
                    this.enemy4.move(random());
                    startOverAfterCollision();
                    moveToNextLevel();
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
    public boolean collision(Player player, Enemy enemy){
        Rectangle playerRectangle=new Rectangle(player.getX(),player.getY(),player.getWidth(),player.getHeight());
        Rectangle enemyRectangle=new Rectangle(enemy.getX(),enemy.getY(),enemy.getWidth(),enemy.getHeight());
        boolean collision=playerRectangle.intersects(enemyRectangle);
        return collision;

    }public boolean playerMeetAlien(Player player, ImageIcon alien2){
        Rectangle playerRectangle=new Rectangle(player.getX(),player.getY(),player.getWidth(),player.getHeight());
        Rectangle alienRectangle = new Rectangle(Constants.ALIEN_X,Constants.ALIEN_Y,Constants.ALIEN_SIZE,Constants.ALIEN_SIZE);
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


    }public void moveToNextLevel(){
        if (playerMeetAlien(this.player,this.alien2)){
            //JFrame winButton = new JFrame();
            //winButton.setLocationRelativeTo(null);
            //JOptionPane.showMessageDialog(winButton, "You are winner!!, press 'OK' TO MOVE TO NEXT LEVEL " );
            //this.player.setX(Constants.ENEMY1_X);
            //this.player.setY(Constants.ENEMY1_Y);
            nextLevel=new JButton("Next Level");
            nextLevel.setVisible(true);
            nextLevel.setLayout(null);
            nextLevel.setBounds(400,300,200,50);
            nextLevel.setFont(new Font("",Font.BOLD,20));
            nextLevel.setForeground(Color.BLUE);
            nextLevel.setBackground(Color.YELLOW);
            nextLevel.addActionListener(e -> {

              sceneId=2;
                System.out.println("wakk");
              //nextLevel.setEnabled(false);


           });
            this.add(nextLevel);

        }


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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.nextLevel){
            System.out.println("sodad");

        }
    }
}
