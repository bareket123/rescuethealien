import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMain extends JFrame implements ActionListener  {
    FirstLevel firstLevel;

    //private ImageIcon backGround=new ImageIcon("C:/Users/barek/IdeaProjects/final vertion of the game/images/space.jpg");
    public static void main(String[] args) {
        GameMain gameMain = new GameMain();


    }


    private static void optionPaneExample() {
        JFrame startButton = new JFrame();
        JFrame instruction = new JFrame();
        startButton.setLocationRelativeTo(null);
        instruction.setLocationRelativeTo(null);
        JOptionPane.showMessageDialog(startButton, "Hello, Welcome to 'RESCUE THE '.");
        JOptionPane.showMessageDialog(instruction, "The rules are simple: " +
                "*You can not get out of the frame" +
                "\n *You must avoid the enemies" +
                "\n* in order to win the game you need to escape through the exit hole");
    }

    public GameMain() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setTitle("Rescue Alien");
        this.setResizable(false);

        FirstLevel firstLevel = new FirstLevel();
        firstLevel.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.add(firstLevel);
        PlayerMovement playerMovement = new PlayerMovement(firstLevel.getPlayer());
        this.addKeyListener(playerMovement);
        resetButton = new JButton();
        resetButton.setText("reset");
        resetButton.setSize(100, 50);
        resetButton.setLocation(0, 200);
        resetButton.addActionListener(this);
        this.add(resetButton);
        this.setVisible(true);

           @Override
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
       /* SecondLevel secondLevel=new SecondLevel(firstLevel.getPlayer());
        secondLevel.setBounds(0,0,Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
        this.add(secondLevel);
        /*PlayerMovement playerMovement1=new PlayerMovement(secondLevel.getPlayer());
        this.addKeyListener(playerMovement1);


        */




/*


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() ==resetButton) {
            this.remove(this.firstLevel);
            GameMain game = new GameMain();
            this.add(game);
            SwingUtilities.updateComponentTreeUI(this);
        }


    }
}

 */
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}